package com.robusta.pdc.reporting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.io.PrintStream;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SysErrorReporterTest {
    public static final String GET_MESSAGE = "test";
    @Mock private PrintStream err;
    @Mock private Logger logger;
    private Exception e;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        e = new Exception(GET_MESSAGE);
    }

    @Test
    public void testReportError() throws Exception {
        new SysErrorReporter(err, logger).reportError(e);
        verify(logger).debug(SysErrorReporter.DEBUG_MESSAGE, e);
        verify(err).println(String.format(SysErrorReporter.ERROR_MESSAGE, GET_MESSAGE));
    }
}
