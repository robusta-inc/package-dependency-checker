package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.DependencyTracker;
import org.junit.Before;
import org.junit.Test;

import static com.robusta.pdc.PackageNamesFixture.SOURCE_FOLDERS;
import static com.robusta.pdc.PackageNamesFixture.VALID_VALUES;

public class PDCTest {
    private DependencyTracker tracker;
    private SourceFolderScanner scanner;

    @Before
    public void setUp() throws Exception {
        tracker = SourceFolderScannerFactory.newDependencyTracker();
        scanner = SourceFolderScannerFactory.newScannerFor(new AllowedPackages("java.util.*"), tracker);
    }

    @Test
    public void testScan() throws Exception {
        scanner.scan(SOURCE_FOLDERS, VALID_VALUES);
        System.out.println("tracker = " + tracker.tracked());
    }
}
