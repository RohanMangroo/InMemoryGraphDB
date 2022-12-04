package GraphDB;

import Types.TypeWrapper;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    private HashMap<String, Node> graph;
    private int numOfNodes;
    private int numOfRelations;

    public Graph(){
        this.graph = new HashMap<String, Node>();
        this.numOfNodes = 0;
        this.numOfRelations = 0;
    }

    public Node createNode(String type, String tag){
        Node node = new Node(type, tag);
        this.graph.put(tag, node);
        return node;
    }

    public void addRelation(String relation, Node from, Node to){
        if(!this.graph.containsKey(from.tag)) { System.out.println("Node " + from.tag + " does not exist" ); return;}
        if(!this.graph.containsKey(to.tag)) { System.out.println("Node " + to.tag + " does not exist"); return;}

        boolean hasRelation = from.getRelations().containsKey(relation);

        if(hasRelation){ from.getRelations().get(relation).add(to.tag);}

        else{
            HashMap<String, HashSet<String>> relations = from.getRelations();
            relations.put(relation, new HashSet<String>());
            relations.get(relation).add(to.tag);
        }

//        System.out.println("Relation: " + relation + " created from " + from.tag + " to " + to.tag);
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
