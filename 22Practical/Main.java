// geeksforgeeks.org was used as a reference for the implementation of the binary search tree and its methods. 
import java.io.*;
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) { // read input from file
            String line;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                processCommand(bst, line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static void processCommand(BinarySearchTree bst, String line) {
        String[] parts = line.split("\\s+");
        
        if (parts.length == 0) {
            return;
        }
        
        String command = parts[0];

        try { // handle each command with appropriate error checking
            switch (command){
                case "BUILD": // build tree with given data
                    if (parts.length < 2) {
                        System.out.println("Cannot build tree with no data");
                        return;
                    }
                    for (int i = 1; i < parts.length; i++) {
                        try{
                            int data = Integer.parseInt(parts[i]);
                            bst.insert(data);
                        } catch (NumberFormatException e) {
                            System.out.println("Incorrect data format: " + parts[i]);
                        }
                    }
                    break;
                
                case "INSERT": // insert a value into the tree
                    if (parts.length != 2) {
                        System.out.println("Cannot insert without a value");
                        return;
                    }
                    try {
                        int value = Integer.parseInt(parts[1]);
                        bst.insert(value);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect data format: " + parts[1]);
                    }
                    break;
                    
                case "DELETE": // delete a value from the tree
                    if (parts.length != 2) {
                        System.out.println("Cannot delete without a value");
                        return;
                    }
                    try {
                        int value = Integer.parseInt(parts[1]);
                        bst.delete(value);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect data format: " + parts[1]);
                    }
                    break;
                    
                case "IN_ORDER": // print the tree in order
                    System.out.print("IN_ORDER:\t\t");
                    bst.inOrder();
                    break;
                    
                case "SIZE": // print the size of the tree
                    System.out.println("SIZE:\t\t\t" + bst.getSize());
                    break;
                    
                case "HEIGHT": // print the height of the tree
                    System.out.println("HEIGHT:\t\t\t" + bst.height());
                    break;
                    
                case "KTH_SMALLEST": // print the kth smallest value in the tree
                    if (parts.length != 2) {
                        System.out.println("Cannot find kth smallest without a value");
                        return;
                    }
                    try {
                        int k = Integer.parseInt(parts[1]);
                        int result = bst.kthSmallest(k);
                        if (result != -1) {
                            System.out.println("KTH_SMALLEST:\t\t" + result + " (k = " + k + ")");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect data format: " + parts[1]);
                    }
                    break;
                    
                case "SEARCH": // search for a value in the tree
                    if (parts.length != 2) {
                        System.out.println("Cannot search without a value");
                        return;
                    }
                    try {
                        int value = Integer.parseInt(parts[1]);
                        boolean found = bst.search(value);
                        System.out.println("SEARCH " + value + ":\t\t" + found);
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect data format: " + parts[1]);
                    }
                    break;
                    
                default: // handle unrecognized commands
                    System.out.println("Input not valid");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Input not valid");
        }
    
    }
}
