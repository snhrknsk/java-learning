package chap1.exercise1_14;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class Walkman2Test {

	private Walkman2 walkman;
	@Before
	public void setUp(){
		walkman = new Walkman2();
	}

	@Test
	public void testPlayBoth() {
		walkman.setTape("tape");
		walkman.setEarphoneStatus(true);
		walkman.setSecondEarPhoneStaus(true);
		assertEquals(Walkman.ERROR_CODE.SUCCESS_PLAYING, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getSuperclass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(true, isPlaying.getBoolean(walkman));
			isPlaying = walkman.getClass().getDeclaredField("isPlayingSecondEarPhone");
			isPlaying.setAccessible(true);
			assertEquals(true, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testPlayOnlyFirstEarPhone() {
		walkman.setTape("tape");
		walkman.setEarphoneStatus(true);
		walkman.setSecondEarPhoneStaus(false);
		assertEquals(Walkman.ERROR_CODE.SUCCESS_PLAYING, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getSuperclass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(true, isPlaying.getBoolean(walkman));
			isPlaying = walkman.getClass().getDeclaredField("isPlayingSecondEarPhone");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testPlayOnlySecondEarPhone() {
		walkman.setTape("tape");
		walkman.setEarphoneStatus(false);
		walkman.setSecondEarPhoneStaus(true);
		assertEquals(Walkman.ERROR_CODE.SUCCESS_PLAYING, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getSuperclass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
			isPlaying = walkman.getClass().getDeclaredField("isPlayingSecondEarPhone");
			isPlaying.setAccessible(true);
			assertEquals(true, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testPlayNoTape() {
		walkman.setEarphoneStatus(true);
		walkman.setSecondEarPhoneStaus(false);
		assertEquals(Walkman.ERROR_CODE.ERROR_NO_TAPE, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getSuperclass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
			isPlaying = walkman.getClass().getDeclaredField("isPlayingSecondEarPhone");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testStop() {
		walkman.setTape("tape");
		walkman.setEarphoneStatus(true);
		walkman.setSecondEarPhoneStaus(true);
		walkman.play();
		walkman.stop();
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getSuperclass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
			isPlaying = walkman.getClass().getDeclaredField("isPlayingSecondEarPhone");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

}
