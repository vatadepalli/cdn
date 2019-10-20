#!/bin/bash
################################# IF ELSE #################################
a=10
b=20

#####   IF  FI   #####
if [ $a == $b ]
then
   echo "a is equal to b"
fi

#####   IF  ELSE FI   #####
if [ $a == $b ]
then
   echo "a is equal to b"
else
   echo "a is not equal to b"
fi

#####   IF  ELIF ELSE FI   #####
if [ $a == $b ]
then
   echo "a is equal to b"
elif [ $a -gt $b ]
then
   echo "a is greater than b"
elif [ $a -lt $b ]
then
   echo "a is less than b"
else
   echo "None of the condition met"
fi


################################ CASE ESAC #################################
##
##    There is no maximum number of patterns, but the minimum is one.
##    ;;  -   Indicates that the program flow should jump to the end of the entire case statement.


option="${1}" 
case ${option} in 
   -f) FILE="${2}" 
      echo "File name is $FILE"
      ;; 
   -d) DIR="${2}" 
      echo "Dir name is $DIR"
      ;; 
   *)  
      echo "`basename ${0}`:usage: [-f file] | [-d directory]" 
      exit 1 # Command to come out of the program with status 1
      ;; 
esac 