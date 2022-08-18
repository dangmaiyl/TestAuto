package page;

import core.BasePage;
import core.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TikiHomePage extends BasePage {

    public TikiHomePage(WebDriver driver){
        super(driver);}
    @FindBy(xpath = "//input[@type=\"text\"]")
    private WebElement searchTextbox;
    @FindBy(xpath = "//button[@data-view-id=\"main_search_form_button\"]")
    private WebElement  searchButton;
    @FindBy(xpath = "//div[@class='inner']//div[@class='name']")
    private List<WebElement> productIphone11;
    @FindBy(xpath = "//div[@class='inner']//div[@class='price-discount__price']")
    private List<WebElement> listPrice;
    @FindBy(xpath = "//div[@class='inner']//a[@class='product-item']")
    private List<WebElement> listProduct;


    public void clickSearchTextbox(){
        clickElement(searchTextbox);
    }

    public void sendKeySearchTextbox(String value){
        senKeyElement(searchTextbox, value);
    }

    public void clickSearchButton(){
        clickElement(searchButton);
    }

    public void navigateTikiHomePage(String url){
        getDriver().get(url);
    }

    public void verifyListProductIphone(String value){
        getExplicitWait().until(ExpectedConditions.visibilityOfAllElements(productIphone11));
        for (WebElement product : productIphone11) {
            if(product.getText().toLowerCase().contains(value))
            Assert.assertTrue(product.getText().toLowerCase().contains(value));
            else
                continue;
        }
    }
    public List<Product> getListProduct(){
        List<Product> ls= new ArrayList<>();
        for(WebElement pro : listProduct) {
            Product product= new Product();
            String name= pro.findElement(By.xpath(".//div[@class='name']")).getText();
            product.setNameProduct(name);
            int price= Integer.parseInt(pro.findElement(By.xpath(".//div[@class='price-discount__price']")).getText().split(" ")[0].replace(".", ""));
            product.setPrice(price);
            String link= pro.getAttribute("href");
            product.setLinkProduct(link);
            ls.add(product);
        }
        return ls;
    }
    public void sort(List<Product> ls){
        Collections.sort(ls, new Comparator<Product>(){
            public int compare(Product s1, Product s2) {
                return (int) (s1.getPrice()- s2.getPrice());
            }
        });
    }
//    public void openNewWindow(){
//        ((JavascriptExecutor) driver).executeScript("window.open()");
//        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));
//    }

}
