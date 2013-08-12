package com.robusta.pdc.command.line;

/**
 * Thrown by the parser when it detects that the user has
 * asked for help (text display) as part of the command
 * line arguments.
 *
 * <p>This can be used by the parser client to change the
 * normal flow and handle the case where the user has asked
 * for command line help.</p>
 */
public class UserHasAskedForHelp extends Exception {
}
