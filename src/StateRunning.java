public class StateRunning extends State {

    @Override
    boolean LockRestartButton() {
        return false;
    }

    @Override
    boolean LockPauseButton() {
        return false;
    }

    @Override
    boolean LockPlayButton() {
        return true;
    }

    @Override
    boolean LockStepButton() {
        return true;
    }
    
}
