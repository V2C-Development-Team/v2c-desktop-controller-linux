package edu.uco.cs.v2c.desktop.linux.model;

import edu.uco.cs.v2c.desktop.linux.dfaparser.Hypervisor;
import edu.uco.cs.v2c.desktop.linux.dfaparser.Machine;
import edu.uco.cs.v2c.desktop.linux.dfaparser.StreamHypervisor;
import edu.uco.cs.v2c.desktop.linux.log.Logger;

public class StreamState implements RecognitionState {
	private static final String LOG_LABEL = "STREAMING MODE";
	private Machine machine;
	private Hypervisor hypervisor;

	public StreamState() {
		try {
			machine = new Machine();
			hypervisor = new StreamHypervisor();
			hypervisor.boot(machine);
			
			machine.registerStateListener(hypervisor);
		} catch (Exception e) {
			Logger.onError(LOG_LABEL, "failed to boot Parser");
		}
	}

	@Override
	public void goToNextRecognitionState(RecognitionStateContext ctx) {
		
		ctx.setState(new CommandState());
		System.out.println("In Command State");
	}

	@Override
	public void SetRecognitionState(RecognitionStateContext ctx, RecognitionState state) {
		
		ctx.setState(state);
	}

	@Override
	public void execute(String commandText, ConfigurationData configurationData) {
		try {
			machine.onInput(commandText);
		} catch (Exception e) {
			Logger.onError(LOG_LABEL, "failed to execute");
		}
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