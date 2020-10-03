package edu.uco.cs.v2c.desktop.linux.model;

import org.json.JSONArray;

public class ConfigurationData {
    JSONArray commands;
    JSONArray macros;

    public JSONArray getCommands() {
        return commands;
    }

    public void setCommands(JSONArray commands) {
        this.commands = commands;
    }

    public JSONArray getMacros() {
        return macros;
    }

    public void setMacros(JSONArray macros) {
        this.macros = macros;
    }
}
