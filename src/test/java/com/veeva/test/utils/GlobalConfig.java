/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.utils;

import com.veeva.test.coreprod.stepdefs.shop.JacketsShopping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This Global Config is responsible for reading the global configuration.
 *  Currently the configuration is defined in resources/config.properties
 *   Below are the global configuraiton implemented as of now.
 *   - browserName
 *   - baseURL
 * @author Nagender Bojjawar
 * @date 2024-10-18
 */
public class GlobalConfig {
    private final Properties properties;
    private static GlobalConfig config;
    private static final Logger logger = LoggerFactory.getLogger(GlobalConfig.class);
    private GlobalConfig()
    {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            logger.error("Unable to load the global config properties file."+e.getMessage());
        }
    }

    public static GlobalConfig getInstance()
    {
        if(config == null){
            config = new GlobalConfig();
        }
        return config;
    }
    /**
     * Reeturns the browsername to be used from global config
     *
     * @author Nagender Bojjawar
     * @date 2024-10-18
     */
    public String getBrowser()
    {
        return properties.getProperty("browser");
    }
    /**
     * Reeturns the baseURL to be used from global config
     *
     * @author Nagender Bojjawar
     * @date 2024-10-18
     */
    public String getBaseURL(){
        return properties.getProperty("baseURL");
    }
}
