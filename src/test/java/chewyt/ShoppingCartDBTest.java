package chewyt;

 import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ShoppingCartDBTest {

    /*  create directory ok
        create file
        test login
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

        path = "testDB"; // new folder expected to be created
        File Dir = new File(path);
        boolean beforeMkDir = Dir.exists(); //expected false
        users = new ArrayList<String>(); //instantiate new arraylist
        ShoppingCartDB.createDirectory(path,users);
        boolean afterMkDir = Dir.exists(); //expected true
       
        assertTrue(beforeMkDir==false&&afterMkDir);
    }

    public void TestDir2() {
        
        //Testing of creation of new DB directory called testDB

        path = "testDB"; // folder expected to be existing
        File Dir = new File(path);
        boolean beforeMkDir = Dir.exists(); //expected true
        users = new ArrayList<String>(); //instantiate new arraylist
        ShoppingCartDB.createDirectory(path,users); // expected loading of files, can be zero files
        boolean afterMkDir = Dir.exists(); //expected true
        
        assertTrue(beforeMkDir&&afterMkDir);
    }
    @Test
    public void TestFile() {
        
        //Testing of creation of new DB directory called testDB

        path = "testDB"; // new folder expected to be created
        File Dir = new File(path);
        boolean beforeMkDir = Dir.exists(); //expected false
        users = new ArrayList<String>(); //instantiate new arraylist
        ShoppingCartDB.createDirectory(path,users);
        boolean afterMkDir = Dir.exists(); //expected true
       
        assertTrue(beforeMkDir==false&&afterMkDir);
    }

    
}
