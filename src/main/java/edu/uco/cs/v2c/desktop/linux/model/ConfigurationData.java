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

import org.json.JSONArray;
import org.json.JSONObject;

public class ConfigurationData {
	private Command[] commands = new Command[0];
	private Macro[] macros = new Macro[0]; //Jon- Updated this so it doesn't throw NPE when looking for the arrays
	

    public Command findCommand(String targetCommand) {
        Command foundCommand = null;

        for (int i = 0; i < commands.length; i++) {
            if (commands[i].getDirective().equals(targetCommand)) {
                foundCommand = commands[i];
            }
        }
        // check if enabled is true
        // else return null

        return foundCommand;
    }

    public Macro findMacro(String targetMacro) {
        Macro foundMacro = null;

        for (int i = 0; i < macros.length; i++) {
            if (macros[i].getDirective().equals(targetMacro)) {
                foundMacro = macros[i];
            }
        }

        return foundMacro;
    }

    public void printCommands() {
        System.out.println("==================CURRENT COMMANDS DATA=================");
        for (int i = 0; i < commands.length; i++) {
            System.out.println("== Name: " + commands[i].getName());
            System.out.println("== Description: " + commands[i].getDescription());
            System.out.println("== Executables: ");
            for (int e = 0; e < commands[i].getExecutables().length; e++) {
                System.out.println("== \"" + commands[i].getExecutables()[e] + "\"");
            }
            System.out.println("== Directive: " + commands[i].getDirective());
            System.out.println("== Enabled: " + commands[i].getEnabled());
            System.out.println("-----------------------------------------------------");
        }
        System.out.println("==================CURRENT COMMANDS DATA=================");
    }

    public void printMacros() {
        System.out.println("==================CURRENT MACROS DATA=================");
        for (int i = 0; i < macros.length; i++) {
            System.out.println("== Name: " + macros[i].getName());
            System.out.println("== Description: " + macros[i].getDescription());
            System.out.println("== Keypresses: ");
            for (int e = 0; e < macros[i].getKeypresses().length; e++) {
                System.out.println("== \"" + macros[i].getKeypresses()[e] + "\"");
            }
            System.out.println("== Directive: " + macros[i].getDirective());
            System.out.println("== Enabled: " + macros[i].getEnabled());
            System.out.println("-----------------------------------------------------");
        }
        System.out.println("==================CURRENT MACROS DATA=================");
    }

    public void updateCommands(JSONArray commands) {
        int commandsLength = commands.length();
        Command[] temporaryCommandArray = new Command[commandsLength];

        for (int i = 0; i < commandsLength; i++) {
            JSONObject currentCommand = commands.getJSONObject(i);
            temporaryCommandArray[i] = new Command();
            String name = currentCommand.getString("name");
            temporaryCommandArray[i].setName(name);
            String description = currentCommand.getString("description");
            temporaryCommandArray[i].setDescription(description);
            JSONArray executables = currentCommand.getJSONArray("executables");
            String[] executablesString = new String[executables.length()];
            for (int e = 0; e < executables.length(); e++) {
                executablesString[e] = executables.getString(e);
            }
            temporaryCommandArray[i].setExecutables(executablesString);
            String directive = currentCommand.getString("directive").toLowerCase();
            temporaryCommandArray[i].setDirective(directive);
            Boolean enabled = currentCommand.getBoolean("enabled");
            temporaryCommandArray[i].setEnabled(enabled);
        }

        setCommands(temporaryCommandArray);
    }

    public void updateMacros(JSONArray macros) {
        int macrosLength = macros.length();
        Macro[] temporaryMacroArray = new Macro[macrosLength];

        for (int i = 0; i < macrosLength; i++) {
            JSONObject currentMacro = macros.getJSONObject(i);
            temporaryMacroArray[i] = new Macro();
            String name = currentMacro.getString("name");
            temporaryMacroArray[i].setName(name);
            String description = currentMacro.getString("description");
            temporaryMacroArray[i].setDescription(description);
            JSONArray keypresses = currentMacro.getJSONArray("keypresses");
            String[] keypressesString = new String[keypresses.length()];
            for (int e = 0; e < keypresses.length(); e++) {
                keypressesString[e] = keypresses.getString(e);
            }
            temporaryMacroArray[i].setKeypresses(keypressesString);
            String directive = currentMacro.getString("directive").toLowerCase();
            temporaryMacroArray[i].setDirective(directive);
            Boolean enabled = currentMacro.getBoolean("enabled");
            temporaryMacroArray[i].setEnabled(enabled);
        }

        setMacros(temporaryMacroArray);
    }

    public Command[] getCommands() {
        return commands;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    public Macro[] getMacros() {
        return macros;
    }

    public void setMacros(Macro[] macros) {
        this.macros = macros;
    }

}
