package com.robusta.pdc.domain;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.regex.Pattern;

import static com.google.common.collect.Iterables.transform;

public class AllowedPackages extends PackageNames {
    static final Pattern JAVA_PACKAGE_NAME_PATTERN_WITH_ENDING_DOT_STAR = Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*)(\\.\\*)?$");
    private final Iterable<Pattern> allowedPackagePatterns;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AllowedPackages(String commaSeparatedPackagesList) {
        super(commaSeparatedPackagesList);
        this.allowedPackagePatterns = buildAllowedPatterns();
    }

    @Override
    protected Pattern supportedPackageNamePattern() {
        return JAVA_PACKAGE_NAME_PATTERN_WITH_ENDING_DOT_STAR;
    }

    /**
     * Build up an iterator of string that contain matcher patterns (as string)
     *
     * <p>com.bar.test.* becomes com\\.bar\\.test.*</p>
     *
     * @return Iterator
     */
    @Override
    public Iterator<String> iterator() {
        return Iterators.transform(super.iterator(), new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input
                        // last .* if exists becomes < (in-significant just a char)
                        .replaceAll("\\.\\*", "<")
                        // all internal dot char become escaped.
                        .replaceAll("\\.", "\\\\.")
                        // final .* if present (now as <) becomes .* to match any char.
                        .replaceAll("<", "\\.\\*");
            }
        });
    }

    public Iterable<Pattern> buildAllowedPatterns() {
        return transform(this, new Function<String, Pattern>() {
            @Override
            public Pattern apply(String input) {
                return Pattern.compile(input);
            }
        });
    }

    public boolean isAllowed(JavaPackage javaPackage) {
        String packageInDotNotation = javaPackage.packageInDotNotation();
        logger.debug("Checking if package: '{}' is allowed", packageInDotNotation);
        for (Pattern allowedPackagePattern : allowedPackagePatterns) {
            logger.debug("Processing with allowed package pattern: '{}'", allowedPackagePattern.pattern());
            if (allowedPackagePattern.matcher(packageInDotNotation).matches()) {
                logger.debug("The package: '{}' was allowed, matched pattern: '{}'", packageInDotNotation, allowedPackagePattern.pattern());
                return true;
            }
        }
        logger.debug("The package: '{}' was not allowed, (did not match any allowed pattern)", packageInDotNotation);
        return false;
    }
}
