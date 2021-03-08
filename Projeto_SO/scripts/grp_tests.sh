#!/bin/bash
# --------------------------------------------------------------------------

# "======================= Compiling ======================="
source sofs20.sh

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m' # No Color


function testhelp()
{
        hm+="  testcs   --- Run the group script test file of Computer Structure\n"
        hm+="  testfrt   --- Run the group script test file of Fill Reference Table\n"
        hm+="  testfrd   --- Run the group script test file of Fill Root Dir\n"
        hm+="  testfit   --- Run the group script test file of Fill Inode Table\n"
        hm+="  testfsb   --- Run the group script test file of Fill SuperBlock\n"
        hm+="  testai    --- Run the group script test file of Alloc inode\n"
        hm+="  testfi    --- Run the group script test file of Free inode\n"
        hm+="  testadb   --- Run the group script test file of Alloc data block\n"
        hm+="  testfdb   --- Run the group script test file of Free data block\n"
        hm+="  testrrc   --- Run the group script test file of Replenish retrieval cache\n"
        hm+="  testdic   --- Run the group script test file of Deplete insertion cache\n"
        hm+="  testgfb   --- Run the group script test file of Get file block\n"
        hm+="  testafb   --- Run the group script test file of Alloc file block\n"
        hm+="  testffb   --- Run the group script test file of Free file blocks\n"
        hm+="  testrfb   --- Run the group script test file of Read file block\n"
        hm+="  testwfb   --- Run the group script test file of Write file block\n"
        hm+="  testgde   --- Run the group script test file of Get direntry\n"
        hm+="  testade   --- Run the group script test file of Add direntry\n"
        hm+="  testdde   --- Run the group script test file of Delete direntry\n"
        hm+="  testrde   --- Run the group script test file of Rename direntry\n"
        hm+="  testcde   --- Run the group script test file of Check directory emptiness\n"
        hm+="  testtp    --- Run the group script test file of Traverse path\n"

        InfoMessage "$hm"
}

