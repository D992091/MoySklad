/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.ParseException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class JUnitTest
{
    //Проверка добавления товара, и повторного добавления товара
    @Test
    public void TestNewproduct(){
        assertEquals("OK", Functions.addProduct("IPHONE"));
        assertEquals("ERROR", Functions.addProduct("IPHONE"));
        assertEquals("OK", Functions.addProduct("SAMSUNG"));
    }
    //Проверка покупки товара, и покупки товара который не был зарегестрирован
    @Test
    public void TestPurchase() {
        Functions.addProduct("IPHONEX");
        assertEquals("OK",Functions.addPurchase("IPHONEX", "1","1000", "01.01.2019"));
        assertEquals("ERROR",Functions.addPurchase("IPHONEXR", "1","1000", "01.01.2019"));
    }
    //Проверка рассчёта прибыльности
    @Test
    public void TestSalesReport() throws ParseException{
        Functions.addProduct("IPHONEXS");
        Functions.addPurchase("IPHONEXS", "1","1000", "01.01.2019");
        Functions.addPurchase("IPHONEXS", "2","2000", "01.02.2019");
        Functions.addDemand("IPHONEXS", "2","5000", "01.03.2019");
        Assert.assertEquals(7000.0, Functions.SalesReport("IPHONEXS", "02.03.2019"));
    }
    //Проверка продажи незарегестрированного товара
    @Test
    public void TestDemand() {
        assertEquals("ERROR",Functions.addDemand("XIAOMI", "1","1000", "01.01.2019"));
    }
   //Проверка продажи большего количества товара,чем имеется
    @Test
    public void TestDemand1() {
        Functions.addProduct("XIAOMI1");
        Functions.addPurchase("XIAOMI1", "1","1000", "01.01.2019");
        assertEquals("ERROR",Functions.addDemand("XIAOMI1", "2","1000", "01.01.2019"));
    }
     //Проверка рассчёта прибыльности2
    @Test
    public void TestSalesReport1() throws ParseException{
        Functions.addProduct("IPHONEXR");
        Functions.addPurchase("IPHONEXR", "1","1000", "01.01.2019");
        Functions.addPurchase("IPHONEXR", "2","2000", "01.02.2019");
        Functions.addDemand("IPHONEXR", "2","5000", "01.03.2019");
        Assert.assertEquals(7000.0, Functions.SalesReport("IPHONEXR", "02.03.2019"));
        Functions.addPurchase("IPHONEXR", "2","3000", "04.03.2019");
        Functions.addDemand("IPHONEXR", "2","4000", "05.03.2019");
        Assert.assertEquals(10000.0, Functions.SalesReport("IPHONEXR", "06.03.2019"));
    }
    //Проверка продажи товара
     @Test
    public void TestDemand2() {
        Functions.addProduct("XIAOMI2");
        Functions.addPurchase("XIAOMI2", "1","1000", "01.01.2019");
        assertEquals("OK",Functions.addDemand("XIAOMI2", "1","3000", "01.01.2019"));
    }
    //Проверка продажи товара на корректность
     @Test
    public void TestDemand3() {
        Functions.addProduct("NOKIA");
        assertEquals("ERROR",Functions.addDemand("NOKIA", "0","3000", "01.01.2019"));
        assertEquals("ERROR",Functions.addDemand("NOKIA", "1","0", "01.01.2019"));
        assertEquals("ERROR",Functions.addDemand("NOKIA", "1","1000", "20.20.2000"));
    }
    //Проверка приобретения товара на корректность
     @Test
    public void TestPurchase1() {
        Functions.addProduct("HONOR");
        assertEquals("ERROR",Functions.addPurchase("HONOR", "0","1000", "01.01.2019"));
        assertEquals("ERROR",Functions.addPurchase("HONOR", "1","0", "01.01.2019"));
        assertEquals("ERROR",Functions.addPurchase("HONOR", "1","1000", "20.20.2019"));
    }
    public JUnitTest()
    {
        
    }
}
