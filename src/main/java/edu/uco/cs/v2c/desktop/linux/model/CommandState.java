package edu.uco.cs.v2c.desktop.linux.model;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import edu.uco.cs.v2c.desktop.linux.command.KeyboardRobot;

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

            // if command is not found, attempt to type the command text
            // try {
            //     KeyboardRobot robot = new KeyboardRobot();
            //     robot.type(commandText);
            //     robot.holdKey(KeyEvent.VK_SPACE);
            //     robot.releaseKey(KeyEvent.VK_SPACE);
            // } catch (AWTException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }

            // System.out.println("trying to execute anyway");
            // terminal.ExecuteCommand(targetCommand);
        }
    }

    @Override
    public void SetRecognitionState(RecognitionStateContext ctx, RecognitionState state) {
        ctx.setState(state);

    }
}