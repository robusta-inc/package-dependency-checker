package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class NonRecursivePackageFinderTest {
    private NonRecursivePackageFinder packageFinder;
    private List<SourceFolderPackage> packages;
    @Mock private SourceFolder sourceFolder;
    @Mock private File packageDirectory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        packages = newArrayList();
        packageFinder = new NonRecursivePackageFinder();
    }

    @Test
    public void testProcessFoundPackageDirectory_willAddASourceFolderPackageIntoCollection() throws Exception {
        packageFinder.processFoundPackageDirectory(packages, sourceFolder, packageDirectory);
        assertThat(packages.size(), equalTo(1));
    }
}
