/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.hooks;

import com.veeva.test.utils.DriverFactory;
import com.veeva.test.context.TestContext;
import com.veeva.test.utils.GlobalConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

/**
 * All the Global hooks are listed here.
 *  Currently
 *   - i added the Driver Initializtion.
 *   - driver exit
 *
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class GlobalHooks {
    private WebDriver driver;
    private final TestContext context;

    public GlobalHooks(TestContext context){
        this.context = context;
    }

    @Before
    public void before(Scenario scenario){
        System.out.println("BEFORE: SCENARIO NAME: " + scenario.getName());
        driver = DriverFactory.initializeDriver(GlobalConfig.getInstance().getBrowser());
        context.driver = driver;
    }

    @After
    public void after(Scenario scenario){
        System.out.println("AFTER: THREAD ID : SCENARIO NAME: " + scenario.getName());
        driver.quit();
    }
}
