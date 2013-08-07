package com.robusta.pdc.domain;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class JavaPackage {
    private final ImportStatement importStatement;
    private final String packageInDotNotation;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Splitter IMPORT_TOKENIZER = Splitter.on('.');
    private static final Joiner IMPORT_TOKEN_JOINER = Joiner.on('.');

    public JavaPackage(ImportStatement importStatement) {
        this.importStatement = importStatement;
        List<String> tokens = newArrayList(IMPORT_TOKENIZER.split(this.importStatement.statement()));
        logger.debug("List of tokens from the import statement: '{}'", tokens);
        String classNameOrStaticMethodName = tokens.remove(tokens.size() - 1);
        logger.debug("Last token - class name or static method name: '{}'", classNameOrStaticMethodName);
        if(isAStaticMethodName(classNameOrStaticMethodName)) {
            tokens.remove(tokens.size() - 1);
        }
        Iterator<String> iterator = Lists.reverse(tokens).iterator();
        while (iterator.hasNext()) {
            String packageNameOrClassName = iterator.next();
            if(isAClassName(packageNameOrClassName)) { // Still a class.
                iterator.remove();
            } else {
                break;
            }
        }
        this.packageInDotNotation = IMPORT_TOKEN_JOINER.join(tokens);
    }

    private boolean isAClassName(String packageNameOrClassName) {
        return packageNameOrClassName.matches("^[_]*[A-Z].*");
    }

    private boolean isAStaticMethodName(String classNameOrStaticMethodName) {
        return classNameOrStaticMethodName.matches("^[_]*[a-z].*");
    }

    public String packageInDotNotation() {
        return packageInDotNotation;
    }

    @Override
    public String toString() {
        return "JavaPackage{" +
                packageInDotNotation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JavaPackage that = (JavaPackage) o;

        if (packageInDotNotation != null ? !packageInDotNotation.equals(that.packageInDotNotation) : that.packageInDotNotation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return packageInDotNotation != null ? packageInDotNotation.hashCode() : 0;
    }
}
