/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/com/veeva/test/coreprod/features/shop","src/test/java/com/veeva/test/coreprod/features/newsfeeds"},  // Path to your feature files
        glue = {"com/veeva/test/hooks","com.veeva.test.coreprod.stepdefs.shop","com.veeva.test.coreprod.stepdefs.newsfeeds"},                    // /*
        plugin = {"pretty", "html:target/cucumber-reports.html"}  // Optional: Generate reports
)
public class NGRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
