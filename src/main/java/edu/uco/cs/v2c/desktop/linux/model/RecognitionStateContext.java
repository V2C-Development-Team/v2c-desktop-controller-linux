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

public class RecognitionStateContext {
    private RecognitionState currentState;

    public RecognitionStateContext() {
        this.currentState = new CommandState();
    }

    public void printState() {
        System.out.println(currentState);
    }

    public RecognitionState getState() {
        return this.currentState; 
    }

    public void setState(RecognitionState state) {
        this.currentState = state;
    }

    public void goToNextRecognitionState() {
        this.currentState.goToNextRecognitionState(this);
    }

    public void execute(String commandText, ConfigurationData configurationData) {
        this.currentState.execute(commandText, configurationData);
    }
}
