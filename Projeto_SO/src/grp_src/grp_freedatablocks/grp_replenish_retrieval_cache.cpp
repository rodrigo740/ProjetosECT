/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
 */

#include "freedatablocks.h"

#include <string.h>
#include <errno.h>
#include <iostream>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs20
{
    void grpReplenishRetrievalCache(void)
    {
        soProbe(443, "%s()\n", __FUNCTION__);

        /* replace the following line with your code */
        //binReplenishRetrievalCache();

        //Hugo Leal, 93059
        /*
        Replenish the retrieval cache.

        References to free data blocks should be transferred from the reference table, or from the insertion cache, to the retrieval cache:

            Nothing is done if the cache is not empty;
            The insertion cache is only used if the reference table is empty.
            Only a single block of the reference table is processed, even if it is not enough to fulfill the retrieval cache:
                the block processed is the first one with references;
                be aware of the circularity of the reference table.
            After transferring a reference from A to B, the value in A must become BlockNullReference.
            The data block meta data fields in the superblock must be updated.

        Remarks
            When calling a function of any layer, the version with prefix so should be used, not the one with prefix grp or bin. 
    */

        //References to free data blocks should be transferred from the reference table, or from the insertion cache, to the retrieval cache:

        //get superblock pointer
        SOSuperblock *sb = soGetSuperblockPointer();

        //Nothing is done if the cache is not empty;
        if (sb->retrieval_cache.idx != REF_CACHE_SIZE)
        {
            return;
        }
        //assert
        //The insertion cache is only used if the reference table is empty.
        if (sb->reftable.count == 0)
        {
            uint32_t av_refs = REF_CACHE_SIZE - sb->insertion_cache.idx;
            memcpy(&sb->retrieval_cache.ref[av_refs], &sb->insertion_cache.ref[0], sb->insertion_cache.idx * sizeof(uint32_t));
            memset(&sb->insertion_cache.ref[0], BlockNullReference, sb->insertion_cache.idx * sizeof(uint32_t));

            sb->retrieval_cache.idx = av_refs;
            sb->insertion_cache.idx = 0;
        }
        else
        {
            u_int32_t *ref_tab = soGetReferenceBlockPointer(sb->reftable.blk_idx);
            // if the number of references is bigger than CACHE SIZE
            if (sb->reftable.count >= REF_CACHE_SIZE)
            {
                 // The number of references is bigger than RPB
                if (REF_CACHE_SIZE + sb->reftable.ref_idx >= RPB)
                {
                    //printf("REF_cache_Size is bigger");
                    uint32_t av_refs = RPB - sb->reftable.ref_idx;
                    uint32_t retrieval_idx = REF_CACHE_SIZE - av_refs;
                    //use reference table
                    memcpy(&sb->retrieval_cache.ref[retrieval_idx], &ref_tab[sb->reftable.ref_idx], av_refs * sizeof(uint32_t));
                    memset(&ref_tab[sb->reftable.ref_idx], BlockNullReference, av_refs * sizeof(uint32_t));

                    //update reftable idx
                    sb->reftable.ref_idx = 0;

                    if (sb->reftable.blk_idx + 1 > sb->rt_size)
                    {
                        sb->reftable.blk_idx = 0;
                    }
                    else
                    {
                        sb->reftable.blk_idx++;
                    }
                    sb->reftable.count -= av_refs;
                    //
                    sb->retrieval_cache.idx = retrieval_idx;
                }
                // if the block has all references 
                else
                {
                    //use reference table
                    //printf("REF_cache_Size is smaller");
                    memcpy(&sb->retrieval_cache.ref[0], &ref_tab[sb->reftable.ref_idx], REF_CACHE_SIZE * sizeof(uint32_t));
                    memset(&ref_tab[sb->reftable.ref_idx], BlockNullReference, REF_CACHE_SIZE * sizeof(uint32_t));
                    sb->reftable.ref_idx += REF_CACHE_SIZE;
                    sb->retrieval_cache.idx = 0;
                    sb->reftable.count -= REF_CACHE_SIZE;
                }
            }
            // if the number of references is smaller than CACHE SIZE
            else
            {
                // The number of references is bigger than RPB
                uint32_t n_ref_av = sb->reftable.count;
                if (n_ref_av + sb->reftable.ref_idx >= RPB)
                {
                    //printf("count is bigger");
                    uint32_t av_refs = RPB - sb->reftable.ref_idx;
                    uint32_t retrieval_idx = n_ref_av - av_refs;
                    //use reference table
                    memcpy(&sb->retrieval_cache.ref[retrieval_idx], &ref_tab[sb->reftable.ref_idx], av_refs * sizeof(uint32_t));
                    memset(&ref_tab[sb->reftable.ref_idx], BlockNullReference, av_refs * sizeof(uint32_t));

                    //update reftable idx
                    sb->reftable.ref_idx = 0;

                    if (sb->reftable.blk_idx + 1 > sb->rt_size)
                    {
                        sb->reftable.blk_idx = 0;
                    }
                    else
                    {
                        sb->reftable.blk_idx++;
                    }

                    sb->retrieval_cache.idx = retrieval_idx;
                    sb->reftable.count-= av_refs;
                }
                // if the block has all references 
                else
                {
                    //printf("count is smaller");
                    //use reference table
                    memcpy(&sb->retrieval_cache.ref[REF_CACHE_SIZE - n_ref_av], &ref_tab[sb->reftable.ref_idx], n_ref_av * sizeof(uint32_t));
                    memset(&ref_tab[sb->reftable.ref_idx], BlockNullReference, n_ref_av * sizeof(uint32_t));
                    sb->reftable.ref_idx += n_ref_av;
                    sb->retrieval_cache.idx = 0;
                    sb->reftable.count = 0;
                }
            }
            soSaveReferenceBlock();
        }
        soSaveSuperblock();
    }
}; // namespace sofs20
