package page;

import core.BasePage;
import core.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AmazonHomePage extends BasePage {
    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type=\"text\"]")
    private WebElement searchTextboxAmz;
    @FindBy(xpath = "//input[@id=\"nav-search-submit-button\"]")
    private WebElement searchButtonAmz;
    @FindBy(xpath = "//div[@class=\"s-matching-dir sg-col-16-of-20 sg-col sg-col-8-of-12 sg-col-12-of-16\"]//span[contains(text(),'Apple')]")
    private List<WebElement> listIphone;
    @FindBy(xpath = "//div[@class=\"a-section a-spacing-none a-spacing-top-small s-price-instructions-style\"]//div[@class=\"a-row a-size-base a-color-base\"]")
    private List<WebElement> listPriceAmz;
    @FindBy(xpath = "//div[@class=\"s-matching-dir sg-col-16-of-20 sg-col sg-col-8-of-12 sg-col-12-of-16\"]//div[@class=\"s-card-container s-overflow-hidden aok-relative puis-include-content-margin s-latency-cf-section s-card-border\"]")
    private List<WebElement> listProductAmz;


    public void navigateAmazoneHomePage(String url) {
        getDriver().get(url);
    }

    public void clickSearchTextboxAmz() {
        clickElement(searchTextboxAmz);
    }

    public void senkeySearchTextboxAmz(String value) {
        senKeyElement(searchTextboxAmz,value);
    }

    public void clickSearchButtonAmz() {
        clickElement(searchButtonAmz);
    }

    public void verifyListProductIphoneAmz(String value) {
        getExplicitWait().until(ExpectedConditions.visibilityOfAllElements(listIphone));
        for (WebElement product : listIphone) {
            Assert.assertTrue(product.getText().toLowerCase().contains(value));
        }
    }

    public List<Product> getListProductAmz(String value) {
        List<Product> ls = new ArrayList<>();
        for (WebElement pro : listProductAmz) {
            Product product = new Product();
            if(pro.getText().toLowerCase().contains(value)){
                if(pro.findElement(By.xpath(".//span[@class=\"a-size-medium a-color-base a-text-normal\"]")).getText().contains("Carrier Subscription")){
                    continue;
                }
            String name = pro.findElement(By.xpath(".//span[@class=\"a-size-medium a-color-base a-text-normal\"]")).getText();
            product.setNameProduct(name);
            int price = Integer.parseInt(pro.findElement(By.xpath(".//span[@class=\"a-price-whole\"]")).getText().replace(",",""));
            product.setPrice(price*23200);
            String link = pro.getAttribute("href");
            product.setLinkProduct(link);
            ls.add(product);}
        }
        return ls;
    }
    public void sort(List<Product> ls) {
        Collections.sort(ls, new Comparator<Product>() {
            public int compare(Product s1, Product s2) {
                return (int) (s1.getPrice() - s2.getPrice());
            }
        });
    }
}