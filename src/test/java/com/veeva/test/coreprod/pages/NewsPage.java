/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.pages;

import com.veeva.test.coreprod.components.VideoFeed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Represents the News and Features  Page (https://www.nba.com/warriors/news) of the application, providing methods to interact with
 * the elements on the page using the Page Object Model (POM) design pattern.
 *
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class NewsPage extends  BasePage{

    public NewsPage(WebDriver driver) {
        super(driver);
    }


    public void countTheTotalNumberOfVideos()
    {
        List <VideoFeed> videoFeedList = prepareVideoFeeds();
        logger.info(Arrays.toString(videoFeedList.toArray()));
    }

    @Override
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(postDuration));
        Pattern pattern = Pattern.compile(".*[^\\s:].*|:.*");
        wait.until(ExpectedConditions.textMatches(By.cssSelector("div[data-testid='post-duration']"), pattern));
        logger.info("POST DURATION"+postDuration.getText());
        wait.until(ExpectedConditions.elementToBeClickable(linkInsiderEmail));
        Actions actions = new Actions(driver);
        actions.moveToElement(linkInsiderEmail).perform();
        super.waitForPageToLoad();
    }

    public void listOnlyTheVideosThatPostedInLastDays(int last_days)
    {
        logger.info(" === TOTAL VIDEOS POSTED IN LAST "+ last_days + " DAYS === ");
        VideoFeed currVideo;
        //
        long currentTimeInMilliSec = new Date().getTime();
        long diffTimeInMilliSeconds;
        long diffTimeInDays;
        for(int i=0; i< videoFeedOjects.size(); i++)
        {
            currVideo =  videoFeedOjects.get(i);
            diffTimeInMilliSeconds = currentTimeInMilliSec - currVideo.getPostedOn().getTime();
            diffTimeInDays = diffTimeInMilliSeconds/ (1000 * 60 * 60 * 24);
            if(diffTimeInDays <= last_days)
            {
                logger.info(currVideo.getTitle() );
            }
        }
    }

    public List<VideoFeed> prepareVideoFeeds()
    {
        if(videoFeedOjects.size()==0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy");
//            logger.info("videoTiles size" + videoTiles.size());
            for (WebElement videoFeed : videoTiles) {
                WebElement titleElement = videoFeed.findElement(By.tagName("h3"));
                WebElement timeElement = videoFeed.findElement(By.tagName("time"));
                String title = titleElement.getText();
                String postedOn = timeElement.getAttribute("datetime");
//                System.out.println(" Title: " + title);
//                System.out.println(" PostedOn: " + postedOn);
                try {
                    videoFeedOjects.add(new VideoFeed(title, dateFormat.parse(postedOn)));
                } catch (ParseException ex) {
                    logger.error("Unable to parse the Video time");
                }
                //videoFeed(title+","+price);
            }
//            logger.info(" === TOTAL NUMBER OF VIDEO FEEDS ARE: "+ videoFeedOjects.size());
        }
        logger.info(" === TOTAL NUMBER OF VIDEO FEEDS ARE: "+ videoFeedOjects.size());
        return videoFeedOjects;
    }

    @FindBy(xpath = "(//div[@data-testid='content-grid'])[2]//div[@data-testid='tile-article' or @data-testid='tile-featured-article']") private  List<WebElement> videoTiles;
    @FindBy(xpath = "//div[@data-testid='post-duration']") private WebElement postDuration; //Video duration of one of video is populated. Ex: 00:07:04
    @FindBy(xpath = "//a[text()='Insider Email']") private WebElement linkInsiderEmail;
    private static final Logger logger = LoggerFactory.getLogger(NewsPage.class);
    private List<VideoFeed> videoFeedOjects = new ArrayList<>();;

}
