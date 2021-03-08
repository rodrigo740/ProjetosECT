/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
 */

#include "freeinodes.h"

#include <stdio.h>
#include <errno.h>
#include <inttypes.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs20
{
    void grpFreeInode(uint16_t in)
    {
        soProbe(402, "%s(%u)\n", __FUNCTION__, in);
        
        // Implemented by Maria Cunha 93089
        // [in]	in - number (reference) of the inode to be freed
        // - The corresponding bit of the ibitmap field is put at zero.
        // - All inode fields are put at zero, except the references, that are put at BlockNullReference.
        // - The inode meta data fields in the superblock must be updated.

        //verify if the number of the inode to be freed is valid            (!)
        if ( in == InodeNullReference || (0>in))
        {
            throw SOException(EINVAL ,__FUNCTION__);
        }

        int inode_handler = soOpenInode(in);
        SOInode* inode_pointer = soGetInodePointer(inode_handler);
        SOSuperblock* superblock_pointer = soGetSuperblockPointer();

        int i = in / 32;
        int j = in % 32;

        //verify if the inode is already freed
        if ((superblock_pointer->ibitmap[i] & (1<<j)) == 0)
        {
            return;
        }

    // All inode fields are put at zero, except the references, that are put at BlockNullReference
        /** inode mode: it stores the file type and permissions.*/
        inode_pointer->mode = 0;                       
        /** link count: number of hard links (directory entries) pointing to the inode */
        inode_pointer->lnkcnt = 0;
        /** user ID of the file owner */
        inode_pointer->owner = 0;
        /** group ID of the file owner */
        inode_pointer->group = 0;
        /** file size in bytes: */
        inode_pointer->size = 0;
        /** block count: total number of blocks used by the file 
         * - it includes data and reference blocks */
        inode_pointer->blkcnt = 0;
        /** access time: time of last access to file data or meta-data */
        inode_pointer->atime = 0;
        /** modification time: time of last change to file data */
        inode_pointer->mtime = 0;
        /** change time: time of last change to file meta-data */
        inode_pointer->ctime = 0;

        /** direct references to the first data blocks with file's data */
        //uint32_t d[N_DIRECT];
        for (int i = 0; i < N_DIRECT; i++)
        {
            inode_pointer->d[i]= BlockNullReference;
        }

        /** references to block(s) that extend the \c d array */
        //uint32_t i1[N_INDIRECT];
        for (int i = 0; i < N_INDIRECT; i++)
        {
            inode_pointer->i1[i]= BlockNullReference;
        }

        /**  references to block(s) that extends the \c i1 array */
        //uint32_t i2[N_DOUBLE_INDIRECT];
        for (int i = 0; i < N_DOUBLE_INDIRECT; i++)
        {
            inode_pointer->i2[i]= BlockNullReference;
        }

    //The corresponding bit of the ibitmap field is put at zero
        superblock_pointer->ibitmap[i] = superblock_pointer->ibitmap[i] & ~(1<<j);
    //The inode meta data fields in superblock must be updated
        superblock_pointer->ifree += 1;
    }
};

