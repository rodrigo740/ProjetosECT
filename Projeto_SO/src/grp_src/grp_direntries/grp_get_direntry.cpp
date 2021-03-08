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
    uint16_t grpGetDirentry(int pih, const char *name)
    {
        soProbe(201, "%s(%d, %s)\n", __FUNCTION__, pih, name);

        /* replace the following line with your code */
        //return binGetDirentry(pih, name);

        //Raquel Pinto - 92948
        
        //Precondition -> pih is a valid inode handler of a directory name is a valid base name (it doesn't contains '/') 
        //pih is a valid handler (Check given handler, throwing an exception in case of error)
        //soCheckInodeHandler(pih,__FUNCTION__);

        //name is a valid base name
        if(strchr(name,'/')!=NULL){
            throw SOException(EINVAL, __FUNCTION__);
        }

        SOInode* Inode = soGetInodePointer(pih);
        SODirentry directory[DPB];
        uint32_t total_blocks = Inode->size/BlockSize;
        uint32_t resto = Inode->size%BlockSize;
        
        if (resto != 0)
        {
            total_blocks++;
        }
        //Returns the corresponding inode number or InodeNullReference, if name doesn't exist
        for(uint32_t i = 0; i<total_blocks; i++){
            soReadFileBlock(pih,i,directory);
            for(uint32_t j = 0; j<DPB; j++){
                // if name direntry is equal of name argument
                if(strcmp(directory[j].name,name) ==0 ){ 
                    return directory[j].in;
                }
            }
        }
        return InodeNullReference;   
    }
};
