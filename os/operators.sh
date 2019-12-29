#!/bin/bash

val=`expr 2 + 2` # Need spaces between operators & Expressions
echo $val

############################ ARITHEMETIC OPERATORS ###############################
a=10
b=20

echo `expr $a + $b` # will give 30
echo `expr $a - $b` # will give -10
echo `expr $a \* $b` # will give 200
echo `expr $b / $a` # will give 2
echo `expr $b % $a` # will give 0
a = $b # would assign value of b into a
echo $a

if [ $a == $b ]
then
   echo "a is equal to b"
fi

if [ $a != $b ]
then
   echo "a is not equal to b"
fi


############################# RELATIONAL OPERATORS ############################
# Specific to numeric values. Do not work for string values unless their value is numeric.
a=10
b=20

# EQUAL TO
if [ $a -eq $b ] 
then
   echo "$a -eq $b : a is equal to b"
else
   echo "$a -eq $b: a is not equal to b"
fi

# NOT EQUAL TO
if [ $a -ne $b ]
then
   echo "$a -ne $b: a is not equal to b"
else
   echo "$a -ne $b : a is equal to b"
fi

# GREATER THAN OR EQUAL TO
if [ $a -gt $b ]
then
   echo "$a -gt $b: a is greater than b"
else
   echo "$a -gt $b: a is not greater than b"
fi

# LESS THAN OR EQUAL TO
if [ $a -lt $b ]
then
   echo "$a -lt $b: a is less than b"
else
   echo "$a -lt $b: a is not less than b"
fi

# GREATER THAN OR EQUAL TO
if [ $a -ge $b ]
then
   echo "$a -ge $b: a is greater or  equal to b"
else
   echo "$a -ge $b: a is not greater or equal to b"
fi

# LESS THAN OR EQUAL TO
if [ $a -le $b ]
then
   echo "$a -le $b: a is less or  equal to b"
else
   echo "$a -le $b: a is not less or equal to b"
fi


############################# BOOLEAN OPERATORS ############################
: '
    !   - Logical Negation
    -o  - OR
    -a  - AND
'

a=10
b=20

# NOT 
if [ $a != $b ]
then
   echo "$a != $b : a is not equal to b"
else
   echo "$a != $b: a is equal to b"
fi

# AND
if [ $a -lt 100 -a $b -gt 15 ]
then
   echo "$a -lt 100 -a $b -gt 15 : returns true"
else
   echo "$a -lt 100 -a $b -gt 15 : returns false"
fi

# OR
if [ $a -lt 100 -o $b -gt 100 ]
then
   echo "$a -lt 100 -o $b -gt 100 : returns true"
else
   echo "$a -lt 100 -o $b -gt 100 : returns false"
fi

############################# STRING OPERATORS ############################
: '
    =   String Comparison
    !=  String Comparison
    -z  Checks if the given string operand size is zero. Returns true for zero.
    -n  Checks if str is not empty string. If empty, it returns false.
'

a="abc"
b="efg"

# CHECK IF TWO STRINGS ARE EQUAL
if [ $a = $b ]
then
   echo "$a = $b : a is equal to b"
else
   echo "$a = $b: a is not equal to b"
fi

# CHECK IF TWO STINGS ARE NOT EQUAL
if [ $a != $b ]
then
   echo "$a != $b : a is not equal to b"
else
   echo "$a != $b: a is equal to b"
fi

# CHECK IF A STRING LENGTH IS ZERO
if [ -z $a ]
then
   echo "-z $a : string length is zero"
else
   echo "-z $a : string length is not zero"
fi

# CHECK IF A STRING LENGTH IS NOT ZERO
if [ -n $a ]
then
   echo "-n $a : string length is not zero"
else
   echo "-n $a : string length is zero"
fi

# CHECK IF NOT EMPTY
if [ $a ]
then
   echo "$a : string is not empty"
else
   echo "$a : string is empty"
fi


############################# FILE TEST OPERATORS ############################
# Assume a variable - file
# file hold an existing file names test
    # test is of size 100 bytes
    # has RWX permissions

file="./scratch.sh"

# CHECK IF READABLE
if [ -r $file ]
then
   echo "File has read access"
else
   echo "File does not have read access"
fi

# CHECK IF WRITABLE
if [ -w $file ]
then
   echo "File has write permission"
else
   echo "File does not have write permission"
fi

# CHECK IF EXECUTABLE
# TODO: This shit doesn't work
if [ -x $file ]
then
   echo "File has execute permission"
else
   echo "File does not have execute permission"
fi

# CHECK IF - ORIDNARY FILE 
    # AS OPPOSED TO - DIRECTORY / SPECIAL
if [ -f $file ]
then
   echo "File is an ordinary file"
else
   echo "This is sepcial file"
fi

# CHECK IF DIRECTORY
if [ -d $file ]
then
   echo "File is a directory"
else
   echo "This is not a directory"
fi

# CHECK IF SIZE GREATE THAN 0
if [ -s $file ]
then
   echo "File size is Non Zero"
else
   echo "File size is zero"
fi

# CHECK IF IT EXISTS
    # IS TRUE EVEN IF ITS A DIRECTORY
if [ -e $file ]
then
   echo "File exists"
else
   echo "File does not exist"
fi

: '
    -b  block special file
    -c  character special file
    -d  directory
    -f  ordinary file
    -g  has its set group ID (SGID) bit set
    -k  has its sticky bit set
    -p  if file in a named pipe
    -t  if file descriptor is open and associated with a terminal
    -u  has its set user ID (SUID) bit set
    -r  readable
    -w  writable
    -x  executable
    -s  size greater than 0
    -e  exists (even if a directory)
'