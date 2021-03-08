/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
 */

#include "freedatablocks.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"

#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <iostream>
using namespace std;

/*
Deplete the insertion cache.


References to free data blocks should be transferred from the insertion cache to the reference table.

Nothing is done if the cache is not full.
Only a single block of the reference table is processed, even if it has no room to empty the insertion cache:
    -> the block processed is the last with references;
    -> make sure the cache is in a valid state, if, after the operation, it is not empty;
    -> be aware of the circularity of the reference table.
after transferring a reference from A to B, the value in A becomes BlockNullReference.
The data block meta data fields in the superblock must be updated.
*/

// Rodrigo Martins 93264
namespace sofs20
{
    /* only fill the current block to its end */
    void grpDepleteInsertionCache(void)
    {
        soProbe(444, "%s()\n", __FUNCTION__);
        
        SOSuperblock* sb = soGetSuperblockPointer();

        if (sb->insertion_cache.idx == REF_CACHE_SIZE)
        {
            
            uint32_t index = (sb->reftable.ref_idx+sb->reftable.count)%RPB;
            uint32_t blockOffset = sb->reftable.blk_idx + sb->reftable.count/RPB;

            if((sb->reftable.ref_idx+sb->reftable.count)%RPB==0){
                blockOffset+=1;
            }

            if (blockOffset >= sb->rt_size)
            {
                blockOffset = sb->rt_size - blockOffset;
            }
            
            uint32_t *  rt = soGetReferenceBlockPointer(blockOffset);
            uint32_t referenceCount = 0; // variable to count how many references where coppied
            
            
            for (uint32_t i = 0; i < REF_CACHE_SIZE ; i++)
            {
                /*There are RPB cells in 1 block, and in this function we can only fill 1 block,
                * so if index == RPB its means the function filled the block and must stop
                */
                
                /* We must copy the reference from the insertion cache to the reference table and update the 
                *  superblock meta data
                */
                rt[index] = sb->insertion_cache.ref[i];                     // reference table <- insertion cache[i]
                sb->insertion_cache.ref[i] = BlockNullReference;            // insertion cache[i] = BlockNullReference
                sb->insertion_cache.idx--;                                  // -1 reference means idx = idx - 1 (after the for cycle the fuction shifts the references in the insertion cache)
                index++;
                sb->reftable.count++;                                       // updating the none null references in the ref Table
                referenceCount++;
                
                if (index == RPB)
                {
                    break;
                }
            }
            
            // Shifting the content of the insertion cache to position 0...
            uint32_t i = 0;
            for (; referenceCount < REF_CACHE_SIZE; referenceCount++)
            {
                sb->insertion_cache.ref[i] = sb->insertion_cache.ref[referenceCount];
                sb->insertion_cache.ref[referenceCount] = BlockNullReference;
                i++;
            }
            
            soSaveReferenceBlock();
            soSaveSuperblock();
        }
    }
};

