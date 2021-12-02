package chewyt;

import java.util.ArrayList;
import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        
        ArrayList<String> cart  = new ArrayList<String>();
        Console cons = System.console();
        String input;
        
        do{

            input = cons.readLine("Welcome to your shopping cart\n");

            String inputline = input.trim().toLowerCase();
            // FOR checking parse strings | System.out.println("Commandline: "+inputline);

            //regex used to ensure correct input is processed
            Pattern add_pattern = Pattern.compile("^add ([a-zA-Z, ]+)");
            Matcher add_matcher = add_pattern.matcher(inputline);
            Pattern del_pattern = Pattern.compile("^delete ([1-9][0-9]*)");
            Matcher del_matcher = del_pattern.matcher(inputline);

            if (add_matcher.find()){

                String itemlist = add_matcher.group(1);
                add(itemlist,cart);
                continue;
            }

            if (inputline.equals("list"))
            {
                display(cart);
                continue;
            }

            if (del_matcher.find()){

                Integer index = Integer.parseInt(del_matcher.group(1));
                delete(cart, index);
                continue;

            }
            // catching incorrect commands other than exit

            if(!(input.equals("exit"))){
                System.out.println("ERROR in console input: "+inputline);
            }
        }while(!(input.equals("exit")));
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



}
