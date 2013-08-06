package com.robusta.pdc;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public abstract class ImportStatementMatchers {
    public static Matcher<ImportStatement> importMatching(Matcher<String> matcher) {
        return new FeatureMatcher<ImportStatement, String>(matcher, "import statement", "import statement") {
            @Override
            protected String featureValueOf(ImportStatement actual) {
                return actual.statement();
            }
        };
    }
}
