package edu.uco.cs.v2c.desktop.linux.model;

import edu.uco.cs.v2c.desktop.linux.dfaparser.Hypervisor;
import edu.uco.cs.v2c.desktop.linux.dfaparser.Machine;
import edu.uco.cs.v2c.desktop.linux.log.Logger;

public class TypingState implements RecognitionState {
	private static final String LOG_LABEL = "TYPING MODE";
	private Machine machine;
	private Hypervisor hypervisor;

	public TypingState() {
		try {
			machine = new Machine();
			hypervisor = new Hypervisor();
			hypervisor.boot(machine);
			machine.registerStateListener(hypervisor);
		} catch (Exception e) {
			Logger.onError(LOG_LABEL, "failed to boot Parser");
		}
	}

	@Override
	public void goToNextRecognitionState(RecognitionStateContext ctx) {
		ctx.setState(new CommandState());
		System.out.println("In Command State");
	}

	@Override
	public void execute(String commandText, ConfigurationData configurationData) {

		try {
			machine.onInput(commandText);
		} catch (Exception e) {
			Logger.onError(LOG_LABEL, "failed to execute");
		}

	}

	@Override
	public void SetRecognitionState(RecognitionStateContext ctx, RecognitionState state) {
		ctx.setState(state);

	}

	// nicks stub
	/*
	 * @Override public void execute(String commandText, ConfigurationData
	 * configurationData) { String[] tokenMacros = commandText.split(" "); for
	 * (String s : tokenMacros) { Macro foundMacro = configurationData.findMacro(s);
	 * if (foundMacro != null && foundMacro.getEnabled()) {
	 * System.out.println("macro found"); foundMacro.execute(); } else {
	 * System.out.println("macro not found"); //
	 * System.out.println("trying to execute anyway"); //
	 * terminal.ExecuteCommand(targetCommand); } } }
	 */
}