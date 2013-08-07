package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.ErrorReporter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

public class CLAParserFactoryTest {
    @Mock private ErrorReporter errorReporter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testParser_withNoErrorReporter() throws Exception {
        assertNotNull(CLAParserFactory.parser(null));
    }

    @Test
    public void testParser_withErrorReporter() throws Exception {
        assertNotNull(CLAParserFactory.parser(errorReporter));
    }
}
