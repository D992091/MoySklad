
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Functions
{

    public static ArrayList<List<String>> prod = new ArrayList<List<String>>();

    public static String addProduct(String name)
    {
        boolean c = true;
        for (int i = 0; i < Sklad.products.size(); i++)
        {
            if (name.equals(Sklad.products.get(i)))
            {
                c = false;
                break;
            }
        }
        if (c == true)
        {
            Sklad.products.add(name);
            System.out.println("OK");
            return "OK";
        }
        else
        {
            System.out.println("ERROR");
            return "ERROR";
        }
    }

    public static String addPurchase(String name, String number, String price, String date)
    {
        boolean c = false;
        for (int i = 0; i < Sklad.products.size(); i++)
        {
            if (name.equals(Sklad.products.get(i)))
            {
                c = true;
                break;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat(Sklad.FORMAT);
        sdf.setLenient(false);
        try
        {
            Date d = sdf.parse(date);
        }
        catch (ParseException ex)
        {
            c = false;
        }
        if ((c == true) && (Integer.parseInt(number) > 0) && (Double.parseDouble(price) > 0))
        {
            ArrayList<String> purch = new ArrayList<String>();
            purch.add(name);
            purch.add(number);
            purch.add(price);
            purch.add(date);
            Sklad.purchase.add(purch);
             ArrayList<String> purch1 = new ArrayList<String>();
             purch1.add(name);
            int numb = -1;
            String t = number;
            for (int i = 0; i < prod.size(); i++)
            {
                if (name.equals(prod.get(i).get(0)))
                {
                    numb = i;
                    t = (Integer.toString(Integer.parseInt(prod.get(numb).get(1)) + Integer.parseInt(number)));
                    break;
                }
            }
            purch1.add(t);
            if (numb != -1)
            {
                prod.remove(numb);
                prod.add(purch1);
            }
            else
            {
                prod.add(purch1);
            }

            System.out.println("OK");
            return "OK";
        }
        else
        {
            System.out.println("ERROR");
            return "ERROR";
        }
    }

    public static String addDemand(String name, String number, String price, String date)
    {
        boolean c = false;
        for (int i = 0; i < Sklad.products.size(); i++)
        {
            if (name.equals(Sklad.products.get(i)))
            {
                c = true;
                break;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat(Sklad.FORMAT);
        sdf.setLenient(false);
        try
        {
            Date d = sdf.parse(date);
        }
        catch (ParseException ex)
        {
            c = false;
        }
        int numb = 0;
        int n = -1;
        for (int i = 0; i < prod.size(); i++)
        {
            if (name.equals(prod.get(i).get(0)))
            {
                numb = Integer.parseInt(prod.get(i).get(1));
                n = i;
                break;
                
            }
        }
        if ((c == true) && (Integer.parseInt(number) > 0) && (Double.parseDouble(price) > 0) && (Integer.parseInt(number) <= numb))
        {
            ArrayList<String> dem = new ArrayList<String>();
            dem.add(name);
            dem.add(number);
            dem.add(price);
            dem.add(date);
            Sklad.demand.add(dem);
            String t = (Integer.toString(numb - Integer.parseInt(number)));
            prod.get(n).set(1, t);
            System.out.println("OK");
            return "OK";
        }
        else
        {
            System.out.println("ERROR");
            return "ERROR";
        }
    }

    public static void ShowProduct()
    {
        for (int i = 0; i < Sklad.products.size(); i++)
        {
            System.out.println(Sklad.products.get(i));
        }
    }

    public static void ShowDemand()
    {
        for (int i = 0; i < Sklad.demand.size(); i++)
        {
            for (int j = 0; j < Sklad.demand.get(i).size(); j++)
            {
                System.out.println(Sklad.demand.get(i).get(j) + " ");
            }
        }
        System.out.println();
    }

    public static void ShowPurchase()
    {
        for (int i = 0; i < Sklad.purchase.size(); i++)
        {
            for (int j = 0; j < Sklad.purchase.get(i).size(); j++)
            {
                System.out.println(Sklad.purchase.get(i).get(j) + " ");
            }
        }
        System.out.println();
    }

    public static void sortDemand() throws ParseException
    {
        for (int i = Sklad.demand.size() - 1; i > 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                SimpleDateFormat sdf1 = new SimpleDateFormat(Sklad.FORMAT);
                Date dat1 = sdf1.parse(Sklad.demand.get(j).get(3));
                Date dat2 = sdf1.parse(Sklad.demand.get(j + 1).get(3));
                if (dat1.after(dat2))
                {
                    List<String> tmp = Sklad.demand.get(j);
                    Sklad.demand.remove(j);
                    Sklad.demand.add(j + 1, tmp);

                }
            }
        }
    }

    public static void sortPurchase() throws ParseException
    {
        for (int i = Sklad.purchase.size() - 1; i > 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                SimpleDateFormat sdf1 = new SimpleDateFormat(Sklad.FORMAT);
                Date dat1 = sdf1.parse(Sklad.purchase.get(j).get(3));
                Date dat2 = sdf1.parse(Sklad.purchase.get(j + 1).get(3));
                if (dat1.after(dat2))
                {
                    List<String> tmp = Sklad.purchase.get(j);
                    Sklad.purchase.remove(j);
                    Sklad.purchase.add(j + 1, tmp);

                }
            }
        }
    }

    public static double SalesReport(String name, String date) throws ParseException
    {
        double sum = 0;
        int count = 0;
        sortDemand();
        sortPurchase();
        for (int i = 0; i < Sklad.demand.size(); i++)
        {
            for (int j = 0; j < Sklad.demand.get(i).size(); j++)
            {
                if (Sklad.demand.get(i).get(0).equals(name))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(Sklad.FORMAT);
                    Date d = sdf.parse(date);
                    Date d1 = sdf.parse(Sklad.demand.get(i).get(3));
                    if (d1.before(d))
                    {
                        sum += Integer.parseInt(Sklad.demand.get(i).get(1)) * Double.parseDouble((Sklad.demand.get(i).get(2)));
                        count += Integer.parseInt(Sklad.demand.get(i).get(1));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < Sklad.purchase.size(); i++)
        {
            for (int j = 0; j < Sklad.purchase.get(i).size(); j++)
            {
                if (Sklad.purchase.get(i).get(0).equals(name))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(Sklad.FORMAT);
                    Date d = sdf.parse(date);
                    Date d1 = sdf.parse(Sklad.purchase.get(i).get(3));
                    if (d1.before(d))
                    {
                        if (Integer.parseInt(Sklad.purchase.get(i).get(1)) <= count)
                        {
                            sum -= Integer.parseInt(Sklad.purchase.get(i).get(1)) * Double.parseDouble((Sklad.purchase.get(i).get(2)));
                            count = count - Integer.parseInt(Sklad.purchase.get(i).get(1));
                        }
                        else
                        {
                            sum -= count * Double.parseDouble((Sklad.purchase.get(i).get(2)));
                        }
                        break;
                    }
                }
            }
            if (count == 0)
            {
                break;
            }
        }

        return sum;
    }
}
