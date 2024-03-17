package com.iemr.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Provider;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.URLName;

import java.util.Properties;

import org.eclipse.angus.mail.imap.IMAPProvider;
import org.eclipse.angus.mail.imap.IMAPSSLProvider;
import org.eclipse.angus.mail.pop3.POP3Provider;
import org.eclipse.angus.mail.pop3.POP3SSLProvider;
import org.eclipse.angus.mail.smtp.SMTPProvider;
import org.eclipse.angus.mail.smtp.SMTPSSLProvider;
import org.eclipse.angus.mail.smtp.SMTPTransport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.mail.javamail.JavaMailSenderImpl;

class RoleMasterApplicationTest {
    /**
     * Method under test: {@link RoleMasterApplication#configProperties()}
     */
    @Test
    void testConfigProperties() throws NoSuchProviderException {
        // Arrange
        RoleMasterApplication roleMasterApplication = new RoleMasterApplication();

        // Act
        roleMasterApplication.configProperties();

        // Assert
        Session session = ((JavaMailSenderImpl) roleMasterApplication.instantiateBeans().getJavaMailSender()).getSession();
        Provider[] providers = session.getProviders();
        Provider provider = providers[0];
        assertEquals("Oracle", provider.getVendor());
        Provider provider2 = providers[1];
        assertEquals("Oracle", provider2.getVendor());
        Provider provider3 = providers[2];
        assertEquals("Oracle", provider3.getVendor());
        Provider provider4 = providers[3];
        assertEquals("Oracle", provider4.getVendor());
        Provider provider5 = providers[4];
        assertEquals("Oracle", provider5.getVendor());
        Provider provider6 = providers[5];
        assertEquals("Oracle", provider6.getVendor());
        Provider provider7 = providers[6];
        assertEquals("Oracle", provider7.getVendor());
        Provider provider8 = providers[7];
        assertEquals("Oracle", provider8.getVendor());
        Provider.Type type = provider7.getType();
        assertEquals("STORE", type.toString());
        assertEquals("TRANSPORT", provider6.getType().toString());
        assertEquals("imap", provider3.getProtocol());
        assertEquals("imaps", provider4.getProtocol());
        assertEquals("org.eclipse.angus.mail.imap.IMAPSSLStore", provider4.getClassName());
        assertEquals("org.eclipse.angus.mail.imap.IMAPStore", provider3.getClassName());
        assertEquals("org.eclipse.angus.mail.pop3.POP3SSLStore", provider2.getClassName());
        assertEquals("org.eclipse.angus.mail.pop3.POP3SSLStore", provider8.getClassName());
        assertEquals("org.eclipse.angus.mail.pop3.POP3Store", provider.getClassName());
        assertEquals("org.eclipse.angus.mail.pop3.POP3Store", provider7.getClassName());
        assertEquals("org.eclipse.angus.mail.smtp.SMTPSSLTransport", provider6.getClassName());
        assertEquals("org.eclipse.angus.mail.smtp.SMTPTransport", provider5.getClassName());
        assertEquals("pop3", provider.getProtocol());
        assertEquals("pop3", provider7.getProtocol());
        assertEquals("pop3s", provider2.getProtocol());
        assertEquals("pop3s", provider8.getProtocol());
        assertEquals("smtp", provider5.getProtocol());
        Transport transport = session.getTransport();
        URLName uRLName = transport.getURLName();
        assertEquals("smtp", uRLName.getProtocol());
        assertEquals("smtps", provider6.getProtocol());
        assertEquals(-1, uRLName.getPort());
        assertEquals(-1, ((JavaMailSenderImpl) roleMasterApplication.instantiateBeans().getJavaMailSender()).getPort());
        assertEquals(0, ((SMTPTransport) transport).getLastReturnCode());
        assertEquals(0, ((SMTPTransport) transport).getSASLMechanisms().length);
        assertEquals(14, providers.length);
        assertFalse(transport.isConnected());
        assertFalse(session.getDebug());
        assertFalse(((SMTPTransport) transport).getReportSuccess());
        assertFalse(((SMTPTransport) transport).getRequireStartTLS());
        assertFalse(((SMTPTransport) transport).getSASLEnabled());
        assertFalse(((SMTPTransport) transport).getStartTLS());
        assertFalse(((SMTPTransport) transport).getUseCanonicalHostName());
        assertFalse(((SMTPTransport) transport).getUseRset());
        assertFalse(((SMTPTransport) transport).isSSL());
        Properties javaMailProperties = ((JavaMailSenderImpl) roleMasterApplication.instantiateBeans().getJavaMailSender())
                .getJavaMailProperties();
        assertTrue(javaMailProperties.isEmpty());
        assertTrue(((SMTPTransport) transport).getNoopStrict());
        String expectedUsername = System.getProperty("user.name");
        assertEquals(expectedUsername, uRLName.getUsername());
        assertSame(type, provider8.getType());
        assertSame(javaMailProperties, session.getProperties());
        Provider provider9 = providers[10];
        assertSame(provider9, provider9);
        Provider provider10 = providers[11];
        assertSame(provider10, provider10);
        Provider provider11 = providers[12];
        assertSame(provider11, provider11);
        Provider provider12 = providers[13];
        assertSame(provider12, provider12);
        Provider provider13 = providers[8];
        assertSame(provider13, provider13);
        Provider provider14 = providers[9];
        assertSame(provider14, provider14);
    }

    /**
     * Method under test: {@link RoleMasterApplication#instantiateBeans()}
     */
    @Test
    void testInstantiateBeans() {
        // Arrange, Act and Assert
        Provider[] providers = ((JavaMailSenderImpl) (new RoleMasterApplication()).instantiateBeans().getJavaMailSender())
                .getSession()
                .getProviders();
        Provider.Type expectedType = (providers[5]).getType();
        assertSame(expectedType, (providers[4]).getType());
    }

    /**
     * Method under test:
     * {@link RoleMasterApplication#configure(SpringApplicationBuilder)}
     */
    @Test
    void testConfigure() {
        // Arrange
        RoleMasterApplication roleMasterApplication = new RoleMasterApplication();
        Class<Object> forNameResult = Object.class;
        SpringApplicationBuilder builder = new SpringApplicationBuilder(forNameResult);

        // Act and Assert
        assertSame(builder, roleMasterApplication.configure(builder));
    }
}
