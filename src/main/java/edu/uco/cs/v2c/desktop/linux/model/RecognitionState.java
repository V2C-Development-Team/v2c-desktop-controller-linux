package edu.uco.cs.v2c.desktop.linux.model;

import java.awt.AWTException;

import edu.uco.cs.v2c.desktop.linux.command.KeyboardRobot;

public interface RecognitionState {
    public void goToNextRecognitionState(RecognitionStateContext ctx);

    public void execute(String commandText, ConfigurationData configurationData);
}

class CommandState implements RecognitionState {
    @Override
    public void goToNextRecognitionState(RecognitionStateContext ctx) {
        ctx.setState(new SpellingState());
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
}

class SpellingState implements RecognitionState {
    @Override
    public void goToNextRecognitionState(RecognitionStateContext ctx) {
        ctx.setState(new StreamState());
    }

    @Override
    public void execute(String commandText, ConfigurationData configurationData) {
        String[] tokenMacros = commandText.split(" ");
        for (String s : tokenMacros) {
            Macro foundMacro = configurationData.findMacro(s);
            if (foundMacro != null && foundMacro.getEnabled()) {
                System.out.println("macro found");
                foundMacro.execute();
            } else {
                System.out.println("macro not found");
                // System.out.println("trying to execute anyway");
                // terminal.ExecuteCommand(targetCommand);
            }
        }
    }
}

class StreamState implements RecognitionState {
    @Override
    public void goToNextRecognitionState(RecognitionStateContext ctx) {
        ctx.setState(new CommandState());
    }

    @Override
    public void execute(String commandText, ConfigurationData configurationData) {
        KeyboardRobot keyboardStreamRobot;
        try {
            keyboardStreamRobot = new KeyboardRobot();
            keyboardStreamRobot.type(commandText);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}