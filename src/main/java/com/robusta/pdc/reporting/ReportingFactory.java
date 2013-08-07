package com.robusta.pdc.reporting;

import com.robusta.pdc.domain.ErrorReporter;
import com.robusta.pdc.domain.ImportTracking;

public abstract class ReportingFactory {
    public static ErrorReporter errorReporter() {
        return new SysErrorReporter();
    }

    public static ImportTracking.Visitor trackingVisitor() {
        return new SysOutDependencyTrackingReporter();
    }
}
