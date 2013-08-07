package com.robusta.pdc;

import com.robusta.pdc.command.line.ParseCommandLineArgumentHasFailed;
import com.robusta.pdc.command.line.ParserFactory;
import com.robusta.pdc.command.line.UserHasAskedForHelp;
import com.robusta.pdc.domain.CommandLineArguments;
import com.robusta.pdc.domain.ImportTracking;
import com.robusta.pdc.reporting.ReportingFactory;
import com.robusta.pdc.scanner.ScannerFactory;
import com.robusta.pdc.tracking.DependencyTracker;

public class PackageDependencyChecker {
    private CommandLineArguments cla;

    public PackageDependencyChecker(String[] arguments) throws ParseCommandLineArgumentHasFailed, UserHasAskedForHelp {
        this.cla = ParserFactory.parser(ReportingFactory.errorReporter()).parseCommandLine(arguments);
    }

    public void doWork() {
        ImportTracking tracking = new DependencyTracker();
        ScannerFactory.newScannerFor(cla.allowedPackages(), tracking).scan(cla.sourceFolders(), cla.packageNames());
        tracking.doWithVisitation(ReportingFactory.trackingVisitor());
    }
}
