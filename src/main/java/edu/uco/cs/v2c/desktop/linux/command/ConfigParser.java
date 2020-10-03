package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.dispatcher.api.listener.ConfigUpdateListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.InboundConfigUpdatePayload;

public class ConfigParser implements ConfigUpdateListener {
    private ConfigurationData configurationData;
    private static final String DESKTOP_VAR = "desktop";
    private static final String COMMANDS_VAR = "commands";
    private static final String MACROS_VAR = "macros";

    public ConfigParser(ConfigurationData configurationData){
        this.configurationData = configurationData;
    }

    @Override public void onConfigUpdate(InboundConfigUpdatePayload payload) {
        System.out.println("CONFIG UPDATING");
        if (payload.getApp().equals(DESKTOP_VAR)) {
            configurationData.setCommands(payload.getConfig().getJSONArray(COMMANDS_VAR));
            configurationData.setMacros(payload.getConfig().getJSONArray(MACROS_VAR));
        } else {
            System.out.println("============NOT DESKTOP==============");
        }
    }

}
