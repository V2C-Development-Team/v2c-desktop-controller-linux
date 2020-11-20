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

            // System.out.println("trying to execute anyway");
            // terminal.ExecuteCommand(targetCommand);
        }
    }

    @Override
    public void SetRecognitionState(RecognitionStateContext ctx, RecognitionState state) {
        ctx.setState(state);

    }
}

/*
 * This file is part of v2c-desktop-controller-linux.
 * 
 * v2c-desktop-controller-linux is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * v2c-desktop-controller-linux is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with v2c-desktop-controller-linux.  If not, see <https://www.gnu.org/licenses/>.
 */