package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNamesFixture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.robusta.pdc.ImportStatementMatchers.importMatching;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class QDocSourceFileScannerTest {
    private QDocSourceFileScanner scanner;
    @Mock ImportStatementCallback callback;

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
}
