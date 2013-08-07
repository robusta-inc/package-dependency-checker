package com.robusta.pdc.reporting;

import com.robusta.pdc.domain.ErrorReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

class SysErrorReporter implements ErrorReporter {
    static final String ERROR_MESSAGE = "Error occurred: '%s', please refer to help for usage options";
    static final String DEBUG_MESSAGE = "Error Reporter invoked to report exception:";
    private final Logger logger;
    private final PrintStream stream;

    SysErrorReporter() {
        this(System.err, LoggerFactory.getLogger(SysErrorReporter.class));
    }

    public SysErrorReporter(PrintStream stream, Logger logger) {
        this.stream = stream;
        this.logger = logger;
    }

    @Override
    public void reportError(Exception e) {
        logger.debug(DEBUG_MESSAGE, e);
        stream.println(String.format(ERROR_MESSAGE, e.getMessage()));
    }
}
