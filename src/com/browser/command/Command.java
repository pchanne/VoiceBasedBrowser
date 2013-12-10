/**
 * This is the main interface which is implemented by all command classes.
 * The execute() method is the main function used by 
 * all the command classes with their own respective functionalities.
 */

package com.browser.command;

public interface Command {

	public void execute();

}
