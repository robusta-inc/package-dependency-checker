package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.*;
import org.apache.commons.cli.*;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.commons.cli.OptionBuilder.withArgName;

/**
 * {@link org.apache.commons.cli.CommandLineParser} based
 * {@link CommandLineParser} implementation.
 *
 * <p>Delegates help content reporting to an injected
 * {@link HelpReporter} and error reporting to an
 * injected {@link ErrorReporter}</p>
 */
@SuppressWarnings("AccessStaticViaInstance")
class ApacheCommonsCommandLineParser implements CommandLineParser {
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
            String sourceDirectories = check(cmd.getOptionValue(OPTION_SOURCE_DIRECTORIES), "specify source directories with -dir option");
            String sourcePackages = check(cmd.getOptionValue(OPTION_SOURCE_PACKAGES), "specify source packages with -source option");
            String targetPackages = check(cmd.getOptionValue(OPTION_TARGET_PACKAGES), "specify target packages with -target option");
            return new CommandLineArguments(
                    new SourceFolders(sourceDirectories),
                    new PackageNames(sourcePackages),
                    new AllowedPackages(targetPackages), cmd.hasOption(OPTION_VERBOSE));
        } catch (ParseException e) {
            errorReporter.reportError(e);
            printHelp();
        } catch (IllegalArgumentException e) {
            errorReporter.reportError(e);
            printHelp();
        }
        throw new ParseCommandLineArgumentHasFailed();
    }

    private String check(String value, String errorToBeRaised) throws MissingOptionException {
        if(isNullOrEmpty(value)) {
            throw new MissingOptionException(errorToBeRaised);
        }
        return value;
    }

    private void printHelp() {
        helper.printHelp(options);
    }
}
