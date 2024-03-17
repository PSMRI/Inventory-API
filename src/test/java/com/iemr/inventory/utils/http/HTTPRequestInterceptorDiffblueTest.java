package com.iemr.inventory.utils.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.utils.sessionobject.SessionObject;
import com.iemr.inventory.utils.validator.Validator;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(classes = {HTTPRequestInterceptor.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class HTTPRequestInterceptorTest {
    @Autowired
    private HTTPRequestInterceptor hTTPRequestInterceptor;

    @MockBean
    private SessionObject sessionObject;

    @MockBean
    private Validator validator;

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#setSessionObject(SessionObject)}
     */
    @Test
    void testSetSessionObject() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     HTTPRequestInterceptor.logger
        //     HTTPRequestInterceptor.sessionObject
        //     HTTPRequestInterceptor.validator

        // Arrange
        HTTPRequestInterceptor httpRequestInterceptor = new HTTPRequestInterceptor();

        // Act
        httpRequestInterceptor.setSessionObject(new SessionObject());
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle() throws Exception {
        // Arrange
        doNothing().when(validator).checkKeyExists();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        boolean actualPreHandleResult = hTTPRequestInterceptor.preHandle(request, new Response(), "Object");

        // Assert
        verify(validator).checkKeyExists();
        assertTrue(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle2() throws Exception {
        // Arrange
        doNothing().when(validator).checkKeyExists();
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        when(request.getMethod()).thenReturn("https://example.org/example");
        when(request.getRequestURI()).thenReturn("https://example.org/example");

        // Act
        boolean actualPreHandleResult = hTTPRequestInterceptor.preHandle(request, new Response(), "Object");

        // Assert
        verify(validator).checkKeyExists();
        verify(request, atLeast(1)).getHeader(Mockito.<String>any());
        verify(request, atLeast(1)).getMethod();
        verify(request, atLeast(1)).getRequestURI();
        assertTrue(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle3() throws Exception {
        // Arrange
        doNothing().when(validator).checkKeyExists();
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getHeader(Mockito.<String>any())).thenReturn("");
        when(request.getMethod()).thenReturn("https://example.org/example");
        when(request.getRequestURI()).thenReturn("https://example.org/example");

        // Act
        boolean actualPreHandleResult = hTTPRequestInterceptor.preHandle(request, new Response(), "Object");

        // Assert
        verify(validator).checkKeyExists();
        verify(request).getRemoteAddr();
        verify(request, atLeast(1)).getHeader(Mockito.<String>any());
        verify(request, atLeast(1)).getMethod();
        verify(request, atLeast(1)).getRequestURI();
        assertTrue(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle4() throws Exception {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("");
        when(request.getMethod()).thenReturn("OPTIONS");
        when(request.getRequestURI()).thenReturn("https://example.org/example");

        // Act
        boolean actualPreHandleResult = hTTPRequestInterceptor.preHandle(request, new Response(), "Object");

        // Assert
        verify(request).getHeader(eq("Authorization"));
        verify(request, atLeast(1)).getMethod();
        verify(request).getRequestURI();
        assertTrue(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle5() throws Exception {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("");
        when(request.getMethod()).thenReturn("https://example.org/example");
        when(request.getRequestURI()).thenReturn("/");
        HttpServletResponseWrapper response = mock(HttpServletResponseWrapper.class);
        doNothing().when(response).setContentLength(anyInt());
        doNothing().when(response).setContentType(Mockito.<String>any());
        doNothing().when(response).setHeader(Mockito.<String>any(), Mockito.<String>any());
        when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(new ByteArrayOutputStream(1)));

        // Act
        boolean actualPreHandleResult = hTTPRequestInterceptor.preHandle(request, response, "Object");

        // Assert
        verify(response).getOutputStream();
        verify(response).setContentLength(eq(223));
        verify(response).setContentType(eq("application/json"));
        verify(request).getHeader(eq("Authorization"));
        verify(request, atLeast(1)).getMethod();
        verify(request, atLeast(1)).getRequestURI();
        verify(response).setHeader(eq("Access-Control-Allow-Origin"), eq("*"));
        assertFalse(actualPreHandleResult);
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
     */
    @Test
    void testPostHandle() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        ModelAndView model = new ModelAndView("View Name");

        // Act
        hTTPRequestInterceptor.postHandle(request, response, "Object", model);

        // Assert that nothing has changed
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getContextPath());
        assertEquals("", request.getMethod());
        assertEquals("", request.getRequestURI());
        assertEquals("", request.getServletPath());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("http", request.getScheme());
        assertEquals("localhost", request.getLocalName());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("localhost", request.getServerName());
        assertEquals(80, request.getLocalPort());
        assertEquals(80, request.getRemotePort());
        assertEquals(80, request.getServerPort());
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertFalse(request.isAsyncStarted());
        assertFalse(request.isAsyncSupported());
        assertFalse(request.isRequestedSessionIdFromURL());
        assertTrue(request.isActive());
        assertTrue(request.isRequestedSessionIdFromCookie());
        assertTrue(request.isRequestedSessionIdValid());
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
        Map<String, Object> expectedModelMap = model.getModel();
        assertSame(expectedModelMap, model.getModelMap());
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
     */
    @Test
    void testPostHandle2() throws Exception {
        // Arrange
        Response response = new Response();
        ModelAndView model = new ModelAndView("View Name");

        // Act
        hTTPRequestInterceptor.postHandle(null, response, "Object", model);

        // Assert that nothing has changed
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
        Map<String, Object> expectedModelMap = model.getModel();
        assertSame(expectedModelMap, model.getModelMap());
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
     */
    @Test
    void testPostHandle3() throws Exception {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");
        when(sessionObject.updateSessionObject(Mockito.<String>any(), Mockito.<String>any())).thenReturn("2020-03-01");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        when(request.getRequestURI()).thenReturn("https://example.org/example");
        Response response = new Response();
        ModelAndView model = new ModelAndView("View Name");

        // Act
        hTTPRequestInterceptor.postHandle(request, response, "Object", model);

        // Assert that nothing has changed
        verify(sessionObject).getSessionObject(eq("https://example.org/example"));
        verify(sessionObject).updateSessionObject(eq("https://example.org/example"), eq("Session Object"));
        verify(request).getHeader(eq("Authorization"));
        verify(request).getRequestURI();
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
        Map<String, Object> expectedModelMap = model.getModel();
        assertSame(expectedModelMap, model.getModelMap());
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    void testAfterCompletion() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();

        // Act
        hTTPRequestInterceptor.afterCompletion(request, response, "Object", new Exception("foo"));

        // Assert that nothing has changed
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getContextPath());
        assertEquals("", request.getMethod());
        assertEquals("", request.getRequestURI());
        assertEquals("", request.getServletPath());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("http", request.getScheme());
        assertEquals("localhost", request.getLocalName());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("localhost", request.getServerName());
        assertEquals(80, request.getLocalPort());
        assertEquals(80, request.getRemotePort());
        assertEquals(80, request.getServerPort());
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertFalse(request.isAsyncStarted());
        assertFalse(request.isAsyncSupported());
        assertFalse(request.isRequestedSessionIdFromURL());
        assertTrue(request.isActive());
        assertTrue(request.isRequestedSessionIdFromCookie());
        assertTrue(request.isRequestedSessionIdValid());
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
    }

    /**
     * Method under test:
     * {@link HTTPRequestInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    void testAfterCompletion2() throws Exception {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        Response response = new Response();

        // Act
        hTTPRequestInterceptor.afterCompletion(request, response, "Object", new Exception("foo"));

        // Assert that nothing has changed
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
    }
}
