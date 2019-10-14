package ch01.ex14;

public class Walkman2 extends Walkman {

	private boolean isConnectSecondEarPhone = false;
	private boolean isPlayingSecondEarPhone = false;

	@Override
	public ERROR_CODE play() {
		ERROR_CODE result = super.play();
		if (result != ERROR_CODE.ERROR_NO_TAPE) {
			if (isConnectSecondEarPhone) {
				//２つ目の端子で再生
				isPlayingSecondEarPhone = true;
				return ERROR_CODE.SUCCESS_PLAYING;
			}
		}
		return result;
	}

	@Override
	public void stop() {
		super.stop();
		if (isPlayingSecondEarPhone) {
			//2つ目の端子の再生を停止
			isPlayingSecondEarPhone = false;
		}
	}

	public void setSecondEarPhoneStaus(boolean isConnect) {
		isConnectSecondEarPhone = isConnect;
	}
}
