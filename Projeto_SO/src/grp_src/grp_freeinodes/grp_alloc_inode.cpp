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
#include <sys/stat.h>
#include <string.h>

#include <iostream>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs20
{
    uint16_t grpAllocInode(uint32_t mode)
    {
        soProbe(401, "%s(0x%x)\n", __FUNCTION__, mode);

        /* replace the following line with your code */
        // return binAllocInode(mode);

        //Luísa Amaral, 93001
        //[in] mode   bitwise-or of type and permissions
        //uint32_t perm = mode & 0777;
        
        
        
        uint32_t type = mode & ~0777;

        // type must represent either a file (S_IFREG), a directory (S_IFDIR), or a symbolic link (S_IFLNK)
        if (!((type == S_IFREG) || (type == S_IFDIR) || (type == S_IFLNK))) {
            throw SOException(EINVAL, __FUNCTION__);
        }

        //permissions already represent a valid permission pattern (a octal value in the range 0000 to 0777)

        SOSuperblock* sb = soGetSuperblockPointer();
        
        // throw ENOSPC if there are no free inodes 
        if (sb->ifree == 0) {
            throw SOException(ENOSPC, __FUNCTION__);
        }

        
        //circular search
        if(sb->iidx + 1 >= sb->itotal) {
            sb->iidx = 1; //inode 1 because inode 0 is occupied with root
        } else {
            sb->iidx++;
        }
        

        int i = sb->iidx/32; //palavra (que tem 32 bits)
        int j = sb->iidx%32; //posição relativa do bit
            
        if(!(sb->ibitmap[i] & (1<<j))) {
            sb->ibitmap[i] = sb->ibitmap[i] | (1<<j);
        }

        sb->ifree -= 1;
        uint32_t inode_number = sb->iidx;

        int ih = soOpenInode(inode_number);
        SOInode* ip = soGetInodePointer(ih);
        memset(ip, 0, sizeof(SOInode));

        //inode mode: it stores the file type and permissions.
        ip->mode = mode;
        // link count: number of hard links (directory entries) pointing to the inode 
        ip->lnkcnt = 0;
        // user ID of the file owner 
        ip->owner = getuid();
        // group ID of the file owner
        ip->group = getgid();
        // file size in bytes
        ip->size = 0;
        // block count: total number of blocks used by the file
        ip->blkcnt = 0;
        // access time: time of last access to file data or meta-data
        ip->atime = time(NULL);
        // modification time: time of last change to file data 
        ip->mtime = time(NULL);
        // change time: time of last change to file meta-data
        ip->ctime = time(NULL);
        
        
        // direct references to the first data blocks with file's data
        //N_DIRECT is the number of direct data block references in the inode 
        for (unsigned int i = 0; i < N_DIRECT; i++)
        {
            ip->d[i] = BlockNullReference;
        }

        // references to block(s) that extend the d array
        //N_INDIRECT is the number of indirect data block references in the inode
        for (unsigned int i = 0; i < N_INDIRECT; i++)
        {
            ip->i1[i] = BlockNullReference;
        }

        // references to block(s) that extends the i1 array
        //N_DOUBLE_INDIRECT is the number of double indirect data block references in the inode
        for (unsigned int i = 0; i < N_DOUBLE_INDIRECT; i++)
        {
            ip->i2[i] = BlockNullReference;
        }
        
        soSaveSuperblock();
        soSaveInode(ih);
        soCloseInode(ih);
        return inode_number;
    }
};

