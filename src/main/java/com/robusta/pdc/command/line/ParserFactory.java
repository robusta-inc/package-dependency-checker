package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.ErrorReporter;

public abstract class ParserFactory {
    public static CommandLineParser parser(ErrorReporter errorReporter) {
        return new ApacheCommonsCommandLineParser(new ApacheFormatterHelpReporter(), errorReporter);
    }
}
