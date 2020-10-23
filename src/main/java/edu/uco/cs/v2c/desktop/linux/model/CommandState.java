package edu.uco.cs.v2c.desktop.linux.model;

public class CommandState implements RecognitionState {
    @Override
    public void goToNextRecognitionState(RecognitionStateContext ctx) {
        ctx.setState(new TypingState());
        System.out.println("In Typing State");
    }

    @Override
    public void execute(String commandText, ConfigurationData configurationData) {
        Command foundCommand = configurationData.findCommand(commandText);
        Macro foundMacro = configurationData.findMacro(commandText);
        if (foundCommand != null && foundCommand.getEnabled()) {
            System.out.println("command found");
            foundCommand.execute();
        } else if (foundMacro != null && foundMacro.getEnabled()) {
            System.out.println("macro found");
            foundMacro.execute();
        } else {
            System.out.println("command/macro not found");
            // System.out.println("trying to execute anyway");
            // terminal.ExecuteCommand(targetCommand);
        }
    }

	@Override
	public void SetRecognitionState(RecognitionStateContext ctx, RecognitionState state) {
		ctx.setState(state);
		
	}
}