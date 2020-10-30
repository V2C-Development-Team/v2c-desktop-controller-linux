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
