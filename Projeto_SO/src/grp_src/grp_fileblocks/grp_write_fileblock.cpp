#include "fileblocks.h"

#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <string.h>
#include <inttypes.h>

/* 
 * Write a file block.
 * Data is written into a specific file block of an in-use inode
 * If the referred block has not been allocated yet, it should be allocated now so that the 
 * data can be stored as its contents
*/ 
// Rodrigo Martins 93264
namespace sofs20
{
    void grpWriteFileBlock(int ih, uint32_t fbn, void *buf)
    {
        soProbe(332, "%s(%d, %u, %p)\n", __FUNCTION__, ih, fbn, buf);

        uint32_t fb = soGetFileBlock(ih, fbn);
        if(fb == BlockNullReference){
            uint32_t num = soAllocFileBlock(ih,fbn);
            soWriteDataBlock(num,buf);
        }else
        {
            soWriteDataBlock(fb,buf);
        }
    }
};

