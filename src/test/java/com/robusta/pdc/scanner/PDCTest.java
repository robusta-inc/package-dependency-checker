package com.robusta.pdc.scanner;

import com.robusta.pdc.ImportStatement;
import org.junit.Before;
import org.junit.Test;

import static com.robusta.pdc.PackageNamesFixture.SOURCE_FOLDERS;
import static com.robusta.pdc.PackageNamesFixture.VALID_VALUES;

public class PDCTest {
    private SourceFolderScanner scanner;
    private PackageFinder packageFinder;
    private PackageCallback packageCallback;
    private SourceFileScanner sourceFileScanner;
    private ImportStatementCallback statementCallback;

    @Before
    public void setUp() throws Exception {
        statementCallback = new ImportStatementCallback() {

            @Override
            public void doWithImportStatement(ImportStatement importStatement) {
                System.out.println(importStatement);
            }
        };
        sourceFileScanner = new QDocSourceFileScanner(statementCallback);
        packageFinder = new NonRecursivePackageFinder();
        packageCallback = new SourceFileScanningPackageCallback(sourceFileScanner);
        scanner = new PackageCallbackSourceFolderScanner(packageFinder, packageCallback);
    }

    @Test
    public void testScan() throws Exception {
        scanner.scan(SOURCE_FOLDERS, VALID_VALUES);
    }
}
