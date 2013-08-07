package com.robusta.pdc;

import com.robusta.pdc.command.line.CommandLineParser;

class LoggingInterceptor {

    public static final String LOG_LEVEL = "log.level";
    public static final String DEBUG = "DEBUG";
    public static final String INFO = "INFO";

    public void interceptAndSetup(String[] args) {
        boolean isVerbose = false;
        for (String arg : args) {
            if(CommandLineParser.OPTION_HYPHEN_VERBOSE.equals(arg)) {
                isVerbose = true;
            }
        }
        System.setProperty(LOG_LEVEL, isVerbose ? DEBUG : INFO);
    }
}
