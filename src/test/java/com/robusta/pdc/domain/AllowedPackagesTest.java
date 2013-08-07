package com.robusta.pdc.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AllowedPackagesTest {
    @Mock private JavaPackage javaPackage;
    private AllowedPackages allowedPackages;
    @Before
    public void setUp() throws Exception {
        initMocks(this);
        allowedPackages = new AllowedPackages("com.bar.foo.api, com.test.bazz.*");
    }

    @Test
    public void testInitialization_normal() throws Exception {
        new AllowedPackages("com.buzz.foo, com.bar.test");
    }

    @Test
    public void testInitialization_wildCarded() throws Exception {
        new AllowedPackages("com.buzz.foo, com.bar.test.*");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialization_wildCardedNotInTheEnd_shouldThrowException() throws Exception {
        new AllowedPackages("com.buzz.foo, com.bar.*.test");
    }

    @Test
    public void testMyUnderstandingOfRegExMatching() throws Exception {
        assertThat("com.bar.test.*".matches("com\\.bar\\.test.*"), equalTo(true));
        assertThat("com.bar.test.TestClass".matches("com\\.bar\\.test.*"), equalTo(true));
    }

    @Test
    public void testIterator() throws Exception {
        assertThat(new AllowedPackages("com.bar.test.*").iterator().next(), equalTo("com\\.bar\\.test.*"));
    }

    @Test
    public void testIsAllowed_whenDoesNotMatch_shouldReturnFalse() throws Exception {
        when(javaPackage.packageInDotNotation()).thenReturn("com.bar.foo.impl");
        assertThat(allowedPackages.isAllowed(javaPackage), equalTo(false));
    }

    @Test
    public void testIsAllowed_whenMatchesSubPackageWithStar_shouldReturnTrue() throws Exception {
        when(javaPackage.packageInDotNotation()).thenReturn("com.test.bazz.something.impl");
        assertThat(allowedPackages.isAllowed(javaPackage), equalTo(true));
    }

    @Test
    public void testIsAllowed_whenMatchesPackageWithNoStar_shouldReturnTrue() throws Exception {
        when(javaPackage.packageInDotNotation()).thenReturn("com.bar.foo.api");
        assertThat(allowedPackages.isAllowed(javaPackage), equalTo(true));
    }
}
