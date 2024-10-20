/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.pages;

import org.openqa.selenium.WebDriver;

/**
 * Represents the PageFactory Manager
 *  All the pages of the applications can be obtained using this PageFacotry Manager class.
 *  If you create new Page in application. Don't forget to add here :)
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class PageFactoryManager {

    private static CoreProductHomePage cpHomePage;
    private static ShopPage shopPage;
    private static NewsPage newsPage;

    public static CoreProductHomePage getCpHomePage(WebDriver driver){
        return cpHomePage == null ? new CoreProductHomePage(driver) : cpHomePage;
    }

    public static ShopPage getShopPage(WebDriver driver)
    {
        return shopPage==null ? new ShopPage(driver) : shopPage;
    }
    public static NewsPage getNewsPage(WebDriver driver)
    {
        return newsPage==null ? new NewsPage(driver) : newsPage;
    }
}
