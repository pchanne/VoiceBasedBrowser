/**
 * This class represents the main Invoker that
 *  will set the appropriate command based 
 * on the speech and then will execute that same command's functionality.
 */

package com.browser.command;

public class Invoker {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	/*
	 * This is the main method to call the particular command's execute()
	 * method.
	 */
	public void invoke() {
		command.execute();
	}

}
