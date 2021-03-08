#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "fileblocks.h"
#include "bin_direntries.h"

#include <errno.h>
#include <string.h>
#include <sys/stat.h>

namespace sofs20
{
    void grpAddDirentry(int pih, const char *name, uint16_t cin)
    {
        soProbe(202, "%s(%d, %s, %u)\n", __FUNCTION__, pih, name, cin);
        //Hugo Leal, 93059
        /* replace the following line with your code */
        //binAddDirentry(pih, name, cin);

        // get inode
        SOInode* parent_inode = soGetInodePointer(pih);
        SODirentry dirs[DPB];
    

        

        // finding an empty dir for writing 
        uint32_t block_iterate = ((parent_inode->size/sizeof(SODirentry))/DPB);
        uint32_t last_entry = ((parent_inode->size/sizeof(SODirentry))%DPB);
        
        // check all complete blocks
        for (uint32_t i = 0; i < block_iterate; i++){
            
            soReadFileBlock(pih,i,dirs);

            for(unsigned j=0; j < DPB; j++){
                if (strcmp(dirs[j].name, name)==0){
                    throw SOException(EEXIST, __FUNCTION__);
                }
            }
        }
        // if last entry is different from the complete blocks
        // check the imcomplete block
        if(last_entry != 0){
            soReadFileBlock(pih,block_iterate,dirs);
            for(unsigned j=0; j < last_entry; j++){
                if (strcmp(dirs[j].name, name)==0){
                    throw SOException(EEXIST, __FUNCTION__);
                }
            }
        }
        
        uint32_t total_block = parent_inode->size/BlockSize;
        uint32_t block = total_block;
        uint32_t entry = (parent_inode->size%BlockSize)/sizeof(SODirentry);
        parent_inode->size+= sizeof(SODirentry);
        soReadFileBlock(pih,block,dirs);
        dirs[entry].in = cin;
        strcpy(dirs[entry].name, name);
        soWriteFileBlock(pih,block,dirs);
        soSaveInode(pih);
    }
};

