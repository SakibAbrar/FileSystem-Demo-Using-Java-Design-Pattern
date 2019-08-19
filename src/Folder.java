import java.util.ArrayList;
import java.util.List;

public class Folder implements Node {
    private String name;
    private String type;
    private String directory;
    private int component_count;
    private Node parent;
    private List<Node> nodeList;

    public Folder(String name, String type, String currentDirectory, Node currentNode) {
        this.name = name;
        this.type = type;
        this.directory = currentDirectory + "/" + name;
        this.parent = currentNode;
        this.component_count = 0;
        this.nodeList = new ArrayList<Node>();
    }

    public Folder(String name, String type, String currentDirectory) {
        this.name = name;
        this.type = type;
        this.directory = currentDirectory + "/" + name;
        this.parent = null;
        this.component_count = 0;
        this.nodeList = new ArrayList<Node>();
    }

    @Override
    public Node getParent() {
        return this.parent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getDirectory() {
        return this.directory;
    }

    @Override
    public int getComponentCount() {
        return this.component_count;
    }

    @Override
    public void details() {
        System.out.println("\nDetails of " + this.name + ":\n");

        System.out.println("Name: " + this.name +
                "\nType: " + this.type +
                "\nDirectory: " + this.directory +
                "\nComponent Count: " + this.component_count + "\n");
    }

    @Override
    public void list() {
        listing(0);
    }

    @Override
    public void listing(int spacing) {
        for(int idx = 0; idx<=spacing; idx++){
            System.out.print(" ");
        }
        System.out.println("------" + this.name);
        for(Node node : nodeList){
            node.listing(spacing + 4);
        }
    }

    @Override
    public Node searchNode(String name) {
        for(Node node: nodeList){
            if(name.equalsIgnoreCase(node.getName())){
                System.out.println( node.getName() + " " + node.getType() + "opened.");
                return node;
            }
        }
        System.out.println("No such Folder found in this directory.");
        return this;
    }


    @Override
    public void addNode(Node node){
        nodeList.add(node);
        System.out.println(node.getName() + " " + node.getType() + " " + "created in " + node.getParent().getDirectory());
        component_count++;
    }

    public void removeNode(Node node){
        nodeList.remove(node);
        component_count--;
    }
}
