import java.util.Scanner;

public class Client {
    private Node currentNode;

    public void initialize(){
        ///initializing with root directory
        currentNode = new Folder("root", "folder", "");
    }

    public void showPrompt(){
        System.out.println("1.Create Folder");
        System.out.println("2.Create File");
        System.out.println("3.Open Folder");
        System.out.println("4.Close Folder");
        System.out.println("5.ls");
        System.out.println("6.quit");

        System.out.println("Current Directory : ");
        System.out.println(currentNode.getDirectory());
    }

    public void loadUI(){
        initialize();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        int inp;
        String name;

        while(run){
            showPrompt();
            inp = scanner.nextInt();
            scanner.nextLine();
            switch (inp){
                case 1:
                    name = scanner.nextLine();
                    currentNode.addNode(new Folder(name, "folder", currentNode.getDirectory(), currentNode));
                    break;
                case 2:
                    name = scanner.nextLine();
                    currentNode.addNode(new File(name, "file", currentNode.getDirectory(), currentNode));
                    break;
                case 3:
                    name = scanner.nextLine();
                    currentNode = currentNode.searchNode(name);
                    break;
                case 4:
                    if(currentNode.getParent()!=null)
                        currentNode = currentNode.getParent();
                    else{
                        System.out.println("This is the root directory.");
                    }
                    break;
                case 5:
                    currentNode.list();
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input! Try again! ");
                    break;
            }
            System.out.flush();
        }
    }
}
