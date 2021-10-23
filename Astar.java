import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Class to perform A* search for both heuristics
 */
public class Astar {

    Node start;
    Node goal;
    int expanded;

    public Astar(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    public int getExpanded() {
        return expanded;
    }

    public Node search(int choice, int limit) {

        // Iniatialize open list as Priority Queue sorted by f values
        PriorityQueue<Node> open = new PriorityQueue<Node>();

        // Initialize closed list
        List<Node> close = new ArrayList<Node>();

        // Add start node to open list
        open.add(start);
        Node curr = null;

        while (!open.isEmpty()) {

            // Get current node from beginning of open queue
            curr = open.remove();
            close.add(curr);
            expanded++;

            // Check if current node is the goal
            if (curr.isGoal()) {
                return curr;
            }

            // Cut off nodes greater than the depth limit
            if (curr.getDepth() > limit) {
                curr = null;
            }

            // Generate possible moves
            Moves m = new Moves();
            List<Node> children = m.getChildren(curr, choice, start, goal);

            for (Node child : children) {

                // Skip if child node is in closed list
                if (isCycle(child, close)) {
                    continue;
                }

                // Skip if child node is in open list and check costs
                if (isCycleCost(child, open)) {
                    continue;
                }

                // Add child node to open pqueue
                open.add(child);

            }

        }

        return curr;
    }

    // Method to check if cycle
    public boolean isCycle(Node n, List<Node> visited) {

        boolean cycle = false;

        // Check if state is found

        for (Node v : visited) {
            if (Arrays.deepEquals(n.getState(), v.getState())) {
                cycle = true;
                break;
            }
        }

        return cycle;

    }

    // Method to check if cycle and greater cost
    public boolean isCycleCost(Node n, PriorityQueue<Node> visited) {

        boolean cycle = false;

        // Check if state is found

        for (Node v : visited) {
            if (Arrays.deepEquals(n.getState(), v.getState()) && n.getPathCost() > v.getPathCost()) {
                cycle = true;
                break;
            }
        }

        return cycle;

    }
}
