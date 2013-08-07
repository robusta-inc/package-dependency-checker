package com.robusta.pdc.domain;

import com.robusta.pdc.PackageNamesFixture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;

import static com.robusta.pdc.PackageNamesFixture.directoryName;
import static java.io.File.separatorChar;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SourceFileTest {
    private SourceFile sourceFile;
    @Mock private File sourceFileAsFile;
    @Mock private SourceFolderPackage aPackage;
    @Mock private SourceFolder sourceFolder;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        sourceFile = new SourceFile(sourceFolder, aPackage, sourceFileAsFile);
    }

    @Test
    public void testJavaFileNameInDotNotation_whenSourceFolderAbsolutePathHasTrailingSeparatorChar() throws Exception {
        when(sourceFolder.getAbsolutePath()).thenReturn(directoryName("var", "app", "code") + separatorChar);
        when(sourceFileAsFile.getAbsolutePath()).thenReturn(directoryName("var", "app", "code") + separatorChar + directoryName("com", "foo", "bar") + separatorChar + "ClassA.java");
        assertThat(sourceFile.javaFileNameInDotNotation(), equalTo("com.foo.bar.ClassA.java"));
    }

    @Test
    public void testJavaFileNameInDotNotation_sanity() throws Exception {
        assertThat(PackageNamesFixture.CLASS_A.javaFileNameInDotNotation(), equalTo("_com._foo._bar.ClassA.java"));
    }

    @Test
    public void testPackageNameInDotNotation_sanity() throws Exception {
        assertThat(PackageNamesFixture.CLASS_A.packageNameInDotNotation(), equalTo("_com._foo._bar"));
    }

}
