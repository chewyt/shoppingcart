package chewyt;

import java.util.ArrayList;
import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class App {
    public static void main(String[] args) {
        
        ArrayList<String> cart  = new ArrayList<String>();
        ArrayList<String> users = new ArrayList<String>(); //User list management
        Console cons = System.console();
        String input;
        String subInput;
        String inputline;
        String subInputLine;
        
        // Processing arguments for CartDB directory
        String path;
        if (args.length == 0){            
            path = "CartDB";
        }else{
            path = args[0];
        }
        // ********************************** //
        // Set DB directory once boot up
        
        ShoppingCartDB.createDirectory(path,users);

        do{

            mainMenuScreen();
            input = cons.readLine("");

            inputline = input.trim().toLowerCase();
            

            // FOR checking parse strings | System.out.println("Commandline: "+inputline);

            //regex used to ensure correct input is processed
            
            Pattern login_pattern = Pattern.compile("login ([a-zA-Z]+)$");
            Matcher login_matcher = login_pattern.matcher(inputline);

            //Check for either login or users command

            if (login_matcher.find()){

                String user = (login_matcher.group(1));
                ShoppingCartDB.login(path,user,users,cart);
                
                do{
                    menuScreen(user);
                    subInput = cons.readLine("");
                    subInputLine = subInput.trim().toLowerCase();
                    
                    //regex used to ensure correct input is processed
                    Pattern add_pattern = Pattern.compile("^add ([a-zA-Z, ]+)");
                    Matcher add_matcher = add_pattern.matcher(subInputLine);
                    Pattern del_pattern = Pattern.compile("^delete ([1-9][0-9]*)");
                    Matcher del_matcher = del_pattern.matcher(subInputLine);

                    if (add_matcher.find()){

                        String itemlist = add_matcher.group(1);
                        add(itemlist,cart);
                        continue;
                    }
        
                    if (subInputLine.equals("list"))
                    {
                        display(cart);
                        continue;
                    }
        
                    if (del_matcher.find()){
        
                        Integer index = Integer.parseInt(del_matcher.group(1));
                        delete(cart, index);
                        continue;
        
                    }
                    
                    if (subInputLine.equals("save"))
                    {
                        //saving command
                        ShoppingCartDB.saveShoppingCart(path,user,cart);
                        continue;
                    }
                    
                    if(!(subInputLine.equals("save"))){
                        System.out.println("ERROR in console input: "+subInputLine);
                    }
                }while(!(subInputLine.equals("save")));

                continue;

            }
            if (inputline.equals("users"))
            {
                //Show users list
                ShoppingCartDB.userslist(path,users);
                continue;
            }

            // catching incorrect commands other than exit

            if(!(inputline.equals("exit"))){
                System.out.println("ERROR in console input: "+inputline);
            }
        }while(!(inputline.equals("exit")));
    }
    
    public static void add(String itemlist, ArrayList<String> cart){
        if(itemlist.contains(","))
                {
                    String items[] = itemlist.split(",");
                    for (String i : items){
                        if(!cart.contains(i.trim())) {
                            cart.add(i.trim());
                            System.out.println(i.trim()+" added to cart");
                        }
                        else{
                            System.out.println("You have "+i.trim()+" in your cart");
                        }
                    }
                }
                else{
                    if(!cart.contains(itemlist.trim())){
                        cart.add(itemlist.trim());
                        System.out.println(itemlist.trim()+" added to cart");
                    }
                    else{
                        System.out.println("You have "+itemlist.trim()+" in your cart");
                    }
                }

    }
    public static void display(ArrayList<String> cartitems){

        if (cartitems.size()==0){
            System.out.println("Your cart is empty");
        }
        else {
            for (int i = 1 ; i <= cartitems.size() ; i++) {
                System.out.println(i+". "+cartitems.get(i-1));
                }
        } 
    }

    public static void delete(ArrayList<String> cartitems, int index){

        if (index <= cartitems.size()){
            System.out.println(cartitems.get(index-1)+" removed from cart");
            cartitems.remove(index-1);
        }
        else {
            System.out.println("Incorrect item index");
        } 
    }

    public static void mainMenuScreen(){
        System.out.println("========================================");
        System.out.println("Welcome to Main Menu");
        System.out.println("========================================");
        System.out.println("1)\tlogin xxx (where xxx is your name)");
        System.out.println("2)\tusers (for checking lists of registered users))");
        System.out.println("3)\texit (to exit this console application)");
        System.out.println("Enter the command:");
    }
    public static void menuScreen(String user){
        System.out.println("========================================");
        System.out.println("Welcome to your shopping cart, "+ user );
        System.out.println("========================================");
        System.out.println("1)\tadd xxx, xxx (where xxx can be added as item/items)");
        System.out.println("2)\tlist (for checking lists of items in your cart))");
        System.out.println("3)\tdelete xxx (for removing one item based on the index of cart list)");
        System.out.println("4)\tsave (for saving lists and auto logout))");
        System.out.println("Enter the command:");
        
    }
}
