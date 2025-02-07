Run instructions
================

MazeSolver provides the main functionality of the program.
Please run MazeSolver and provide the file to read

Maze creates a simple maze instance and provides methods to import the Maze and print it



Solving a maze
==============

The idea here is to write a program to solve simple mazes. The mazes are given in 
a file and the program must read in the file, solve the maze and output the solution.

The program should be written to the following specification:

  - The program must be written in Java or C#
  - It can be assumed that the input file will always follow the guidelines provided in the "Maze input/output formats" section
    (no validation is necessary)
  - Maze outputs should be written to the console as per the "Maze input/output formats" guidelines
  - If there is no possible solution to the maze the output should indicate this somehow
  - Arbitrary sized mazes should be handled
  - The program should allow all valid movements as per the "Movement" guidelines
  - Name and location of the input file should be selectable at runtime

The emphasis should be on code readability and simplicity. Please provide the source code for your solution, including clear 
instructions on how to run.

A folder of sample mazes has been provided with this README file. The samples are all solveable, and your program should be able
to solve them within seconds. Be aware that your solution may be tested against other mazes, some of which are not solveable.

Good luck!
  
  
Maze input/output formats
=========================

The input is a maze description file in plain text.  
 1 - denotes walls
 0 - traversable passage way

INPUT:
<WIDTH> <HEIGHT><CR>
<START_X> <START_Y><CR>		(x,y) location of the start. (0,0) is upper left and (width-1,height-1) is lower right
<END_X> <END_Y><CR>		    (x,y) location of the end
<HEIGHT> rows where each row has <WIDTH> {0,1} integers space delimited

OUTPUT:
 the maze with a path from start to end
 walls marked by '#', passages marked by ' ', path marked by 'X', start/end marked by 'S'/'E'

Example file:  
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1

Example output:
##########
#SXX     #
# #X######
# #XX    #
# ##X# ###
# # X# # #
# # XX   #
# ###X####
# #  XXXE#
##########


Movement
========
Valid moves are N, S, E, W only, with no diagonal movement allowed. Moves in any of these directions will be blocked by maze walls, which are
identified as 1's in the input file. The edges of the grid should not be considered as walls unless specified, and allow for wrapping movement.

Example of horizontal wrapping:

10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1 
0 0 1 0 0 0 0 0 0 0 
0 0 1 0 0 1 1 1 1 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 1 0 1 0 1 1 1 
1 0 1 0 0 1 0 1 0 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 0 0 0 1 1 1 1 
1 0 1 0 0 0 0 0 0 1 
1 1 1 1 1 1 1 1 1 1 


##########
XS# XXXXXX
  # X#####
# # X    #
# ##X# ###
# # X# # #
# # X    #
# # X ####
# # XXXXE#
##########


Example of vertical wrapping:

10 10
1 1
8 8
1 0 1 1 1 1 1 1 1 1 
1 0 1 0 0 0 0 0 0 0 
1 0 1 0 1 1 1 1 1 1 
1 1 1 0 0 0 0 0 0 1 
1 0 1 1 0 1 0 1 1 1 
1 0 1 0 0 1 0 1 0 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 1 1 0 1 1 1 1 
1 0 0 0 0 0 0 0 0 1 
1 0 1 1 1 1 1 1 1 1 

#X########
#S#       
# # ######
###      #
# ## # ###
# #  # # #
# #      #
# ### ####
#XXXXXXXE#
#X########
