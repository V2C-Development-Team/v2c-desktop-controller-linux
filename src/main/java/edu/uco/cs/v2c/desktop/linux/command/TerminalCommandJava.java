package edu.uco.cs.v2c.desktop.linux.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TerminalCommandJava {

	Runtime rt;
	TerminalCommandJava rte;
	printOutput errorReported, outputMessage;

	// public TerminalCommandJava() {
	// }
	
	public printOutput getStreamWrapper(InputStream is, String type) {
		return new printOutput(is, type);
	}
	
	public void ExecuteCommand(String s) {
		rt = Runtime.getRuntime();
		rte = new TerminalCommandJava();
		try {
			Process proc = rt.exec(s);
			proc.waitFor();
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private class printOutput extends Thread {
		InputStream is = null;

		printOutput(InputStream is, String type) {
			this.is = is;
		}

		public void run() {
			String s = null;
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
