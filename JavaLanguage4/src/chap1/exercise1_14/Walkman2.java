package chap1.exercise1_14;

public class Walkman2 extends Walkman {

	private boolean isConnectSecondEarPhone = false;
	private boolean isPlayingSecondEarPhone = false;

	@Override
	public ERROR_CODE play() {
		ERROR_CODE result = super.play();
		if (result == ERROR_CODE.SUCCESS_PLAYING) {
			if (isConnectSecondEarPhone) {
				//２つ目の端子で再生
				isPlayingSecondEarPhone = true;
			}
		}
		return super.play();
	}

	@Override
	public void stop() {
		super.stop();
		if (isPlayingSecondEarPhone) {
			//2つ目の端子の再生を停止
		}
	}
}
