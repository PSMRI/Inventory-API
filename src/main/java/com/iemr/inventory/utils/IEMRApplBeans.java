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
package com.iemr.inventory.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.iemr.inventory.utils.config.ConfigProperties;
import com.iemr.inventory.utils.gateway.email.EmailService;
import com.iemr.inventory.utils.gateway.email.GenericEmailServiceImpl;
import com.iemr.inventory.utils.redis.RedisStorage;
import com.iemr.inventory.utils.sessionobject.SessionObject;


@Configuration
public class IEMRApplBeans {
    private @Value("${spring.data.redis.host}") String redisHost;
    private @Value("${spring.data.redis.port}") int redisPort;

    @Bean
    public EmailService getEmailService() {
        EmailService emailService = new GenericEmailServiceImpl();
        return emailService;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSender mailSender = new JavaMailSenderImpl();
        return mailSender;
    }

    @Bean
    public ConfigProperties configProperties() {
        return new ConfigProperties();
    }

    @Bean
    public SessionObject sessionObject() {
        return new SessionObject();
    }

    @Bean
    public RedisStorage redisStorage() {
        return new RedisStorage();
    }

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        System.out.print("Connecting to Redis " + redisHost + ":" + redisPort);

        return new LettuceConnectionFactory(redisHost, redisPort);
    }

}
