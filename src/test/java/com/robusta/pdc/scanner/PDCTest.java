package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.ImportTracking;
import org.junit.Before;
import org.junit.Test;

import static com.robusta.pdc.PackageNamesFixture.SOURCE_FOLDERS;
import static com.robusta.pdc.PackageNamesFixture.VALID_VALUES;
import static com.robusta.pdc.reporting.ReportingFactory.trackingVisitor;
import static com.robusta.pdc.scanner.ScannerFactory.importStatementScanner;
import static com.robusta.pdc.tracking.ImportTrackingFactory.violationTracking;

public class PDCTest {
    private SourceFolderScanner scanner;
    private ImportTracking tracker;

    @Before
    public void setUp() throws Exception {
        tracker = violationTracking(new AllowedPackages("java.util.*"));
        scanner = importStatementScanner(tracker);
    }

    @Test
    public void testScan() throws Exception {
        scanner.scan(SOURCE_FOLDERS, VALID_VALUES);
        tracker.allowVisitor(trackingVisitor());
    }
}
