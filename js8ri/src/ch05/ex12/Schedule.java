package ch05.ex12;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Schedule {

	private final ZonedDateTime scheduledTime;
	private String schedule;
	private static final int NOTIFY_TIME = 3600;
	private static ZoneId jp = ZoneId.of("Asia/Tokyo");
	private static ZoneId sgt = ZoneId.of("Asia/Singapore");
	private static final ZoneId la = ZoneId.of("America/Los_Angeles");

	public Schedule(ZonedDateTime scheduledTime, String scheduleTitle) {
		this.scheduledTime = scheduledTime;
		schedule = scheduleTitle;
		System.out.println(scheduleTitle + "：" + scheduledTime);
	}

	public void registerNotifyZone(ZoneId zone) {
		ZonedDateTime now = ZonedDateTime.now(zone);
		System.out.println("登録Zoneでの現在日時 : " + now);
		int zoneOffset = scheduledTime.getOffset().getTotalSeconds() - now.getOffset().getTotalSeconds();
		long duration = ChronoUnit.SECONDS.between(now, scheduledTime) + zoneOffset;
		if (duration <= 0) {
			System.out.println(schedule + " : すでに予定は終わっています 現地時刻-" + now);
		} else if (duration < NOTIFY_TIME) {
			System.out.println(schedule + " : すでに予定の一時間前です 現地時刻-" + now);
		}else {
			new Thread(() -> {
				try {
					Thread.sleep((duration - NOTIFY_TIME) * 1000);
					System.out.println(schedule + " : 予定の一時間前です 現地時刻-" + ZonedDateTime.now(zone));
				} catch (Exception e) {
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		//日本時間とシンガポール時間(時差-1時間)、10秒後にシンガポール時間で通知
		ZonedDateTime scheduleTime = ZonedDateTime.now(jp).plusSeconds(10);
		Schedule event = new Schedule(scheduleTime, "Tmpスケジュール");
		event.registerNotifyZone(sgt);

//		ZonedDateTime scheduleTimeLa = ZonedDateTime.now(jp).minusHours(16).plusSeconds(10);
//		Schedule event2 = new Schedule(scheduleTimeLa, "Tmpスケジュール2");
//		event2.registerNotifyZone(la);
	}


}
