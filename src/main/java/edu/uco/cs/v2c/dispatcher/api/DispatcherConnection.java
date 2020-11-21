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
package edu.uco.cs.v2c.dispatcher.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uco.cs.v2c.dispatcher.api.listener.CommandListener;
import edu.uco.cs.v2c.dispatcher.api.listener.ConfigUpdateListener;
import edu.uco.cs.v2c.dispatcher.api.listener.ConnectionCloseListener;
import edu.uco.cs.v2c.dispatcher.api.listener.ConnectionOpenListener;
import edu.uco.cs.v2c.dispatcher.api.listener.HeartbeatListener;
import edu.uco.cs.v2c.dispatcher.api.listener.MessageListener;
import edu.uco.cs.v2c.dispatcher.api.listener.WebSocketErrorListener;
import edu.uco.cs.v2c.dispatcher.api.payload.PayloadHandlingException;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.ErrorPayload;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.HeartbeatPayload;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.InboundConfigUpdatePayload;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.IncomingPayload;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteCommandPayload;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteMessagePayload;
import edu.uco.cs.v2c.dispatcher.api.payload.outgoing.OutgoingPayload;

/**
 * Processes incoming payloads.
 * 
 * @author Caleb L. Power
 */
public class DispatcherConnection extends WebSocketClient {
  
  private Set<CommandListener> commandListeners = new LinkedHashSet<>();
  private Set<ConfigUpdateListener> configUpdateListeners = new LinkedHashSet<>();
  private Set<ConnectionCloseListener> connectionCloseListeners = new LinkedHashSet<>();
  private Set<ConnectionOpenListener> connectionOpenListeners = new LinkedHashSet<>();
  private Set<HeartbeatListener> heartbeatListeners = new LinkedHashSet<>();
  private Set<MessageListener> messageListeners = new LinkedHashSet<>();
  private Set<WebSocketErrorListener> errorListeners = new LinkedHashSet<>();
  private ExecutorService executor = null;
  
  final Set<?> listenersArr[] = new Set<?>[] {
    commandListeners,
    configUpdateListeners,
    connectionCloseListeners,
    connectionOpenListeners,
    messageListeners,
    errorListeners,
    heartbeatListeners
  };
  
