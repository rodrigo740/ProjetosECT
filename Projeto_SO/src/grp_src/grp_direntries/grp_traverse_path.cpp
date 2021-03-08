#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "fileblocks.h"
#include "direntries.h"
#include "bin_direntries.h"

#include <errno.h>
#include <string.h>
#include <libgen.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>

namespace sofs20
{
    uint16_t grpTraversePath(char *path)
    {
        //Lúcia Sousa 93086
        soProbe(221, "%s(%s)\n", __FUNCTION__, path);
		/* replace the following line with your code */
        //return binTraversePath(path);

        //get the inode associated to a given path
        //the directory hierarchy of the file system is traversed to find an entry 
        //whose name is the rightmost component of the given path
        //soGetDirentry() -> get the inode associated to a given name
        //path -> to be traversed
        //pih -> inode handler of the parent directory
        //nameEntry -> name of the entry to be searched for
        //soOpenInode() -> inode handler
        //basename() -> return a pointer to the last component of the pathname 
        //dirname() -> return a pointer to a string that is a path name of the parent directory

        /**********************************EXCEPTIONS*************************************
        ENOTDIR -> if one of the path components, with the exception of the rightmost 
        one, is not a directory
        EACCES -> if the process that calls the operation does not have traverse (x) 
        permission on all the components of the path, with exception of the rightmost one
        **********************************************************************************/
        
        //return the corresponding inode number

        uint32_t inode;
        //duplicate string path
        char *copyPath = strdup(path);
        //last component of the pathname
        char *base = strdup(basename(copyPath));
        //path name
        char *nameDir = dirname(copyPath);

        //if its root inode = 0
        if(strlen(path) == 1 && path[0] == '/'){
            inode = 0;
            return inode;
        }

        if(strcmp(nameDir,"/") == 0){
            int pih = soOpenInode(0);														
            if(!soCheckInodeAccess(pih, 01)) throw SOException(EACCES,__FUNCTION__);		
            inode = soGetDirentry(pih, base);										
            if(inode == InodeNullReference) throw SOException(ENOENT,__FUNCTION__); 	
            soSaveInode(pih);	
                                                                    
            return inode;
		}
        
        int pih = soOpenInode(soTraversePath(nameDir));
        if(!soCheckInodeAccess(pih, 01)) throw SOException(EACCES,__FUNCTION__);
        inode = soGetDirentry(pih,base);
        if(inode == InodeNullReference) throw SOException(ENOENT,__FUNCTION__); 	
        soSaveInode(pih);
        return inode;
    }
};

