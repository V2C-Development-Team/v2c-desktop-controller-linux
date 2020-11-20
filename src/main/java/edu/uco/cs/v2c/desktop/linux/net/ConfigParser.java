package edu.uco.cs.v2c.desktop.linux.net;

import edu.uco.cs.v2c.dispatcher.api.listener.ConfigUpdateListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.InboundConfigUpdatePayload;

public class ConfigParser implements ConfigUpdateListener {

  @Override public void onConfigUpdate(InboundConfigUpdatePayload payload) {
    System.out.printf("CONFIG UPDATE for %1$s = %2$s", payload.getApp(), payload.getConfig().toString());
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