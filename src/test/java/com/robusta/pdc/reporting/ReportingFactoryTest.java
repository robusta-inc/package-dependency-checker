package com.robusta.pdc.reporting;

import org.junit.Test;

import static com.robusta.pdc.reporting.ReportingFactory.errorReporter;
import static com.robusta.pdc.reporting.ReportingFactory.trackingVisitor;
import static org.junit.Assert.assertNotNull;

public class ReportingFactoryTest {
    @Test
    public void testErrorReporter() throws Exception {
        assertNotNull(errorReporter());
    }

    @Test
    public void testTrackingVisitor() throws Exception {
        assertNotNull(trackingVisitor());
    }
}
