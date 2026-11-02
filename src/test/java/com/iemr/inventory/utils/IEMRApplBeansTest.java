/*
* AMRIT - Accessible Medical Records via Integrated Technologies
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

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.iemr.inventory.utils.gateway.email.EmailService;
import com.iemr.inventory.utils.gateway.email.GenericEmailServiceImpl;

@DisplayName("IEMRApplBeans Test Suite")
class IEMRApplBeansTest {

	private IEMRApplBeans beans;

	@BeforeEach
	@DisplayName("Create the bean configuration before each test")
	void setUp() {
		beans = new IEMRApplBeans();
	}

	@Test
	@DisplayName("getEmailService should supply the generic email implementation")
	void getEmailService_shouldSupplyGenericImplementation() {
		EmailService emailService = beans.getEmailService();

		assertNotNull(emailService);
		assertInstanceOf(GenericEmailServiceImpl.class, emailService);
	}

	@Test
	@DisplayName("getJavaMailSender should supply a JavaMailSender implementation")
	void getJavaMailSender_shouldSupplyMailSenderImplementation() {
		JavaMailSender mailSender = beans.getJavaMailSender();

		assertNotNull(mailSender);
		assertInstanceOf(JavaMailSenderImpl.class, mailSender);
	}

	@Test
	@DisplayName("configProperties should supply a fresh properties holder on each call")
	void configProperties_shouldSupplyFreshHolder() {
		assertNotNull(beans.configProperties());
		assertNotSame(beans.configProperties(), beans.configProperties());
	}
}