  /**
   * Instantiates the payload processor.
   * 
   * @param destination the address of the server
   * @throws URISyntaxException if the destination address was malformed
   */
  public DispatcherConnection(String destination) throws URISyntaxException {
    super(new URI(destination));
    executor = Executors.newSingleThreadExecutor();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onClose(int code, String reason, boolean remote) {
    executor.execute(new Runnable() {
      @Override public void run() {
        synchronized(connectionCloseListeners) {
          for(ConnectionCloseListener listener : connectionCloseListeners)
            listener.onClose(code, reason);
        }
      }
    });
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onOpen(ServerHandshake handshake) {
    executor.execute(new Runnable() {
      @Override public void run() {
        synchronized(connectionOpenListeners) {
          for(ConnectionOpenListener listener : connectionOpenListeners)
            listener.onConnect();
        }
      }
    });
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onMessage(String message) {
    Runnable runnable = null;
    
    try {
      JSONObject json = new JSONObject(message);
      
      switch(IncomingPayload.IncomingAction.valueOf(json.getString("action"))) {
      case ROUTE_COMMAND: {
        RouteCommandPayload payload = new RouteCommandPayload(json);
        runnable = new Runnable() {
          @Override public void run() {
            synchronized(commandListeners) {
              for(CommandListener listener : commandListeners)
                listener.onIncomingCommand(payload);
            }
          }
        };
        
        
        break;
      }
      
      case ROUTE_MESSAGE: {
        RouteMessagePayload payload = new RouteMessagePayload(json);
        runnable = new Runnable() {
          @Override public void run() {
            synchronized(messageListeners) {
              for(MessageListener listener : messageListeners)
                listener.onIncomingMessage(payload);
            }
          }
        };
        
        break;
      }
      
      case UPDATE_CONFIGURATION: {
        InboundConfigUpdatePayload payload = new InboundConfigUpdatePayload(json);
        runnable = new Runnable() {
          @Override public void run() {
            synchronized(configUpdateListeners) {
              for(ConfigUpdateListener listener : configUpdateListeners)
                listener.onConfigUpdate(payload);
            }
          }
        };
        
        break;
      }
      
      case WEBSOCKET_ERROR: {
        ErrorPayload payload = new ErrorPayload(json);
        runnable = new Runnable() {
          @Override public void run() {
            synchronized(errorListeners) {
              for(WebSocketErrorListener listener : errorListeners)
                listener.onRemoteError(payload);
            }
          }
        };
        
        break;
      }
      
      case HEARTBEAT: {
        HeartbeatPayload payload = new HeartbeatPayload(json);
        runnable = new Runnable() {
          @Override public void run() {
            synchronized(heartbeatListeners) {
              for(HeartbeatListener listener : heartbeatListeners)
                listener.onHeartbeat(payload);
            }
          }
        };
        
        break;
      }
      
      default:
        break;
      }
      
    } catch(PayloadHandlingException | JSONException e) {
      e.printStackTrace();
    }

    if(runnable != null)
      executor.execute(runnable);
  }
  
  /**
   * Handles a local error on the WebSocket.
   * 
   * @param cause the cause
   */
  @Override public void onError(Exception cause) {
    executor.execute(new Runnable() {
      @Override public void run() {
        synchronized(errorListeners) {
          for(WebSocketErrorListener listener : errorListeners)
            listener.onLocalError(cause);
        }
      }
    });
  }
  
  /**
   * Registers one or more listeners.
   * 
   * @param listeners the listeners
   */
  public void registerListener(Object... listeners) {
    
    for(Object listener : listeners) {
      if(listener instanceof CommandListener)
        synchronized(commandListeners) {
          commandListeners.add((CommandListener)listener);
        }
      
      if(listener instanceof ConfigUpdateListener)
        synchronized(configUpdateListeners) {
          configUpdateListeners.add((ConfigUpdateListener)listener);
        }
      
      if(listener instanceof ConnectionCloseListener)
        synchronized(connectionCloseListeners) {
          connectionCloseListeners.add((ConnectionCloseListener)listener);
        }
      
      if(listener instanceof ConnectionOpenListener)
        synchronized(connectionOpenListeners) {
          connectionOpenListeners.add((ConnectionOpenListener)listener);
        }
      
      if(listener instanceof MessageListener)
        synchronized(messageListeners) {
          messageListeners.add((MessageListener)listener);
        }
      
      if(listener instanceof WebSocketErrorListener)
        synchronized(errorListeners) {
          errorListeners.add((WebSocketErrorListener)listener);
        }
      
      if(listener instanceof HeartbeatListener)
        synchronized(heartbeatListeners) {
          heartbeatListeners.add((HeartbeatListener)listener);
        }
      
    }
    
  }
  
  /**
   * Deregisters a listener.
   * 
   * @param listener the listener
   */
  public void deregisterListener(Object listener) {
    for(Set<?> listeners : listenersArr)
      synchronized(listeners) {
        if(listeners.contains(listener))
          listeners.remove(listener);
      }
  }
  
  /**
   * Deregisters all listeners.
   */
  public void clearListeners() {
    for(Set<?> listeners : listenersArr)
      synchronized(listeners) {
        listeners.clear();
      }
  }
  
  /**
   * Sends a payload to the connected WebSocket server.
   * 
   * @param payload the payload
   */
  public void send(OutgoingPayload payload) {
    send(payload.toString());
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void close() {
    try {
      executor.shutdown();
    } catch(SecurityException e) { }
    
    if(isOpen()) super.close();
    
    clearListeners();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void closeBlocking() throws InterruptedException {
    try {
      executor.shutdown();
    } catch(SecurityException e) { }
    
    try {      
      if(isOpen()) super.closeBlocking();
    } catch(InterruptedException e) {
      throw e;
    } finally {
      clearListeners();
    }
  }
  
}
