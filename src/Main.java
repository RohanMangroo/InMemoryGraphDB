import GraphDB.Graph;
import GraphDB.Node;

public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();

        Node lebron = graph.createNode("Player", "lebron");
        lebron.addProps("name", "Lebron James").addProps("age", 34);

        Node anthony = graph.createNode("Player", "anthony");
        anthony.addProps("name", "Anthony Davis").addProps("age", 31);

        Node russell = graph.createNode("Player", "russell");
        russell.addProps("name", "Russell Westbrook").addProps("age", 31);

        Node lakers = graph.createNode("Team", "lakers");
        lakers.addProps("name", "Los Angeles Lakers");

        graph.addRelation("PLAYS_FOR", lebron, lakers);
        graph.addRelation("PLAYS_FOR", anthony, lakers);
        graph.addRelation("PLAYS_FOR", russell, lakers);

        graph.addRelation("TEAMMATES", lebron, anthony);
        graph.addRelation("TEAMMATES", lebron, russell);

        graph.addRelation("TEAMMATES", anthony, lebron);
        graph.addRelation("TEAMMATES", anthony, russell);

        graph.addRelation("TEAMMATES", russell, lebron);
        graph.addRelation("TEAMMATES", russell, anthony);


        graph.print();
//        lebron.getRelations().
    }
}