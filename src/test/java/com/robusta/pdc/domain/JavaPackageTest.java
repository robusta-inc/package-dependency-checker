package com.robusta.pdc.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JavaPackageTest {
    @Mock private SourceFile sourceFile;
    @Mock private ImportStatement importStatement;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testPackageInDotNotation_forClassImport() throws Exception {
        when(importStatement.statement()).thenReturn("com.foo.test.ClassA");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("com.foo.test"));
    }

    @Test
    public void testPackageInDotNotation_forStaticMethodClassImport() throws Exception {
        when(importStatement.statement()).thenReturn("com.foo.test.ClassA.staticMethod");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("com.foo.test"));
    }

    @Test
    public void testPackageInDotNotation_forInnerClassStaticMethodClassImport() throws Exception {
        when(importStatement.statement()).thenReturn("_com._foo._bar.ClassB.InnerClassB.staticApi");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("_com._foo._bar"));
    }

    @Test
    public void testPackageInDotNotation_forStaticMethodStartingWith_ClassImport() throws Exception {
        when(importStatement.statement()).thenReturn("_com._foo._bar.ClassB._staticApi");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("_com._foo._bar"));
    }

    @Test
    public void testPackageInDotNotation_forClassImport_startingWith_() throws Exception {
        when(importStatement.statement()).thenReturn("_com._foo._bar._ClassB");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("_com._foo._bar"));
    }

    @Test
    public void testPackageInDotNotation_forStarImport() throws Exception {
        when(importStatement.statement()).thenReturn("_com._foo._bar.*");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("_com._foo._bar"));
    }

    @Test
    public void testPackageInDotNotation_forStarImportInClass() throws Exception {
        when(importStatement.statement()).thenReturn("_com._foo._bar.ClassA.*");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("_com._foo._bar"));
    }

    @Test
    public void testPackageInDotNotation_javaxImport() throws Exception {
        when(importStatement.statement()).thenReturn("javax.xml.XMLConstants.DEFAULT_NS_PREFIX");
        assertThat(new JavaPackage(importStatement).packageInDotNotation(), equalTo("javax.xml"));
    }
}
