package com.robusta.pdc.command.line;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * Command line arguments help reporting encapsulation using
 * an underlying {@link HelpFormatter}
 */
class ApacheFormatterHelpReporter implements HelpReporter {
    private final HelpFormatter formatter = new HelpFormatter();

    @Override
    public void printHelp(Options options) {
        formatter.printHelp(CMD_LINE_SYNTAX, options);
    }
}
