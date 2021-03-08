#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <string.h>
#include <inttypes.h>

namespace sofs20
{
    void grpResetFreeDataBlocks(uint32_t ntotal, uint32_t itotal, uint32_t dbtotal)
    {
        soProbe(607, "%s(%u, %u, %u)\n", __FUNCTION__, ntotal, itotal, dbtotal);

        /* replace the following line with your code */
        //binResetFreeDataBlocks(ntotal, itotal, dbtotal);
        
        //Hugo Leal, 93059

        //  Fill with zeros the free data blocks.
        //  The following must be considered:
        //  All data blocks, but the one used by the root directory, must be filled with pattern 0x0.
        // Parameters
        //    [in]	ntotal	Total number of blocks in the device
        //    [in]	itotal	Total number of inodes
        //    [in]  dbtotal	Total number of data blocks



        // Create a static array of BlockSize, all filled with zeros
        char zeros [BlockSize];   
        memset(zeros, 0, BlockSize);
        
        
        // First pool of data blocks
        // Value from which we rewrite 0x0
        // itotal -> total number of inodes
        // IPB -> Number of inodes per block 
        
        // get the first index of DataBlocks
        int firstDatablocksindex = itotal/IPB + 1;

        // For cycle that rewrites the data blocks with zero
        // starts from the second block of datablocks blocks and rewrites all data blocks
        // 'firstDatablocksindex' is added to 'i' to write on the appropriate reference  
        for (unsigned int i = 1; i < dbtotal; i++)
        {
            soWriteRawBlock(i + firstDatablocksindex, zeros);
        }
    }
};
