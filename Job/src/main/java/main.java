/**
 * @author Matus Valko
 */

import utils.Parser;

import javax.management.InvalidAttributeValueException;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        System.out.println("Write a year from 2003 to 2014 to check highest and lowest unemployment rate = ");
        try(Scanner scan = new Scanner(System.in)){
            Parser parser = new Parser();
            String input = scan.nextLine();
            if (Integer.parseInt(input) < 2003 || Integer.parseInt(input) > 2014)
                throw new InvalidAttributeValueException();
            parser.printLowestURCountries(input);
            System.out.println();
            parser.printHighestURCountries(input);
        }catch (InvalidAttributeValueException e){
            System.out.println("Wrong year");
        }
        catch(Exception e){
            System.out.println("An exception occurred");
        }

    }
}
