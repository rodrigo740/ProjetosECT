#include "fileblocks.h"

#include "freedatablocks.h"
#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <errno.h>

#include <iostream>

namespace sofs20
{

    // constants
    static uint32_t i1_SIZE = N_INDIRECT*RPB;
    static uint32_t i2_SIZE = N_DOUBLE_INDIRECT*(RPB*RPB);

    static uint32_t grpAllocIndirectFileBlock(SOInode * ip, uint32_t afbn);
    static uint32_t grpAllocDoubleIndirectFileBlock(SOInode * ip, uint32_t afbn);


    /* ********************************************************* */

    uint32_t grpAllocFileBlock(int ih, uint32_t fbn)
    {
        soProbe(302, "%s(%d, %u)\n", __FUNCTION__, ih, fbn);

        // Implemented by Maria Cunha 93089
        /*
            ih - inode handler
            fbn - file block number
            dbn - data block number is given by d[fbn]
        */

       // | Exception | : EINVAL if fbn is not valid
       //   - bigger or equal to the maximum number of fileblocks
       //   - less than 0 
       
       
        if (((N_DIRECT + i1_SIZE + i2_SIZE) <= fbn) || (0 > fbn))
        {
            throw SOException(EINVAL,__FUNCTION__);
        }

        // evaluate where it will be stored 
        if (fbn < N_DIRECT)
        {
            // Direct   ->   the data block is store in the inode
            SOInode *inode_p = soGetInodePointer(ih);
            uint32_t db_ref = soAllocDataBlock();
            
            inode_p->d[fbn] = db_ref;
            inode_p->blkcnt += 1;

            soSaveInode(ih);
            return db_ref;

        }else if (N_DIRECT+i1_SIZE > fbn)
        {
            // Indirect
            SOInode *inode_p = soGetInodePointer(ih);
            uint32_t db_ref = grpAllocIndirectFileBlock(inode_p,fbn);

            soSaveInode(ih);
            return db_ref;
        }else
        {
            // Double Indirect
            SOInode *inode_p = soGetInodePointer(ih);
            uint32_t db_ref = grpAllocDoubleIndirectFileBlock(inode_p,fbn);

            soSaveInode(ih);
            return db_ref;
        }
    }

    /*
        Indirect
    */
    static uint32_t grpAllocIndirectFileBlock(SOInode * ip, uint32_t afbn)
    {
        soProbe(302, "%s(%d, ...)\n", __FUNCTION__, afbn);

        uint32_t blck_ref ;
        uint32_t refs[RPB];   //temporary array to store references of the block being referenced
        /*
            i1 -> points to a data block with references
        */
        uint32_t i_i1 = (afbn - N_DIRECT)/RPB;           // index of block in i1
        uint32_t i_db = (afbn - N_DIRECT)%RPB;         // index of referenced block

        if (ip->i1[i_i1] == BlockNullReference)
        {
            // Null reference so it's necessary to alloc data block
            blck_ref = soAllocDataBlock();
            // inicialize all references on the block alocated
            for (long unsigned int i = 0; i < RPB; i++)
            {
                refs[i] = BlockNullReference;
            }
            ip->i1[i_i1] = blck_ref;            //assign reference
            ip->blkcnt += 1;                    // block i1[i_i1]
        }else
        {
            blck_ref = ip->i1[i_i1];            // data block already allocated so fetch block reference
            soReadDataBlock(blck_ref,refs);     // copy references to a temporary array
        }
        ip->blkcnt += 1;                        // block d[...]
        uint32_t db_ref = soAllocDataBlock();
        refs[i_db]= db_ref;                     // assign reference to temporary array
        soWriteDataBlock(blck_ref,refs);        // update references on the data block
        return db_ref;
    }

    /* ********************************************************* */

    /*
        Double Indirect
    */
    static uint32_t grpAllocDoubleIndirectFileBlock(SOInode * ip, uint32_t afbn)
    {
        soProbe(302, "%s(%d, ...)\n", __FUNCTION__, afbn);
        uint32_t ref_i1 , ref_i2 ;
        uint32_t refs_i1[RPB], refs[RPB];   // temporary arrays to store i1's references and data block's references
        /*
            i1 -> points to a data block with references
        */
        uint32_t n = afbn - N_DIRECT - N_INDIRECT*RPB;
        uint32_t i_i1 = n/(RPB);             // index of block in i1
        /*
            i2 -> points to a data block that extends i1
        */
        uint32_t i_i2 = 0;           // index of block in i2
        uint32_t i_db = n%RPB;      // index of block in data block       
        // first allocate/read i2
        if (ip->i2[i_i2] == BlockNullReference)
        {
            // Null reference so it's necessary to alloc data block
            ref_i2 = soAllocDataBlock();
            // inicialize all references on the block allocated
            for (long unsigned int i = 0; i < RPB; i++)
            {
                refs_i1[i] = BlockNullReference;
            }
            ip->i2[i_i2] = ref_i2;  // assign reference
            ip->blkcnt += 1;        // block i2[i_i2]
        }else
        {
            ref_i2 = ip->i2[i_i2];               // data block already allocated so fetch block reference
            soReadDataBlock(ref_i2,refs_i1);     // copy references to a temporary array
        }
        
        //then do the same to i1
        if (refs_i1[i_i1] == BlockNullReference)
        {
            // Null reference so it's necessary to alloc data block
            ref_i1 = soAllocDataBlock();
            // inicialize all references on the block alocated
            for (long unsigned int i = 0; i < RPB; i++)
            {
                refs[i] = BlockNullReference;
            }
            ip->blkcnt += 1;        // block i1[i_i1]
            refs_i1[i_i1] = ref_i1;
        }else
        {
            ref_i1 = refs_i1[i_i1];            // data block already allocated so fetch block reference
            soReadDataBlock(ref_i1,refs);     // copy references to a temporary array
        }
        uint32_t db_ref = soAllocDataBlock();
        refs[i_db]= db_ref;                     // assign reference to temporary array
        ip->blkcnt += 1;                        // block d[...]
        
        // update references on the data block
        soWriteDataBlock(ref_i2,refs_i1);      
        soWriteDataBlock(ref_i1,refs);          
        return db_ref;
    }
};

