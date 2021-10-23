import java.util.ArrayList;
import java.util.List;

/**
 * This class obtains the path from the current node to the start node
 */

public class Paths {

    private List<Node> path = new ArrayList<Node>();

    public Paths(Node root) {
    }

    // Method to find path
    public List<Node> path(Node goal, Node start) {

        // Compare current node to goal and get parent until goal is found
        if (!(goal.equals(start))) {
            path.add(goal);
            path(goal.getParent(), start);
        }

        return path;
    }
}
