import java.util.Arrays;

/**
 * This class will be the different game states
 */
public class Node implements Comparable<Node>{

    private int[][] state; // Location of tiles on board
    private String action; // Action performed to reach current state
    private Node parent; // Parent Node
    private int pathCost; // Total distance traveled (g)
    private int depth; // Current depth of Node in tree
    private int heuristic; // Heurstic cost of node to goal (h)
    private int totalCost; // Total cost of heuristic + pathcost (f)

    // Location of blank tile
    private int emptyRow;
    private int emptyCol;

    
    public Node(int[][] state) {
        this.setState(state);
        this.setParent(null);
        this.setAction("");
        this.setPathCost(0);
        this.setDepth(0);
        this.setHeuristic(0);

        // Determine where the blank tile is located
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    this.setEmptyRow(i);
                    this.setEmptyCol(j);
                    break;
                }
            }
        }
    }

    public Node(int[][] state, Node parent, String action, int depth) {
        this.setState(state);
        this.setParent(parent);
        this.setAction(action);
        this.setPathCost(0);
        this.depth = depth;
        this.setHeuristic(0);

        // Determine where the blank tile is located
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    this.setEmptyRow(i);
                    this.setEmptyCol(j);
                    break;
                }
            }
        }
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow) {
        this.emptyRow = emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }

    public void setEmptyCol(int emptyCol) {
        this.emptyCol = emptyCol;
    }

    // Method to check if current state is goal state
    public boolean isGoal() {

        boolean goal = false;

        int[][] goalState = { { 1, 2, 3 }, { 4, 0, 5 }, { 6, 7, 8 } };

        if (Arrays.deepEquals(state, goalState)) {
            goal = true;
        }

        return goal;
    }

    // Method to print the state
    public void printBoard(){
            for (int[] row : state) {
                System.out.println();
                for (int num : row) {
                    System.out.print(num + " ");
                }
            }
    }

    // Method to compare f values
    public int compareTo(Node other){
        if (this.getTotalCost() == other.getTotalCost()){
            return 0;
        }
        else if (this.getTotalCost() > other.getTotalCost()){
            return 1;
        }
        else {
            return -1;
        }
    }
}
