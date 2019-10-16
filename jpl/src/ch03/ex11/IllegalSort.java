package ch03.ex11;

public class IllegalSort extends SortDouble {

	private boolean secondSort = false;

	protected void doSort() {
		if (secondSort) {
			return;
		}
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0) {
					swap(i, j);
				}
			}
		}
		secondSort = true;
		//もう一度sortを呼び出しメトリクス初期化
		sort(null);
	}

}
