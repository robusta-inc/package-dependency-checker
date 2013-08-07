package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.ImportStatement;

public interface ImportStatementCallback {
    void doWithImportStatement(ImportStatement importStatement);
}
