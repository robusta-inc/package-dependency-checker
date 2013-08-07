package com.robusta.pdc.domain;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

public class DependencyTracker {
    private final Multimap<SourceFile, JavaPackage> sourceFileHasPackageDependenciesTracker;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public DependencyTracker() {
        this.sourceFileHasPackageDependenciesTracker = HashMultimap.create(); // Possibly needs to be sync when multi threaded.
    }

    public void track(ImportStatement importStatement) {
        logger.debug("Tracker asked to track import statement: '{}'", importStatement);
        SourceFile sourceFile = importStatement.sourceFile();
        JavaPackage javaPackage = importStatement.importedPackage();
        if(sourceFileHasPackageDependenciesTracker.containsKey(sourceFile)) {
            Collection<JavaPackage> sourceFilePackages = sourceFileHasPackageDependenciesTracker.get(sourceFile);
            if(sourceFilePackages.contains(javaPackage)) {
                logger.debug("Tracker is already in track of imported package: '{}' for specified source file: '{}", javaPackage, sourceFile);
            } else {
                logger.debug("Tracker is already in track of source file: '{}'. Adding imported package to it: '{}", sourceFile, javaPackage);
                sourceFilePackages.add(javaPackage);
            }
        } else {
            logger.debug("New Tracker created for source file: '{}'. And adding imported package to it: '{}", sourceFile, javaPackage);
            sourceFileHasPackageDependenciesTracker.put(sourceFile, javaPackage);
        }
    }

    public Map<SourceFile, Collection<JavaPackage>> tracked() {
        return sourceFileHasPackageDependenciesTracker.asMap();
    }
}
