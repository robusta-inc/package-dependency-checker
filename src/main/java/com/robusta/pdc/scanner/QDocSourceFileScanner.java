package com.robusta.pdc.scanner;

import com.robusta.pdc.ImportStatement;
import com.robusta.pdc.SourceFile;
import org.hamcrest.generator.qdox.JavaDocBuilder;

import java.io.IOException;

public class QDocSourceFileScanner implements SourceFileScanner {
    private final ImportStatementCallback callback;

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
            e.printStackTrace();
        }
    }
}
