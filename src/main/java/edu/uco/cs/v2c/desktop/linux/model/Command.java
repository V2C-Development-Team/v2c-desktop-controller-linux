package edu.uco.cs.v2c.desktop.linux.model;

import edu.uco.cs.v2c.desktop.linux.command.TerminalCommandJava;

public class Command {
    String name;
    String description;
    String[] executables;
    String directive;
    Boolean enabled;

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
        TerminalCommandJava terminal;
        for (int i = 0; i < executables.length; i++) {
            terminal = new TerminalCommandJava();
            terminal.ExecuteCommand(executables[i]);
        }
    }

}
