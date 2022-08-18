package test;

import core.BaseTest;
import core.ExcelUtils;
import core.Product;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.AmazonHomePage;
import page.TikiHomePage;
import java.util.*;

public class Tests extends BaseTest {
    String target = "iPhone 11";
    List<Product> ls1 = new ArrayList<>();
    List<Product> ls2 = new ArrayList<>();
    String path = System.getProperty("user.dir");
   @Test(dataProvider = "data")
    public void comparePrice(String urlTiki,String nameProduct,String urlAmzon) {
        TikiHomePage tikiHomePage = new TikiHomePage(getDriver());
        AmazonHomePage amazonHomePage = new AmazonHomePage(getDriver());
        tikiHomePage.navigateTikiHomePage(urlTiki);
        tikiHomePage.sendKeySearchTextbox(nameProduct);
        tikiHomePage.clickSearchButton();
        tikiHomePage.verifyListProductIphone(nameProduct);
        ls1 = tikiHomePage.getListProduct();
        amazonHomePage.navigateAmazoneHomePage(urlAmzon);
        amazonHomePage.clickSearchTextboxAmz();
        amazonHomePage.senkeySearchTextboxAmz(nameProduct);
        amazonHomePage.clickSearchButtonAmz();
        ls2 = amazonHomePage.getListProductAmz(nameProduct);
        ls1.addAll(ls2);
        amazonHomePage.sort(ls1);
        for (Product ls : ls1) {
            System.out.println(ls);
        }
    }
    @DataProvider(name = "data")
    public Object[][] getData() {
        ExcelUtils excelUtils = new ExcelUtils();
        return excelUtils.getTableArray(path+"\\Book1.xlsx", "Sheet1", 0, 3);
    }
}
