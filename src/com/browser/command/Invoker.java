package com.browser.command;

public class Invoker {
	
	private Command command;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public Command getCommand(){
		return command;
	}
	public void invoke()
	{
		command.execute();
	}

}
