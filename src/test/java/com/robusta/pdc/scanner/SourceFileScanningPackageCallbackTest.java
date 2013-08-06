package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNamesFixture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SourceFileScanningPackageCallbackTest {
    private SourceFileScanningPackageCallback callback;
    @Mock private SourceFileScanner scanner;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        callback = new SourceFileScanningPackageCallback(scanner);
    }

    @Test
    public void testDoWithPackage() throws Exception {
        callback.doWithPackage(PackageNamesFixture.COM_FOO_BAR);
        verify(scanner).scan(PackageNamesFixture.CLASS_A);
        verify(scanner).scan(PackageNamesFixture.CLASS_B);
    }
}
