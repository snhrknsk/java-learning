package chap1.exercise1_14;

public class Walkman {

	enum ERROR_CODE {
			SUCCESS_PLAYING,
			ERROR_NO_EARPHONE,
			ERROR_NO_TAPE
	}

	private boolean isConnectEarPhone = false;
	private boolean isPlaying = false;
	private Object tape = null;

	public ERROR_CODE play() {
		if (tape == null) {
			return ERROR_CODE.ERROR_NO_TAPE;
		}
		if (!isConnectEarPhone) {
			return ERROR_CODE.ERROR_NO_EARPHONE;
		}
		//tapeオブジェクトを再生
		isPlaying = true;
		return ERROR_CODE.SUCCESS_PLAYING;
	}

	public void stop() {
		if (isPlaying && tape != null) {
			//tapeオブジェクトの停止
			isPlaying = false;
		}
	}

	public void setTape(Object tape) {
		this.tape = tape;
	}

	public void setEarphoneStatus(boolean isConnect) {
		isConnectEarPhone = isConnect;
	}
}
