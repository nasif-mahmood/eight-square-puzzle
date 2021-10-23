# Eight Square Puzzle In AI

This program can solve a given 8 square puzzle using different search methods

## Search Methods
* Depth-Limited Search
* Iterative Deepening Search
* A* Search (# of tiles in the wrong place)
* A* Search (Manhattan distance of tiles)

### Depth-Limited Search
Given a start state, the program will find the goal by creating a search tree built by moving the blank tile up, down, left, right to create nodes
If the goal is not found within a depth of 10, the program will return failure

### Iterative Deepening Search
Given a start state, the program will find the goal by creating trees of increasing depth and performing DFS on them until the goal state is reached or the depth exceeds 10

### A* Searches
Given a start state and heuristic, the program will use the heuristic value to find the optimal path to the goal (path with the least cost)

## Example

Start State
0 | 2 | 3
--|---|--
1 | 7 | 5
4 | 6 | 8

Goal State
1 | 2 | 3
--|---|--
4 | 0 | 5
6 | 7 | 8

Method - DLS
0 | 2 | 3
--|---|--
1 | 7 | 5
4 | 6 | 8

Down
1 | 2 | 3
--|---|--
0 | 7 | 5
4 | 6 | 8

Down
1 | 2 | 3
--|---|--
4 | 7 | 5
0 | 6 | 8

Right
1 | 2 | 3
--|---|--
4 | 7 | 5
6 | 0 | 8

Up
1 | 2 | 3
--|---|--
4 | 0 | 5
6 | 7 | 8 

Number of moves = 4
Number of states enqueued = 430
