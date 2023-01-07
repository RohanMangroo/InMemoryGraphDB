package GraphDB;

import java.util.HashMap;
import java.util.Scanner;

public class DBCollection {
    private final HashMap<String, Graph> databases;
    private final Scanner scanner;

    public DBCollection(){
        this.databases = new HashMap<String, Graph>();
        this.scanner = new Scanner(System.in);
    }
    public void mainMenu(){
        this.clearScreen();
        this.print();

        System.out.println("Main Menu");
        System.out.println("1: Create New Database");
        System.out.println("2: Select Database");
        System.out.println("3: Delete Database");

        int choice = this.scanner.nextInt();

        switch (choice) {
            case 1 -> createGraph();
            case 2 -> selectGraph();
            case 3 -> deleteGraph();
            default -> mainMenu();
        }
    }

    public void print(){
        System.out.println("Databases:");
        this.databases.forEach((String key, Graph value) -> {
            System.out.println(" name: " + key);
        });
        System.out.println("");
    }

    public void createGraph(){
        System.out.println("Enter database name:");
        String dbName = this.scanner.next();
        this.databases.put(dbName, new Graph());

        this.clearScreen();
        this.mainMenu();
    }

    public void deleteGraph(){
        System.out.println("Database to be deleted:");
        String name = this.scanner.next();
        this.databases.remove(name);
        System.out.println(name + " database has been removed");

        this.clearScreen();
        this.mainMenu();
    }

    private void selectGraph() {
        System.out.println("Enter database name:");
        String dbName = this.scanner.next();
        if(!this.databases.containsKey(dbName)) this.mainMenu();

        this.clearScreen();
        this.graphMenu(dbName);
    }

    private void graphMenu(String dbName){
        Graph graph = this.databases.get(dbName);

        System.out.println("Graph Name: " + dbName);
        System.out.println("\nMenu");
        System.out.println("1: Print All Nodes");
        System.out.println("2: Add Node");
        System.out.println("3: Get Node");
        System.out.println("4: Add Relation");
        System.out.println("5: Delete Node");
        System.out.println("6: Delete Relation\n");
        System.out.println("7: Main Menu");

        int choice = this.scanner.nextInt();
        this.clearScreen();

        switch (choice) {
            case 1 -> graph.print();
            case 2 -> graph.createNode();
            case 3 -> this.nodeMenu(graph);
            case 4 -> graph.addRelation();
            case 5 -> graph.deleteNode();
//            case 6 -> graph.deleteRelation();
            case 7 -> this.mainMenu();
            default -> mainMenu();
        }
        this.graphMenu(dbName);
    }


    private void nodeMenu(Graph graph){
        System.out.println("Enter node tag:");
        String tag = this.scanner.next();
        Node node = graph.getNode(tag);

        System.out.println("Node tag: " + node.tag);
        System.out.println("\nMenu");
        System.out.println("1: Print Properties");
        System.out.println("2: Add Property");
        System.out.println("3: Delete Property");
        System.out.println("4: Main Menu");

        int choice = this.scanner.nextInt();
        this.clearScreen();

        switch (choice) {
            case 1 -> node.printProps();
            case 2 -> node.addProps();
            default -> mainMenu();
        }
    }
    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
