#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <string.h>
#include <time.h>
#include <unistd.h>
#include <sys/stat.h>
#include <inttypes.h>

namespace sofs20
{
    void grpFillInodeTable(uint32_t itotal, bool date)
    {
        soProbe(604, "%s(%u)\n", __FUNCTION__, itotal);        
        
        /* replace the following line with your code */
        //binFillInodeTable(itotal, date);
        
        // Raquel Pinto - 92948
        SOInode inodes[IPB];

        // the number of blocks is given by itotal/IPB blocks
        uint32_t numBlocks = itotal/IPB;
        for(uint32_t b = 1; b<numBlocks+1;b++){ // goes through the blocks -> the first block is the number 1 not 0
            for (uint32_t i = 0; i<IPB;i++){  // goes through the inodes of block 
                if(b==1 && i==0){ // inode 0
                    //Inode number 0 must be filled knowing it is the root directory, considering:
                    inodes[0].mode = S_IFDIR | 0755;//its permissions should be 0755
                    inodes[0].lnkcnt = 2; // 2 directory entries
                    inodes[0].owner = getuid(); //owner and group are given by syscalls getuid and getgid
                    inodes[0].group = getgid();
                    inodes[0].size = ((2*BlockSize)/DPB); 
                    // 2* = 2 args of function -> he have DPB and BlockSize, so we need (2*BlockSize)/DPB file size in bytes
                    inodes[0].blkcnt = 1;
                    inodes[0].d[0] = 0; // the d field in inode 0 and reference number 0 is diferent from the others
                    for(uint32_t j=1;j<N_DIRECT;j++){ 
                        inodes[0].d[j] = BlockNullReference;
                    }
                    //if true current date is set; otherwise date is put at zero       
                    if(date){
                        inodes[0].atime = date;
                        inodes[0].mtime = date;
                        inodes[0].ctime = date;
                    }else{
                        inodes[0].atime = 0;
                        inodes[0].mtime = 0;
                        inodes[0].ctime = 0;
                    }

                }else{ // all other inodes
                //All others inodes must be put in the clean state, meaning:
                
                    //all other fields at 0.
                    inodes[i].mode = 0;
                    inodes[i].lnkcnt = 0; 
                    inodes[i].owner = 0;
                    inodes[i].group = 0;
                    inodes[i].size = 0;  
                    inodes[i].blkcnt = 0;
                    inodes[i].atime = 0;
                    inodes[i].mtime = 0;
                    inodes[i].ctime = 0;
                    //all reference fields at BlockNullReference
                    for(uint32_t j=0;j< N_DIRECT;j++){
                        inodes[i].d[j] = BlockNullReference;
                    }
                }               
            } 
            //all reference fields at BlockNullReference
            for (uint32_t i = 0; i<IPB;i++){ // all inodes (0 including)
                for(uint32_t j=0;j<N_INDIRECT;j++){
                    inodes[i].i1[j] = BlockNullReference;
                }
                for(uint32_t j=0;j<N_DOUBLE_INDIRECT;j++){
                    inodes[i].i2[j] = BlockNullReference;
                }
            }
            soWriteRawBlock(b, &inodes);
        }       
    
    }
};
