import java.util.ArrayList;
import java.util.List;

public class Drive implements Node {
    private String name;
    private String type;
    private String directory;
    private int component_count;
    private List<Node> nodeList;
    private Node parent;


    public Drive(String name) {
        this.name = name;
        this.type = "drive";
        this.directory = name;
        this.component_count = 0;
        this.nodeList = new ArrayList<Node>();
        parent = null;
    }



    @Override
    public void details() {

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
    public Node getParent() {
        return null;
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
    public void addNode(Node node) {

        if("drive".equalsIgnoreCase(node.getType())){
            System.out.println("You can't add drive inside a drive!Try adding files and folders instead!");
        }

        else{
            nodeList.add(node);
            System.out.println(node.getName() + " " + node.getType() + " " + "created in " + node.getParent().getDirectory());
            component_count++;
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
}
