package com.robusta.pdc;

import com.google.common.base.Splitter;

import java.util.Iterator;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

public class PackageNames implements Iterable<String> {
    private final Iterable<String> packagesInDotNotationList;

    static final Pattern JAVA_PACKAGE_NAME_PATTERN = Pattern.compile("^([a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*)?$"); //http://stackoverflow.com/questions/3577250/python-regex-for-java-package-names
    static final Splitter SPLIT_BY_COMMA_WITH_TRIM = Splitter.on(",").omitEmptyStrings().trimResults();

    public PackageNames(String commaSeparatedPackagesList) {
        this(toList(commaSeparatedPackagesList));
    }

    protected static Iterable<String> toList(String commaSeperatedStringList) {
        checkArgument(!isNullOrEmpty(commaSeperatedStringList), "Package Names cannot be initialized. A valid argument (not null or empty) will be required");
        return SPLIT_BY_COMMA_WITH_TRIM.split(commaSeperatedStringList);
    }

    private static Iterable<String> checkValidity(Iterable<String> listOfPackageNames) {
        for (String packageName : listOfPackageNames) {
            checkArgument(JAVA_PACKAGE_NAME_PATTERN.matcher(packageName).matches(), "The specified package: '%s' is not a valid package name", packageName);
        }
        return listOfPackageNames;
    }

    private PackageNames(Iterable<String> packages) {
        this.packagesInDotNotationList = checkValidity(packages);
    }

    @Override
    public Iterator<String> iterator() {
        return packagesInDotNotationList.iterator();
    }

    @Override
    public String toString() {
        return "PackageNames={" + packagesInDotNotationList + '}';
    }
}
