package com.robusta.pdc.command.line;

/**
 * Thrown by the parser when it detects missing mandatory
 * arguments or argument values.
 *
 * <p>This can be used by the parser client to change the
 * normal flow and handle the exception case.</p> */
public class ParseCommandLineArgumentHasFailed extends Exception {
}
