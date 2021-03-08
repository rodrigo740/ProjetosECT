          #include "fileblocks.h"

#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <errno.h>

namespace sofs20
{
    /* ********************************************************* */

    //constantes 
    static uint32_t i1_SIZE = N_DIRECT*RPB;
    static uint32_t i2_SIZE = N_DOUBLE_INDIRECT*(RPB^2);
 
    static uint32_t grpGetIndirectFileBlock(SOInode * ip, uint32_t fbn);
    static uint32_t grpGetDoubleIndirectFileBlock(SOInode * ip, uint32_t fbn);


    /* ********************************************************* */

    uint32_t grpGetFileBlock(int ih, uint32_t fbn)
    {
        soProbe(301, "%s(%d, %u)\n", __FUNCTION__, ih, fbn);

        /* replace the following line with your code */
        //return binGetFileBlock(ih, fbn);
        
        //Raquel Pinto - 92948
        
        /* Get the data block number corresponding to the given file block.
        The file block number (fbn) is the number of a block from the file (inode) point of view, 0 representing the first block, 1 the second, and so forth.
        The data block number (dbn) corresponding to fbn is given by d[fbn]. Note, however, that array d[.] is only partially stored in the inode.
        */
        //Parameters -> ih - inode handler and fbn - file block number
        //Precondition -> ih is a valid handler of an inode in-use and fbn is a valid file block number
         
        //soCheckInodeHandler(ih,__FUNCTION__); //ih is a valid handler (Check given handler, throwing an exception in case of error)
        SOInode *inode =  soGetInodePointer(ih);
        
        // fdb isn't a valid file block number)
        if(((N_DIRECT + i1_SIZE + i2_SIZE) <= fbn) || (0 > fbn)){
            throw SOException(EINVAL,__FUNCTION__);
        }

        //return the number of the corresponding data block
        if(fbn < N_DIRECT){ // direct
            if(inode->d[fbn] ==BlockNullReference ){
                return BlockNullReference;
            }else{
                return inode->d[fbn];
            }               
        }else if (N_DIRECT+i1_SIZE > fbn){ // indirect
            uint32_t  indirectRef = grpGetIndirectFileBlock(inode,fbn);  
            return indirectRef; 
        }else{// doubleIndirect
            uint32_t doubleIndirectRef = grpGetDoubleIndirectFileBlock(inode,fbn);
            return doubleIndirectRef;
        }
        
        return BlockNullReference;
    }

    /* ********************************************************* */


    static uint32_t grpGetIndirectFileBlock(SOInode * ip, uint32_t afbn)
    {
        soProbe(301, "%s(%d, ...)\n", __FUNCTION__, afbn);

        /* replace the following two lines with your code */
        // throw SOException(ENOSYS, __FUNCTION__); 
        // return 0;

        //afbn correct = afbn - N_DIRECT

        uint32_t i_indirect = (afbn - N_DIRECT)/RPB;
        // Indirect zone address
        uint32_t ref= (afbn - N_DIRECT) % RPB;
        if(ip->i1[i_indirect] == BlockNullReference){
            return BlockNullReference;
        }
        uint32_t i1_aux[RPB];
        soReadDataBlock(ip->i1[i_indirect],i1_aux);
        return i1_aux[ref];
    }

    /* ********************************************************* */


    static uint32_t grpGetDoubleIndirectFileBlock(SOInode * ip, uint32_t afbn)
    {
        soProbe(301, "%s(%d, ...)\n", __FUNCTION__, afbn);

        /* replace the following two lines with your code */
        // throw SOException(ENOSYS, __FUNCTION__); 
        //  return 0;

        // afbn correct = afbn - N_DIRECT - N_INDIRECT*RPB

        // Indirect zone address
        uint32_t i_indirect = ((afbn - N_DIRECT - N_INDIRECT*RPB)/RPB)%RPB;
       
        // doubleIndirect zone address
        // first % RPB -> indirect zone, second % RPB -> double indirect zone
        uint32_t ref = (afbn - N_DIRECT - N_INDIRECT*RPB) % RPB % RPB; 
        uint32_t i_doubleIndirect = (afbn - N_DIRECT - i1_SIZE )/(RPB*RPB);
        
        if(ip->i2[i_doubleIndirect] == BlockNullReference){
            return BlockNullReference;
        }

        uint32_t i1_aux[RPB];
        soReadDataBlock(ip->i2[i_doubleIndirect],i1_aux);
        
        if(i1_aux[i_indirect] == BlockNullReference){
            return BlockNullReference;
        }

        uint32_t i2_aux[RPB];
        soReadDataBlock(i1_aux[i_indirect],i2_aux);
        return i2_aux[ref];
         
        }
};
