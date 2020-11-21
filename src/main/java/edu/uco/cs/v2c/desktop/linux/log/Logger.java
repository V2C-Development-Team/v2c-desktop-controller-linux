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
package edu.uco.cs.v2c.desktop.linux.log;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple logger.
 * 
 * @author Caleb L. Power
 */
public class Logger {
  
  private static enum LogLevel {
    DEBUG, INFO, ERROR
  }
  
  private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
  
  // TODO make the timezone configurable maybe
  private static Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
  
  /**
   * Prints a debugging message.
   * 
   * @param label the label or relevant module
   * @param message the message
   */
  public static void onDebug(String label, String message) {
    log(System.out, LogLevel.DEBUG, label, message);
  }
  
  /**
   * Prints an informational message.
   * 
   * @param label the label or relevant module
   * @param message the message
   */
  public static void onInfo(String label, String message) {
    log(System.out, LogLevel.INFO, label, message);
  }
  
  /**
   * Prints an error message.
   * 
   * @param label the label or relevant module
   * @param message the message
   */
  public static void onError(String label, String message) {
    log(System.err, LogLevel.ERROR, label, message);
  }
  
  private static void log(PrintStream out, LogLevel level, String label, String message) {
    calendar.setTimeInMillis(System.currentTimeMillis());
    System.out.printf("[%1$s: %2$s] %3$s - %4$s\n",
        sdf.format(calendar.getTime()),
        level.name(),
        label,
        message);
  }
  
}
