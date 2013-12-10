/**
 * This class performs mapping between voice commands and the corresponding actions.
 */
package com.browser.helper;

import javafx.application.Platform;
import com.browser.command.AddBookMarkCommand;
import com.browser.command.BackCommand;
import com.browser.command.BookMarkCommand;
import com.browser.command.BottomCommand;
import com.browser.command.ForwardCommand;
import com.browser.command.GoCommand;
import com.browser.command.HomeCommand;
import com.browser.command.Invoker;
import com.browser.command.LinksCommand;
import com.browser.command.NavigateCommand;
import com.browser.command.NextLinkCommand;
import com.browser.command.NextTextCommand;
import com.browser.command.NextTitleCommand;
import com.browser.command.ReadCommand;
import com.browser.command.RefreshCommand;
import com.browser.command.SaveCommand;
import com.browser.command.ScreenCommand;
import com.browser.command.ScrollDownCommand;
import com.browser.command.ScrollUpCommand;
import com.browser.command.SmartNotesCommand;
import com.browser.command.TextCommand;
import com.browser.command.TitlesCommand;
import com.browser.command.TopCommand;
import com.browser.controller.ViewController;
import com.browser.main.VoiceBrowser;
import com.browser.reader.SpeechReaderTask;
import com.browser.view.SideBarView;
import com.browser.view.TabView;

public class SpeechHelper {

	private String website;
	private ViewController viewController;
	public static SpeechReaderTask speechReaderTask;
	public static int readCounter;
	public static int viewCounter;
	private boolean bookmarkFlag;
	private Invoker invokerObj;

	public SpeechHelper() {
		readCounter = 0;
		viewCounter = 0;

		speechReaderTask = new SpeechReaderTask();
		website = null;
		bookmarkFlag = false;

		invokerObj = new Invoker();
	}

	/*
	 * This method will check if the received command is for website navigation
	 * or action commands and perform relevant action.
	 */
	public void speechTest(final String Command) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				SpeechHelper.this.viewController = TabView
						.getCurrentViewController();
				if (Command != null) {
					website = Command.replace(".dot", ".");
					website = website.replace(" ", "");

					if (website != null) {
						String list[] = { ".com", ".edu", ".org", ".net",
								".in", ".us", ".gov", ".mil", ".info", ".jobs" };
						for (String str : list) {
							if (website.contains(str)) {
								VoiceBrowser.logger.info("Website requested: "
										+ website);
								viewController.getTabToolBar()
										.getAddressBarField().setText(website);

								return;
							}
						}

						if (Command.equalsIgnoreCase("go")) {
							invokerObj.setCommand(new GoCommand());

						}
						if (Command.equalsIgnoreCase("back")) {
							invokerObj.setCommand(new BackCommand());

						}
						if (Command.equalsIgnoreCase("forward")) {
							invokerObj.setCommand(new ForwardCommand());
						}
						if (Command.equalsIgnoreCase("refresh")) {
							invokerObj.setCommand(new RefreshCommand());
						}
						if (Command.equalsIgnoreCase("home")) {
							invokerObj.setCommand(new HomeCommand());
						}
						if (Command.equalsIgnoreCase("book mark")) {
							bookmarkFlag = true;
							invokerObj.setCommand(new BookMarkCommand());

						}
						if (Command.equalsIgnoreCase("Add") && bookmarkFlag) {
							invokerObj.setCommand(new AddBookMarkCommand());
							bookmarkFlag = false;
						}
						if (Command.equalsIgnoreCase("read")) {
							invokerObj.setCommand(new ReadCommand());
						}
						if (Command.equalsIgnoreCase("smart notes")) {
							invokerObj.setCommand(new SmartNotesCommand());
						}
						if (Command.equalsIgnoreCase("save")
								&& !(SideBarView.getTextArea().getText()
										.equalsIgnoreCase(""))) {
							invokerObj.setCommand(new SaveCommand());
						}

						if (Command.equalsIgnoreCase("Screen")) {
							invokerObj.setCommand(new ScreenCommand());
						}

						if (Command.equalsIgnoreCase("top")) {
							invokerObj.setCommand(new TopCommand());
						}
						if (Command.equalsIgnoreCase("bottom")) {
							invokerObj.setCommand(new BottomCommand());

						}
						if (Command.equalsIgnoreCase("Links")) {
							invokerObj.setCommand(new LinksCommand());
						}
						if (Command.equalsIgnoreCase("Titles")) {
							invokerObj.setCommand(new TitlesCommand());
						}
						if (Command.equalsIgnoreCase("Text")) {
							invokerObj.setCommand(new TextCommand());
						}
						if (Command.equalsIgnoreCase("Next Link")) {
							invokerObj.setCommand(new NextLinkCommand());
						}
						if (Command.equalsIgnoreCase("Next Title")) {
							invokerObj.setCommand(new NextTitleCommand());
						}
						if (Command.equalsIgnoreCase("Next Text")) {
							invokerObj.setCommand(new NextTextCommand());
						}

						if (Command.equalsIgnoreCase("navigate")) {
							invokerObj.setCommand(new NavigateCommand());
						}
						if (Command.equalsIgnoreCase("scroll down")) {
							invokerObj.setCommand(new ScrollDownCommand());
						}
						if (Command.equalsIgnoreCase("scroll up")) {
							invokerObj.setCommand(new ScrollUpCommand());
						}
						if (invokerObj.getCommand() != null)
							invokerObj.invoke();
					}
				}
			}
		});

	}
}
