#!/bin/sh

commitid=24ffe2020330b9267c9e959e3cf74ed5669ebfff

branches=`git branch -l | tr '*' ' '`

for b in $branches
do
    if [ "$b" != "00-beginning" ];then
        echo "==== $b ===="
        git checkout $b
        git cherry-pick -x $commitid
        git push origin $b
    fi
done
