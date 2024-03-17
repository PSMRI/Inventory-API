package com.iemr.inventory.config;

import com.iemr.inventory.utils.http.HTTPRequestInterceptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@ContextConfiguration(classes = {InterceptorConfig.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class InterceptorConfigTest {
    @MockBean
    private HTTPRequestInterceptor hTTPRequestInterceptor;

    @Autowired
    private InterceptorConfig interceptorConfig;

    /**
     * Method under test:
     * {@link InterceptorConfig#addInterceptors(InterceptorRegistry)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddInterceptors() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2fe8ee83 testClass = com.iemr.inventory.config.FakeClass291, locations = [], classes = [com.iemr.inventory.config.InterceptorConfig], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@3e1abdad, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5036d5e6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@ee20f7d1, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@780cfb5c], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        interceptorConfig.addInterceptors(new InterceptorRegistry());
    }
}
