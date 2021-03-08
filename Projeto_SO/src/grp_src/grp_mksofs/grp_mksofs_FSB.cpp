#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <string.h>
#include <inttypes.h>

#define FIRST_MAGIC_NUMBER 0xFFFF

namespace sofs20
{
    void grpFillSuperblock(const char *name, uint32_t ntotal, uint32_t itotal, uint32_t dbtotal)
    {
        soProbe(602, "%s(%s, %u, %u, %u)\n", __FUNCTION__, name, ntotal, itotal, dbtotal);

        /* replace the following line with your code */
        // binFillSuperblock(name, ntotal, itotal, dbtotal);

        //Luísa Amaral, 93001

        struct SOSuperblock sb;
        //The magic number must be put at 0xFFFF. Afterwards, it will be put with the correct value.
        sb.magic = FIRST_MAGIC_NUMBER; //magic number - file system identification number 
        sb.version = VERSION_NUMBER;
        //?
        sb.mntstat = 0; //positive means properly unmounted
        strncpy(sb.name, name, PARTITION_NAME_LEN+1); //volume name
        sb.ntotal = ntotal;   //total number of blocks in the device 
        
        
        /* Inode table's metadata */
        sb.itotal = itotal;   //total number of inodes 
        sb.ifree = itotal - 1; //number of free inodes
        sb.iidx = 0; //bit index of last allocated inode
        
        //All bits of the inode bitmap must be 0, except the one representing inode 0.
        //bitmap representing inode allocation states
        sb.ibitmap[0] = 1;
        int i = 1;
        while (i < MAX_INODES/32) {
            sb.ibitmap[i] = 0;
            i++;
        }

        /* Data blocks' metadata */
        sb.dbp_start = (itotal/IPB)+1; //physical number of the block where the data block pool starts
        sb.dbtotal = dbtotal; //total number of data blocks 
        sb.dbfree = dbtotal - 1; //number of free data blocks
         

        /* Retrieval cache's metadata */
        //the cache is managed in a circular way  
        if (sb.dbfree >= REF_CACHE_SIZE) {
            sb.retrieval_cache.idx = 0;
        } else {
            sb.retrieval_cache.idx = REF_CACHE_SIZE - sb.dbfree;
        }

        //the retrieval cache must be left filled (totally, if possible).
        uint32_t bloco = 1;
        for(unsigned int k = 0; k < REF_CACHE_SIZE; k++) {
            if(k < sb.retrieval_cache.idx) {
                sb.retrieval_cache.ref[k] = BlockNullReference;
            } else {
                sb.retrieval_cache.ref[k] = bloco;
                bloco++;
            }
        }

        /* Insertion cache's metadata */
        sb.insertion_cache.idx = 0; //index of first free/occupied cell
        //The insertion cache must be left empty.
        for(int j = 0; j < REF_CACHE_SIZE; j++) {
            sb.insertion_cache.ref[j] = BlockNullReference;
        }

        /* Reference table's metadata */
        sb.rt_start = sb.dbp_start + dbtotal; //physical number of the block where the reference table starts 
        sb.rt_size = ntotal - sb.rt_start; //number of blocks the reference table comprises 
        
        sb.reftable.blk_idx = 0; //index, within the reference table, of the first block with references 
        sb.reftable.ref_idx = 0; //index of first cell with references, within the previous block 
        
        if(dbtotal < REF_CACHE_SIZE) { //checks if all the blocks fit inside retrieval cache
            sb.reftable.count = 0;
        } else { //if not then they go to the reference table
            sb.reftable.count = dbtotal - REF_CACHE_SIZE - 1; //total number of not null references in the reference table
        }
        
        
        // [in]	n	physical number of the disk block to be written into
        // [in]	buf	pointer to the buffer containing the data to be written from
         soWriteRawBlock(0, &sb);
    }
};

