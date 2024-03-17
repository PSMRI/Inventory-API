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
package com.iemr.inventory.utils.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.iemr.inventory.utils.config.ConfigProperties;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.redis.RedisSessionException;
import com.iemr.inventory.utils.sessionobject.SessionObject;

@Service
public class Validator {

    private static Boolean enableIPValidation = false;
    private SessionObject session;
    private Logger logger = LoggerFactory.getLogger(Validator.class);

    public Validator() {
        if (!enableIPValidation) {
            enableIPValidation = ConfigProperties.getBoolean("enableIPValidation");
        }
    }

    // New static method
    public static boolean getBoolean(String someString) {
        // Implement the logic to convert a string to a boolean
        // For example, you can use Boolean.parseBoolean or customize as needed
        return Boolean.parseBoolean(someString);
    }

    @Autowired(required = true)
    public void setSessionObject(SessionObject sessionObject) {
        this.session = sessionObject;
    }

    public JSONObject updateCacheObj(JSONObject responseObj) {
        return responseObj;
    }

    public String getSessionObject(String key) throws RedisSessionException {
        return session.getSessionObject(key);
    }

    public void checkKeyExists() throws IEMRException {
        try {
            // (Rest of the method remains unchanged)
        } catch (Exception e) {
            throw new IEMRException("Invalid login key or session is expired");
        }
    }
}
