#!/bin/bash

rm $1/$1.in
awk -v arg=$1 '/test/{x=arg"/"++i".in";next}{print > x;}' $1.test
javac $1/$1.java
./tester "$1"
