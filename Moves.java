import java.util.ArrayList;
import java.util.List;

/**
 * Class to generate moves
 */

public class Moves {

    public Moves() {

    }

    // Method to create Node's children
    public List<Node> getChildren(Node parent) {

        List<Node> children = new ArrayList<>();

        /**
         * List of action strings, for every action, create successor
         */
        for (String action : getActions(parent.getEmptyRow(), parent.getEmptyCol())) {
            int[][] childState = createState(action, parent, parent.getEmptyRow(), parent.getEmptyCol());

            Node child = new Node(childState, parent, action, parent.getDepth() + 1);
            children.add(child);
        }

        return children;

    }

    // Method to set heuristic for children as they are created
    public List<Node> getChildren(Node parent, int choice, Node start, Node goal) {

        List<Node> children = new ArrayList<>();

        /**
         * List of action strings, for every action, create successor
         */
        for (String action : getActions(parent.getEmptyRow(), parent.getEmptyCol())) {
            int[][] childState = createState(action, parent, parent.getEmptyRow(), parent.getEmptyCol());

            Node child = new Node(childState, parent, action, parent.getDepth() + 1);

            // Add heuristic of number of tile in wrong place to the node
            if (choice == 0) {
                Heuristic h = new Heuristic();
                child.setHeuristic(h.heuristic1(child, goal));
                distance(child, start); // Set pathcost
                int fVal = child.getHeuristic() + child.getPathCost();
                child.setTotalCost(fVal);
            }
    
            // Otherwise choose heuristic2 (Manhattan distance)
            else if (choice == 1) {
                Heuristic h = new Heuristic();
                child.setHeuristic(h.heuristic2(child, goal));
                distance(child, start);
                int fVal = child.getHeuristic() + child.getPathCost();
                child.setTotalCost(fVal);
            }

            children.add(child);
        }

        

        return children;

    }

    // Method to find out possible moves for Node
    public List<String> getActions(int emptyRow, int emptyCol) {

        List<String> actions = new ArrayList<>();

        // Up
        if (emptyRow != 0) {
            actions.add("up");
        }

        // Down
        if (emptyRow != 2) {
            actions.add("down");

        }

        // Left
        if (emptyCol != 0) {
            actions.add("left");

        }

        // Right
        if (emptyCol != 2) {
            actions.add("right");

        }

        return actions;
    }

    // Method to create a state
    public int[][] createState(String action, Node parent, int emptyRow, int emptyCol) {

        // Create blank state for child
        int[][] newState = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newState[i][j] = parent.getState()[i][j];
            }
        }

        // Move gaps depending on directions
        if (action.equals("up")) {
            newState[emptyRow - 1][emptyCol] = 0;
            newState[emptyRow][emptyCol] = parent.getState()[emptyRow - 1][emptyCol];
        }

        if (action.equals("down")) {
            newState[emptyRow + 1][emptyCol] = 0;
            newState[emptyRow][emptyCol] = parent.getState()[emptyRow + 1][emptyCol];

        }

        if (action.equals("left")) {
            newState[emptyRow][emptyCol - 1] = 0;
            newState[emptyRow][emptyCol] = parent.getState()[emptyRow][emptyCol - 1];
        }

        if (action.equals("right")) {
            newState[emptyRow][emptyCol + 1] = 0;
            newState[emptyRow][emptyCol] = parent.getState()[emptyRow][emptyCol + 1];
        }

        return newState;
    }


        // Method to find the distance traveled for Astar(g value)
        public static void distance(Node root, Node start) {

            int totalDistance = 0;
    
            // Get path from root(start) to current node(root)
            Paths p = new Paths(root);
            List<Node> path = p.path(root, start);
    
            // Increment path costs by 1 since each move costs 1
            for (Node n : path) {
                totalDistance++;
            }
    
            root.setPathCost(totalDistance);
        }
    
}
