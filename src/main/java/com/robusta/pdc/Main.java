package com.robusta.pdc;

import com.robusta.pdc.command.line.ParseCommandLineArgumentHasFailed;
import com.robusta.pdc.command.line.ParserFactory;
import com.robusta.pdc.command.line.UserHasAskedForHelp;
import com.robusta.pdc.domain.CommandLineArguments;
import com.robusta.pdc.domain.ImportTracking;
import com.robusta.pdc.reporting.ReportingFactory;
import com.robusta.pdc.scanner.ScannerFactory;
import com.robusta.pdc.scanner.SourceFolderScanner;
import com.robusta.pdc.tracking.DependencyTracker;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {
    public static void main(String[] args) {
        try {
            new PackageDependencyChecker(args).doWork();
        } catch (ParseCommandLineArgumentHasFailed parseCommandLineArgumentHasFailed) {
            System.exit(-1);
        } catch (UserHasAskedForHelp userHasAskedForHelp) {
            System.exit(-1);
        }
        System.exit(0);
    }

    public static class PackageDependencyChecker {
        private CommandLineArguments cla;

        public PackageDependencyChecker(String[] arguments) throws ParseCommandLineArgumentHasFailed, UserHasAskedForHelp {
            this.cla = ParserFactory.parser(ReportingFactory.errorReporter()).parseCommandLine(arguments);
            System.setProperty("log.level", cla.isVerbose() ? "DEBUG": "INFO");
            DOMConfigurator.configure(getClass().getClassLoader().getResource("log4j.xml"));
        }

        public void doWork() {
            ImportTracking tracking = new DependencyTracker();
            SourceFolderScanner scanner = ScannerFactory.newScannerFor(cla.allowedPackages(), tracking);
            scanner.scan(cla.sourceFolders(), cla.packageNames());
            tracking.doWithVisitor(ReportingFactory.trackingVisitor());
        }
    }
}
