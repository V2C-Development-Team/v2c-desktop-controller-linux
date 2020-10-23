package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.dispatcher.api.listener.ConfigUpdateListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.InboundConfigUpdatePayload;

import org.json.JSONArray;


public class ConfigParser implements ConfigUpdateListener {
    private ConfigurationData configurationData;
    private static final String DESKTOP_VAR = "desktop";
    private static final String COMMANDS_VAR = "commands";
    private static final String MACROS_VAR = "macros";
    

    public ConfigParser(ConfigurationData configurationData) {
        this.configurationData = configurationData;
    }

    @Override
    public void onConfigUpdate(InboundConfigUpdatePayload payload) {
        if (payload.getApp().equals(DESKTOP_VAR)) {
        	//Jon-updated this, If there is no array there, it will throw a JSON exception or NPE, this is checking first.
            configurationData.updateCommands((payload.getConfig().has(COMMANDS_VAR)) ? payload.getConfig().getJSONArray(COMMANDS_VAR) : new JSONArray()); 
            configurationData.updateMacros((payload.getConfig().has(MACROS_VAR)) ? payload.getConfig().getJSONArray(MACROS_VAR) : new JSONArray());
        } else {
            System.out.println("============NOT DESKTOP==============");
        }
        configurationData.printCommands();
        configurationData.printMacros();
    }

}
