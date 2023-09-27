public abstract class State {
    // --- méthodes abstraites --- //
    abstract boolean LockRestartButton();
    abstract boolean LockPauseButton();
    abstract boolean LockPlayButton();
    abstract boolean LockStepButton(); 
}
