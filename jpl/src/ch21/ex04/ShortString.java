package ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * ShorStringを拡張すべきではない。
 * previousでひとつ前の対象の文字列に戻った場合にnextShortを変更する必要があるため。
 *
 */
public class ShortString implements ListIterator<String> {

	private ListIterator<String> strings;
	private final int maxLen;
	private String nextShort;
	private String prevShort;
	private int nextIndex = -1;
	private int prevIndex = -1;

	public ShortString(ListIterator<String> strings, int maxLen) {
		this.maxLen = maxLen;
		this.strings = strings;
		nextShort = null;
		prevShort = null;
	}

	@Override
	public boolean hasNext() {
		if (nextShort != null) {
			return true;
		}
		int currentIndex = strings.nextIndex();
		boolean hasNext = moveToNext();
		clearIndex(strings.nextIndex() - currentIndex, true);
		return hasNext;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext()) {
			 throw new NoSuchElementException();
		}
		moveToNext();
		String n = nextShort;
		nextShort = null;
		prevShort = null;
		return n;
	}

	/**
	 * stringsを次の要素まで実際に動かす
	 * @return 次の要素があればtrue
	 */
	private boolean moveToNext() {
		while(strings.hasNext()) {
			nextShort = strings.next();
			if (nextShort.length() <= maxLen) {
				prevShort = null;
				nextIndex = strings.nextIndex() - 1;
				return true;
			}
		}
		nextShort = null;
		nextIndex = strings.nextIndex();
		return false;
	}

	@Override
	public boolean hasPrevious() {
		if (prevShort != null) {
			return true;
		}
		int currentIndex = strings.nextIndex();
		boolean hasPrev = moveToPrev();
		clearIndex(currentIndex - strings.nextIndex(), false);
		return hasPrev;
	}

	@Override
	public String previous() {
		if (prevShort == null && !hasPrevious()) {
			throw new NoSuchElementException();
		}
		moveToPrev();
		String p = prevShort;
		prevShort = null;
		nextShort = null;
		return p;
	}

	private boolean moveToPrev() {
		while(strings.hasPrevious()) {
			prevShort = strings.previous();
			if (prevShort.length() <= maxLen) {
				nextIndex = prevIndex;
				prevIndex = strings.previousIndex() + 1;
				return true;
			}
		}
		prevIndex = -1;
		prevShort = null;
		return false;
	}

	@Override
	public int nextIndex() {
		hasNext();
		return nextIndex;
	}

	@Override
	public int previousIndex() {
		hasPrevious();
		return prevIndex;
	}

	@Override
	public void remove() {
		strings.remove();
		nextShort = null;
		prevShort = null;
	}

	@Override
	public void set(String e) {
		strings.set(e);
		nextShort = null;
		prevShort = null;
	}

	@Override
	public void add(String e) {
		strings.add(e);
		nextShort = null;
		prevShort = null;
	}

	/**
	 * backCount分stringsの位置を戻す<br>
	 * isNextがtrueの時は戻り、falseの時は進む
	 * @param backCount
	 * @param isNext
	 */
	private void clearIndex(int backCount, boolean isNext) {
		if (isNext) {
			for (int i = 0; i < backCount; i++) {
				if (strings.hasPrevious()) {
					strings.previous();
				}
			}
		} else {
			for (int i = 0; i < backCount; i++) {
				if (strings.hasNext()) {
					strings.next();
				}
			}
		}
	}


}
