package edu.uco.cs.v2c.desktop.linux.model;

import edu.uco.cs.v2c.desktop.linux.command.TerminalCommand;

public class Command {
    String name;
    String description;
    String[] executables;
    String directive;
    Boolean enabled;

    public Command(String name, String description, String[] executables, String directive, Boolean enabled) {
        this.name = name;
        this.description = description;
        this.executables = executables;
        this.directive = directive;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getExecutables() {
        return executables;
    }

    public void setExecutables(String[] executables) {
        this.executables = executables;
    }

    public String getDirective() {
        return directive;
    }

    public void setDirective(String directive) {
        this.directive = directive;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void execute() {
        TerminalCommand terminal;
        for (int i = 0; i < executables.length; i++) {
            terminal = new TerminalCommand();
            terminal.ExecuteCommand(executables[i]);
        }
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