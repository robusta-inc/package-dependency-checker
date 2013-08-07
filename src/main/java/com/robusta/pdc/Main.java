package com.robusta.pdc;

import com.robusta.pdc.command.line.ParseCommandLineArgumentHasFailed;
import com.robusta.pdc.command.line.UserHasAskedForHelp;

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
}
