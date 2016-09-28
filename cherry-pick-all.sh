#!/bin/sh

commitid=$1

branches="01-cf-push 02-scale-out 03-auto-recover 04-log-aggregation 05-session-cluster 06-metrics 07-zero-downtime-push"
for b in $branches
do
    echo "==== $b ===="
    git checkout $b
    git cherry-pick -x $commitid
    git push origin $b
done
git checkout 00-beginning
