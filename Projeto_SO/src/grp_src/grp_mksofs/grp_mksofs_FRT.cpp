#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <inttypes.h>
#include <string.h>

namespace sofs20
{
    void grpFillReferenceTable(uint32_t ntotal, uint32_t itotal, uint32_t dbtotal)
    {
        //Lúcia Sousa 93086
        soProbe(605, "%s(%u, %u, %u)\n", __FUNCTION__, ntotal, itotal, dbtotal);
        //calculate vars
        //Get the blocks of inode table
        uint32_t iblocks = itotal/IPB;
        //get the number of ReferenceBlocks
        uint32_t nRefBlock = ntotal - 1 - iblocks - dbtotal; //first element of ref table
        //get the reference
        uint32_t refCount = REF_CACHE_SIZE+1;
        //array of references in a block
        uint32_t reference[RPB];
       
        if (nRefBlock != 0){
            //for each block of reference table
            for(uint32_t i = 0; i < nRefBlock; i++){
                // fill the remaning cells of block, for cicle goes trough each reference
                for(uint32_t j = 0; j < RPB; j++){         
                    if(refCount == dbtotal){ 
                        reference[j] = BlockNullReference;
                    }
                    else{
                        reference[j] = refCount;
                        refCount++;
                    }
                }
                soWriteRawBlock(i+1+iblocks+dbtotal,reference);
            }
        }
    }
};