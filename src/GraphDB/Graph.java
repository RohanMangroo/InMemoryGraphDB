package GraphDB;

import Types.TypeWrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Graph {
    private HashMap<String, Node> graph;
    private int numOfNodes;
    private final Scanner scanner;

    public Graph(){
        this.graph = new HashMap<String, Node>();
        this.numOfNodes = 0;
        this.scanner = new Scanner(System.in);
    }


    public Node getNode(String tag){
        return this.graph.get(tag);
    }
    public Node createNode(){
        System.out.println("Enter type of node:");
        String type = this.scanner.next();

        System.out.println("Enter tag of node:");
        String tag = this.scanner.next();

        Node node = new Node(type, tag);
        this.graph.put(tag, node);
        this.numOfNodes++;
        return node;
    }

    public void addRelation(){
        System.out.println("Enter type of relation:");
        String relation = this.scanner.next();

        System.out.println("From which node?");
        String fromNode = this.scanner.next();

        System.out.println("To which node?");
        String toNode = this.scanner.next();

        Node from = this.graph.get(fromNode);
        Node to = this.graph.get(toNode);

        if(!this.graph.containsKey(from.tag)) { System.out.println("Node " + from.tag + " does not exist" ); return;}
        if(!this.graph.containsKey(to.tag)) { System.out.println("Node " + to.tag + " does not exist"); return;}

        boolean hasRelation = from.getRelations().containsKey(relation);

        if(hasRelation){ from.getRelations().get(relation).add(to.tag);}

        else{
            HashMap<String, HashSet<String>> relations = from.getRelations();
            relations.put(relation, new HashSet<String>());
            relations.get(relation).add(to.tag);
        }
    }

    public void deleteNode(){
        System.out.println("Enter tag of node:");
        String tag = this.scanner.next();

        Node node = this.graph.get(tag);

        HashMap<String, HashSet<String>> nodeToDeleteRelations = node.getRelations();

        nodeToDeleteRelations.forEach((String key, HashSet<String> value) -> {
            value.forEach((String str) -> {
                deleteRelation(str, node.tag);
            });
        });

        this.graph.remove(node.tag);
        this.numOfNodes--;
    }

    private void deleteRelation(String tag, String tagToDelete){
        Node node = this.graph.get(tag);
        HashMap<String, HashSet<String>> relations = node.getRelations();

        relations.forEach((String key, HashSet<String> relation) -> {
            relation.remove(tagToDelete);
        });
    }


    public void print(){
        this.graph.forEach((String graphKey, Node graphValue) -> {
            System.out.println(graphKey+": ");
            System.out.println(" type: " + graphValue.getType());
            /*===================================================*/

            System.out.print(" properties: " + "{ ");
            graphValue.getProperties().forEach((String propKey, TypeWrapper propValue) -> {
                System.out.print(propKey + ": " + propValue.getValue() + ", ");
            });
            System.out.println("}");
            /*===================================================*/

            System.out.print(" relations: " + "{ ");
            graphValue.getRelations().forEach((String relKey, HashSet<String> relValue) -> {
                System.out.print(relKey + ": " + relValue + " ");
            });
            System.out.println(" }");
            /*===================================================*/

            System.out.println();
        });
    }
}
