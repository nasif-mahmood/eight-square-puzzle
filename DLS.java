import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * This class will be used for DFS and IDS
 * It is called DLS because the assignment has a hard depth limit of 10
 * It performs Depth-Limited search
 */
public class DLS {

    private Node cutNode;
    private int expanded;
    public DLS() {

    }

    public int getExpanded(){
        return expanded;
    }

    public Node search(Node root, int limit) {

        // frontier LIFO stack with initial node as element
        Stack<Node> frontier = new Stack<>();
        frontier.push(root);

        // List of visited node to prevent cycle
        List<Node> visited = new ArrayList<>();

        // result <- failure
        Node result = null;

        // while frontier not empty
        while (!frontier.isEmpty()) {

            // Get top node from stack
            Node node = frontier.pop();
            visited.add(node);
            expanded++;
            
            
            // return node if goalstate found
            if (node.isGoal()) {
                return node;
            }

            // if node depth greater than limit, then cutoff
            if (node.getDepth() > limit) {
                result = cutNode;
            }

            // check if not cycle, then expand
            else  {

                // Create children for current node
                Moves m = new Moves();
                List<Node> check = m.getChildren(node);
                List<Node> children = new ArrayList<>();
    
                // Check and make sure no dupes
                for (Node c : check) {
                    if (!isCycle(c, visited)) {
                        if (c.isGoal()){
                            return c;
                        }
                        children.add(c);
                        visited.add(c);
                    }
                }

                // add each child in expansion to frontier
                for (Node child : children) {
                        frontier.add(child);
                }

                check.clear();
                children.clear();
            }
        }

        return result;
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
}
