#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "fileblocks.h"
#include "bin_direntries.h"

#include <errno.h>
#include <string.h>
#include <sys/stat.h>

namespace sofs20
{
    bool grpCheckDirEmpty(int ih)
    {
        soProbe(205, "%s(%d)\n", __FUNCTION__, ih);

        /* replace the following line with your code */
        // return binCheckDirEmpty(ih);

        // Luísa Amaral, 93001

        SOInode* inp = soGetInodePointer(ih);
        
        if ((inp->mode  & ~0777) != S_IFDIR){
            throw SOException(ENOTDIR, __FUNCTION__);
        }
        

        // uint32_t numBlocks = inp->size/BlockSize;
        uint32_t r = inp->size%BlockSize;
        uint32_t numEntries = r/sizeof(SODirentry);
        
        if (numEntries != 2) return false; //if it is empty then the only entries are . and ..
        return true;

        
    }
};

