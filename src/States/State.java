package States;

public abstract class State {
    // --- méthodes abstraites --- //
    public abstract boolean LockRestartButton();
    public abstract boolean LockPauseButton();
    public abstract boolean LockPlayButton();
    public abstract boolean LockStepButton(); 
}
