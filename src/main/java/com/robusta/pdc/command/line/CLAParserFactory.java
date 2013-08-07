package com.robusta.pdc.command.line;

import com.robusta.pdc.domain.ErrorReporter;

/**
 * A factory that produces a {@link CommandLineParser} instance.
 *
 * <p>Accepts an optional {@link ErrorReporter} which will be
 * used by the parser instance to report any issues in parsing
 * the command line.</p>
 */
public abstract class CLAParserFactory {
    private static final ErrorReporter NO_OP = new ErrorReporter() {
        @Override
        public void reportError(Exception ignored) {
        }
    };

    /**
     * A {@link CommandLineParser} instance that encapsulates
     * {@link org.apache.commons.cli.CommandLineParser} from
     * commons-cli.
     *
     * @param errorReporter ErrorReporter optional, when null,
     *                      {@link #NO_OP} no-op reporter is used.
     * @return CommandLineParser
     */
    public static CommandLineParser parser(ErrorReporter errorReporter) {
        return new ApacheCommonsCommandLineParser(new ApacheFormatterHelpReporter(), errorReporter != null ? errorReporter : NO_OP);
    }
}
