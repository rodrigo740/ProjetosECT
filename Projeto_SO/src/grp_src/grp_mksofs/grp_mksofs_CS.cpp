/**
 * Implemented by Maria Cunha 93089
 */
#include "grp_mksofs.h"

#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <inttypes.h>
#include <iostream>

#include <math.h>
#include <inttypes.h>

namespace sofs20
{
    void grpComputeStructure(uint32_t ntotal, uint32_t & itotal, uint32_t & dbtotal)
    {
        soProbe(601, "%s(%u, %u, ...)\n", __FUNCTION__, ntotal, itotal);

        // Implemented by Maria Cunha 93089
    // if, at entry, the proposed value for itotal is 0, value ntotal/16 should be used

        if (itotal == 0)
        {
            itotal = ntotal/16;
        }

    //- itotal must be rounded up to a multiple of 32;
    //- itotal must be rounded up to a multiple of IPB;
    //- itotal must be lower than or equal to the rounded up value of ntotal/8
    //- itotal must be greater than or equal to IPB

        if (itotal > ceil(ntotal/8))
        {
            itotal = ceil(ntotal/8);
        }

        if (itotal < IPB)
        {
            itotal = IPB;
        }
    
        if (itotal % IPB != 0)
        {
            itotal = uint32_t(( (itotal/IPB) + 1 ) * IPB) ;
        }
        
        if (itotal % 32 != 0)
        {
            itotal = uint32_t(( (itotal/32) + 1 ) * 32) ;
        }

        
    /*
                ntotal = total number of blocks of the disk         (in)
                IPB = number of inodes per block
                itotal = total number of inodes                    (in/out)
                RPB = number of references per block
                dbtotal = total number of data blocks               (out)
    
    */
    // - for every free data block (all but one), there must exist a reference in the retrieval cache or in the reference table
    // 1 bloco de datablocks  => 1/RPB na reference table

      
        uint32_t ibtotal = itotal/IPB;                               //number of blocks in the inode table
        
        dbtotal = ntotal - ibtotal - 1;                     //db_total = total - inode blocks - superblock
        
        uint32_t candbtotal = dbtotal - 1 ;           //candidates to data blocks 

        if ( candbtotal > REF_CACHE_SIZE )                     // cache is full and needs to start filling the reference table
        {
            // 1 DBblock => 1/RPB blocos na reference table
            //       =>             dbrftotal / (RPB + 1)    
            candbtotal -= REF_CACHE_SIZE;
            uint32_t q =  candbtotal / (1 + RPB) ;
            uint32_t r =  candbtotal % (1 + RPB) ;
            uint32_t rtotal = q;

            if (r >= 2)
            {
                // data blocks = Q * RPB + (R - 1)
                // reference blocks = Q + 1
                rtotal = (q + 1);
            }else if (r == 1)
            {
                //- If, after making the partition, a spare block remains, it should be assigned to the inode table
                itotal += 2*IPB;
            }else
            {
                // data blocks = Q * RPB
                // reference blocks = Q
                rtotal = q;
            }
            // the total number of data blocks is the total number of blocks of the disk (ntotal) less the inode blocks(itotal),
            // the superblock (1) and reference table blocks (rtotal)
            dbtotal = ntotal - ibtotal - 1 - rtotal ;
        }
    }
};

