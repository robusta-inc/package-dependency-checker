package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.ImportStatement;
import com.robusta.pdc.domain.SourceFile;
import org.hamcrest.generator.qdox.JavaDocBuilder;
import org.hamcrest.generator.qdox.model.JavaSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class QDocSourceFileScanner implements SourceFileScanner {
    private final ImportStatementCallback callback;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public QDocSourceFileScanner(ImportStatementCallback callback) {
        this.callback = callback;
    }

    @Override
    public void scan(SourceFile sourceFile) {
        try {
            JavaSource javaSource = new JavaDocBuilder().addSource(sourceFile.file());
            String packageNameFromSourceFile = sourceFile.packageNameInDotNotation();
            String actualPackageName = javaSource.getPackageName();
            if(!actualPackageName.equals(packageNameFromSourceFile)) {
                throw new PackageNameInJavaClassMismatchesPackageNameDeducedFromPath(sourceFile, actualPackageName, packageNameFromSourceFile);
            }
            String[] imports = javaSource.getImports();
            for (String anImport : imports) {
                callback.doWithImportStatement(new ImportStatement(anImport, sourceFile));
            }
        } catch (IOException e) {
            logger.debug("IO Exception processing source file: '{}'", sourceFile, e);
            logger.warn("Source file: '{}' could not be processed due to IO Exception: '{}", sourceFile, e.getMessage());
        } catch (PackageNameInJavaClassMismatchesPackageNameDeducedFromPath e) {
            logger.warn(e.getMessage());
        }
    }

    private class PackageNameInJavaClassMismatchesPackageNameDeducedFromPath extends Exception {
        private final SourceFile sourceFile;
        private final String actualPackageName;
        private final String packageNameFromSourceFile;

        public PackageNameInJavaClassMismatchesPackageNameDeducedFromPath(SourceFile sourceFile, String actualPackageName, String packageNameFromSourceFile) {
            this.sourceFile = sourceFile;
            this.actualPackageName = actualPackageName;
            this.packageNameFromSourceFile = packageNameFromSourceFile;
        }

        @Override
        public String getMessage() {
            return String.format("Package name read from file: '%s' mismatches package name deduced from path: '%s'. Source file: '%s' cannot be processed", actualPackageName, packageNameFromSourceFile, sourceFile);
        }
    }
}
