package chewyt;

import java.io.Console;
import java.util.logging.ConsoleHandler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Console cons = System.console();
        String input;
        do{
            input = cons.readLine("Whats your hobby?");
            input=input.trim().toLowerCase();
        

            switch (input){

                case "swim" : System.out.println("The nearest pool is at my home");
                break;
                case "jog" : System.out.println("How far can you jog in your longest marathon?");
                break;
                case "code" : System.out.println("Lets be the fellow bro!");
                break;
                case "exit" : 
                break;
                default: System.out.printf("What is this %s hobby of yours?\n", input);
                break;
            }
        }while(!input.toLowerCase().equals("exit"));
    }
}