# run all tests
function testall() 
{
    local list="testcs testfrd testfrt testfit testfsb testai testfi testadb testfdb testrrc testdic testgfb testafb testffb testrfb testwfb testgde testade testdde testrde testcde testtp"
    local quiet=""
    local result=""
    local diff_fail=()
    local list_fail=()
    read -p $'Do you wish to run the tests quietly (y|n) ? : ' quiet
    for arg in $list;do
        local func=$arg
        if [ $quiet == "Y" ] || [ $quiet == "y" ]
        then
            printf ${YELLOW}"$arg : \n"${NC}
            $arg &> tempall
            if [ $? == "0" ]
            then
                printf ${GREEN}"Test passed\n"${NC}
            elif [ $? == "1" ]
            then
                printf ${RED}"Test failed\n"${NC}
                diff_fail+=("$DIFF") 
                list_fail+=("$func")
            else
                printf ${RED}"Not implemented yet\n"${NC}
            fi
        else
            printf ${YELLOW}"$arg : \n"${NC}
            $arg
        fi
    done

    if [ $quiet == "Y" ] || [ $quiet == "y" ]
    then
        if [ "$list_fail" != "" ]
        then
        printf ${RED}"\n======================= Failed tests: =======================\n"

        for ((idx=0; idx<${#list_fail[@]}; ++idx)); do
            printf ${RED}"${list_fail[idx]} : \n"
            printf ${YELLOW}"${diff_fail[idx]} \n"
        done

        fi
        rm tempall #remove the file with logs
    fi
    printf "\nTests done!\n"
}

# --------------------------------------------------------------------------
# Maria Cunha 93089
function testcs(){
    echo "======================= Compiling ======================="
    m > /dev/null

    echo "======================= Compute Structuring ======================="
    d="50 100 1000 1500 2000 2500"
    echo -ne "Creating and formating disks :"
    for i in $d;
    do
        echo -ne " $i ."
        c "$i" &>/dev/null
        f -b -r 601-601 -p 0-0 > CS_grp.txt
        s >> CS_grp.txt
        c "$i" &>/dev/null
        f -b -p 0-0 > CS_prof.txt
        s >> CS_prof.txt
    done

    echo $'\n======================= Comparing results ======================='
    DIFF=$(diff CS_grp.txt CS_prof.txt)
    rm CS_grp.txt
    rm CS_prof.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------
function testfrt(){
    #Lúcia Sousa 93086
    echo "======================= Compiling ======================="
    m &> /dev/null

    echo "======================= Creating disk with size 1000 ======================="
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f &> /dev/null 
    
    s -r 996-999 >> grp_frt.txt

    echo "======================= Formating disk bin ======================="
    f -b &> /dev/null

    s -r 996-999 >> bin_frt.txt

    echo "======================= Creating disk with size 350 ======================="
    c 350 &> /dev/null

    echo "======================= Formating disk ======================="
    f &> /dev/null 
    
    s -r 348-349 >> grp_frt.txt

    echo "======================= Formating disk bin ======================="
    f -b &> /dev/null

    s -r 348-349 >> bin_frt.txt

    echo "======================= Creating disk with size 50 ======================="
    c 50 &> /dev/null

    echo "======================= Formating disk ======================="
    f &> /dev/null 
    
    s >> grp_frt.txt

    echo "======================= Formating disk bin ======================="
    f -b &> /dev/null

    s >> bin_frt.txt

    echo "======================= Comparing results ======================="
    DIFF=$(diff grp_frt.txt bin_frt.txt)

    rm grp_frt.txt
    rm bin_frt.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------

function testfit()
{   # Raquel Pinto - 92948
    echo "======================= Compiling ======================="
    m &> /dev/null
    echo "======================= Creating disk ======================="
    c &> /dev/null
    #Grp
    echo "======================= Formating disk ======================="
    f &> /dev/null
    echo -ne "\n******************************** Default disk ********************************\n" > Test_fit.txt
   
    s -i 1-4 >> Test_fit.txt
    
    #Bin
    echo -ne "\n******************************** Default disk ********************************\n" > Test_fit_Bin.txt
    echo "======================= Formating binary disk ======================="
    f -b &> /dev/null

    s -i 1-4 >> Test_fit_Bin.txt
    
    #Grp
    echo -ne "\n******************************** Disk 50 ********************************\n" >> Test_fit.txt
   
    echo "======================= Creating disk 50 ======================="
    c 50 &> /dev/null
    echo "======================= Formating disk ======================="
    f &> /dev/null

    s -i 1-3 >> Test_fit.txt

    #Bin
    echo -ne "\n******************************** Disk 50 ********************************\n" >> Test_fit_Bin.txt
    echo "======================= Formating binary disk ======================="
    f -b &> /dev/null

    s -i 1-3 >> Test_fit_Bin.txt

    #Grp
    echo -ne "\n******************************** Disk 500 ********************************\n" >> Test_fit.txt
   
    echo "======================= Creating disk 500 ======================="
    c 500 &> /dev/null
    echo "======================= Formating disk ======================="
    f &> /dev/null

    s -i 1-3 >> Test_fit.txt
    
    #Bin
    echo -ne "\n******************************** Disk 500 ********************************\n" >> Test_fit_Bin.txt
    echo "======================= Formating binary disk ======================="
    f -b &> /dev/null

    s -i 1-3 >> Test_fit_Bin.txt

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <Test_fit.txt > o
    mv o Test_fit.txt

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <Test_fit_Bin.txt > o
    mv o Test_fit_Bin.txt

    echo "======================= Comparing results ======================="
    DIFF=$(diff Test_fit.txt Test_fit_Bin.txt)
    
    rm Test_fit.txt
    rm Test_fit_Bin.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------

#Luísa Amaral, 93001
function testfsb()
{
    echo "======================= Compiling ======================="
    m > temp.txt

    printf "======================= Testing grp fsb =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt

    echo "======================= Formating disk ======================="
    f -b -r 602-602 > temp.txt

    s > grptestfsb.txt

    echo "======================= Creating disk with size 400 ======================="
    c 400 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b -r 602-602 > temp.txt

    s >> grptestfsb.txt

    echo "======================= Creating disk with size 50 ======================="
    c 50 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b -r 602-602 > temp.txt

    s >> grptestfsb.txt

    # with bin
    printf "======================= Testing bin fsb =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    s > bintestfsb.txt


    echo "======================= Creating disk with size 400 ======================="
    c 400 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    s >> bintestfsb.txt

    echo "======================= Creating disk with size 50 ======================="
    c 50 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    s >> bintestfsb.txt

    printf "======================= Comparing results =======================\n"

    DIFF=$(diff grptestfsb.txt bintestfsb.txt)
    
    rm grptestfsb.txt
    rm bintestfsb.txt
    rm temp.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi

    
    

}

# --------------------------------------------------------------------------

#Luísa Amaral, 93001
function testai()
{
    echo "======================= Compiling ======================="
    m > temp.txt

    printf "======================= Testing grp ai =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -r 401-401 -p 0-0 > temp.txt
    done

    s > ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -r 401-401  -p 0-0 > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt


    echo "======================= Creating disk with size 700 ======================="
    c 700 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -r 401-401  -p 0-0 > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -r 401-401  -p 0-0 > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt


    echo "======================= Creating disk with size 50 ======================="
    c 50 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for i in $(seq 1 31)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -r 401-401 -p 0-0 > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 31)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 31)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -r 401-401 -p 0-0 > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt

    for i in $(seq 1 31)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_grp.txt
    s -i 1 >> ai_grp.txt


    # # with bin
    printf "======================= Testing bin ai =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt
    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1  -p 0-0 > temp.txt
    done

    s > ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1  -p 0-0 > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt


    echo "======================= Creating disk with size 700 ======================="
    c 700 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1  -p 0-0 > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1  -p 0-0 > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 63)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt



    echo "======================= Creating disk with size 50 ======================="
    c 50 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for i in $(seq 1 31)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1  -p 0-0 > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 31)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 31)
    do
        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1  -p 0-0 > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt

    for i in $(seq 1 31)
    do
        echo -ne "inode" $(ttfi $i) -b > temp.txt
    done

    s >> ai_bin.txt
    s -i 1 >> ai_bin.txt


    printf "======================= Comparing results =======================\n"

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <ai_grp.txt > o
    mv o ai_grp.txt

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <ai_bin.txt > o
    mv o ai_bin.txt

    DIFF=$(diff ai_grp.txt ai_bin.txt)

    rm ai_grp.txt
    rm ai_bin.txt
    rm temp.txt

    if [ "$DIFF" != "" ]
        then
            printf ${RED}"Test failed\n"${NC}
            printf "\nDifferences between the grp version and bin version:\n\n"
            echo "$DIFF"
            return 1
            
        else
            printf ${GREEN}"Test passed\n"${NC}
            return 0
        fi

    

}

# --------------------------------------------------------------------------
# Maria Cunha 93089
function testfi()
{
    echo "======================= Compiling ======================="
    m &> /dev/null
    echo "======================= Testing disk with size 1000 ======================="
    printf "======================= Testing grp fi =======================\n"
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null

    echo "======================= Allocating inodes ======================="
    for arg in $(seq 0 62); do
        ttai 2 -b &> /dev/null
    done
    s > fi_grp.txt
    echo $'======================= Freeing inodes ======================='

    for arg in $(seq 0 62);do
        echo -ne "fi\n$arg\nq\n" | tt -q 1 -p 0-0 -b -r 402-402 &> /dev/null
        s >> fi_grp.txt
    done
    s -i 1 >> fi_grp.txt

    printf "======================= Testing bin fi ======================="
    #binary
    c &> /dev/null
    printf "\n======================= Formating disk =======================\n"
    f -b &> /dev/null
    for arg in $(seq 0 62); do
        ttai 2 -b > /dev/null
    done
    s > fi_prof.txt

    for arg in $(seq 0 62);do
        ttfi "$arg" -b  &> /dev/null
        s >> fi_prof.txt
    done
    s -i 1 >> fi_prof.txt

    echo "======================= Testing disk with size 700 ======================="
    printf "======================= Testing grp fi =======================\n"
    c 700 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b &> /dev/null

    for arg in $(seq 0 62); do
        ttai 2 -b &> /dev/null
    done
    s >> fi_grp.txt
    echo $'======================= Freeing inodes ======================='

    for arg in $(seq 0 62);do
        echo -ne "fi\n$arg\nq\n" | tt -q 1 -p 0-0 -b -r 402-402 &> /dev/null
        s >> fi_grp.txt
    done
    s -i 1 >> fi_grp.txt

    #binary
    printf "======================= Testing bin fi ======================="
    c 700 &> /dev/null
    printf "\n======================= Formating disk =======================\n"
    f -b &> /dev/null
    for arg in $(seq 0 62); do
        ttai 2 -b &> /dev/null
    done
    s >> fi_prof.txt

    for arg in $(seq 0 62);do
        ttfi "$arg" -b  &> /dev/null
        s >> fi_prof.txt
    done
    s -i 1 >> fi_prof.txt

    echo "======================= Testing disk with size 50 ======================="
    printf "======================= Testing grp fi =======================\n"
    c 50 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b &> /dev/null

    echo "======================= Allocating inodes ======================="

    for arg in $(seq 0 30); do
        ttai 2 -b &> /dev/null
    done
    s >> fi_grp.txt
    echo $'======================= Freeing inodes ======================='

    for arg in $(seq 0 30);do
        echo -ne "fi\n$arg\nq\n" | tt -q 1 -p 0-0 -b -r 402-402 &> /dev/null
        echo "freed inode $arg" >> fi_grp.txt
        s >> fi_grp.txt
    done
    s -i 1 >> fi_grp.txt

    #binary
    printf "======================= Testing bin fi =======================\n"
    c 50 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b > /dev/null
    for arg in $(seq 0 30); do
        ttai 2 -b > /dev/null
    done
    s >> fi_prof.txt

    for arg in $(seq 0 30);do
        ttfi "$arg" -b &> /dev/null
        echo "freed inode $arg" >> fi_prof.txt
        s >> fi_prof.txt
    done
    s -i 1 >> fi_prof.txt

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <fi_grp.txt > o
    mv o fi_grp.txt

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <fi_prof.txt > o
    mv o fi_prof.txt

    printf "======================= Comparing results ======================="
    DIFF=$(diff fi_grp.txt fi_prof.txt)

    rm fi_grp.txt
    rm fi_prof.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"\nTest failed\n"
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo $DIFF
        return 1
        
    else
        printf ${GREEN}"\nTest passed\n"
        return 0
    fi
}

# --------------------------------------------------------------------------

function testfdb() 
{   # Raquel Pinto - 92948
    echo "======================= Compiling ======================="
    m &> /dev/null
    #Grp
    echo "======================= Creating disk ======================="
    c &> /dev/null
    echo "======================= Formating disk ======================="
    f -b&> /dev/null
    
    echo -ne "\n******************************** Default disk ********************************\n" > Test_fdb.txt
    for i in $(seq 1 68)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 68)
    do
        echo -ne "fdb\n${i}\nq\n" | tt -q 1 -b -r 442-442 &> /dev/null
    done
        
    s >> Test_fdb.txt

    echo -ne "fdb\n70\nq\n" | tt -q 1 -b -r 442-442 &> /dev/null
     
    echo -ne "*****************************************************************\n" >> Test_fdb.txt
    s >> Test_fdb.txt

    #Bin
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo -ne "\n******************************** Default disk ********************************\n" > Test_fdb_Bin.txt
    for i in $(seq 1 68)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 68)
    do
        echo -ne "fdb\n${i}\nq\n" | tt -b -q 1 -p 0-0  &> /dev/null
    done
    
    s >> Test_fdb_Bin.txt

    echo -ne "fdb\n70\nq\n" | tt -b -q 1 -p 0-0  &> /dev/null
    echo -ne "*****************************************************************\n" >> Test_fdb_Bin.txt
    s >> Test_fdb_Bin.txt

    #Grp
    echo -ne "\n******************************** Disk 50 ********************************\n" >> Test_fdb.txt
    echo "======================= Creating disk 50 ======================="
    c 50 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b&> /dev/null

    for i in $(seq 1 45)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 45)
    do
        echo -ne "fdb\n${i}\nq\n" | tt -q 1 -b -r 442-442 &> /dev/null
    done
        
    s >> Test_fdb.txt

    echo -ne "fdb\n46\nq\n" | tt -q 1 -b -r 442-442 &> /dev/null
     
    echo -ne "*****************************************************************\n" >> Test_fdb.txt
    s >> Test_fdb.txt
   
    #Bin
    echo -ne "\n******************************** Disk 50 ********************************\n" >> Test_fdb_Bin.txt
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    for i in $(seq 1 45)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 45)
    do
        echo -ne "fdb\n${i}\nq\n" | tt -b -q 1 -p 0-0  &> /dev/null
    done
    
    s >> Test_fdb_Bin.txt

    echo -ne "fdb\n46\nq\n" | tt -b -q 1 -p 0-0  &> /dev/null
    echo -ne "*****************************************************************\n" >> Test_fdb_Bin.txt
    s >> Test_fdb_Bin.txt
    
    #Grp
    echo -ne "\n******************************** Disk 700 ********************************\n" >> Test_fdb.txt
    echo "======================= Creating disk 700 ======================="
    c 700 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b&> /dev/null

    for i in $(seq 1 68)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 68)
    do
        echo -ne "fdb\n${i}\nq\n" | tt -q 1 -b -r 442-442 &> /dev/null
    done
        
    s >> Test_fdb.txt

    echo -ne "fdb\n69\nq\n" | tt -q 1 -b -r 442-442 &> /dev/null
     
    echo -ne "*****************************************************************\n" >> Test_fdb.txt
    s >> Test_fdb.txt
   
    #Bin
    echo -ne "\n******************************** Disk 700 ********************************\n" >> Test_fdb_Bin.txt
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    for i in $(seq 1 68)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 68)
    do
        echo -ne "fdb\n${i}\nq\n" | tt -b -q 1 -p 0-0  &> /dev/null
    done
    
    s >> Test_fdb_Bin.txt

    echo -ne "fdb\n69\nq\n" | tt -b -q 1 -p 0-0  &> /dev/null
    echo -ne "*****************************************************************\n" >> Test_fdb_Bin.txt
    s >> Test_fdb_Bin.txt

    #comparing results
    echo "======================= Comparing results for full cache ======================="
    DIFF=$(diff Test_fdb.txt Test_fdb_Bin.txt)
        
    
    rm Test_fdb.txt
    rm Test_fdb_Bin.txt
    
    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------

