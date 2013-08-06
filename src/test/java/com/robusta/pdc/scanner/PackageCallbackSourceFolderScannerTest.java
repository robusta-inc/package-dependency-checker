package com.robusta.pdc.scanner;

import com.google.common.collect.Lists;
import com.robusta.pdc.PackageNames;
import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;
import com.robusta.pdc.SourceFolders;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PackageCallbackSourceFolderScannerTest {
    private PackageCallbackSourceFolderScanner scanner;
    @Mock private PackageFinder packageFinder;
    @Mock private PackageCallback packageCallback;
    @Mock private SourceFolders sourceFolders;
    @Mock private SourceFolder sourceFolder;
    @Mock private SourceFolderPackage aPackage;

    private PackageNames packageNames;
    private Collection<SourceFolderPackage> packages;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scanner = new PackageCallbackSourceFolderScanner(packageFinder, packageCallback);
        packageNames = new PackageNames("com.foo.bar, com.foo2.bar2");
        packages = newArrayList(aPackage);
    }

    @Test
    public void testScan_whenFinderFoundPackages_shouldSendPackagesIntoTheCallback() throws Exception {
        given_sourceFoldersReturnsAnIterableOfSourceFolder();
        whenPackageFinderIsAskedToFindPackageItReturns(packages);
        scanner.scan(sourceFolders, packageNames);
        verify(packageCallback).doWithPackage(aPackage);
        verifyNoMoreInteractions(packageCallback);
    }

    private void given_sourceFoldersReturnsAnIterableOfSourceFolder() {
        when(sourceFolders.iterator()).thenReturn(newArrayList(sourceFolder).iterator());
    }

    @Test
    public void testScan_whenFinderFoundNOPackages_shouldBeANoOp() throws Exception {
        given_sourceFoldersReturnsAnIterableOfSourceFolder();
        whenPackageFinderIsAskedToFindPackageItReturns(Lists.<SourceFolderPackage>newArrayList());
        scanner.scan(sourceFolders, packageNames);
        verifyZeroInteractions(packageCallback);
    }

    private void whenPackageFinderIsAskedToFindPackageItReturns(Collection<SourceFolderPackage> folderPackages) {
        when(packageFinder.find(sourceFolder, packageNames)).thenReturn(folderPackages);
    }
}
