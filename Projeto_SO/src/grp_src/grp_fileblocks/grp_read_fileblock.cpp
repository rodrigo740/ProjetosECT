#include "fileblocks.h"

#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <string.h>
#include <inttypes.h>

namespace sofs20
{
    void grpReadFileBlock(int ih, uint32_t fbn, void *buf)
    {
        soProbe(331, "%s(%d, %u, %p)\n", __FUNCTION__, ih, fbn, buf);

        //Hugo Leal, 93059
        /* replace the following line with your code */
        //binReadFileBlock(ih, fbn, buf);
        
        //  Read a file block.

        // Data is read from a specific file block of an in-use inode
        uint32_t fileblock = soGetFileBlock(ih, fbn);
        
        // If the referred file block has not been allocated yet, 
        // meaing that fileblock equals BlockNullRefence
        if(fileblock == BlockNullReference){
            //the returned data will consist of a byte stream filled with the null character (ascii code 0);
            // meaing that buf pointer needs to be field with 0, Block Size
            memset(buf, '\0', BlockSize);
            return;
        }
        // Else we read the correpsondent data block to buf
        soReadDataBlock(fileblock, buf);
    }
};