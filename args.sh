#!/bin/bash


echo $$ # Process ID (PID) of the current shell

echo $! # The process number of the last background command.

echo $0 # Filename of the current script

echo $1 # 1st Argument
echo $2 # 2nd Argument

echo $# # Number of Arguments supplied to the script (not incl. 0)

echo $* # All the arguments are double quoted. If a script receives two arguments, $* is equivalent to $1 $2.

echo $@ # All the arguments are individually double quoted. If a script receives two arguments, $@ is equivalent to $1 $2.

echo $? # The exit status of the last command executed.


# $* and $@
    # There are special parameters that allow accessing all the command-line arguments at once. 
    # both will act the same unless they are enclosed in double quotes, ""
        # "$*" special parameter takes the entire list as one argument with spaces between
        # "$@" special parameter takes the entire list and separates it into separate arguments.

for TOKEN in $*
do
   echo $TOKEN
done