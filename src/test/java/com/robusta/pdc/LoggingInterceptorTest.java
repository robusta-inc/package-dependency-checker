package com.robusta.pdc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LoggingInterceptorTest {
    private Main.LoggingInterceptor interceptor;
    @Before
    public void setUp() throws Exception {
        interceptor = new Main.LoggingInterceptor();
    }

    @Test
    public void testInterceptAndSetup() throws Exception {
        interceptor.interceptAndSetup(new String[] {"-notVerbose"});
        assertThat(System.getProperty(Main.LoggingInterceptor.LOG_LEVEL), equalTo(Main.LoggingInterceptor.INFO));
    }

    @Test
    public void testInterceptAndSetup_VerboseLogging() throws Exception {
        interceptor.interceptAndSetup(new String[] {"-verbose"});
        assertThat(System.getProperty(Main.LoggingInterceptor.LOG_LEVEL), equalTo(Main.LoggingInterceptor.DEBUG));
    }
}
