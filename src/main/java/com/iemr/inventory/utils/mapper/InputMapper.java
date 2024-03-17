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
package com.iemr.inventory.utils.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;

@Service
public class InputMapper {
    static GsonBuilder builder;
    ExclusionStrategy strategy;
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public InputMapper() {
        if (builder == null) {
            builder = new GsonBuilder();
            builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        }
    }

    public static InputMapper gson() {
        return new InputMapper();
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return builder.create().fromJson(json, classOfT);
    }

}