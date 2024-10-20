/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.stepdefs.newsfeeds;

import com.veeva.test.context.TestContext;
import com.veeva.test.coreprod.pages.CoreProductHomePage;
import com.veeva.test.coreprod.pages.NewsPage;
import com.veeva.test.coreprod.pages.PageFactoryManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Step Definitions  of FeedsAndNews of the application,
 *
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class FeedsAndNews {

    private final CoreProductHomePage cpHomePage;
    private final NewsPage newsPage;
    private final TestContext context;

    private static final Logger logger = LoggerFactory.getLogger(FeedsAndNews.class);

    public FeedsAndNews(TestContext testContext)
    {
        this.context = testContext;
        cpHomePage = PageFactoryManager.getCpHomePage(context.driver);
        newsPage = PageFactoryManager.getNewsPage(context.driver);
    }

    @When("I navigate to News and Features Section")
    public void i_navigate_to_news_and_features_section() {
        cpHomePage.navigateToNewsFeeds();
        newsPage.waitForPageToLoad();
    }

    @Then("I count the total number of video feeds")
    public void i_count_the_total_number_of_video_feeds() {
        newsPage.countTheTotalNumberOfVideos();
    }

    @Then("I count the video feeds that are older than {int} days")
    public void i_count_the_video_feeds_that_are_older_than_days(Integer daysOlder) {
        newsPage.listOnlyTheVideosThatPostedInLastDays(daysOlder.intValue());
    }

}
