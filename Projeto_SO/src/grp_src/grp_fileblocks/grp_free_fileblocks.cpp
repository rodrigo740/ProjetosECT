#include "fileblocks.h"

#include "freedatablocks.h"
#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <inttypes.h>
#include <errno.h>
#include <assert.h>

namespace sofs20
{
#if true
    /* free all blocks between positions ffbn and RPB - 1
     * existing in the block of references given by i1.
     * Return true if, after the operation, all references become BlockNullReference.
     * It assumes i1 is valid.
     */
    static bool grpFreeIndirectFileBlocks(SOInode * ip, uint32_t i1, uint32_t ffbn);

    /* free all blocks between positions ffbn and RPB**2 - 1
     * existing in the block of indirect references given by i2.
     * Return true if, after the operation, all references become BlockNullReference.
     * It assumes i2 is valid.
     */
    static bool grpFreeDoubleIndirectFileBlocks(SOInode * ip, uint32_t i2, uint32_t ffbn);
#endif

    /* ********************************************************* */

    static unsigned freed = 0;

    void grpFreeFileBlocks(int ih, uint32_t ffbn)
    {
        soProbe(303, "%s(%d, %u)\n", __FUNCTION__, ih, ffbn);

        /* replace the following line with your code */
        //binFreeFileBlocks(ih, ffbn);
        
        //Luísa Amaral, 93001

        SOInode* inp = soGetInodePointer(ih);

        uint32_t d_idx = 0;
        uint32_t i1_idx = 0;
        uint32_t i2_idx = 0;
        freed = 0;
        bool cleanIndirect = false;
        bool cleanDoubleIndirect = false;

        if(ffbn < N_DIRECT) {
            d_idx = ffbn; //starts in d[]
            cleanIndirect = true;
            cleanDoubleIndirect = true;
        } else if(ffbn < N_DIRECT+N_INDIRECT*RPB) { //starts in i1[]
            // (ffbn - NDIRECT)/RPB = i -> index em i1[]
            // (ffbn - NDIRECT)%RPB = j -> referencia em i1[i]
            d_idx = N_DIRECT;
            i1_idx = (ffbn - N_DIRECT)/RPB;
            cleanDoubleIndirect = true;
        } else { //starts in i2[]
            d_idx = N_DIRECT;
            i1_idx = N_INDIRECT;
            i2_idx = (ffbn-N_DIRECT-N_INDIRECT*RPB)/(RPB*RPB);
        }

        //clear d[]
        for (uint32_t i = d_idx; i < N_DIRECT; i++)
        {
            if (inp ->d[i]!=BlockNullReference) {
                soFreeDataBlock(inp->d[i]);
                freed++;
                inp->d[i] = BlockNullReference;
            }
        }

        //clear i1[] 
        //since it is indirect we might have to free 1 extra block
        for (uint32_t i = i1_idx; i < N_INDIRECT; i++)
        {
            if(inp->i1[i] != BlockNullReference) {
                bool freeBlock = false;
                if(i==i1_idx && !cleanIndirect) {
                    freeBlock = grpFreeIndirectFileBlocks(inp,inp->i1[i],(ffbn-N_DIRECT)%RPB); //in the first block we start cleaning from the especified reference
                } else if(i>=i1_idx || cleanIndirect)
                {
                    freeBlock = grpFreeIndirectFileBlocks(inp, inp->i1[i],0); //in the rest we clean after 0
                }
            
                if(freeBlock) { //references were clean so block needs to be freed
                    soFreeDataBlock(inp->i1[i]);
                    freed++;
                    inp->i1[i] = BlockNullReference;
                }
            }
        }

        //clear i2[]
        //since it is double indirect we might have to free 2 extra blocks
        for (uint32_t i = i2_idx; i < N_DOUBLE_INDIRECT; i++)
        {
            if(inp->i2[i] != BlockNullReference) {
                bool freeBlock = false;
                if(i==i2_idx && !cleanDoubleIndirect) {
                    freeBlock = grpFreeDoubleIndirectFileBlocks(inp,inp->i2[i],(ffbn-N_DIRECT-N_INDIRECT*RPB)%(RPB*RPB)); //in the first block we start cleaning from the especified reference
                } else if(i >= i2_idx || cleanDoubleIndirect) {
                    freeBlock = grpFreeDoubleIndirectFileBlocks(inp, inp->i2[i],0); //in the rest we clean after 0
                }
            
                if(freeBlock) { //references were clean so block needs to be freed
                    soFreeDataBlock(inp->i2[i]);
                    freed++;
                    inp->i2[i] = BlockNullReference;
                }
            }
        }       

        //update block count
        //printf("Number of freed blocks: %d \n", freed);
        inp->blkcnt -= freed;
        soSaveInode(ih);
    }

    /* ********************************************************* */

#if true
    static bool grpFreeIndirectFileBlocks(SOInode * ip, uint32_t i1, uint32_t ffbn)
    {
        soProbe(303, "%s(..., %u, %u)\n", __FUNCTION__, i1, ffbn);

        /* replace the following line with your code */
        // throw SOException(ENOSYS, __FUNCTION__); 

        //Luísa Amaral, 93001

        uint32_t buffer[RPB];
        soReadDataBlock(i1, buffer);

        bool delete_block = true;
        for(uint32_t i = 0; i<RPB; i++) {
            if(i>=ffbn && buffer[i] != BlockNullReference) {
                soFreeDataBlock(buffer[i]);
                freed++;
                buffer[i] = BlockNullReference;
            }
            if(buffer[i] != BlockNullReference) delete_block = false; //there are references in this block that are != nil so block is not freed
        }
        soWriteDataBlock(i1, buffer);
        return delete_block;

    }
#endif

    /* ********************************************************* */

#if true
    static bool grpFreeDoubleIndirectFileBlocks(SOInode * ip, uint32_t i2, uint32_t ffbn)
    {
        soProbe(303, "%s(..., %u, %u)\n", __FUNCTION__, i2, ffbn);

        /* replace the following line with your code */
        // throw SOException(ENOSYS, __FUNCTION__); 

        //Luísa Amaral, 93001

        uint32_t buffer[RPB];
        soReadDataBlock(i2, buffer);

        bool delete_block = true;
        for(uint32_t i = 0; i<RPB; i++) {
            if(buffer[i] != BlockNullReference) {
                bool freeBlock = false;
                
                if(i==ffbn/RPB) {
                    freeBlock = grpFreeIndirectFileBlocks(ip, buffer[i], ffbn%RPB);
                } 
                else if(i>ffbn/RPB) {
                    freeBlock = grpFreeIndirectFileBlocks(ip, buffer[i], 0);
                } 
                if(freeBlock) {
                    soFreeDataBlock(buffer[i]);
                    freed++;
                    buffer[i] = BlockNullReference;
                }
                if(buffer[i] != BlockNullReference) delete_block = false; //there are references in this block that are != nil so block is not freed
            }
        }
        soWriteDataBlock(i2, buffer);
        return delete_block;

    }
#endif

    /* ********************************************************* */
};

