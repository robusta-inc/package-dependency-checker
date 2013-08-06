package com.robusta.pdc.scanner;

import com.robusta.pdc.ImportStatement;

public interface ImportStatementCallback {
    void doWithImportStatement(ImportStatement importStatement);
}
