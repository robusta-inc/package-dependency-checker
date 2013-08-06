package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNamesFixture;
import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AbstractDirectoryFindingPackageFinderTest {
    private CollectingPackageFinder packageFinder = new CollectingPackageFinder();

    @Mock private SourceFolder sourceFolder;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testFind_withValidValues_shouldBeAbleToFindThePackages() throws Exception {
        when(sourceFolder.getAbsolutePath()).thenReturn(PackageNamesFixture.ABS_PATH);
        packageFinder.find(sourceFolder, PackageNamesFixture.VALID_VALUES);
        assertThat(packageFinder.collection().size(), equalTo(2));
    }

    @Test
    public void testFind_withInvalidValues_shouldReturnAnEmptyValue() throws Exception {
        when(sourceFolder.getAbsolutePath()).thenReturn(PackageNamesFixture.ABS_PATH);
        packageFinder.find(sourceFolder, PackageNamesFixture.INVALID_VALUES);
        assertThat(packageFinder.collection().size(), equalTo(0));
    }

    private class CollectingPackageFinder extends AbstractDirectoryFindingPackageFinder {
        private List<File> collection = newArrayList();

        @Override
        protected void processFoundPackageDirectory(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory) {
            collection.add(packageDirectory);
        }

        public List<File> collection() {
            return collection;
        }
    }

}
