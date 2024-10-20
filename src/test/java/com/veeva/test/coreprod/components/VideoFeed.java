/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.coreprod.components;

import java.util.Date;
/**
 * Represents a VideoFeed with a title and the date it was posted.
 *
 * <p>This class provides details of the video such as its title and
 * the date it was posted. It can be extended or used as is to manage
 * video feeds in an application.</p>
 *
 * Fields:
 * - title: the title of the video.
 * - postedOn: the date the video was posted.
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class VideoFeed {
    private String title;
    private Date postedOn;
    //We can define more based on usage.
    // Getters and setters (if needed)
    public String getTitle() {
        return title;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public VideoFeed(String title, Date postedOn)
    {
        this.title=title;
        this.postedOn = postedOn;
    }

}
