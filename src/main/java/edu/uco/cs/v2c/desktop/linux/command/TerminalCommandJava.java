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
