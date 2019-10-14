package ch01.ex14;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class WalkmanTest {

	private Walkman walkman;
	@Before
	public void setUp(){
		walkman = new Walkman();
	}

	@Test
	public void testPlay() {
		walkman.setTape("tape");
		walkman.setEarphoneStatus(true);
		assertEquals(Walkman.ERROR_CODE.SUCCESS_PLAYING, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(true, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testFailPlayNoTape() {
		walkman.setEarphoneStatus(true);
		assertEquals(Walkman.ERROR_CODE.ERROR_NO_TAPE, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testFailPlayNoEarPhone() {
		walkman.setTape("tape");
		assertEquals(Walkman.ERROR_CODE.ERROR_NO_EARPHONE, walkman.play());
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

	@Test
	public void testStopPlay() {
		walkman.setTape("tape");
		walkman.setEarphoneStatus(true);
		walkman.play();
		walkman.stop();
		try {
			Field isPlaying;
			isPlaying = walkman.getClass().getDeclaredField("isPlaying");
			isPlaying.setAccessible(true);
			assertEquals(false, isPlaying.getBoolean(walkman));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			fail();
		}
	}

}
