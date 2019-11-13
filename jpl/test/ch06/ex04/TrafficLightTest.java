package ch06.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TrafficLightTest {

	@Test
	public void test() {
		Color expected = Color.BLUE;
		assertThat(TrafficLight.BLUE.getColor(), is(expected));
		expected = Color.YELLOW;
		assertThat(TrafficLight.YELLOW.getColor(), is(expected));
		expected = Color.RED;
		assertThat(TrafficLight.RED.getColor(), is(expected));
	}

}
