package GraphDB;

import Types.IntegerWrapper;
import Types.StringWrapper;
import Types.TypeWrapper;
import java.util.HashMap;
import java.util.HashSet;

public class Node {
    private String type;
    public String tag;
    private HashMap<String, TypeWrapper> properties;
    private HashMap<String, HashSet<String>> relations;

    Node(String type, String tag){
        this.type = type;
        this.tag = tag;
        this.properties = new HashMap<String, TypeWrapper>();
        this.relations = new HashMap<String, HashSet<String>>();
    }

    public String getType() {
        return type;
    }

    public HashMap<String, TypeWrapper> getProperties() {
        return properties;
    }

    public HashMap<String, HashSet<String>> getRelations() {
        return relations;
    }

    public <T> Node addProps(String key, T value){
        String type = value.getClass().getSimpleName();

        switch(type) {
            case "String":
                this.properties.put(key, new StringWrapper((String) value));
                break;
            case "Integer":
                this.properties.put(key, new IntegerWrapper((Integer) value));
            default:
//                System.out.println(type);
        }

        return this;
    }

    /*=======================================================================*/
    public void setType(String type) {
        this.type = type;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setProperties(HashMap<String, TypeWrapper> properties) {
        this.properties = properties;
    }

    public void setRelations(HashMap<String, HashSet<String>> relations) {
        this.relations = relations;
    }
}
