package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.CommandLineArguments;

public interface CommandLineParser {
    CommandLineArguments parseCommandLine(String[] arguments) throws UserHasAskedForHelp, ParseCommandLineArgumentHasFailed;
}
