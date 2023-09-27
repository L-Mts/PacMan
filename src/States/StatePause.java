package States;

public class StatePause extends State {

    @Override
    public boolean LockPauseButton() {
        return true;
    }

    @Override
    public boolean LockPlayButton() {
        return false;
    }

    @Override
    public boolean LockRestartButton() {
        return false;
    }

    @Override
    public boolean LockStepButton() {
        return false;
    }
    
}
