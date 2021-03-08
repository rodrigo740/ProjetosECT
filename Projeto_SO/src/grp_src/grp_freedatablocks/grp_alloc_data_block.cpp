/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2009-2020
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
    uint32_t grpAllocDataBlock()
    {
        //Lúcia Sousa 93086
        soProbe(441, "%s()\n", __FUNCTION__);
         /*
        Allocate a free data block.
        A data block reference is retrieved from the retrieval cache.
        -If the retrieval cache is empty, the replenish function must be called first.
        -The first reference in the retrieval cache must be retrieved and returned.
        -The data block meta data fields in the superblock must be updated.
        */

        //get a pointer to a superblock
        SOSuperblock* sb = soGetSuperblockPointer();

        //if retrieval cache is empty
        if(sb->retrieval_cache.idx == REF_CACHE_SIZE){
            //call replenish function
            soReplenishRetrievalCache();
        }
        
        //if there are no free data blocks throw exception
        if(sb->retrieval_cache.idx == BlockNullReference){
            throw SOException(ENOSPC,__FUNCTION__);
        }

        uint32_t r = sb->retrieval_cache.ref[sb->retrieval_cache.idx];
        //first reference in retrieval is retrieved
        sb->retrieval_cache.ref[sb->retrieval_cache.idx] = BlockNullReference;
        //increment idx
        sb->retrieval_cache.idx++;
        //decrement number of free data blocks
        sb->dbfree--;

        //save superblock to disk
        soSaveSuperblock();

        //return first reference
        return r;

        /* replace the following line with your code */
        //return binAllocDataBlock();
    }
};

