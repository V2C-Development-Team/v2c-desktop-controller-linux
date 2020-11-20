package edu.uco.cs.v2c.desktop.linux.command;

public class TerminalCommand {

	// Jon-Heavily edited this and removed extraneous code
	// I have removed the kickback of any output, but if
	// this is needed for functionality let me know and I'll
	// cook some modifications up.

	Runtime rt;
	TerminalCommand rte;

	public void ExecuteCommand(String s) {
		rt = Runtime.getRuntime();
		rte = new TerminalCommand();

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