function testrrc()
{
   # Hugo Leal - 93059

    # grp call
    echo "======================= Semi_Full Reference Table from start to finish ======================="
    m > tmp
    c &> tmp
    f -b > tmp

    echo "======================= Allocating Data blocks ======================="

    for i in $(seq 1 68)
    do
        ttadb -b > tmp
    done

    echo "======================= Freeing Data blocks ======================="

    for i in $(seq 48 68)
    do
        ttfdb ${i} -b > tmp
    done

    echo "======================= Replenishing Cache ======================="
    echo -ne "rrc\nq\n" | tt -b -r 443-443 -q 1 > tmp    
    s -r 996-999 >> grp_rrc
    s >>  grp_rrc


    c &> tmp
    f -b > tmp

    ## bin call
    c &> tmp
    f -b > tmp

    echo "======================= Allocating Data blocks ======================="

    for i in $(seq 1 68)
    do
        ttadb -b > tmp
    done

    echo "======================= Freeing Data blocks ======================="

    for i in $(seq 48 68)
    do
        ttfdb ${i} -b > tmp
    done

    echo "======================= Replenishing Cache ======================="
    ttrrc -b > tmp

    s -r 996-999 >> str_rrc
    s >> str_rrc

    echo "======================= Full Reference Table from start to finish ======================="

    c &> tmp
    f -b > tmp

    echo "======================= Allocating Data blocks ======================="

    for i in $(seq 1 68)
    do
    ttadb -b > tmp
    done

    echo "======================= Freeing Data blocks ======================="
    for i in $(seq 1 68)
    do
        ttfdb ${i} -b > tmp
    done
    
    echo "======================= Replenishing Cache ======================="
    #ttrrc
    echo -ne "rrc\nq\n" | tt -b -r 443-443 -q 1 > tmp 

    s -r 996-999 >> grp_rrc
    s >> grp_rrc


    c &> tmp
    f -b > tmp

    echo "======================= Allocating Data blocks ======================="

    for i in $(seq 1 68)
    do
    ttadb -b > tmp
    done

    echo "======================= Freeing Data blocks ======================="
    for i in $(seq 1 68)
    do
        ttfdb ${i} -b > tmp
    done

    echo "======================= Replenishing Cache ======================="
    ttrrc -b > tmp

    s -r 996-999 >> str_rrc
    s >> str_rrc

    echo "======================= Small Disk replenish ======================="

    c 50 &> tmp
    f -b > tmp

    echo "======================= Allocating Data blocks ======================="

    for i in $(seq 1 30)
    do
    ttadb -b > tmp
    done

    echo "======================= Freeing Data blocks ======================="   
    for i in $(seq 1 30)
    do
        ttfdb ${i} -b > tmp
    done
    echo "======================= Replenishing Cache ======================="
    echo -ne "rrc\nq\n" | tt -b -r 443-443 -q 1 > tmp 
    s >> grp_rrc
    
    c 50 &> tmp
    f -b > tmp
    echo "======================= Allocating Data blocks ======================="

    for i in $(seq 1 30)
    do
    ttadb -b > tmp
    done

    echo "======================= Freeing Data blocks ======================="
    for i in $(seq 1 30)
    do
        ttfdb ${i} -b &> tmp
    done

    ttrrc -b &> tmp

    s >> str_rrc

    echo "======================= Comparing results ======================="
    DIFF=$(diff grp_rrc str_rrc)
    rm tmp
    rm grp_rrc
    rm str_rrc
    
    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi

}

# --------------------------------------------------------------------------

function testdic()
{
    # Rodrigo Martins 93264
    echo "teste" > grp_dic
    echo "teste" > bin_dic

    dic_Test(){

        printf "======================= Testing disk with size $1 =======================\n"$NC

        c $1 &> /dev/null

        f -b &> /dev/null

        for i in $(seq 1 7)
        do
            for i in $(seq 1 68)
            do
                echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 > /dev/null
            done

                
            for i in $(seq 1 68)
            do
                echo -ne "fdb\n${i}\nq\n" | tt -b -q 1 -p 0-0 > /dev/null
            done

            echo -ne "dic\nq\n" | tt -b -q 1 -p 0-0 -r 444-444 > /dev/null

            printf "\n\nSuperblock\n" >> grp_dic
            s >> grp_dic
            printf "\nReference Table\n" >> grp_dic
            s -r $2-$3 >> grp_dic

        done

        f -b &> /dev/null

        for i in $(seq 1 7)
        do
            for i in $(seq 1 68)
            do
                echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 > /dev/null
            done

                
            for i in $(seq 1 68)
            do
                echo -ne "fdb\n${i}\nq\n" | tt -b -q 1 -p 0-0 > /dev/null
            done

            echo -ne "dic\nq\n" | tt -b -q 1 -p 0-0 > /dev/null

            printf "\n\nSuperblock\n" >> bin_dic
            s >> bin_dic
            printf "\nReference Table\n" >> bin_dic
            s -r $2-$3 >> bin_dic

        done
    }


    flag=0


    echo "======================= Compiling ======================="
    m &> /dev/null
    dic_Test 75 74 74
    dic_Test 100 99 99 
    dic_Test 125 124 124
    dic_Test 500 498 499
    dic_Test 1000 996 999
    dic_Test 1500 1494 1499

    DIFF+=$(diff grp_dic bin_dic)

    if [ "$DIFF" != "" ]
        then
            flag=1
            printf ${RED}"Test failed\n"${NC}
            cp grp_dic grp_dic_disk$1
            cp bin_dic bin_dic_disk$1
            printf ${RED}"Test failed\n"
            printf "\nDifferences between the grp version and bin version:\n\n"
            echo "$DIFF" 
        else
            printf ${GREEN}"Test passed\n"${NC}
            
        fi
    
    rm grp_dic
    rm bin_dic
    return $flag

}

# --------------------------------------------------------------------------

