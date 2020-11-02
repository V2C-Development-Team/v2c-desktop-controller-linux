package edu.uco.cs.v2c.desktop.linux.model;

public interface RecognitionState {
    public void goToNextRecognitionState(RecognitionStateContext ctx);

    public void SetRecognitionState(RecognitionStateContext ctx, RecognitionState state);

    public void execute(String commandText, ConfigurationData configurationData);
}