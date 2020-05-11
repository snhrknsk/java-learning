package ch21.ex05;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

public class ArrayBunchListTest {

	private Integer[][] arrays;

	/**
	 * 配列<br>
	 * {1,2,3,4}<br>
	 * {5,6}<br>
	 * {7,8,9,10}<br>
	 * に対する操作
	 */
	@Test
	public void test() {
		/* 配列
		 * {1,2,3,4}
		 * {5,6}
		 * {7,8,9,10}
		 */
		arrays = new Integer[3][];
		Integer temp[] = {1,2,3,4};
		arrays[0] = temp;
		Integer temp2[] = {5,6};
		arrays[1] = temp2;
		Integer temp3[] = {7,8,9,10};
		arrays[2] = temp3;

		ArrayBunchList<Integer> list = new ArrayBunchList<>(arrays);
		ListIterator<Integer> ite = list.listIterator();

		//初期状態
		assertEquals(true, ite.hasNext());
		assertEquals(false, ite.hasPrevious());
		assertEquals(-1, ite.previousIndex());
		assertEquals(0, ite.nextIndex());
		try {
			ite.set(50);
			fail();
		} catch (IllegalStateException e) {
		}

		//next,previous繰り返す
		assertEquals(new Integer(1), ite.next());
		assertEquals(true, ite.hasNext());
		assertEquals(true, ite.hasPrevious());
		assertEquals(0, ite.previousIndex());
		assertEquals(1, ite.nextIndex());

		assertEquals(new Integer(1), ite.previous());
		assertEquals(true, ite.hasNext());
		assertEquals(false, ite.hasPrevious());
		assertEquals(-1, ite.previousIndex());
		assertEquals(0, ite.nextIndex());

		ite.next();
		ite.next();
		ite.next();
		ite.next();

		//次の配列の要素に飛ぶ(array = 1)
		assertEquals(new Integer(5), ite.next());
		assertEquals(true, ite.hasNext());
		assertEquals(true, ite.hasPrevious());
		assertEquals(4, ite.previousIndex());
		assertEquals(5, ite.nextIndex());

		//set
		ite.set(20);
		assertEquals(new Integer(20), ite.previous());
		ite.set(30);
		assertEquals(new Integer(30), ite.next());

		ite.next();
		ite.next();
		ite.next();
		ite.next();
		ite.next();

		//最後の要素
		assertEquals(false, ite.hasNext());
		assertEquals(true, ite.hasPrevious());
		assertEquals(9, ite.previousIndex());
		assertEquals(10, ite.nextIndex());

		try {
			ite.add(3);
			fail();
		}catch (UnsupportedOperationException e) {
		}
		try {
			ite.remove();
			fail();
		}catch (UnsupportedOperationException e) {
		}
	}

	/**
	 * 配列<br>
	 * {1,2,3,4}<br>
	 * {}<br>
	 * {5,6}<br>
	 * {7,8,9,10}<br>
	 * に対する操作(空配列有)
	 */
	@Test
	public void test2() {
		/* 配列
		 * {1,2,3,4}
		 * {}
		 * {5,6}
		 * {7,8,9,10}
		 */
		arrays = new Integer[4][];
		Integer temp[] = {1,2,3,4};
		arrays[0] = temp;
		Integer temp2[] = {};
		arrays[1] = temp2;
		Integer temp3[] = {5,6};
		arrays[2] = temp3;
		Integer temp4[] = {7,8,9,10};
		arrays[3] = temp4;

		ArrayBunchList<Integer> list = new ArrayBunchList<>(arrays);
		ListIterator<Integer> ite = list.listIterator();

		ite.next();
		ite.next();
		ite.next();
		ite.next();

		//次の配列の要素に飛ぶ(array = 2)
		assertEquals(new Integer(5), ite.next());
		assertEquals(true, ite.hasNext());
		assertEquals(true, ite.hasPrevious());
		assertEquals(4, ite.previousIndex());
		assertEquals(5, ite.nextIndex());

		//前の要素に戻る
		ite.previous();
		assertEquals(new Integer(4), ite.previous());
		assertEquals(true, ite.hasNext());
		assertEquals(true, ite.hasPrevious());

	}

}
