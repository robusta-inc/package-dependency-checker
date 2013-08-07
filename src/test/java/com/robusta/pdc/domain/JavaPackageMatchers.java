package com.robusta.pdc.domain;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class JavaPackageMatchers {
    public static Matcher<JavaPackage> packageMatching(Matcher<String> packageMatcher) {
        return new FeatureMatcher<JavaPackage, String>(packageMatcher, "Package dot notation matching", "Package dot notation") {
            @Override
            protected String featureValueOf(JavaPackage actual) {
                return actual.packageInDotNotation();
            }
        };
    }
}
