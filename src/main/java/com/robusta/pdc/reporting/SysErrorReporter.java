package com.robusta.pdc.reporting;

import com.robusta.pdc.domain.ErrorReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SysErrorReporter implements ErrorReporter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void reportError(Exception e) {
        logger.debug("Error Reporter invoked to report exception:", e);
        System.err.println(String.format("Error occurred: '%s', please refer to help for usage options", e.getMessage()));
    }
}
