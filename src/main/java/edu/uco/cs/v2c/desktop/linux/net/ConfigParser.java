package edu.uco.cs.v2c.desktop.linux.net;

import edu.uco.cs.v2c.dispatcher.api.listener.ConfigUpdateListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.InboundConfigUpdatePayload;

public class ConfigParser implements ConfigUpdateListener {

  @Override public void onConfigUpdate(InboundConfigUpdatePayload payload) {
    System.out.printf("CONFIG UPDATE for %1$s = %2$s", payload.getApp(), payload.getConfig().toString());
  }

}
