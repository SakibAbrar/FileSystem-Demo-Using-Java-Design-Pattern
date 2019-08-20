import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private Node currentNode = null;
    private List<Node> driveList;
    private Scanner scanner;

    public void pressEnter(){
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public void listDrives(){
        if(driveList.isEmpty()){
            System.out.println("No drives found! Try adding some!");
            return;
        }
        System.out.println("Total Drive : " + driveList.size());

        for(Node drive: driveList){
            System.out.println(drive.getName());
        }
        System.out.println();
    }

    public Node openExistingDrive(String name){
        for(Node drive: driveList){
            if(name.equalsIgnoreCase(drive.getName())){
                System.out.println( drive.getName() + " " + drive.getType() + " opened.");
                return drive;
            }
        }
        System.out.println("No such drive was found!");
        return currentNode;
    }

    public void listAll(){
        for(Node drive: driveList){
            drive.list();
        }
    }


    public void initialize(){
        scanner = new Scanner(System.in);
        driveList = new ArrayList<Node>();
    }



    public void showPrompt(){
        System.out.println("1.Add Drive and Open it");
        System.out.println("2.List Drive");
        System.out.println("3.Open Drive");
        System.out.println("4.Create Folder");
        System.out.println("5.Open Folder");
        System.out.println("6.Close Folder");
        System.out.println("7.Create File");
        System.out.println("8.Details of Current Node");
        System.out.println("9.List Current Node");
        System.out.println("10.List All");
        System.out.println("11.Quit");

        if(currentNode==null){
            System.out.println("Current Directory : ");
            System.out.println("Not in any directory!");
        }

        else{
            System.out.println("Current Directory : ");
            System.out.println(currentNode.getDirectory() + '/');
        }

    }

    public void loadUI(){
        initialize();
        boolean run = true;
        int inp;
        String name;
        Drive drive;
        while(run){
            showPrompt();
            inp = scanner.nextInt();
            scanner.nextLine();
            switch (inp){
                case 1:
                    System.out.println("(Try assigning a capital letter for a drive name)");
                    System.out.println("Name of the Drive :");
                    name = scanner.nextLine();
                    drive = new Drive(name + ":");
                    driveList.add(drive);
                    currentNode = drive;
                    System.out.println(drive.getName() + " " + drive.getType() + " " + "created!");
                    pressEnter();
                    break;
                case 2:
                    listDrives();
                    pressEnter();
                    break;
                case 3:
                    name = scanner.nextLine();
                    currentNode = openExistingDrive(name);
                    pressEnter();
                    break;
                case 4:
                    if(currentNode==null){
                        System.out.println("Try adding some drives first!");
                        break;
                    }
                    System.out.println("Name of the Folder :");
                    name = scanner.nextLine();
                    currentNode.addNode(new Folder(name, "folder", currentNode.getDirectory(), currentNode));
                    pressEnter();
                    break;
                case 5:
                    if(currentNode==null){
                        System.out.println("Try adding some drives first!");
                        break;
                    }
                    System.out.println("Name of the Folder :");
                    name = scanner.nextLine();
                    currentNode = currentNode.searchNode(name);
                    break;
                case 6:
                    if(currentNode==null){
                        System.out.println("Try adding some drives first!");
                        break;
                    }
                    if(currentNode.getParent()!=null)
                        currentNode = currentNode.getParent();
                    else{
                        System.out.println("This is the root directory.");
                    }
                    pressEnter();
                    break;
                case 7:
                    if(currentNode==null){
                        System.out.println("Try adding some drives first!");
                        break;
                    }
                    System.out.println("Name of the File :");
                    name = scanner.nextLine();
                    currentNode.addNode(new File(name, "file", currentNode.getDirectory(), currentNode));
                    pressEnter();
                    break;
                case 8:
                    if(currentNode==null){
                        System.out.println("Try adding some drives first!");
                        break;
                    }
                    currentNode.details();
                    pressEnter();
                    break;
                case 9:
                    if(currentNode==null){
                        System.out.println("Try adding some drives first!");
                        break;
                    }
                    currentNode.list();
                    pressEnter();
                    break;
                case 10:
                    listAll();
                    break;
                case 11:
                    run = false;
                    System.out.println("Closing the program!");
                    break;
                default:
                    System.out.println("Wrong input! Try again!");
                    break;
            }
            System.out.flush();
        }
    }

}
