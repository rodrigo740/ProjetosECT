#!/bin/bash
source sofs20.sh

    cd build
    make
    cd ..

    cd bin
    ./createDisk /tmp/dsk 1000
    ./mksofs /tmp/dsk

    mkdir -p /tmp/mnt
    ./sofsmount /tmp/dsk /tmp/mnt

    cd /tmp/mnt
    
    for j in {1..100}
    do
        for i in {1..63}
        do
            touch z$i
        done

        for i in {1..63}
        do
            rm z$i
        done
    done

    mkdir dir1

    for i in {1..10}
    do
        touch z$i
    done
    


    for i in {1..20}
    do
        touch d$i
    done

    for i in {1..20}
    do
        rm d$i
    done

    rmdir dir1

    mkdir dir1

    touch a1
    touch a2
    
    cd dir1
    mkdir dir2

    for i in {1..20}
    do
        touch d$i
    done

    for i in {1..5}
    do
        rm d$i
    done

    cd dir2

    for i in {1..20}
    do
        touch d$i
    done

    cd ..
    cd ..

    rm a1
    rm a2

    mkdir dir3
    mkdir dir4
    mkdir dir5
    mkdir dir6

    ll

    mv dir3 ola1
    mv dir4 ola2
    mv dir5 ola3
    mv dir6 ola4

    ll

    echo "path "
    pwd
    cd dir3
    pwd
    cd ..
    cd dir4
    pwd

    mkdir dir7
    cd dir7
    pwd

    for i in {1..5}
    do
        mkdir aa$i
        cd aa$i
        pwd
        cd ..
        mv aa$i zz$i
        cd zz$i
        pwd
        cd ..
    done

    rmdir zz1
    rmdir zz4
    pwd

    ll zz1
    ll zz4
    
    mkdir i1
    cd i1
    mkdir i2
    cd i2
    mkdir i3

    for i in {1..20}
    do
        touch di$i
    done

    mkdir i4
    cd i4

    pwd

    cd ..

    for i in {1..20}
    do
        mv di$i ri$i
    done

    pwd
    ll


    
    cd /tmp
    fusermount -u mnt
    rmdir mnt

