public class StateInitial extends State {

    @Override
    boolean LockRestartButton() {
        return true;
    }

    @Override
    boolean LockPauseButton() {
        return true;
    }

    @Override
    boolean LockPlayButton() {
        return false;
    }

    @Override
    boolean LockStepButton() {
        return false;
    }
    
}
