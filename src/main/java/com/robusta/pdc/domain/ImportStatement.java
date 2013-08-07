package com.robusta.pdc.domain;

public class ImportStatement {
    private final String statement;
    private final SourceFile sourceFile;

    public ImportStatement(String statement, SourceFile sourceFile) {
        this.statement = statement;
        this.sourceFile = sourceFile;
    }

    public JavaPackage importedPackage() {
        return new JavaPackage(this);
    }

    public String statement() {
        return statement;
    }

    public SourceFile sourceFile() {
        return sourceFile;
    }

    @Override
    public String toString() {
        return "ImportStatement{" +
                "statement='" + statement + '\'' +
                ", sourceFile=" + sourceFile +
                '}';
    }
}
