package com.robusta.pdc.command.line;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class ApacheFormatterHelpReporter implements HelpReporter {
    @Override
    public void printHelp(Options options) {
        new HelpFormatter().printHelp("java –jar dependencyChecker.jar", options);
    }
}
