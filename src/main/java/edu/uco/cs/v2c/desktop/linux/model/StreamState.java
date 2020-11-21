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
