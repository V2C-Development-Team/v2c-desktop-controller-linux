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
package edu.uco.cs.v2c.desktop.linux.command;

public class TerminalCommandJava {

	// Jon-Heavily edited this and removed extraneous code
	// I have removed the kickback of any output, but if
	// this is needed for functionality let me know and I'll
	// cook some modifications up.

	Runtime rt;
	TerminalCommandJava rte;

	public void ExecuteCommand(String s) {
		rt = Runtime.getRuntime();
		rte = new TerminalCommandJava();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// Process proc = rt.exec(s);
					rt.exec(s);

				} catch (Exception e) {
					System.out.println("Failed to execute " + s);
					System.out.println(e.toString());
				}

			}

		});
		thread.setDaemon(true);
		thread.start();

	}

}
