//  Maria Cunha 93089
#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "fileblocks.h"
#include "bin_direntries.h"

#include <string.h>
#include <assert.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>

#include <iostream>
#include <string.h>

namespace sofs20
{
    void grpRenameDirentry(int pih, const char *name, const char *newName)
    {
        soProbe(204, "%s(%d, %s, %s)\n", __FUNCTION__, pih, name, newName);

        // Implemented by Maria Cunha 93089
        /**
         * Rename an entry of a directory.
         * A direntry associated from the given directory is renamed.
         *      - The renaming can not be done deleting the entry and adding a new one.
         * |Precondition:
         *      - pih is a valid inode handler of a directory where the user has write access
         * |Note:
         *      - name is valid and exists
         *      - newName is valid and doesn't exist yet
         * | Parameters:
         *     [in] pih         inode handler of the parent inode
         *     [in] name        current name of the entry 
         *     [in] newName     new name for the entry
         * 
        */

        // 1 - procurar entrada (Barramento do diretório)
        SOInode *inode_parent = soGetInodePointer(pih);
        //verificar se é ficheiro e permissoes de acesso
        // uint32_t type = inode_parent->mode & ~0777;
        // uint32_t perm = inode_parent->mode & 0777;
        // if ((type != S_IFDIR) && (perm != S_IWRITE))
        // {
        //     throw SOException(EINVAL, __FUNCTION__);
        // }
        
        //assert ((type != S_IFDIR) && (perm != S_IWRITE));

        int resto = inode_parent->size%BlockSize;
        unsigned int blocks = inode_parent->size/BlockSize;
        if (resto != 0)
        {
            blocks += 1;
        }
        unsigned int b_index = BlockNullReference;
        unsigned int e_index = BlockNullReference;
        SODirentry block[DPB];
        for (unsigned int i = 0; i < blocks; i++)
        {
            SODirentry block_entries[DPB];
            //search each entry
            soReadFileBlock(pih,i,block_entries);
            for (long unsigned int j = 0; j < DPB; j++){
                SODirentry entry = block_entries[j];
                if (!strcmp(entry.name,name))
                {
                    b_index = i;
                    e_index = j;
                    memcpy(block,block_entries,BlockSize);
                }else if (!strcmp(entry.name,newName))
                {
                   throw SOException(EEXIST,__FUNCTION__);      //->> exception of newName already used
                }
            }
        }

        if (b_index == BlockNullReference)
        {
            throw SOException(ENOENT,__FUNCTION__);      //->> exception of block/index not found
        }
        // 2 - alterar o nome
        // update entries of block
        strcpy(block[e_index].name,newName);
        soWriteFileBlock(pih,b_index,block);
    }
};

