package States;

public class StateInitial extends State {

    @Override
    public boolean LockRestartButton() {
        return true;
    }

    @Override
    public boolean LockPauseButton() {
        return true;
    }

    @Override
    public boolean LockPlayButton() {
        return false;
    }

    @Override
    public  boolean LockStepButton() {
        return false;
    }
    
}
