package com.robusta.pdc;

public class ImportStatement {
    private final String statement;
    private final SourceFile sourceFile;

    public ImportStatement(String statement, SourceFile sourceFile) {
        this.statement = statement;
        this.sourceFile = sourceFile;
    }

    public String statement() {
        return statement;
    }

    @Override
    public String toString() {
        return "ImportStatement{" +
                "statement='" + statement + '\'' +
                ", sourceFile=" + sourceFile +
                '}';
    }
}
