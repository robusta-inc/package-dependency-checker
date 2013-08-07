package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.reporting.SysOutDependencyTrackingVisitor;
import com.robusta.pdc.tracking.DependencyTracker;
import org.junit.Before;
import org.junit.Test;

import static com.robusta.pdc.PackageNamesFixture.SOURCE_FOLDERS;
import static com.robusta.pdc.PackageNamesFixture.VALID_VALUES;

public class PDCTest {
    private DependencyTracker tracker;
    private SourceFolderScanner scanner;

    @Before
    public void setUp() throws Exception {
        tracker = new DependencyTracker();
        scanner = ScannerFactory.newScannerFor(new AllowedPackages("java.util.*"), tracker);
    }

    @Test
    public void testScan() throws Exception {
        scanner.scan(SOURCE_FOLDERS, VALID_VALUES);
        tracker.doWithVisitation(new SysOutDependencyTrackingVisitor());
    }
}
