import java.util.Arrays;

/**
 * This class will find the hueristic value of the given node
 */
public class Heuristic {

    public Heuristic() {

    }

    // Method to find heuristic 1 for A* (# of tiles in wrong position)
    public int heuristic1(Node root, Node goal) {

        int[][] state = root.getState();
        int[][] goalState = goal.getState();

        int heuristic = 0;

        // If current node is the goal, then its heuristic will be 0
        if (Arrays.deepEquals(state, goalState)) {
            return heuristic;
        }

        else {
            // find out number of tiles in wrong position
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (state[i][j] != goalState[i][j] && state[i][j] != 0) {
                        heuristic++;
                    }
                }
            }
        }
        return heuristic;
    }

    // Method to find heuristic 2 for A* (Manhattan distance of each tile)
    public int heuristic2(Node root, Node goal) {

        int[][] state = root.getState();
        int[][] goalState = goal.getState();

        int heuristic = 0;

        // If current node is the goal, then its heuristic will be 0
        if (Arrays.deepEquals(state, goalState)) {
            return heuristic;
        }

        else {

            // Check the node by checking the current state to the goal state and returning the difference in locations
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (state[i][j] != goalState[i][j] && state[i][j] != 0) {
                        int val = state[i][j];
                        Tiles g = findOther(state, goalState, val);
                        int diff = Math.abs(g.getFirst() - i) + Math.abs(g.getSecond() - j);
                        heuristic = diff + heuristic;
                    }
                }
            }
        }
        return heuristic;
    }

    // Method to find where the tile is located in goal compared to current state
    private Tiles findOther(int[][] state, int[][] goalState, int val) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goalState[i][j] == val) {
                    Tiles t = new Tiles(i, j);
                    return t;
                }
            }
        }

        return null;
    }

    /**  Class to return location of tiles
    * This class is really simple since it is just two getters for the state tiles locations
    * I decided to make a class in here instead of its own file
    */
    class Tiles {
        private int i;
        private int j;

        public Tiles(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getFirst() {
            return i;
        }

        public int getSecond() {
            return j;
        }
    }
}
