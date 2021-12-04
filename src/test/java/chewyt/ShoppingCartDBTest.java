package chewyt;

 import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ShoppingCartDBTest {

    /*  create directory ok
        create file ok
        test login ignored , as it is a method to run other methods,like load and create
        test load cart
        test save cart
        test userlist
    */

    //initilaise variables
    String path;
    String user;
    ArrayList <String> cart;    // = new ArrayList<String>();
    ArrayList <String> users;   // =  new ArrayList<String>();

    
    @Test
    public void TestDir() {
        
        //Testing of creation of new DB directory called testDB

        path = "testnewDB"; // new folder expected to be created
        File Dir = new File(path);
        
        final boolean beforeMkDir = Dir.exists(); //expected false
        System.out.println("1st test result (Before Mkdir): "+ beforeMkDir);
        
        users = ShoppingCartDB.createDirectory(path,users);
        System.out.println("1) "+users);

        boolean afterMkDir = Dir.exists(); //expected true
        System.out.println("1st test result (After Mkdir): "+ afterMkDir);

        users = ShoppingCartDB.createDirectory(path,users); // expected loading of files, can be zero files
        System.out.println("2) "+users);

        boolean afterMkDir2 = Dir.exists(); //expected true
        System.out.println("1st test result (After Mkdir2): "+ afterMkDir2);
       
        assertTrue(!beforeMkDir&&afterMkDir&&afterMkDir2);
    }
    
    @Test
    public void TestFile() {
        
        //Testing of creation of new DB file for bro under testDB directory

        path = "testDB"; // existing folder DB
        user = "newbro"; // new user
        cart = new ArrayList<String>();
        
        
        File Dir = new File(path+"/"+user+".db");
        
        final boolean beforeMkFile = Dir.exists(); //expected false for empty file
        System.out.println("beforeMkFile: "+ beforeMkFile);
        boolean fileCreate = ShoppingCartDB.createFile(path,user,cart); // true for creation success
        System.out.println("boolean fileCreate: "+fileCreate);
        boolean afterMkFile = Dir.exists(); //expected true
        System.out.println("afterMkFile: "+afterMkFile);

        //try to add similar file again
        boolean fileCreate2 = ShoppingCartDB.createFile(path,user,cart); // false as file is already existing
        System.out.println("boolean fileCreate2: (false)"+fileCreate2);
        boolean afterMkFile2 = Dir.exists(); //expected true
        System.out.println("afterMkFile2: "+afterMkFile2);
       
        assertTrue(!beforeMkFile&&fileCreate&&afterMkFile&&!fileCreate2&&afterMkFile2);
    }
    
    @Test
    public void TestloadCart() {
        
        //Testing of loading cart

        path = "testDB"; // existing folder DB
        user = "bro"; // similar user
        cart = new ArrayList<String>();

        File Dir = new File(path+"/"+user+".db");

        boolean beforeLoad = Dir.exists(); //expected true
        
        cart = ShoppingCartDB.loadShoppingCart(path,user,cart); //false for similar file
        System.out.println("CART: "+cart); //expected no elements
        
       
        assertTrue(beforeLoad&&cart.isEmpty());
    }
    @Test
    public void TestsaveCart() {
        
        //Testing of saving items into file and checking difference for loading cart

        path = "testDB"; // existing folder DB
        user = "bro"; // similar user
        cart = new ArrayList<String>();

        File Dir = new File(path+"/"+user+".db");
        cart.add("apple");
        cart.add("orange");
        
        ShoppingCartDB.saveShoppingCart(path,user,cart);
        boolean afterSaving = Dir.exists(); //expected true
        System.out.println("Last test after saving shoule be true: "+afterSaving);
       
        cart.clear();
        boolean beforeLoad = cart.isEmpty();
        System.out.println("Cart is empty: (true) "+beforeLoad);
        cart = ShoppingCartDB.loadShoppingCart(path,user,cart);
        boolean afterLoad = cart.isEmpty();
        System.out.println("Cart is empty: (false) "+afterLoad);

        assertTrue(afterSaving&&beforeLoad&&!afterLoad);
    }

    
}