function testgfb() 
{   # Raquel Pinto - 92948
    echo "======================= Compiling ======================="
    m &> /dev/null
    #Grp
    echo "======================= Creating disk ======================="
    c &> /dev/null
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    echo -ne "\n******************************** Default disk ********************************\n" > Test_gfb.txt
   
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    #Direct
    echo -ne "******************************** Direct addressing ********************************\n" >> Test_gfb.txt
    echo -ne "wfb\n1\n2\n33\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "gfb\n1\n2\nq\n" | tt -q 1 -b -r 301-301 &> /dev/null
    s >> Test_gfb.txt
    s -i 1 >> Test_gfb.txt
    s -x 6 >> Test_gfb.txt
    
    #Indirect
    echo -ne "wfb\n1\n4\n44\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "gfb\n1\n4\nq\n" | tt -q 1 -b -r 301-301 &> /dev/null
    echo -ne "\n******************************** Indirect addressing ********************************\n" >> Test_gfb.txt
    s >> Test_gfb.txt
    s -i 1 >> Test_gfb.txt
    s -r 7 >> Test_gfb.txt
    s -x 8 >> Test_gfb.txt

    #Double Indirect
    echo -ne "wfb\n1\n1000\n55\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "gfb\n1\n1000\nq\n" | tt -q 1 -b -r 301-301 &> /dev/null
    echo -ne "\n******************************** Double Indirect addressing ********************************\n" >> Test_gfb.txt
    s >>Test_gfb.txt
    s -i 1 >> Test_gfb.txt
    s -r 9 >> Test_gfb.txt
    s -r 10 >> Test_gfb.txt
    s -x 11 >> Test_gfb.txt
    
    #Bin
    echo "======================= Formating binary disk ======================="
    f -b &> /dev/null
    echo -ne "\n******************************** Default disk ********************************\n" > Test_gfb_Bin.txt
   
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    
    #Direct_Test
    echo -ne "******************************** Direct addressing ********************************\n" >> Test_gfb_Bin.txt
    echo -ne "wfb\n1\n2\n33\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "gfb\n1\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    s >> Test_gfb_Bin.txt
    s -i 1 >> Test_gfb_Bin.txt
    s -x 6 >> Test_gfb_Bin.txt

    #Indirect_Test
    echo -ne "wfb\n1\n4\n44\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "gfb\n1\n4\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "\n******************************** Indirect addressing ********************************\n" >> Test_gfb_Bin.txt
    s >> Test_gfb_Bin.txt
    s -i 1 >> Test_gfb_Bin.txt
    s -r 7 >> Test_gfb_Bin.txt
    s -x 8 >> Test_gfb_Bin.txt

    #Double_Indirect_Test
    echo -ne "wfb\n1\n1000\n55\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "gfb\n1\n1000\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "\n******************************** Double Indirect addressing ********************************\n" >> Test_gfb_Bin.txt
    s >> Test_gfb_Bin.txt
    s -i 1 >> Test_gfb_Bin.txt
    s -r 9 >> Test_gfb_Bin.txt
    s -r 10 >> Test_gfb_Bin.txt
    s -x 11 >> Test_gfb_Bin.txt

    #Grp
    echo "======================= intensive test =======================" >> Test_gfb.txt
    f -b &> /dev/null
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    for i in $(seq 1 800)
    do
        echo -ne "wfb\n1\n$i\n44\nq\n" | tt -b -q 1 -p 0-0&> /dev/null
        echo -ne "gfb\n1\n$i\nq\n" | tt -q 1 -b -r 301-301 &> /dev/null
    done
    s >> Test_gfb.txt
    s -i 1 >> Test_gfb.txt
    for i in $(seq 5 810)
    do
        s -x $i >> Test_gfb.txt
    done

    #Bin
    echo "======================= intensive test =======================" >> Test_gfb_Bin.txt
    f -b &> /dev/null
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    
    for i in $(seq 1 800)
    do
        echo -ne "wfb\n1\n$i\n44\nq\n" | tt -q 1 -b -p 0-0 &> /dev/null
        echo -ne "gfb\n1\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done
    s >> Test_gfb_Bin.txt
    s -i 1 >> Test_gfb_Bin.txt
    for i in $(seq 5 810)
    do
        s -x $i >> Test_gfb_Bin.txt
    done

    #Grp
    echo -ne "\n******************************** Disk 50 (intensive test) ********************************\n" >> Test_gfb.txt
    echo "======================= Creating disk 50 ======================="
    c 50 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
   
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    for i in $(seq 1 45)
    do
        echo -ne "wfb\n1\n$i\n44\nq\n" | tt -b -q 1 -p 0-0&> /dev/null
        echo -ne "gfb\n1\n$i\nq\n" | tt -q 1 -b -r 301-301 &> /dev/null
    done
    s >> Test_gfb.txt
    s -i 1 >> Test_gfb.txt
    for i in $(seq 5 45)
    do
        s -x $i >> Test_gfb.txt
    done

    #Bin
    f -b &> /dev/null
    echo -ne "\n******************************** Disk 50 (intensive test) ********************************\n" >> Test_gfb_Bin.txt
   
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    
    for i in $(seq 1 45)
    do
        echo -ne "wfb\n1\n$i\n44\nq\n" | tt -q 1 -b -p 0-0 &> /dev/null
        echo -ne "gfb\n1\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done
    s >> Test_gfb_Bin.txt
    s -i 1 >> Test_gfb_Bin.txt
    for i in $(seq 5 45)
    do
        s -x $i >> Test_gfb_Bin.txt
    done

    #Grp
    echo -ne "\n******************************** Disk 350 (intensive test) ********************************\n" >> Test_gfb.txt
    echo "======================= Creating disk 350 ======================="
    c 350 &> /dev/null
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
   
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    for i in $(seq 1 320)
    do
        echo -ne "wfb\n1\n$i\n44\nq\n" | tt -b -q 1 -p 0-0&> /dev/null
        echo -ne "gfb\n1\n$i\nq\n" | tt -q 1 -b -r 301-301 &> /dev/null
    done
    s >> Test_gfb.txt
    s -i 1 >> Test_gfb.txt
    for i in $(seq 5 325)
    do
        s -x $i >> Test_gfb.txt
    done
    
    #Bin
    f -b &> /dev/null
    echo -ne "\n******************************** Disk 350 (intensive test) ********************************\n" >> Test_gfb_Bin.txt
   
    echo -ne "ai\n1\n666\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    
    for i in $(seq 1 320)
    do
        echo -ne "wfb\n1\n$i\n44\nq\n" | tt -q 1 -b -p 0-0 &> /dev/null
        echo -ne "gfb\n1\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done
    s >> Test_gfb_Bin.txt
    s -i 1 >> Test_gfb_Bin.txt
    for i in $(seq 5 325)
    do
        s -x $i >> Test_gfb_Bin.txt
    done
    
    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <Test_gfb.txt > o
    mv o Test_gfb.txt

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <Test_gfb_Bin.txt > o
    mv o Test_gfb_Bin.txt

    #comparing results
    echo "======================= Comparing results for direct addressing ======================="
    DIFF=$(diff Test_gfb.txt Test_gfb_Bin.txt)
    
    
    rm Test_gfb.txt
    rm Test_gfb_Bin.txt
    
    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------
# Maria Cunha 93089
function testafb()
{
    i1_SIZE=$((3*256))
    echo "======================= Compiling ======================="
    m > /dev/null
    echo "======================= Using disk with size 1000 ======================="
    #TEST 1
    printf "======================= Creating and formating disk for 1st test ======================="
    c &> /dev/null
    f -b &> /dev/null
    printf "\n======================= Testing allocing file blocks from 254 to 300 =======================\n"
    echo "+ Allocated inode nº $(ttai 2 -b ) " &> afb_grp.txt
    for fn in $(seq 254 300);do
        echo -ne "afb\n1\n$fn\nq\n" | tt -q 1 -p 0-0 -b -r 302-302 >> afb_grp.txt
        s -i 1  >> afb_grp.txt
        if ((4 + i1_SIZE*3 > fn));then
            #zona indireta
            if (( fn < 260))
            then
                s -r 6-6 >> afb_grp.txt
            else
                s -r 13-13 >> afb_grp.txt
            fi
        fi
    done
    c &> /dev/null
    f -b &> /dev/null
    printf "=======================Gerating binary verison ======================="
    echo "+ Allocated inode nº $(ttai 2 -b ) " &> afb_prof.txt
    for fn in $(seq 254 300);do
        ttafb "$fn" -in 1 -b >> afb_prof.txt
        s -i 1  >> afb_prof.txt
        if ((4 + i1_SIZE*3 > fn));then
            #zona indireta
            if (( fn < 260))
            then
                s -r 6-6 >> afb_prof.txt
            else
                s -r 13-13 >> afb_prof.txt
            fi
        fi
    done

    #TEST2
    printf "\n======================= Creating and formating disk for 2nd test ======================="
    c &> /dev/null
    f -b &> /dev/null
    printf "\n======================= Testing allocing file blocks from 769 to 800 =======================\n"
    printf "+ Allocated inode nº $(ttai 2 -b ) " &>> afb_grp.txt
    for fn in $(seq 769 800);do
        echo -ne "afb\n1\n$fn\nq\n" | tt -q 1 -p 0-0 -b -r 302-302 >> afb_grp.txt
        s -i 1  >> afb_grp.txt
        if ((4 + i1_SIZE > fn));then
            #zona indireta
            s -r 6-6 >> afb_grp.txt
        else
            #zona double indirect
            s -r 10-10 >> afb_grp.txt
            s -r 11-11 >> afb_grp.txt
        fi
    done
    c &> /dev/null
    f -b &> /dev/null
    printf "======================= Gerating binary verison ======================="
    printf "+ Allocated inode nº $(ttai 2 -b ) " &>> afb_prof.txt
    for fn in $(seq 769 800);do
        ttafb "$fn" -in 1 -b >> afb_prof.txt
        s -i 1  >> afb_prof.txt
        if ((4 + i1_SIZE > fn));then
            #zona indireta
            s -r 6-6 >> afb_prof.txt
        else
            #zona double indirect
            s -r 10-10 >> afb_prof.txt
            s -r 11-11 >> afb_prof.txt
        fi
    done

    #TEST 3 
    printf "\n======================= Creating and formating disk for 3rd test ======================="
    c &> /dev/null
    f -b &> /dev/null
    printf "\n======================= Testing allocing file blocks from 65539 to 65806 =======================\n"
    printf "+ Allocated inode nº $(ttai 2 -b ) " &>> afb_grp.txt
    for fn in $(seq 65539 65806);do
        echo -ne "afb\n1\n$fn\nq\n" | tt -q 1 -p 0-0 -b -r 302-302 >> afb_grp.txt
        s -i 1  >> afb_grp.txt
        if ((4 + i1_SIZE*3 <= fn));then
            #zona double indirect
            if ((fn == 65539));then
                s -r 6-7 >> afb_grp.txt
            elif ((fn < 65796));then
                s -r 6-6 >> afb_grp.txt
                s -r 9-9 >> afb_grp.txt
            else
                s -r 6-6 >> afb_grp.txt
                s -r 266-266 >> afb_grp.txt
            fi
        fi
    done
    c &> /dev/null
    f -b &> /dev/null
    printf "======================= Gerating binary verison ======================="
    printf "+ Allocated inode nº $(ttai 2 -b ) " &>> afb_prof.txt
    for fn in $(seq 65539 65806);do
        ttafb "$fn" -in 1 -b >> afb_prof.txt
        s -i 1  >> afb_prof.txt
        if ((4 + i1_SIZE*3 <= fn));then
            #zona double indirect
            if ((fn == 65539));then
                s -r 6-7 >> afb_prof.txt
            elif ((fn < 65796));then
                s -r 6-6 >> afb_prof.txt
                s -r 9-9 >> afb_prof.txt
            else
                s -r 6-6 >> afb_prof.txt
                s -r 266-266 >> afb_prof.txt
            fi
        fi
    done

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <afb_prof.txt > o
    mv o afb_prof.txt

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <afb_grp.txt > o
    mv o afb_grp.txt

    DIFF=$(diff afb_grp.txt afb_prof.txt)

    printf "\n======================= Comparing results =======================\n"

    rm afb_grp.txt
    rm afb_prof.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"
        return 0
    fi
}

# --------------------------------------------------------------------------

function testadb()
{
    #Lúcia Sousa 93086
    echo "======================= Compiling ======================="
    m &> /dev/null

    echo "======================= Creating disk with size 1000 ======================="
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo "======================= Alloc data blocks bin ======================="
    for i in $(seq 1 50)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    s >> bin_adb.txt
    
    echo "======================= Formating disk ======================="
    f &> /dev/null

    echo "======================= Alloc data blocks grp ======================="
    for i in $(seq 1 50)
    do
        echo -ne "adb\nq\n" | tt -q 1 -p 0-0 &> /dev/null
    done

    s >> grp_adb.txt

    echo "======================= Creating disk with size 350 ======================="
    c 350 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo "======================= Alloc data blocks bin ======================="
    for i in $(seq 1 50)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    s >> bin_adb.txt
    
    echo "======================= Formating disk ======================="
    f &> /dev/null

    echo "======================= Alloc data blocks grp ======================="
    for i in $(seq 1 50)
    do
        echo -ne "adb\nq\n" | tt -q 1 -p 0-0 &> /dev/null
    done

    s >> grp_adb.txt

    echo "======================= Creating disk with size 50 ======================="
    c 50 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo "======================= Alloc data blocks bin ======================="
    for i in $(seq 1 25)
    do
        echo -ne "adb\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    s >> bin_adb.txt
    
    echo "======================= Formating disk ======================="
    f &> /dev/null

    echo "======================= Alloc data blocks grp ======================="
    for i in $(seq 1 25)
    do
        echo -ne "adb\nq\n" | tt -q 1 -p 0-0 &> /dev/null
    done

    s >> grp_adb.txt

    echo "======================= Comparing results ======================="
    DIFF=$(diff grp_adb.txt bin_adb.txt)

    rm grp_adb.txt
    rm bin_adb.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi

}

# --------------------------------------------------------------------------

#Luísa Amaral, 93001
function testffb()
{
    echo "======================= Compiling ======================="
    m > temp.txt

    printf "======================= Testing grp ffb =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt


    ttai 1 -b > temp.txt

    for i in $(seq 1 900)
    do
        # echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -p 301-302
        echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -b > temp.txt
        # ttwfb $i -in 1 -v
    done

    echo -ne "ffb\n1\n800\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s > grptestffb.txt
    s -i 1 >> grptestffb.txt

    # ttffb 450 > temp.txt
    echo -ne "ffb\n1\n450\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt

    ttffb 2 > temp.txt #with tt tools

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt


    echo "======================= Creating disk with size 350 ======================="
    c 350 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    ttai 1 -b > temp.txt

    for i in $(seq 1 320)
    do
        # echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -p 301-302
        echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -b > temp.txt
        # ttwfb $i -in 1 -v
    done

    echo -ne "ffb\n1\n300\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt

    # ttffb 450 > temp.txt
    echo -ne "ffb\n1\n100\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt

    echo -ne "ffb\n1\n0\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt


    echo "======================= Creating disk with size 50 ======================="
    c 50 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    ttai 1 -b > temp.txt

    for i in $(seq 1 40)
    do
        # echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -p 301-302
        echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -b > temp.txt
        # ttwfb $i -in 1 -v
    done

    echo -ne "ffb\n1\n46\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt

    # ttffb 450 > temp.txt
    echo -ne "ffb\n1\n20\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt

    echo -ne "ffb\n1\n4\nq\n" | tt -q 1 -b -r 303-303 > temp.txt

    s >> grptestffb.txt
    s -i 1 >> grptestffb.txt



    # with bin
    printf "======================= Testing bin ffb =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    ttai 1 -b > temp.txt

    for i in $(seq 1 900)
    do
        # echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -b -r 332-332
        ttwfb $i -in 1 -b > temp.txt
    done

    echo -ne "ffb\n1\n800\nq\n" | tt -q 1 -b > temp.txt

    s > bintestffb.txt
    s -i 1 >> bintestffb.txt

    echo -ne "ffb\n1\n450\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    # ttffb 2 -b > temp.txt
    echo -ne "ffb\n1\n2\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt


    echo "======================= Creating disk with size 350 ======================="
    c 350 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    ttai 1 -b > temp.txt

    for i in $(seq 1 320)
    do
        # echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -p 301-302
        echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -b > temp.txt
        # ttwfb $i -in 1 -v
    done

    echo -ne "ffb\n1\n300\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    # ttffb 450 > temp.txt
    echo -ne "ffb\n1\n100\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    echo -ne "ffb\n1\n0\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    echo "======================= Creating disk with size 50 ======================="
    c 50 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    ttai 1 -b > temp.txt

    for i in $(seq 1 40)
    do
        # echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -p 301-302
        echo -ne "wfb\n1\n$i\n55\nq\n" | tt -q 1 -b > temp.txt
        # ttwfb $i -in 1 -v
    done

    echo -ne "ffb\n1\n46\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    # ttffb 450 > temp.txt
    echo -ne "ffb\n1\n20\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    echo -ne "ffb\n1\n4\nq\n" | tt -q 1 -b > temp.txt

    s >> bintestffb.txt
    s -i 1 >> bintestffb.txt

    printf "======================= Comparing results =======================\n"

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <grptestffb.txt > o
    mv o grptestffb.txt

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <bintestffb.txt > o
    mv o bintestffb.txt

    DIFF=$(diff grptestffb.txt bintestffb.txt)
    
    rm grptestffb.txt
    rm bintestffb.txt
    rm temp.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi

}

# --------------------------------------------------------------------------

function testrfb()
{
     # Hugo Leal - 93059
    c &> /dev/null
    f -b -p 0-0 &> /dev/null
    
    echo "======================= Allocating inode ======================="
    
    for i in $(seq 1 63)
    do
        local inode=$(ttai 1 -b)
        for j in $(seq 1 10)
        do
            ttwfb ${j} -in ${i} -pt 33 -b >> temp
            ttrfb ${j} -in ${i} >> grp_out_rfb
        done        
    done
    
    s >> grp_out_rfb

    # bin
    c &> /dev/null
    f -b -p 0-0 &> /dev/null
    
    echo "======================= Allocating inode ======================="
       
     
    for i in $(seq 1 63)
    do
        local inode=$(ttai 1 -b)
        for j in $(seq 1 10)
        do
            ttwfb ${j} -in ${i} -pt 33 -b >> temp
            ttrfb ${j} -in ${i} -b >> bin_out_rfb
        done        
    done
    
    s >> bin_out_rfb

    echo "======================= Smaller Disk ======================="

    c 50 &> /dev/null
    f -b > /dev/null
    
    echo "======================= Alloccating inode ======================="
    
    for i in $(seq 1 31)
    do
        local inode=$(ttai 1 -b)
        ttwfb 1 -in ${i} -pt 33 -b >> temp
        ttrfb 1 -in ${i} >> grp_out_rfb       
    done
    
    s >> grp_out_rfb

    # bin
    c 50 &> /dev/null
    f -b -p 0-0 &> /dev/null
    
    echo "======================= Alloccating inode ======================="
       
     
    for i in $(seq 1 31)
    do
        local inode=$(ttai 1 -b)
        ttwfb 1 -in ${i} -pt 33 -b >> temp
        ttrfb 1 -in ${i} -b >> bin_out_rfb      
    done
    
    s >> bin_out_rfb


    echo "======================= Comparing results ======================="
    DIFF=$(diff grp_out_rfb bin_out_rfb)

    
    rm grp_out_rfb 
    rm bin_out_rfb

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------

function testwfb()
{
    # Rodrigo Martins 93264

    twfb(){
        
        printf "======================= Creating disk with size $1 =======================\n"${NC}
        c $1 &> /dev/null
        f -b > /dev/null

        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -p 0-0 > grp_wfb

        for i in $(seq 1 $2)
        do 
            echo -ne "wfb\n1\n$i\n3D\nq\n" | tt -b -q 1 -p 0-0 -r 332-332 >> grp_wfb
        done


        for i in $(seq $3 $4)
        do 
            s -x $i >> grp_wfb
        done

        s -i 1 >> grp_wfb

        
        # bin 

        f -b > /dev/null

        echo -ne "ai\n1\n777\nq\n" | tt -b -q 1 -p 0-0 > bin_wfb

        for i in $(seq 1 $2)
        do 
            echo -ne "wfb\n1\n$i\n3D\nq\n" | tt -b -q 1 -p 0-0 >> bin_wfb
        done


        for i in $(seq $3 $4)
        do 
            s -x $i >> bin_wfb
        done

        s -i 1 >> bin_wfb

        RED='\033[0;31m'
        GREEN='\033[0;32m' 
        NC='\033[0m' # No Color


        #remove the date in the files
        while read -r line
        do
            [[ ! $line =~ "atime" ]] && echo "$line"
        done <grp_wfb > o
        mv o grp_wfb

        while read -r line
        do
            [[ ! $line =~ "atime" ]] && echo "$line"
        done <bin_wfb > o
        mv o bin_wfb

        DIFF=$(diff grp_wfb bin_wfb)

        if [ "$DIFF" != "" ]
        then
            flag=1
            printf ${RED}"Test failed\n"$NC

            cp grp_wfb grp_wfb_disk$1
            cp bin_wfb bin_wfb_disk$1
            printf ${RED}"Test failed\n"
            printf "\nDifferences between the grp version and bin version:\n\n"
            echo "$DIFF" 
        else
            printf ${GREEN}"Test passed\n"$NC
        fi
    }

    flag=0

    echo "======================= Compiling ======================="
    m > /dev/null
    # diskSize datablockAmount start end 
    twfb 50 40 4 41
    twfb 100 90 4 94
    twfb 1000 200 6 206
    twfb 1500 200 8 208

    rm grp_wfb
    rm bin_wfb

    return $flag

}

# --------------------------------------------------------------------------

function testgde() 
{   # Raquel Pinto - 92948
    echo "======================= Compiling ======================="
    m &> /dev/null
    #Grp
    echo "======================= Creating disk ======================="
    c &> /dev/null
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    echo "======================= Default disk ======================="
   
    for i in $(seq 1 63)
    do
        echo -ne "ai\n2\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 63)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "gde\n0\nz$i\nq\n" | tt -q 1 -p 0-0 -b -r 201-201 >> Test_gde.txt
    done

    #Bin
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    echo "======================= Default disk ======================="
   
    for i in $(seq 1 63)
    do
        echo -ne "ai\n2\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 63)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "gde\n0\nz$i\nq\n" | tt -b -q 1 -p 0-0 >> Test_gde_Bin.txt
    done

    #Grp
    echo "======================= Disk 50 ======================="
    echo "======================= Creating disk 50 ======================="
    c 50 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
   
    for i in $(seq 1 31)
    do
        echo -ne "ai\n2\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 31)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "gde\n0\nz$i\nq\n" | tt -q 1 -p 0-0 -b -r 201-201 >> Test_gde.txt
    done

    #Bin
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    echo "======================= Disk 50 ======================="
    
    for i in $(seq 1 31)
    do
        echo -ne "ai\n2\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 31)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "gde\n0\nz$i\nq\n" | tt -b -q 1 -p 0-0 >> Test_gde_Bin.txt
    done

    
    #Grp
    echo "======================= Disk 650 ======================="
    echo "======================= Creating disk 650 ======================="
    c 650 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
   
    for i in $(seq 1 63)
    do
        echo -ne "ai\n2\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 63)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "gde\n0\nz$i\nq\n" | tt -q 1 -p 0-0 -b -r 201-201>> Test_gde.txt
    done

    #Bin
    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    echo "======================= Disk 650 ======================="
    
    for i in $(seq 1 63)
    do
        echo -ne "ai\n2\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 1 63)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "gde\n0\nz$i\nq\n" | tt -b -q 1 -p 0-0 >> Test_gde_Bin.txt
    done

    echo "======================= Comparing results ======================="
    DIFF=$(diff Test_gde.txt Test_gde_Bin.txt)

    rm Test_gde.txt
    rm Test_gde_Bin.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
    fi
}

# --------------------------------------------------------------------------

function testade()
{
    # Hugo Leal - 93059
    c &> temp.txt
    f -b -p 0-0 &> temp.txt
    
    echo "======================= Allocating inode ======================="
    local inode=$(ttai 2 -b)
    echo "======================= Allocating direntries ======================="
    for i in $(seq 1 2300)
    do
        
        #ttade $inode teste${i} 2 &> temp.txt &> /dev/null
        echo -ne "ade\n$inode\nteste${i}\n2\nq\n"  | tt -q 1 -g &> /dev/null
    done

    s -d 6-$(((2300/16)+7))>> grp_out
    s >> grp_out

    # bin
    c &> temp.txt
    f -b -p 0-0 &> temp.txt
    
    echo "======================= Allocating inode ======================="
    local inode=$(ttai 2 -b)
    echo "======================= Allocating direntries ======================="
    for i in $(seq 1 2300)
    do
        ttade $inode teste${i} 2 -b &> temp.txt &> /dev/null
        #echo -ne "ade\n$inode\nteste${i}\n2\nq\n" | tt -q 2 -b
    done
    
    s -d 6-$(((2300/16)+7)) >> bin_out
    s >> bin_out

    echo "======================= Comparing results ======================="
    DIFF=$(diff grp_out bin_out)

    rm grp_out 
    rm bin_out
    rm temp.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------

function testdde()  #ver
{
    # Rodrigo Martins 93264
    
    echo "======================= Compiling ======================="
    m &> /dev/null

    echo "======================= Creating disk with size 1000 ======================="
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null

    echo "Testing function with 1 entry" > dde_grp

    # Creates 1 dir entry and then deletes it

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    # Data block before deleting entry 'z'
    s -d 5 >> dde_grp
    

    echo "======================= Deleting entry ======================="
    echo -ne "dde\n0\nz\nq\n" | tt -q 2 -p 0-0 

    echo -ne "\n\n" >> dde_grp
    # Data block after deleting entry 'z'
    s -d 5 >> dde_grp

    echo -ne "\n\n" >> dde_grp
    f -b &> /dev/null
    echo "Testing function with 2 entries" >> dde_grp

    # Creates 2 dir entry and then deletes the first one

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz1\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz2\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    # Data block before deleting entry 'z1'
    s -d 5 >> dde_grp

    echo "======================= Deleting entry ======================="
    echo -ne "dde\n0\nz1\nq\n" | tt -q 2 -p 0-0

    echo -ne "\n\n" >> dde_grp
    # Data block after deleting entry 'z1'
    s -d 5 >> dde_grp

    f -b &> /dev/null
    # Creates 2 dir entry and then deletes the last one

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz1\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz2\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    # Data block before deleting entry 'z2'
    s -d 5 >> dde_grp

    echo "======================= Deleting entry ======================="
    echo -ne "dde\n0\nz2\nq\n" | tt -q 2 -p 0-0

    echo -ne "\n\n" >> dde_grp
    # Data block after deleting entry 'z2'
    s -d 5 >> dde_grp

    f -b &> /dev/null

    # Creating enough dir entries to fill more than 1 data block to test if function frees empty data block

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done


    for i in $(seq 1 63)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo -ne "\n\n\n\n show Block 10 as dir entries: \n\n" >> dde_grp
    echo -ne "sb\nd\n10\nq\n" | tt -q 1 -p 0-0 >> dde_grp
    echo -ne "\n\n\n\n show Block 9 as References: \n\n" >> dde_grp
    echo -ne "sb\nr\n9\nq\n" | tt -q 1 -p 0-0 >> dde_grp
    echo -ne "\n\n\n\n Inode: \n\n" >> dde_grp
    echo -ne "sb\ni\n1\nq\n" | tt -q 1 -p 0-0 >> dde_grp


    echo -ne "\n\n\n\n Delete last entrie: \n\n" >> dde_grp
    echo -ne "dde\n0\nz63\nq\n" | tt -q 1 -p 0-0 >> dde_grp
    echo -ne "\n\n\n\n show Block 10 as dir entries: \n\n" >> dde_grp
    echo -ne "sb\nd\n10\nq\n" | tt -q 1 -p 0-0 >> dde_grp
    echo -ne "\n\n\n\n show Block 9 as References: \n\n" >> dde_grp
    echo -ne "sb\nr\n9\nq\n" | tt -q 1 -p 0-0 >> dde_grp
    echo -ne "\n\n\n\n Inode: \n\n" >> dde_grp
    echo -ne "sb\ni\n1\nq\n" | tt -q 1 -p 0-0 >> dde_grp

    f -b &> /dev/null

    echo -ne "ai\n2\n777\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    for i in $(seq 1 15760)
    do
        echo -ne "ade\n1\nz$i\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo -ne "dde\n1\nz1\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz12351\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz12350\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz12349\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz12348\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz37\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp

    #zona duplamente indireta
    echo -ne "dde\n1\nz15754\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15753\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15749\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15747\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15752\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15748\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15746\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15750\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15745\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    echo -ne "dde\n1\nz15751\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp

    for i in $(seq 10000 12000)
    do
        echo -ne "dde\n1\nz$i\nq\n" | tt -b -q 1 -r 203-203 -p 0-0 >> dde_grp
    done


    for i in $(seq 6 905)
    do
        s -d $i >> dde_grp
    done

    s >> dde_grp

    f -b &> /dev/null

    echo "======================= Using bin for compare ======================="
    # Bin version for compare

    echo "Testing function with 1 entry" > dde_prof

    # Creates 1 dir entry and then deletes it

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    # Data block before deleting entry 'z'
    s -d 5 >> dde_prof
    

    echo "======================= Deleting entry ======================="
    echo -ne "dde\n0\nz\nq\n" | tt -b -q 1 -p 0-0

    echo -ne "\n\n" >> dde_prof
    # Data block after deleting entry 'z'
    s -d 5 >> dde_prof

    echo -ne "\n\n" >> dde_prof
    f -b &> /dev/null
    echo "Testing function with 2 entries" >> dde_prof

    # Creates 2 dir entry and then deletes the first one

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz1\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz2\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    # Data block before deleting entry 'z1'
    s -d 5 >> dde_prof

    echo "======================= Deleting entry ======================="
    echo -ne "dde\n0\nz1\nq\n" | tt -b -q 1 -p 0-0

    echo -ne "\n\n" >> dde_prof
    # Data block after deleting entry 'z1'
    s -d 5 >> dde_prof

    f -b &> /dev/null
    # Creates 2 dir entry and then deletes the last one

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz1\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nz2\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null

    # Data block before deleting entry 'z2'
    s -d 5 >> dde_prof

    echo "======================= Deleting entry ======================="
    echo -ne "dde\n0\nz2\nq\n" | tt -b -q 1 -p 0-0

    echo -ne "\n\n" >> dde_prof
    # Data block after deleting entry 'z2'
    s -d 5 >> dde_prof
    
    f -b &> /dev/null

    # Creating enough dir entries to fill more than 1 data block to test if function frees empty data block

    for i in $(seq 1 63)
    do
        echo -ne "ai\n1\n66\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done


    for i in $(seq 1 63)
    do
        echo -ne "ade\n0\nz$i\n$i\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo -ne "\n\n\n\n show Block 10 as dir entries: \n\n" >> dde_prof
    echo -ne "sb\nd\n10\nq\n" | tt -q 1 -p 0-0 >> dde_prof
    echo -ne "\n\n\n\n show Block 9 as References: \n\n" >> dde_prof
    echo -ne "sb\nr\n9\nq\n" | tt -q 1 -p 0-0 >> dde_prof
    echo -ne "\n\n\n\n Inode: \n\n" >> dde_prof
    echo -ne "sb\ni\n1\nq\n" | tt -q 1 -p 0-0 >> dde_prof

    
    echo -ne "\n\n\n\n Delete last entrie: \n\n" >> dde_prof
    echo -ne "dde\n0\nz63\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "\n\n\n\n show Block 10 as dir entries: \n\n" >> dde_prof
    echo -ne "sb\nd\n10\nq\n" | tt -q 1 -p 0-0 >> dde_prof
    echo -ne "\n\n\n\n show Block 9 as References: \n\n" >> dde_prof
    echo -ne "sb\nr\n9\nq\n" | tt -q 1 -p 0-0 >> dde_prof
    echo -ne "\n\n\n\n Inode: \n\n" >> dde_prof
    echo -ne "sb\ni\n1\nq\n" | tt -q 1 -p 0-0 >> dde_prof

    
    f -b &> /dev/null

    echo -ne "ai\n2\n777\nq\n" | tt -b -q 1 -p 0-0

    for i in $(seq 1 15760)
    do
        echo -ne "ade\n1\nz$i\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo -ne "dde\n1\nz1\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz12351\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz12350\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz12349\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz12348\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz37\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof

    #zona duplamente indireta
    echo -ne "dde\n1\nz15754\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15753\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15749\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15747\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15752\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15748\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15746\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15750\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15745\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    echo -ne "dde\n1\nz15751\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof

    for i in $(seq 10000 12000)
    do
        echo -ne "dde\n1\nz$i\nq\n" | tt -b -q 1 -p 0-0 >> dde_prof
    done


    for i in $(seq 6 905)
    do
        s -d $i >> dde_prof
    done

    s >> dde_prof

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <dde_grp > o
    mv o dde_grp

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <dde_prof > o
    mv o dde_prof

    DIFF=$(diff dde_grp dde_prof)
    rm dde_grp
    rm dde_prof
    
    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}

# --------------------------------------------------------------------------
# Maria Cunha 93089
function testrde()
{
    echo "======================= Compiling ======================="
    m > /dev/null
    echo "======================= Using disk with size 1000 ======================="
    printf "======================= Testing grp rde ======================="
    printf "\n======================= Creating disk and Formating it ======================="
    c &> /dev/null
    f -b &> /dev/null
    #allocing inode
    ttai 2 -b &> /dev/null
    #adding entries
    echo -ne "\n======================= Adding 100 entries ======================= " 
    for n in $(seq 0 100);do
        ttade 1 t"$n" $((n+2)) -b &> /dev/null
    done
    s -i 1 >> rde_grp.txt
    s -d 6-13 >> rde_grp.txt
    #renaming entries
    echo -ne "\n======================= Renaming entries ======================= " 
    for n in $(seq 0 100);do
        echo -ne "rde\n1\nt$n\nn$n\nq\n" | tt -q 1 -p 0-0 -r 204-204 &> /dev/null
        s -i 1 >> rde_grp.txt
        s -d 6-13 >> rde_grp.txt
    done
    
    #binary version
    printf "\n======================= Testing bin rde ======================="
    printf "\n======================= Creating disk and Formating it =======================\n"
    c &> /dev/null
    f -b &> /dev/null
    #allocing inode
    ttai 2 -b &> /dev/null
    #adding entries
    for n in $(seq 0 100);do
        ttade 1 t"$n" $((n+2)) -b &> /dev/null
    done
    s -i 1 >> rde_prof.txt
    s -d 6-13 >> rde_prof.txt
    
    #renaming entries
    for n in $(seq 0 100);do
        ttrde 1 t"$n" n"$n" -b &> /dev/null
        s -i 1 >> rde_prof.txt
        s -d 6-13 >> rde_prof.txt
    done

    echo "======================= Using disk with size 100 ======================="
    printf "======================= Testing grp rde ======================="
    printf "\n======================= Creating disk and Formating it ======================="
    c 100 &> /dev/null
    f -b &> /dev/null
    #allocing inode
    ttai 2 -b &> /dev/null
    #adding entries
    echo -ne "\n======================= Adding 20 entries ======================= " 
    for n in $(seq 0 20);do
        ttade 1 t"$n" $((n+2)) -b &> /dev/null
    done
    s -i 1 >> rde_grp.txt
    s -d 4-5 >> rde_grp.txt
    #renaming entries
    echo -ne "\n======================= Renaming entries ======================= " 
    for n in $(seq 0 20);do
        echo -ne "rde\n1\nt$n\nn$n\nq\n" | tt -q 1 -p 0-0 -r 204-204 &> /dev/null
        s -i 1 >> rde_grp.txt
        s -d 4-5 >> rde_grp.txt
    done
    
    #binary version
    printf "\n======================= Testing bin rde ======================="
    printf "\n======================= Creating disk and Formating it ======================="
    c 100 &> /dev/null
    f -b &> /dev/null
    #allocing inode
    ttai 2 -b &> /dev/null
    #adding entries
    for n in $(seq 0 20);do
        ttade 1 t"$n" $((n+2)) -b &> /dev/null
    done
    s -i 1 >> rde_prof.txt
    s -d 4-5 >> rde_prof.txt

    #renaming entries
    for n in $(seq 0 20);do
        ttrde 1 t"$n" n"$n" -b &> /dev/null
        s -i 1 >> rde_prof.txt
        s -d 4-5 >> rde_prof.txt
    done

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <rde_prof.txt > o
    mv o rde_prof.txt

    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <rde_grp.txt > o
    mv o rde_grp.txt
    DIFF=$(diff rde_grp.txt rde_prof.txt)
    rm rde_grp.txt
    rm rde_prof.txt
    echo -ne "\n======================= Comparing results ======================="
    if [ "$DIFF" != "" ]
    then
        printf ${RED}"\nTest failed\n"
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"\nTest passed\n"
        return 0
    fi
}

# --------------------------------------------------------------------------

#Luísa Amaral, 93001
function testcde()
{
    echo "======================= Compiling ======================="
    m > temp.txt

    printf "======================= Testing grp cde =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    lst="2 2"
    for arg in $lst; do
        ttai $arg -b > temp.txt
    done

    ttade 1 aaa 2 -b > temp.txt
    ttcde 0 > grptestcde.txt
    ttcde 1 >> grptestcde.txt
    ttcde 2 >> grptestcde.txt

    echo "======================= Creating disk with size 500 ======================="
    c 500 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    lst="2 2"
    for arg in $lst; do
        ttai $arg -b > temp.txt
    done

    ttade 1 aaa 2 -b > temp.txt
    ttcde 0 >> grptestcde.txt
    ttcde 1 >> grptestcde.txt
    ttcde 2 >> grptestcde.txt


    # with bin
    printf "======================= Testing bin cde =======================\n"
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> temp.txt
    echo "======================= Formating disk ======================="
    f -b > temp.txt

    for arg in $lst; do
        ttai $arg -b > temp.txt
    done

    ttade 1 aaa 2 -b > temp.txt
    ttcde 0 -b > bintestcde.txt
    ttcde 1 -b >> bintestcde.txt
    ttcde 2 -b >> bintestcde.txt

    echo "======================= Creating disk with size 500 ======================="
    c 500 &> temp.txt

    echo "======================= Formating disk ======================="
    f -b > temp.txt

    lst="2 2"
    for arg in $lst; do
        ttai $arg -b > temp.txt
    done

    ttade 1 aaa 2 -b > temp.txt
    ttcde 0 -b >> bintestcde.txt
    ttcde 1 -b >> bintestcde.txt
    ttcde 2 -b >> bintestcde.txt


    printf "======================= Comparing results =======================\n"

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <grptestcde.txt > o
    mv o grptestcde.txt

    #remove the date in the files
    while read -r line
    do
        [[ ! $line =~ "atime" ]] && echo "$line"
    done <bintestcde.txt > o
    mv o bintestcde.txt

    DIFF=$(diff grptestcde.txt bintestcde.txt)
    
    rm grptestcde.txt
    rm bintestcde.txt
    rm temp.txt
    
    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi

}

# --------------------------------------------------------------------------

function testtp()
{
    #Lúcia Sousa 93086
    echo "======================= Compiling ======================="
    m &> /dev/null

    echo "======================= Creating disk with size 1000 ======================="
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo "======================= Alloc inode bin ======================="
    for i in $(seq 1 25)
    do
        echo -ne "ai\n2\n755\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo "======================= Add dir entry bin ======================="
    echo -ne "ade\n0\naaa\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nbbb\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    for i in $(seq 1 20)
    do
        j=$((i+1))
        echo -ne "ade\n$i\na$i\n$j\nq\n"| tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 22 25)
    do
        echo -ne "ade\n2\nb$i\n$i\nq\n"| tt -b -q 1 -p 0-0 &> /dev/null
    done

    completepath="/aaa"
    for i in $(seq 1 20)
    do
        restpath="/a$i"
        completepath="$completepath$restpath"
        echo "======================= Traverse path $completepath -> group ======================="
        echo -ne "tp\n$completepath\nq\n" | tt -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath\nq\n" | tt -q 1 -p 0-0 >> grp_tp.txt
    done

    completepath="/bbb"
    for i in $(seq 22 25)
    do
        restpath="/b$i"
        echo "======================= Traverse path $completepath$restpath -> group ======================="
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -q 1 -p 0-0 >> grp_tp.txt
    done

    completepath="/aaa"
    for i in $(seq 1 20)
    do
        restpath="/a$i"
        completepath="$completepath$restpath"
        echo "======================= Traverse path $completepath -> bin ======================="
        echo -ne "tp\n$completepath\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath\nq\n" | tt -b -q 1 -p 0-0 >> bin_tp.txt
    done

    completepath="/bbb"
    for i in $(seq 22 25)
    do
        restpath="/b$i"
        echo "======================= Traverse path $completepath$restpath -> bin ======================="
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -b -q 1 -p 0-0 >> bin_tp.txt
    done

    echo "======================= Creating disk with size 350 ======================="
    c 350 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo "======================= Alloc inode bin ======================="
    for i in $(seq 1 25)
    do
        echo -ne "ai\n2\n755\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo "======================= Add dir entry bin ======================="
    echo -ne "ade\n0\naaa\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nbbb\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    for i in $(seq 1 20)
    do
        j=$((i+1))
        echo -ne "ade\n$i\na$i\n$j\nq\n"| tt -b -q 1 -p 0-0 &> /dev/null
    done

    for i in $(seq 22 25)
    do
        echo -ne "ade\n2\nb$i\n$i\nq\n"| tt -b -q 1 -p 0-0 &> /dev/null
    done

    completepath="/aaa"
    for i in $(seq 1 20)
    do
        restpath="/a$i"
        completepath="$completepath$restpath"
        echo "======================= Traverse path $completepath -> group ======================="
        echo -ne "tp\n$completepath\nq\n" | tt -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath\nq\n" | tt -q 1 -p 0-0 >> grp_tp.txt
    done

    completepath="/bbb"
    for i in $(seq 22 25)
    do
        restpath="/b$i"
        echo "======================= Traverse path $completepath$restpath -> group ======================="
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -q 1 -p 0-0 >> grp_tp.txt
    done

    completepath="/aaa"
    for i in $(seq 1 20)
    do
        restpath="/a$i"
        completepath="$completepath$restpath"
        echo "======================= Traverse path $completepath -> bin ======================="
        echo -ne "tp\n$completepath\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath\nq\n" | tt -b -q 1 -p 0-0 >> bin_tp.txt
    done

    completepath="/bbb"
    for i in $(seq 22 25)
    do
        restpath="/b$i"
        echo "======================= Traverse path $completepath$restpath -> bin ======================="
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -b -q 1 -p 0-0 >> bin_tp.txt
    done
    
    echo "======================= Creating disk with size 50 ======================="
    c 50 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b &> /dev/null
    
    echo "======================= Alloc inode bin ======================="
    for i in $(seq 1 25)
    do
        echo -ne "ai\n2\n755\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    done

    echo "======================= Add dir entry bin ======================="
    echo -ne "ade\n0\naaa\n1\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    echo -ne "ade\n0\nbbb\n2\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
    for i in $(seq 1 20)
    do
        j=$((i+1))
        echo -ne "ade\n$i\na$i\n$j\nq\n"| tt -b -q 1 -p 0-0 &> /dev/null
    done
    for i in $(seq 22 25)
    do
        echo -ne "ade\n2\nb$i\n$i\nq\n"| tt -b -q 1 -p 0-0 &> /dev/null
    done

    completepath="/aaa"
    for i in $(seq 1 20)
    do
        restpath="/a$i"
        completepath="$completepath$restpath"
        echo "======================= Traverse path $completepath -> group ======================="
        echo -ne "tp\n$completepath\nq\n" | tt -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath\nq\n" | tt -q 1 -p 0-0 >> grp_tp.txt
    done

    completepath="/bbb"
    for i in $(seq 22 25)
    do
        restpath="/b$i"
        echo "======================= Traverse path $completepath$restpath -> group ======================="
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -q 1 -p 0-0 >> grp_tp.txt
    done

    completepath="/aaa"
    for i in $(seq 1 20)
    do
        restpath="/a$i"
        completepath="$completepath$restpath"
        echo "======================= Traverse path $completepath -> bin ======================="
        echo -ne "tp\n$completepath\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath\nq\n" | tt -b -q 1 -p 0-0 >> bin_tp.txt
    done

    completepath="/bbb"
    for i in $(seq 22 25)
    do
        restpath="/b$i"
        echo "======================= Traverse path $completepath$restpath -> bin ======================="
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -b -q 1 -p 0-0 &> /dev/null
        echo -ne "tp\n$completepath$restpath\nq\n" | tt -b -q 1 -p 0-0 >> bin_tp.txt
    done

    echo "======================= Compare results ======================="
    DIFF=$(diff grp_tp.txt bin_tp.txt)

    rm grp_tp.txt
    rm bin_tp.txt

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"${NC}
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"${NC}
        return 0
    fi
}


# --------------------------------------------------------------------------

function testfrd(){
    #Rodrigo Martins 93264

    echo "======================= Compiling ======================="
    m > /dev/null

    printf "======================= Creating disk with size 50 =======================\n"
    c 50 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b -r 606-606 > /dev/null 
    
    s -d 3 > grp_frd


    printf "======================= Creating disk with size 1000 =======================\n"
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f -b -r 606-606 > /dev/null 
    
    s -d 5 >> grp_frd


    printf "======================= Creating disk with size 2000 =======================\n"
    c 2000 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b -r 606-606 > /dev/null 
    
    s -d 9 >> grp_frd


    printf "======================= Creating disk with size 3500 =======================\n"
    c 3500 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b -r 606-606 > /dev/null 
    
    s -d 15 >> grp_frd



    # bin

    printf "======================= Creating disk with size 50 =======================\n"
    c 50 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b > /dev/null 
    
    s -d 3 > bin_frd

   
    printf "======================= Creating disk with size 1000 =======================\n"
    c &> /dev/null

    echo "======================= Formating disk ======================="
    f -b > /dev/null 
    
    s -d 5 >> bin_frd

    
    printf "======================= Creating disk with size 2000 =======================\n"
    c 2000 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b > /dev/null 
    
    s -d 9 >> bin_frd


    printf "======================= Creating disk with size 3500 =======================\n"
    c 3500 &> /dev/null

    echo "======================= Formating disk ======================="
    f -b > /dev/null 
    
    s -d 15 >> bin_frd


    echo "======================= Comparing results ======================="
    DIFF=$(diff grp_frd bin_frd)

    rm bin_frd
    rm grp_frd

    if [ "$DIFF" != "" ]
    then
        printf ${RED}"Test failed\n"
        printf "\nDifferences between the grp version and bin version:\n\n"
        echo "$DIFF"
        return 1
        
    else
        printf ${GREEN}"Test passed\n"
        return 0
    fi
}
