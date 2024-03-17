package com.iemr.inventory;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ServletInitializer.class})
@ExtendWith(SpringExtension.class)
class ServletInitializerTest {
    @Autowired
    private ServletInitializer servletInitializer;

    /**
     * Method under test:
     * {@link ServletInitializer#configure(SpringApplicationBuilder)}
     */
    @Test
    void testConfigure() {
        // Arrange
        Class<Object> forNameResult = Object.class;
        SpringApplicationBuilder application = new SpringApplicationBuilder(forNameResult);

        // Act and Assert
        assertSame(application, servletInitializer.configure(application));
    }

    /**
     * Method under test:
     * {@link ServletInitializer#configure(SpringApplicationBuilder)}
     */
    @Test
    void testConfigure2() {
        // Arrange
        Class<Object> forNameResult = Object.class;

        SpringApplicationBuilder application = new SpringApplicationBuilder(forNameResult);
        application.applicationStartup(mock(ApplicationStartup.class));

        // Act and Assert
        assertSame(application, servletInitializer.configure(application));
    }

    /**
     * Method under test: {@link ServletInitializer#onStartup(ServletContext)}
     */
    @Test
    void testGettersAndSetters() throws ServletException {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SpringBootServletInitializer.logger
        //     SpringBootServletInitializer.registerErrorPageFilter

        // Arrange
        ServletInitializer servletInitializer = new ServletInitializer();

        // Act
        servletInitializer.onStartup(new MockServletContext());
        servletInitializer.onStartup(new MockServletContext());
    }
}
