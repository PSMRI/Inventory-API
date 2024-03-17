/*
 * AMRIT – Accessible Medical Records via Integrated Technology
 * Integrated EHR (Electronic Health Records) Solution
 *
 * Copyright (C) "Piramal Swasthya Management and Research Institute"
 *
 * This file is part of AMRIT.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */
package com.iemr.inventory.utils.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:/application.properties")
@Component
public class ConfigProperties {
    private static Properties properties;
    private static Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
    @Value("${iemr.extend.expiry.time:false}")
    private static Boolean extendExpiryTime;
    @Value("${iemr.session.expiry.time:100}")
    private static Integer sessionExpiryTime;
    private Environment environment;
    @Value("${iemr.redis.url:localhost}")
    private String redisurl;
    @Value("${iemr.redis.port:0000}")
    private Integer redisport;

    public ConfigProperties() {
        initializeProperties();
    }

    private static void initializeProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                InputStream fis = ConfigProperties.class.getResourceAsStream("/application.properties");
                properties.load(fis);
            } catch (IOException e) {
                logger.error("Loading of config file failed with error " + e.getLocalizedMessage(), e);
            }
        }
    }

    public static boolean getExtendExpiryTime() {
        if (extendExpiryTime == null) {
            extendExpiryTime = getBoolean("iemr.session.expiry.time");
        }
        return extendExpiryTime;
    }

    public static int getSessionExpiryTime() {
        if (sessionExpiryTime == null) {
            sessionExpiryTime = getInteger("iemr.session.expiry.time");
        }
        return sessionExpiryTime;
    }

    public static String getPropertyByName(String propertyName) {
        String result = null;
        try {
            if (properties == null) {
                initializeProperties();
            }
            result = properties.getProperty(propertyName);
        } catch (Exception e) {
            logger.error(propertyName + " retrieval failed.", e);
        }
        return result;
    }

    public static Boolean getBoolean(String propertyName) {
        Boolean result = false;
        try {
            result = Boolean.parseBoolean(getPropertyByName(propertyName));
        } catch (Exception e) {
            logger.error(propertyName + " retrieval failed.", e);
        }
        return result;
    }

    public static Integer getInteger(String propertyName) {
        Integer result = 0;
        try {
            result = Integer.parseInt(getPropertyByName(propertyName));
        } catch (NumberFormatException e) {
            logger.error(propertyName + " retrieval failed.", e);
        }
        return result;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getRedisUrl() {
        if (redisurl == null) {
            redisurl = getPropertyByName("iemr.redis.url");
        }
        return redisurl;
    }

    public int getRedisPort() {
        if (redisport == null) {
            redisport = getInteger("iemr.redis.port");
        }
        return redisport;
    }

    public Long getLong(String propertyName) {
        Long result = 0L;
        try {
            result = Long.parseLong(getPropertyByName(propertyName));
        } catch (NumberFormatException e) {
            logger.error(propertyName + " retrieval failed.", e);
        }
        return result;
    }

    public Float getFloat(String propertyName) {
        Float result = 0F;
        try {
            result = Float.parseFloat(getPropertyByName(propertyName));
        } catch (NumberFormatException e) {
            logger.error(propertyName + " retrieval failed.", e);
        }
        return result;
    }

    public String getPassword(String key) {
        String password = "";
        password = getPropertyByName(key);
        if (password != null && password.startsWith("0X10:")) {
            password = new String(Base64.getDecoder().decode(password.split(":")[1]));
        }
        return password;
    }
}
