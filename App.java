import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * By: Nasif Mahmood This program will
 * perform the 8-puzzle problem using different search methods These include:
 * Depth-First Search, Iterative Deepening Search and A* search with two
 * different heuristics The heuristics for the A* algorithm are the number of
 * tiles in the wrong place and Manhattan distance Every search tree has a hard
 * limit of depth 10 which means the Nodes will be cut off if they are depth 11
 * or higher
 * 
 * This class is the main method to run the program
 */
public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        int[][] start = new int[3][3];

        String test;

        boolean correct = false;

        // Loop to obtain correct input state from user
        do {
            try {
                System.out.println("Please enter values for start state");
                test = input.nextLine();
                String[] integerStrings = test.split(" ");
                int[] integers = new int[integerStrings.length];

                for (int i = 0; i < integers.length; i++) {
                    integers[i] = Integer.parseInt(integerStrings[i]);
                    if (integers[i] >= 9 || integers[i] < 0) {
                        throw new IllegalArgumentException("number not between 0 and 8 inclusive");
                    }
                }

                // Check if duplicate values are input into state
                if (dupesCheck(integers)) {
                    throw new IllegalArgumentException("no duplicates");
                }

                int count = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        start[i][j] = integers[count];
                        count++;
                    }
                }
                correct = true;
            } catch (Exception e) {
                System.out.println(
                        "Error with input. Please enter 9 numbers with a \"0\" indicating the blank tile. No duplicates tiles allowed");
            }
        } while (!correct);


        // Print start and goal states
        int[][] goal = { { 1, 2, 3 }, { 4, 0, 5 }, { 6, 7, 8 } };

        System.out.println("Start:");
        Node s = new Node(start);
        s.printBoard();

        System.out.println("\n\n\n");

        Node g = new Node(goal);
        System.out.println("Goal to find:");
        g.printBoard();

        System.out.println("\n\n\n");

        // Obtain search choice from user
        boolean correctChoice = false;
        int choice = -1;
        System.out.println(
                "Please enter search method choice \n(DFS = 1, IDS = 2, Astar1 = 3(# of wrong tiles), Astar2 = 4(Manhattan distance)) \nEnter 0 to exit");
        do {
            try {
                String value;
                value = input.next();
                choice = Integer.parseInt(value);
                if (choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4) {

                    // Exit if 0 is entered
                    if (choice == 0) {
                        System.out.println("Exiting");
                        correctChoice = true;
                        input.close();
                        return;
                    }

                    // Perform DFS if choice is 1
                    else if (choice == 1) {
                        System.out.println("Depth First Search chosen (depth limit of 10)");
                        s.printBoard();
                        System.out.println("Initial state");
                        DLS dls = new DLS();
                        Node dfs = dls.search(s, 9);
                        if (dfs == null) {
                            System.out.println("goal not found in limit");
                        }

                        // Print path
                        else {
                            Paths p = new Paths(dfs);
                            List<Node> path = p.path(dfs, s);

                            System.out.println("\nOutput(List of states starting from input to goal state if found)");
                            int pathSize = path.size();
                            s.printBoard();
                            for (int i = pathSize - 1; i >= 0; i--) {
                                System.out.println();
                                path.get(i).printBoard();
                                System.out.println(path.get(i).getAction());
                                if (i == 1) {
                                    System.out.println("\nGoal state:");
                                }
                            }
                            System.out.println("Number of moves = " + pathSize);
                            System.out.println("Number of states enqueued = " + dls.getExpanded());
                            System.out.println("\nNote: 0 represents an empty tile");
                        }
                        System.out.println(
                                "Please enter search method choice \n(DFS = 1, IDS = 2, Astar1 = 3(# of wrong tiles), Astar2 = 4(Manhattan distance)) \nEnter 0 to exit");

                    }

                    // Perform IDS if choice is 2
                    else if (choice == 2) {
                        System.out.println(
                                "Iterative Deepening Search chosen (depth limit will increment from 0 until 10 or goal found)");
                        s.printBoard();
                        System.out.println("Initial state");
                        Node answer = null;
                        DLS ids = new DLS();
                        answer = iterativeDeep(s, answer, 9, ids);
                        if (answer == null) {
                            System.out.println("goal not found in limit");
                        }

                        // Print path
                        else {
                            Paths p = new Paths(answer);
                            List<Node> path = p.path(answer, s);

                            System.out.println("\nOutput(List of states starting from input to goal state if found)");
                            int pathSize = path.size();
                            s.printBoard();
                            for (int i = pathSize - 1; i >= 0; i--) {
                                System.out.println();
                                path.get(i).printBoard();
                                System.out.println(path.get(i).getAction());
                                if (i == 1) {
                                    System.out.println("\nGoal state:");
                                }
                            }
                            System.out.println("Number of moves = " + pathSize);
                            System.out.println("Number of states enqueued = " + ids.getExpanded());
                            System.out.println("\nNote: 0 represents an empty tile");

                        }
                        System.out.println(
                                "Please enter search method choice \n(DFS = 1, IDS = 2, Astar1 = 3(# of wrong tiles), Astar2 = 4(Manhattan distance)) \nEnter 0 to exit");

                    }

                    // First heuristic choice
                    else if (choice == 3) {
                        System.out.println("A* search chosen using heuristic: \"number of tiles out of place\"");
                        s.printBoard();
                        System.out.println("Initial state");
                        Astar as = new Astar(s, g);

                        try {
                            Node a1 = as.search(0, 9);

                            // Print path
                            Paths p = new Paths(a1);
                            List<Node> path = p.path(a1, s);

                            System.out.println("\nOutput(List of states starting from input to goal state if found)");
                            int pathSize = path.size();
                            s.printBoard();
                            for (int i = pathSize - 1; i >= 0; i--) {
                                System.out.println();
                                path.get(i).printBoard();
                                System.out.println(path.get(i).getAction());
                                if (i == 1) {
                                    System.out.println("\nGoal state:");
                                }
                            }
                            System.out.println("Number of moves = " + pathSize);
                            System.out.println("Number of states enqueued = " + as.getExpanded());
                            System.out.println("\nNote: 0 represents an empty tile");

                        } catch (Exception x) {

                            System.out.println("goal not found in limit");
                        }
                        System.out.println(
                                "Please enter search method choice \n(DFS = 1, IDS = 2, Astar1 = 3(# of wrong tiles), Astar2 = 4(Manhattan distance)) \nEnter 0 to exit");

                    }

                    // Second heuristic choice
                    else if (choice == 4) {
                        System.out.println("A* search chosen using heuristic: \"Manhattan distance\"");
                        s.printBoard();
                        System.out.println("Initial state");
                        Astar as = new Astar(s, g);

                        try {
                            Node a2 = as.search(1, 9);

                            // Print path
                            Paths p = new Paths(a2);
                            List<Node> path = p.path(a2, s);

                            System.out.println("\nOutput(List of states starting from input to goal state if found)");
                            int pathSize = path.size();
                            s.printBoard();
                            for (int i = pathSize - 1; i >= 0; i--) {
                                System.out.println();
                                path.get(i).printBoard();
                                System.out.println(path.get(i).getAction());
                                if (i == 1) {
                                    System.out.println("\nGoal state:");
                                }
                            }
                            System.out.println("Number of moves = " + pathSize);
                            System.out.println("Number of states enqueued = " + as.getExpanded());
                            System.out.println("\nNote: 0 represents an empty tile");

                        } catch (Exception x2) {
                            System.out.println("goal not found in limit");
                        }

                        System.out.println(
                                "Please enter search method choice \n(DFS = 1, IDS = 2, Astar1 = 3(# of wrong tiles), Astar2 = 4(Manhattan distance)) \nEnter 0 to exit");

                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Please input a valid number (DFS = 0, IDS = 1, Astar1 = 2, Astar2 = 3)");
            }
        } while (!correctChoice && choice != 0);
    }

    // Method to perform IDS
    public static Node iterativeDeep(Node start, Node n, int limit, DLS ids) {

        for (int i = 0; i <= limit; i++) {
            if (n == null) {
                n = ids.search(start, i);
            }
        }

        return n;
    }

    // Method to check for duplicates in state itself
    public static boolean dupesCheck(int[] arr) {

        boolean duplicates = false;

        List<Integer> list = new ArrayList<>();

        for (int x : arr) {
            if (list.contains(x)) {
                return true;
            }
            list.add(x);
        }

        return duplicates;
    }

}
