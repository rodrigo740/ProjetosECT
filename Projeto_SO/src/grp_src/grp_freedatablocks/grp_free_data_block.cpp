/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
 */

#include "freedatablocks.h"

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
    void grpFreeDataBlock(uint32_t bn)
    {
        soProbe(442, "%s(%u)\n", __FUNCTION__, bn);

        //Raquel Pinto - 92948
        
        /* replace the following line with your code */
        //  binFreeDataBlock(bn);
    
        //bn the number (reference) of the data block to be freed
        //bn must represent a valid data block number
        SOSuperblock *sb=  soGetSuperblockPointer();
        // bn must be between zero and dbtotal(total number of data blocks)
        if(0>bn || bn>sb->dbtotal){ // if is invalid 
            //EINVAL if data block number is not valid.
            throw SOException(EINVAL,__FUNCTION__); 

        }else{ // if bn is valid
            //If the insertion cache is full, the deplete function must be called first.
            if(sb->insertion_cache.idx >= REF_CACHE_SIZE){ 
               soDepleteInsertionCache();
            }
            //The reference must be inserted at the first empty cell.
            sb->insertion_cache.ref[sb->insertion_cache.idx] = bn;
            //The data block meta data fields in the superblock must be updated.
            sb->insertion_cache.idx ++;
            sb->dbfree ++; // increases dbfree (number of free data blocks)
            soSaveReferenceBlock();
        }
        soSaveSuperblock();
    }
};
