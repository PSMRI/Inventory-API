package com.iemr.inventory.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.utils.http.HTTPRequestInterceptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@ContextConfiguration(classes = {HttpInterceptorConfig.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class HttpInterceptorConfigTest {
    @MockBean
    private HTTPRequestInterceptor hTTPRequestInterceptor;

    @Autowired
    private HttpInterceptorConfig httpInterceptorConfig;

    /**
     * Method under test:
     * {@link HttpInterceptorConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    void testAddInterceptors() {
        // Arrange
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        when(registry.addInterceptor(Mockito.<HandlerInterceptor>any()))
                .thenReturn(new InterceptorRegistration(new BlockingMethodInterceptor()));

        // Act
        httpInterceptorConfig.addInterceptors(registry);

        // Assert that nothing has changed
        verify(registry).addInterceptor(Mockito.<HandlerInterceptor>any());
    }
}
