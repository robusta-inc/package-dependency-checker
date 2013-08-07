package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.*;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static org.apache.commons.cli.OptionBuilder.withArgName;

@SuppressWarnings("AccessStaticViaInstance")
class ApacheCommonsCommandLineParser implements CommandLineParser {
    public static final String OPTION_SOURCE_DIRECTORIES = "dir";
    public static final String OPTION_SOURCE_PACKAGES = "source";
    public static final String OPTION_TARGET_PACKAGES = "target";
    public static final String OPTION_HELP = "help";
    public static final String OPTION_VERBOSE = "verbose";
    private final Options options;
    private final ErrorReporter errorReporter;
    private final HelpReporter helper;

    public ApacheCommonsCommandLineParser(HelpReporter helper, ErrorReporter errorReporter) {
        this.helper = helper;
        this.errorReporter = errorReporter;
        options = new Options()
                .addOption(
                withArgName(OPTION_SOURCE_DIRECTORIES)
                        .hasArg()
                        .withDescription("comma separated list of source directories")
                        .create(OPTION_SOURCE_DIRECTORIES))
                .addOption(withArgName(OPTION_SOURCE_PACKAGES)
                        .hasArg()
                        .withDescription("comma separated list of source packages")
                        .create(OPTION_SOURCE_PACKAGES))
                .addOption(withArgName(OPTION_TARGET_PACKAGES)
                        .hasArg()
                        .withDescription("comma separated list of target (allowed) packages, supports wildcard character *")
                        .create(OPTION_TARGET_PACKAGES))
                .addOption(withArgName(OPTION_VERBOSE)
                        .withDescription("enables verbose output")
                        .create(OPTION_VERBOSE))
                .addOption(withArgName(OPTION_HELP)
                        .withDescription("print help")
                        .create(OPTION_HELP));

    }
    @Override
    public CommandLineArguments parseCommandLine(String[] arguments) throws UserHasAskedForHelp, ParseCommandLineArgumentHasFailed {
        BasicParser parser = new BasicParser();
        try {
            CommandLine cmd = parser.parse( options, arguments);
            if(cmd.hasOption(OPTION_HELP)) {
                printHelp();
                throw new UserHasAskedForHelp();
            }
            return new CommandLineArguments(
                    new SourceFolders(cmd.getOptionValue(OPTION_SOURCE_DIRECTORIES)),
                    new PackageNames(cmd.getOptionValue(OPTION_SOURCE_PACKAGES)),
                    new AllowedPackages(cmd.getOptionValue(OPTION_TARGET_PACKAGES)));
        } catch (ParseException e) {
            errorReporter.reportError(e);
            printHelp();
        } catch (IllegalArgumentException e) {
            errorReporter.reportError(e);
            printHelp();
        }
        throw new ParseCommandLineArgumentHasFailed();
    }

    private void printHelp() {
        helper.printHelp(options);
    }
}
