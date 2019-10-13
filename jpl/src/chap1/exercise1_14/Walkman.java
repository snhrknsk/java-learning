package chap1.exercise1_14;

public class Walkman {

	enum ERROR_CODE {
			SUCCESS_PLAYING,//再生できている場合
			ERROR_NO_EARPHONE,//イヤホンが刺さっていない場合
			ERROR_NO_TAPE//テープが存在しない場合
	}

	private boolean isConnectEarPhone = false;
	private boolean isPlaying = false;
	private Object tape = null;

	/**
	 * テープが存在しイヤホンがささっている場合再生
	 *
	 * @return 再生時のエラーコード
	 */
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

	/**
	 * 音楽が再生されている場合音楽を止める
	 */
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
