/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the CoreProduct (Warriors) Page (https://www.nba.com/warriors/) of the application, providing methods to interact with
 * the elements on the page using the Page Object Model (POM) design pattern.
 *
 * <p>This class contains web element locators and methods to interact with
 * the Home page of Core Product i.e https://www.nba.com/warriors/ .</p>
 *
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class CoreProductHomePage extends  BasePage{

    public CoreProductHomePage(WebDriver driver) {
        super(driver);
    }


    public void hoverToShop()
    {
        wait.until(ExpectedConditions.visibilityOf(linkShop));
        Actions actions = new Actions(driver);
        actions.moveToElement(linkShop).perform();
        wait.until(ExpectedConditions.visibilityOf(subCatMens));
    }

    public void navigateToMensCategory()
    {
        hoverToShop();
        subCatMens.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
//        logger.info("Title:" + driver.getTitle());
    }

    public void hoverToThreeDotsMenu()
    {
        wait.until(ExpectedConditions.visibilityOf(threeDotMenu));
        Actions actions = new Actions(driver);
        actions.moveToElement(threeDotMenu).perform();
        wait.until(ExpectedConditions.visibilityOf(subMenuNewsFeeds));
    }
    public void navigateToNewsFeeds()
    {
        hoverToThreeDotsMenu();
        wait.until(ExpectedConditions.elementToBeClickable(subMenuNewsFeeds));
//        subMenuNewsFeeds.click();
        load("warriors/news");
    }
    @Override
    public void waitForPageToLoad() {

        if (_isDailogDisplayed())
        {
            logger.info("Closing the alert dailog");
            _closeDailogBox();
        }
        wait.until(ExpectedConditions.invisibilityOfAllElements(loadingIcon));
//        wait.until(ExpectedConditions.visibilityOf(grayBGFillers));
        wait.until(ExpectedConditions.invisibilityOfAllElements(grayBGFillers));

        super.waitForPageToLoad();
    }

    private boolean _isDailogDisplayed()
    {
        logger.info("Checking the alert dailog is displayed");
        boolean is_dailog_displayed = false;
        try {

            wait.until(ExpectedConditions.visibilityOf(btnCloseDailog));
            logger.info("The alert dailog is displayed");
            is_dailog_displayed = true;
        }
        catch (Exception ex){
            logger.info("The alert dailog is NOT displayed");
        }
        return  is_dailog_displayed;
    }
    private void _closeDailogBox()
    {
        btnCloseDailog.click();
        wait.until(ExpectedConditions.invisibilityOf(btnCloseDailog));
    }

    @FindBy(xpath = "//span[text()='Shop']") private WebElement linkShop;
//    @FindBy(xpath = "//a[contains(@title, 'Men') and @target='_blank']") private WebElement subCategoryMens;
    @FindBy(xpath = "//li[@data-testid='nav-item-https://shop.warriors.com/golden-state-warriors-men/t-14145130+ga-67+z-978556-2897172570']/a") private WebElement subCatMens;
    @FindBy(xpath = "//div[text()='x']") private WebElement btnCloseDailog;

    @FindBy(xpath = "//li[@data-testid='nav-item-#']") private WebElement threeDotMenu;
    @FindBy(xpath = "//a[contains(@href,'news')]") private WebElement subMenuNewsFeeds;
    @FindBy(css = "div.bg-gray-400") private WebElement grayBGFillers;
    @FindBy(xpath = "//img[contains(@src,'loadingio.svg')]") private WebElement loadingIcon;
    private static final Logger logger = LoggerFactory.getLogger(CoreProductHomePage.class);

}
