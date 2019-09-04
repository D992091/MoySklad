import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Sklad
{

    public static ArrayList<String> commands = new ArrayList<String>();
    public static ArrayList<String> products = new ArrayList<String>();
    public static ArrayList<List<String>> purchase = new ArrayList<List<String>>();
    public static ArrayList<List<String>> demand = new ArrayList<List<String>>();
    final static String FORMAT = "dd.MM.yyyy";

    public static void main(String[] args) throws ParseException, IOException
    {

        
            commands.add("NEWPRODUCT");
            commands.add("PURCHASE");
            commands.add("DEMAND");
            commands.add("SALESREPORT");
            commands.add("EXIT");
            commands.add("SHOWPRODUCT");
            commands.add("SHOWPURCHASE");
            commands.add("SHOWDEMAND");    
            Scanner in = new Scanner(System.in);
            System.out.print("Insert commands");
            System.out.println("");
            String comm = " ";
            while (!comm.equals("EXIT"))
            {
                try{
                String str = in.nextLine();
                String[] arr = str.split(" ");
                if (arr[0].equals("EXIT"))
                {
                    break;
                }
                for (int i = 0; i < commands.size(); i++)
                {
                    if (arr[0].equals(commands.get(i)))
                    {
                        if (arr[0].equals("NEWPRODUCT"))
                        {
                            Functions.addProduct(arr[1]);
                            break;
                        }
                        if (arr[0].equals("PURCHASE"))
                        {
                            Functions.addPurchase(arr[1], arr[2], arr[3], arr[4]);
                            break;
                        }
                        if (arr[0].equals("DEMAND"))
                        {
                            Functions.addDemand(arr[1], arr[2], arr[3], arr[4]);
                            break;
                        }
                        if (arr[0].equals("SHOWPRODUCT"))
                        {
                            Functions.ShowProduct();
                            break;
                        }
                        if (arr[0].equals("SHOWPURCHASE"))
                        {
                            Functions.ShowPurchase();
                            break;
                        }
                        if (arr[0].equals("SHOWDEMAND"))
                        {
                            Functions.ShowDemand();
                            break;
                        }
                        if (arr[0].equals("SALESREPORT"))
                        {
                            System.out.println(Functions.SalesReport(arr[1], arr[2]));
                            break;
                        }
                    }
                }
            }
                catch (Exception e)
        {
            System.out.println("ERROR");
        }
            }
            in.close();

        
    }

}
