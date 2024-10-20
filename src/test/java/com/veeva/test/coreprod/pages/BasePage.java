/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.pages;

import com.veeva.test.utils.GlobalConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * This method loads given endpoint string appending to baseURL.
     *
     * @param endPoint the first integer
     *
     * Example usage:
     * <pre>
     *     .load(EndPoint.COREPRODUCT.url);
     *     This will navigate to https://www.nba.com/warriors
     *     Where https://www.nba.com/ is baseURL from config.properties
     * </pre>
     * @author Nagender Bojjawar
     * @date 2024-10-18
     */
    public void load(String endPoint){
//        driver.get("https://www.nba.com/" + endPoint);
        driver.get(GlobalConfig.getInstance().getBaseURL() + endPoint);
    }

    //This will be overridden on child pages based on critieria to define the page load
    public void waitForPageToLoad()
    {

    }
    protected WebDriver driver;
    protected WebDriverWait wait;


}
