package ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BitSetMap {

	private Map<Byte, BitSet> used = new HashMap<>();

	public BitSetMap(String str) {
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			Byte upperBit = (byte) (c >> 8);
			BitSet lowerSet;
			if (used.containsKey(upperBit)) {
				lowerSet = used.get(upperBit);
			} else {
				lowerSet = new BitSet();
			}
			lowerSet.set(c & 0xff);
			used.put(upperBit, lowerSet);
		}
	}

	@Override
	public String toString() {
		StringBuilder desc = new StringBuilder("[");
		for (Entry<Byte, BitSet> entry : used.entrySet()) {
			Byte upperBit = entry.getKey();
			BitSet lowerSet = entry.getValue();
			for (int i = lowerSet.nextSetBit(0); i >= 0; i = lowerSet.nextSetBit(i + 1)) {
				desc.append((char) (upperBit << 8 | i));
			}
		}
		return desc.append("]").toString();
	}

	public static void main(String[] args) {
		System.out.println(new BitSetMap("Testing 1 2 3"));
	}
}
