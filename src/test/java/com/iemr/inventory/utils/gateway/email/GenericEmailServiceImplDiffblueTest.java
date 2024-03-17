package com.iemr.inventory.utils.gateway.email;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GenericEmailServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GenericEmailServiceImplTest {
    @Autowired
    private GenericEmailServiceImpl genericEmailServiceImpl;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GenericEmailServiceImpl#setJavaMailSender(JavaMailSender)}
     *   <li>{@link GenericEmailServiceImpl#sendEmailWithAttachment(String, String)}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     GenericEmailServiceImpl.javaMailSender

        // Arrange
        GenericEmailServiceImpl genericEmailServiceImpl = new GenericEmailServiceImpl();

        // Act
        genericEmailServiceImpl.setJavaMailSender(new JavaMailSenderImpl());
        genericEmailServiceImpl.sendEmailWithAttachment("Json Object", "Template");
    }

    /**
     * Method under test: {@link GenericEmailServiceImpl#sendEmail(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmail() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //    Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.json.JSONException: A JSONObject text must begin with '{' at 1 [character 2 line 1]
        //       at org.json.JSONTokener.syntaxError(JSONTokener.java:503)
        //       at org.json.JSONObject.<init>(JSONObject.java:208)
        //       at org.json.JSONObject.<init>(JSONObject.java:402)
        //       at com.iemr.inventory.utils.gateway.email.GenericEmailServiceImpl.sendEmail(GenericEmailServiceImpl.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        genericEmailServiceImpl.sendEmail("Json Object");
    }

    /**
     * Method under test: {@link GenericEmailServiceImpl#sendEmail(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmail2() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //    Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.json.JSONException: A JSONObject text must begin with '{' at 1 [character 2 line 1]
        //       at org.json.JSONTokener.syntaxError(JSONTokener.java:503)
        //       at org.json.JSONObject.<init>(JSONObject.java:208)
        //       at org.json.JSONObject.<init>(JSONObject.java:402)
        //       at com.iemr.inventory.utils.gateway.email.GenericEmailServiceImpl.sendEmail(GenericEmailServiceImpl.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        genericEmailServiceImpl.sendEmail("Json Object", "Template");
    }
}
