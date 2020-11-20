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