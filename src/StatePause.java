public class StatePause extends State {

    @Override
    boolean LockPauseButton() {
        return true;
    }

    @Override
    boolean LockPlayButton() {
        return false;
    }

    @Override
    boolean LockRestartButton() {
        return false;
    }

    @Override
    boolean LockStepButton() {
        return false;
    }
    
}
