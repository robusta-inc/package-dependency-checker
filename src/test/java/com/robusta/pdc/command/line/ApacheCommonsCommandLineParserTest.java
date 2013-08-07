package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.CommandLineArguments;
import com.robusta.pdc.domain.ErrorReporter;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApacheCommonsCommandLineParserTest {
    private ApacheCommonsCommandLineParser parser;
    @Mock private ErrorReporter errorReporter;
    @Mock private HelpReporter helper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        parser = new ApacheCommonsCommandLineParser(helper, errorReporter);
    }

    @Test(expected = UserHasAskedForHelp.class)
    public void testParseCommandLine_whenUserAsksForHelp_shouldPrintHelpAndThrowException() throws Exception {
        parser.parseCommandLine(new String[] {"-help"});
        verify(helper).printHelp(any(Options.class));
        verifyZeroInteractions(errorReporter);
    }

    @Test
    public void testParseCommandLine() throws Exception {
        CommandLineArguments arguments = parser.parseCommandLine(new String[]{"-dir", "c:/my/java/source", "-source", "com.xyz.services.impl", "-target", "com.xyz.services,com.xyz.persistence"});
        assertNotNull(arguments);
        verifyZeroInteractions(helper);
        verifyZeroInteractions(errorReporter);
    }

    @Test(expected = ParseCommandLineArgumentHasFailed.class)
    public void testParseCommandLine_whenArgumentAreMissing() throws Exception {
        CommandLineArguments arguments = parser.parseCommandLine(new String[]{"-dir", "-source", "com.xyz.services.impl", "-target", "com.xyz.services,com.xyz.persistence"});
        assertNotNull(arguments);
        verify(helper).printHelp(any(Options.class));
        verify(errorReporter).reportError(any(Exception.class));
    }

    @Test(expected = ParseCommandLineArgumentHasFailed.class)
    public void testParseCommandLine_whenOptionAreMissing() throws Exception {
        CommandLineArguments arguments = parser.parseCommandLine(new String[]{"-source", "com.xyz.services.impl", "-target", "com.xyz.services,com.xyz.persistence"});
        assertNotNull(arguments);
        verify(helper).printHelp(any(Options.class));
        verify(errorReporter).reportError(any(Exception.class));
    }
}
