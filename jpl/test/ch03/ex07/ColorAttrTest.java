package ch03.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import ch03.ex07.ColorAttr.ScreenColor;

public class ColorAttrTest {

	@Test
	public void testHashCode() {
		ColorAttr attr1 = new ColorAttr("name1", "value1");
		ColorAttr attr2 = new ColorAttr("name2", "value2");
		ColorAttr attr3 = new ColorAttr("name1", "value1");
		assertThat(attr1.hashCode(), is(not(attr2.hashCode())));
		assertThat(attr1.hashCode(), is(attr3.hashCode()));
	}

	@Test
	public void testSetValueObject() {
		ColorAttr target = new ColorAttr("name", "value");
		Object old = target.setValue("new");
		assertThat(old, is((Object)"value"));
		assertThat(target.getValue(), is((Object)"new"));
	}

	@Test
	public void testColorAttrStringScreenColor() {
		ScreenColor color = new ScreenColor("red");
		ColorAttr target = new ColorAttr("name", color);
		assertThat(target.getValue(), is((Object)color.toString()));
	}

	@Test
	public void testEqualsObject() {
		ScreenColor color1 = new ScreenColor("value1");
		ScreenColor color2 = new ScreenColor("value2");
		ColorAttr attr1 = new ColorAttr("name1", color1);
		ColorAttr attr2 = new ColorAttr("name2", color1);
		ColorAttr attr3 = new ColorAttr("name1", color2);
		ColorAttr attr4 = new ColorAttr("name1", color1);
		assertThat(attr1.equals(attr2), is(false));
		assertThat(attr1.equals(attr3), is(false));
		assertThat(attr1.equals(attr4), is(true));
	}

}
