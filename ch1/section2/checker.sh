#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

LINE=$(ls -l "$1"/*.in | wc -l)

for i in `seq 1 $LINE`;
	do
	python "$1/$1.py" "$1/$i.in" > "$1/py.out"
	DIFF=$(diff "$1/$i.out" "$1/py.out")
	if [ "$DIFF" != "" ] 
	then
		echo -e "${RED}************TEST CASE $i FAILED************${NC}"
		echo "---------Expected----------"
		cat "$1/$i.out"
		echo "---------Actual------------"
		cat "$1/py.out"
	else
		echo -e "${GREEN}************TEST CASE $i PASSED************${NC}"
	fi
done