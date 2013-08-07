package com.robusta.pdc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LoggingInterceptorTest {
    private LoggingInterceptor interceptor;
    @Before
    public void setUp() throws Exception {
        interceptor = new LoggingInterceptor();
    }

    @Test
    public void testInterceptAndSetup() throws Exception {
        interceptor.interceptAndSetup(new String[] {"-notVerbose"});
        assertThat(System.getProperty(LoggingInterceptor.LOG_LEVEL), equalTo(LoggingInterceptor.INFO));
    }

    @Test
    public void testInterceptAndSetup_VerboseLogging() throws Exception {
        interceptor.interceptAndSetup(new String[] {"-verbose"});
        assertThat(System.getProperty(LoggingInterceptor.LOG_LEVEL), equalTo(LoggingInterceptor.DEBUG));
    }
}
