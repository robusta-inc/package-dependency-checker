package com.robusta.pdc;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

public class PackageNamesTest {
    public static final String PACKAGE_1 = "com.foo.bar1";
    public static final String PACKAGE_2 = "com.foo.bar2";

    private PackageNames packageNames;

    @Before
    public void setUp() throws Exception {
        packageNames = new PackageNames(PACKAGE_1 + ", " + PACKAGE_2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializationWithNullArgument_shouldFailFastWithException() throws Exception {
        new PackageNames(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializationWithEmptyArgument_shouldFailFastWithException() throws Exception {
        new PackageNames("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializationWithInvalidPackageName_shouldFailFastWithException() throws Exception {
        new PackageNames("com.invalid.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializationWithInvalidPackageName2_shouldFailFastWithException() throws Exception {
        new PackageNames("2com.invalid");
    }


    @Test
    public void testInitializationWithPackageNameWithUnderscore_shouldBeValid() throws Exception {
        new PackageNames("_com.valid");
    }

    @Test
    public void testInitializationWithPackageNameWithCapsLetter_shouldBeValid() throws Exception {
        new PackageNames("Com.valid");
    }

    @Test
    public void testIterator() throws Exception {
        assertThat(packageNames, hasItems(PACKAGE_1, PACKAGE_2));
    }
}
