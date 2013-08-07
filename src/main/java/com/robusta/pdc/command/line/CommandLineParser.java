package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.CommandLineArguments;
import com.robusta.pdc.domain.ErrorReporter;

public interface CommandLineParser {
    CommandLineArguments parseCommandLine(String[] arguments, ErrorReporter errorReporter);
}
