package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNamesFixture;
import com.robusta.pdc.domain.SourceFile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;

import static com.robusta.pdc.ImportStatementMatchers.importMatching;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class QDocSourceFileScannerTest {
    private QDocSourceFileScanner scanner;
    @Mock private ImportStatementCallback callback;
    @Mock private SourceFile sourceFile;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scanner = new QDocSourceFileScanner(callback);
    }

    @Test
    public void testScan() throws Exception {
        scanner.scan(PackageNamesFixture.CLASS_C);
        verify(callback).doWithImportStatement(argThat(importMatching(equalTo("_com._foo._bar.ClassA"))));
    }

    @Test
    public void testScan_simulationOfIOException() throws Exception {
        when(sourceFile.file()).thenReturn(new File("/this/file/cannot/be/found/should/lead/to/io/exception"));
        scanner.scan(sourceFile);
    }
}
