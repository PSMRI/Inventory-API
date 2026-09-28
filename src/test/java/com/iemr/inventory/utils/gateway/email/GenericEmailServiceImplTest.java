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
package com.iemr.inventory.utils.gateway.email;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
@DisplayName("GenericEmailServiceImpl Test Suite")
class GenericEmailServiceImplTest {

	private static final String SINGLE_RECIPIENT =
			"{\"to\":\"ops@example.org\",\"from\":\"inventory@example.org\","
					+ "\"subject\":\"Low stock\",\"message\":\"Paracetamol is running low\"}";

	@Mock
	private JavaMailSender javaMailSender;

	private GenericEmailServiceImpl emailService;

	@BeforeEach
	@DisplayName("Wire the service with a mocked mail sender")
	void setUp() {
		emailService = new GenericEmailServiceImpl();
		emailService.setJavaMailSender(javaMailSender);
	}

	private SimpleMailMessage captureSentMessage() {
		ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(javaMailSender).send(captor.capture());
		return captor.getValue();
	}

	@Test
	@DisplayName("sendEmail(payload, template) should send the single recipient described by the payload")
	void sendEmailWithTemplate_shouldSendSingleRecipient() {
		emailService.sendEmail(SINGLE_RECIPIENT, "low-stock-template");

		SimpleMailMessage sent = captureSentMessage();
		assertArrayEquals(new String[] { "ops@example.org" }, sent.getTo());
		assertEquals("inventory@example.org", sent.getFrom());
		assertEquals("Low stock", sent.getSubject());
		assertEquals("Paracetamol is running low", sent.getText());
	}

	@Test
	@DisplayName("sendEmail(payload) should split a semicolon-separated recipient list")
	void sendEmail_shouldSplitRecipientList() {
		emailService.sendEmail("{\"to\":\"ops@example.org;stores@example.org\","
				+ "\"from\":\"inventory@example.org\",\"subject\":\"Low stock\",\"message\":\"Running low\"}");

		assertArrayEquals(new String[] { "ops@example.org", "stores@example.org" }, captureSentMessage().getTo());
	}

	@Test
	@DisplayName("sendEmail(payload) should send a lone recipient unchanged")
	void sendEmail_shouldSendLoneRecipientUnchanged() {
		emailService.sendEmail(SINGLE_RECIPIENT);

		assertArrayEquals(new String[] { "ops@example.org" }, captureSentMessage().getTo());
	}

	@Test
	@DisplayName("sendEmail should refuse a payload that is missing a required field")
	void sendEmail_shouldRefusePayloadMissingField() {
		assertThrows(JSONException.class, () -> emailService.sendEmail("{\"to\":\"ops@example.org\"}"));

		verifyNoInteractions(javaMailSender);
	}

	@Test
	@DisplayName("sendEmailWithAttachment is not implemented and should send nothing")
	void sendEmailWithAttachment_shouldSendNothing() {
		emailService.sendEmailWithAttachment(SINGLE_RECIPIENT, "low-stock-template");

		verifyNoInteractions(javaMailSender);
	}
}
