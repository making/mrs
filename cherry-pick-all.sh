#!/bin/sh

commitid=$1

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
