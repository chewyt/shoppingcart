package chewyt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class ShoppingCartDB {

    public static void main(String[] args) {
        //createDirectory("CartDB");
        //createFile("CartDB", "John");
        //ArrayList<String> al = new ArrayList<String>();
        //loadShoppingCart("CartDB", "John", al);
        //System.out.println("Al list : "+ al);
        
    }
    
    public static void login(String path,String user, ArrayList<String> users, ArrayList<String> cart){
        //DEV STOP HERE FOR NOW!!
        if (!users.contains(user))
        {
            users.add(user); //Add to user list
            ShoppingCartDB.createFile(path, user,cart); //create file based on username            
        }else{
            cart = ShoppingCartDB.loadShoppingCart(path,user,cart); //read file contents into cart Arraylist
        }
        
    }

    public static ArrayList<String> createDirectory(String path, ArrayList<String> users){
        
        File folder = new File(path);
        
        try {
            if(folder.mkdir()){
                System.out.println("Status: FolderDB created successfully");
            }else{
                System.out.println("Status: DB folder already exists and loaded.");
                loadUsers(path, users);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Folder not created");
            e.printStackTrace();
        }
        return users;
    }

    public static boolean createFile(String path, String user,ArrayList<String> cart){
        
        File DBfile = new File(path+"/"+user+".db");
               
        try {
            if(DBfile.createNewFile()){
                System.out.println("Status: DB file created successfully");
                return true;
            }else{
                System.out.println("Status: DB file exists. Load DB file");
                loadShoppingCart(path, user, cart);
                return false;
            }
        } catch (IOException e) {
            System.out.println("ERROR: File not created");
            e.printStackTrace();
            return false;
        }          
    }

    public static ArrayList<String> loadShoppingCart(String path, String user, ArrayList<String> cart){

        
        try (FileReader DBfile = new FileReader(path+"/"+user+".db")) {
            BufferedReader reader = new BufferedReader(DBfile);
            reader.readLine(); //Ignoring first line
            String line=null;   
            while( (line = reader.readLine()) !=null ){
                cart.add(line);
            }
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("A File not found error occurred.");
            e.printStackTrace();
        }
         catch (IOException e) {
        System.out.println("An IO error occurred.");
        e.printStackTrace();
    }
        return cart;

    }

    public static void saveShoppingCart(String path, String user, ArrayList<String> cart)
    {
        try {
            FileWriter fileWriter = new FileWriter((path+"/"+user+".db"), false);
            fileWriter.write(path+"/"+user+".db");
            for(String i:cart){
                fileWriter.append("\n"+i);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        } //overwrites file
        
    }

    public static void userslist(String path, ArrayList<String> users){
        
        System.out.println("\nThe following users are registered:");
        int num = 1;
        for(String user : users){
                System.out.println((num)+". "+user);
                num++; 
        }
        System.out.println();
    }
    public static ArrayList<String> loadUsers(String path, ArrayList<String> users){
        File folder = new File(path);
        File [] listOfFiles = folder.listFiles();

        for(File f : listOfFiles){
            if(f.isFile()){
                users.add(f.getName().substring(0,f.getName().length()-3));
            }
        }
        return users;
    }
}
