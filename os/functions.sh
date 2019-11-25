#!/bin/bash

# Functions enable you to break down the overall functionality of a script into smaller, logical subsections, which can then be called upon to perform their individual tasks when needed.

# Code Reuse


################## CREATING FUNCTIONS ##################

# Define your function here
Hello () {
   echo "Hello World"
}

# Invoke your function
Hello

################### PASS PARAMS TO FUNCTIONS ##################
Hello_Param(){
    echo "Hello World $1 $2"
}

Hello_Param Vijaya Aditya

################### RETURN VALUES FROM FUNCTIONS ##################
# If you execute an exit command from inside a function, its effect is not only to terminate execution of the function But also of the shell program that called the function.

Hello_Return () {
   echo "Hello World $1 $2"
   return 10
}

Hello_Return Banna Super

# Capture value returned by last command
ret=$?

echo "Returned value is: $ret"


################### NESTED FUNCTIONS ##################
number_one () {
   echo "This is the first function speaking..."
   number_two
}

number_two () {
   echo "This is now the second function speaking..."
}

# Calling function one.
number_one


################### FUNCTION CALL FROM PROMPT ##################
# You can put definitions for commonly used functions inside your .profile.
    # These definitions will be available whenever you log in.

# Alternatively, you can group the definitions in a file, say test.sh
    # and then execute the file in the current shell by typing 
        # test.sh

######################## REMOVE DEFINITON #######################

unset -f function_name

