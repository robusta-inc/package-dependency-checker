package com.robusta.pdc.command.line;

import org.apache.commons.cli.Options;

public interface HelpReporter {
    public static final String CMD_LINE_SYNTAX = "java –jar dependencyChecker.jar";
    void printHelp(Options options);
}
