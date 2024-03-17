package com.iemr.inventory.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(classes = {BlockingMethodInterceptor.class})
@ExtendWith(SpringExtension.class)
class BlockingMethodInterceptorTest {
    @Autowired
    private BlockingMethodInterceptor blockingMethodInterceptor;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link BlockingMethodInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     *   <li>
     * {@link BlockingMethodInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws Exception {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   There are no fields that could be asserted on.

        // Arrange
        BlockingMethodInterceptor blockingMethodInterceptor = new BlockingMethodInterceptor();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();

        // Act
        blockingMethodInterceptor.afterCompletion(request, response, "Handler", new Exception("foo"));
        MockHttpServletRequest request2 = new MockHttpServletRequest();
        Response response2 = new Response();
        blockingMethodInterceptor.postHandle(request2, response2, "Handler", new ModelAndView("View Name"));
    }

    /**
     * Method under test:
     * {@link BlockingMethodInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle() throws Exception {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getMethod()).thenReturn("https://example.org/example");
        HttpServletResponseWrapper response = mock(HttpServletResponseWrapper.class);
        doNothing().when(response).setStatus(anyInt());

        // Act
        boolean actualPreHandleResult = blockingMethodInterceptor.preHandle(request, response, "Handler");

        // Assert
        verify(request).getMethod();
        verify(response).setStatus(eq(405));
        assertFalse(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link BlockingMethodInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle2() throws Exception {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getMethod()).thenReturn("GET");

        // Act
        boolean actualPreHandleResult = blockingMethodInterceptor.preHandle(request, mock(HttpServletResponseWrapper.class),
                "Handler");

        // Assert
        verify(request).getMethod();
        assertTrue(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link BlockingMethodInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle3() throws Exception {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getMethod()).thenReturn("POST");

        // Act
        boolean actualPreHandleResult = blockingMethodInterceptor.preHandle(request, mock(HttpServletResponseWrapper.class),
                "Handler");

        // Assert
        verify(request).getMethod();
        assertTrue(actualPreHandleResult);
    }
}
