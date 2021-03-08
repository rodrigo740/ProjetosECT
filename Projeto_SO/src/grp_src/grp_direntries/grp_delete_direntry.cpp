#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "fileblocks.h"
#include "bin_direntries.h"

#include <errno.h>
#include <string.h>
#include <sys/stat.h>

// Rodrigo Martins 93264

namespace sofs20
{
    uint16_t grpDeleteDirentry(int pih, const char *name)
    {
        soProbe(203, "%s(%d, %s)\n", __FUNCTION__, pih, name);

        SOInode *ip = soGetInodePointer(pih);


        uint32_t blocks = ip->size / BlockSize;
        uint32_t remainder = ip->size % BlockSize;
        if (remainder != 0)
        {
            blocks++;
        }

        uint16_t inodeNumber;
        uint32_t entryPosition, entryBlock;

        char s[SOFS20_FILENAME_LEN+1] = "\0";


        for (uint32_t j = 0; j < blocks; j++)
        {
            SODirentry entries[DPB];
            soReadFileBlock(pih, j, entries);
            
            for (uint32_t i = 0; i < DPB; i++)
            {
                // If the entry was found, the function must search for the last entry or the first empty entry
                if (strcmp(entries[i].name, name) == 0)
                {
                    
                    inodeNumber = entries[i].in;
                    entryPosition = i;
                    entryBlock = j;

                    uint32_t lBlock;

                    SODirentry lastEntrys[DPB];
                    // Starts reading the entrys but this time is searching for an empty one
                    for (uint32_t g = 0; g < blocks; g++)
                    {
                        
                        soReadFileBlock(pih, g, lastEntrys);
                        lBlock = g;

                        for (uint32_t k = 0; k < DPB; k++)
                        {
                            // The empty entry was found, now it must delete the target entry and empty the block if necessary
                            if (strcmp(lastEntrys[k].name, s) == 0){
                            
                                // Entry in the same block
                                if (lBlock == entryBlock)
                                {
                                    entries[entryPosition] = entries[k - 1];
                                    entries[k - 1] = entries[k];
                                    soWriteFileBlock(pih, entryBlock, entries);

                                    ip->size -= sizeof(SODirentry);

                                    if (ip->size % BlockSize == 0)
                                    {
                                        // check if db is empty
                                        for (uint32_t h = 0; h < DPB; h++)
                                        {
                                            // not empty, cant be freed
                                            if (strcmp(entries[h].name, s) != 0)
                                            {
                                                soSaveInode(pih);
                                                return inodeNumber;
                                            }
                                        }
                                        soFreeFileBlocks(pih, g);
                                    }
                                    
                                }else
                                {
                                    entries[entryPosition] = lastEntrys[k - 1];
                                    lastEntrys[k - 1] = lastEntrys[k];
                                    soWriteFileBlock(pih, entryBlock, entries);
                                    soWriteFileBlock(pih, lBlock, lastEntrys);

                                    ip->size -= sizeof(SODirentry);

                                    if (ip->size % BlockSize == 0)
                                    {
                                        // check if db is empty
                                        for (uint32_t h = 0; h < DPB; h++)
                                        {
                                            // not empty, cant be freed
                                            if (strcmp(lastEntrys[h].name, s) != 0)
                                            {
                                                soSaveInode(pih);
                                                return inodeNumber;
                                            }
                                        }
                                        soFreeFileBlocks(pih, g);
                                    }
                                }
                                
                                soSaveInode(pih);
                                return inodeNumber;
                            }
                        }
                    }

                    // If there are no empty entries the last block is full and the last entry will be used for replacing
                    // the target entry 
                    SODirentry empty = {0,'\0'};
                    
                    if(lBlock == entryBlock){

                        entries[entryPosition] = entries[DPB-1];
                        entries[DPB-1] = empty;  
                        soWriteFileBlock(pih, entryBlock, entries);

                        ip->size -= sizeof(SODirentry);

                        soSaveInode(pih);
                        return inodeNumber;
                    }else{
                        
                        entries[entryPosition] = lastEntrys[DPB-1];
                        lastEntrys[DPB-1] = empty;
                        soWriteFileBlock(pih, entryBlock, entries);
                        soWriteFileBlock(pih, lBlock, lastEntrys);
                
                        ip->size -= sizeof(SODirentry);

                        soSaveInode(pih);
                        return inodeNumber;
                    }
                }
            }
        }
        // The entry wasn't found
        throw SOException(ENOENT, __FUNCTION__);
    }
}; // namespace sofs20
