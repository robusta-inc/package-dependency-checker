package com.robusta.pdc;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.robusta.pdc.domain.*;
import org.junit.Before;
import org.junit.Test;

import static com.robusta.pdc.Main.PackageDependencyChecker;
import static com.robusta.pdc.PackageNamesFixture.*;
import static com.robusta.pdc.domain.JavaPackageMatchers.packageMatching;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PackageDependencyCheckerAcceptanceTest {
    private PackageDependencyChecker dependencyChecker;
    private Multimap<SourceFile, JavaPackage> dependencies;
    @Before
    public void setUp() throws Exception {
        dependencies = HashMultimap.create();
        dependencyChecker = new PackageDependencyChecker(new ImportTracking.Visitor() {
            @Override
            public void visit(SourceFile sourceFile, JavaPackage javaPackage) {
                dependencies.put(sourceFile, javaPackage);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testScan() throws Exception {
        dependencyChecker.doWork(new CommandLineArguments(SOURCE_FOLDERS, VALID_VALUES, new AllowedPackages("com.*,javax.security.*"), true));
        assertThat(dependencies.asMap(),
                allOf(
                        hasEntry(equalTo(CLASS_A), hasItems(packageMatching(equalTo("javax.xml")))),
                        hasEntry(equalTo(CLASS_C), hasItems(packageMatching(equalTo("_com._foo._bar")))),
                        hasEntry(equalTo(CLASS_D), hasItems(packageMatching(equalTo("_com._foo._bar"))))
                ));
    }
}
