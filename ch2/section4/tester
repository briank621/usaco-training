#!/bin/bash

LINE=$(ls -l "$1"/*.in | wc -l)

for i in `seq 1 $LINE`;
	do
		java -cp "$1" "$1" "$1/$i.in" > "$1/$i.out"
	done