package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.ImportStatement;
import com.robusta.pdc.domain.SourceFile;
import org.hamcrest.generator.qdox.JavaDocBuilder;
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
            String[] imports = new JavaDocBuilder().addSource(sourceFile.file()).getImports();
            for (String anImport : imports) {
                callback.doWithImportStatement(new ImportStatement(anImport, sourceFile));
            }
        } catch (IOException e) {
            logger.debug("IO Exception processing source file: '{}'", sourceFile, e);
            logger.warn("Source file: '{}' could not be processed due to IO Exception: '{}", sourceFile, e.getMessage());
        }
    }
}
