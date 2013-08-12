package com.robusta.pdc;

import com.robusta.pdc.command.line.CommandLineParser;
import com.robusta.pdc.command.line.ParseCommandLineArgumentHasFailed;
import com.robusta.pdc.command.line.UserHasAskedForHelp;
import com.robusta.pdc.domain.CommandLineArguments;
import com.robusta.pdc.domain.ImportTracking;

import static com.robusta.pdc.command.line.CLAParserFactory.parser;
import static com.robusta.pdc.reporting.ReportingFactory.errorReporter;
import static com.robusta.pdc.reporting.ReportingFactory.trackingVisitor;
import static com.robusta.pdc.scanner.ScannerFactory.importStatementScanner;
import static com.robusta.pdc.tracking.ImportTrackingFactory.violationTracking;

/**
 * Entry point into package-dependancy-checker (pdc)
 * using command line. 
 * 
 * <p>Uses a Command line argument (CLA) parser to 
 * process, validate and consume the CLA which is then
 * passed into {@link PackageDependencyChecker}</p>
 * 
 * <p>Handles normal system exit and processing error 
 * exits with appropriate return codes.
 */
public class Main {
    public static void main(String[] args) {
        bootstrap(args);
        try {
            CommandLineArguments cla = parser(errorReporter()).parseCommandLine(args);
            new PackageDependencyChecker().doWork(cla);
        } catch (ParseCommandLineArgumentHasFailed parseCommandLineArgumentHasFailed) {
            System.exit(-1);
        } catch (UserHasAskedForHelp userHasAskedForHelp) {
            System.exit(0);
        }
        System.exit(0);
    }

    private static void bootstrap(String[] args) {
        // Must be done as bootstrap. this will ensure that the logging subsystem is
        // properly setup.

        // If log system is initialized prior to this step, the DomConfigurator needs to
        // be invoked. Adds a nasty dependency to log4j from the app, but there is no
        // slf4j way to reinit the log system.
        new LoggingInterceptor().interceptAndSetup(args);
    }

    /**
     * Package Dependecy checker. 
     * 
     * <p>Accepts a command line arguments with a 
     * source folders, source packages and target 
     * packages.</p>
     * 
     * <p>Uses the tracking factory to get a violation 
     * (import dependancy in source file that is not in
     * target packages supplied) kind of import tracker.
     * </p>
     * 
     * <p>Sends the import tracker and the command line 
     * arguments into the scanner factory to get a source
     * folders scanner. Calls scan on the scanner</p>
     * 
     * <p>The results of the scanning operation is expected
     * to be populated into the tracker, so send in a 
     * reporting visitor into the tracker for visitation of 
     * tracked contents</p>
     * 
     * <p>The tracking visitor implementation used currently
     * outputs the dependency tracked information to standard
     * output</p>
     */
    static class PackageDependencyChecker {

        private final ImportTracking.Visitor outputReporter;

        private PackageDependencyChecker() {
            this(trackingVisitor());
        }

        protected PackageDependencyChecker(ImportTracking.Visitor outputReporter) {
            this.outputReporter = outputReporter;
        }

        public void doWork(CommandLineArguments cla) {
            ImportTracking tracking = violationTracking(cla.allowedPackages());
            importStatementScanner(tracking).scan(cla.sourceFolders(), cla.packageNames());

            tracking.allowVisitor(outputReporter);
        }
    }

    static class LoggingInterceptor {

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
}
