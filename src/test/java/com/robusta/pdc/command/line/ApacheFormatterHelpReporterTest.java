package com.robusta.pdc.command.line;

import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class ApacheFormatterHelpReporterTest {
    private ApacheFormatterHelpReporter reporter;

    @Mock private Options options;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        reporter = new ApacheFormatterHelpReporter();
    }

    @Test
    public void testPrintHelp() throws Exception {
        reporter.printHelp(options);
    }
}
