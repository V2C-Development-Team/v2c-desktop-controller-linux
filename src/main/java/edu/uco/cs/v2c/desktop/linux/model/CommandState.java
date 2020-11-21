/*
 * Copyright (c) 2020 Caleb L. Power, Everistus Akpabio, Rashed Alrashed,
 * Nicholas Clemmons, Jonathan Craig, James Cole Riggall, and Glen Mathew.
 * All rights reserved. Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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