/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.stepdefs.shop;

import com.veeva.test.constants.EndPoint;
import com.veeva.test.context.TestContext;
import com.veeva.test.coreprod.pages.ShopPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.veeva.test.coreprod.pages.CoreProductHomePage;
import com.veeva.test.coreprod.pages.PageFactoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Represents the Step Definitions  of Shopping Page of the application,
 *
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class JacketsShopping {

    private final CoreProductHomePage cpHomePage;
    private final ShopPage shopPage;
    private final TestContext context;
    private static final Logger logger = LoggerFactory.getLogger(JacketsShopping.class);


    public JacketsShopping(TestContext context)
    {
        this.context = context;
        cpHomePage = PageFactoryManager.getCpHomePage(context.driver);
        shopPage = PageFactoryManager.getShopPage(context.driver);
    }

    @Given("I am on the Core Product home page")
    public void i_am_on_the_core_product_home_page() {
            cpHomePage.load(EndPoint.COREPRODUCT.url);
            cpHomePage.waitForPageToLoad();
    }
    @When("I navigate to the Shop Menus Mens category")
    public void i_navigate_to_the_shop_menus_mens_category() {
        cpHomePage.navigateToMensCategory();
    }
    @Then("I should find all jackets across all paginated pages")
    public void i_should_find_all_jackets_across_all_paginated_pages() {
        shopPage.waitForPageToLoad();
        shopPage.filterJackets();
    }
    @Then("I store each jacket's price, title, and top seller message in a text file")
    public void i_store_each_jacket_s_price_title_and_top_seller_message_in_a_text_file() {
        shopPage.printJacketsPrices();
    }
}
