/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * Represents the Shopping Page (https://shop.warriors.com/) of the application, providing methods to interact with
 * the elements on the page using the Page Object Model (POM) design pattern.
 *
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */

public class ShopPage extends  BasePage{

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void filterJackets()
    {
        radioButtonJackets.click();
        wait.until(ExpectedConditions.visibilityOf(filteredJacketsSelection));
    }

    public void printJacketsPrices()
    {
        _resetFile();
        _printCurrentlyDisplayedProdcuts();
        while (!_isLastPage()) {
            _goToNextPageAndWaitForPageToLoad();
            _printCurrentlyDisplayedProdcuts();
        }
    }

    private  void _printCurrentlyDisplayedProdcuts()
    {
        logger.info("Current Items Printing are :"+ labelItemCount.getText());
        List<WebElement> productDivs = _getDisplayedProdcuts();
        for (WebElement productDiv : productDivs) {
            WebElement titleElement = productDiv.findElement(By.cssSelector("div.product-card-title a"));
            String title = titleElement.getAttribute("title");
            System.out.println("Product Title: " + title);

            // Find the price using the specified CSS selector
            WebElement priceElement = productDiv.findElement(By.cssSelector("span.price span span.sr-only"));
            String price = priceElement.getText(); // Get the price text
            System.out.println("Product Price: " + price);
            _writeToFile(title+","+price);
        }
    }
    private List<WebElement> _getDisplayedProdcuts()
    {
        return prodcutItems;
    }


    private void _goToNextPageAndWaitForPageToLoad()
    {
        String currentLabelText = labelItemCount.getText();
        logger.info("Waiting for label to change from :"+ currentLabelText);
        btnNextPageEnabled.click();
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.xpath("//div[@data-talos='itemCount']"), currentLabelText)
        ));
    }

    private void _resetFile()
    {
        //Clean the file if exists
        try {
            if (Files.exists(Paths.get(JACKETS_FILE_NAME))) { Files.delete(Paths.get(JACKETS_FILE_NAME)); }
        } catch (IOException e) { logger.error("Error deleting the file: " + e.getMessage()); }

        try {
            listWriter = new BufferedWriter(new FileWriter(JACKETS_FILE_NAME));
        }
         catch (IOException e) {
             logger.error("Error writing to the file: " + e.getMessage());
        }
    }
    private void _writeToFile(String message)
    {
        try {
            listWriter.write(message);
            listWriter.newLine();
        } catch (IOException e) { logger.error("Error while writing the message to the file: " + e.getMessage()); }
    }
    private boolean _isLastPage()
    {
        boolean isLastPage = false;
        try {
            btnNextPageEnabled.isDisplayed();
            isLastPage = false;
        }
        catch (org.openqa.selenium.NoSuchElementException ex) {
            isLastPage = true;
        }
        return isLastPage;
    }
    @Override
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(radioButtonJackets));
        super.waitForPageToLoad();
    }

    @FindBy(xpath = "//li/a[@data-trk-id='side-nav-jackets-all-departments-boxes-15674']") private WebElement radioButtonJackets;
    @FindBy(xpath = "//a[@data-trk-id='jackets' and @aria-label='Remove Jackets filter']") private  WebElement filteredJacketsSelection;
    @FindBy(xpath = "//div[@data-talos='ddPageSize']/../input") private WebElement inputItemsPerPage;  //Ex: 72
    @FindBy(xpath = "//div[@data-talos='itemCount']") private WebElement labelItemCount;    //Ex: 1 - 72 of 336
    @FindBy(css = "li.nex-page.disabled") private  WebElement btnNextPageDisabled;  //lastPage and no next page
    @FindBy(css = "li.next-page:not(.disabled)") private  WebElement btnNextPageEnabled;    //nextPage is available
    @FindBy(css = "div.product-card") private  List<WebElement> prodcutItems;

    private static final Logger logger = LoggerFactory.getLogger(ShopPage.class);
    private String JACKETS_FILE_NAME="jackets_list_with_prices.txt";
    BufferedWriter  listWriter;
}
