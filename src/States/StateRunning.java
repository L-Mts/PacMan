package States;

public class StateRunning extends State {

    @Override
    public boolean LockRestartButton() {
        return false;
    }

    @Override
    public boolean LockPauseButton() {
        return false;
    }

    @Override
    public boolean LockPlayButton() {
        return true;
    }

    @Override
    public boolean LockStepButton() {
        return true;
    }
    
}
