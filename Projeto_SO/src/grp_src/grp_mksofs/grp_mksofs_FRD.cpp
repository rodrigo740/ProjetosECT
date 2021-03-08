#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <string.h>
#include <inttypes.h>

// Rodrigo Martins 93264
namespace sofs20
{
    /*
       filling in the contents of the root directory:
       the first 2 entries are filled in with "." and ".." references
       the other entries are empty.
    */
    void grpFillRootDir(uint32_t itotal)
    {
        soProbe(606, "%s(%u)\n", __FUNCTION__, itotal);

        /* A directory is an array of SODirentry's.
         * Fill the array with empty entries, then fill the first and second entries with "." and ".." both pointing
         * to Inode number 0
        */

        SODirentry diretorio[DPB];
        memset(diretorio,'\0',sizeof(diretorio));
        
        SODirentry d0 ={0,'.'};
        SODirentry d1 ={0,".."};

        diretorio[0] = d0;
        diretorio[1] = d1;
        
        int pos = 1+itotal/IPB;
        soWriteRawBlock(pos,&diretorio);
    }
};

