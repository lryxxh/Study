package lang;

import java.util.BitSet;

public class BitSet_Test {
	
	public BitSet_Test() {
	}
	
	public static void main(String[] args) {
		BitSet bitSet = new BitSet(16);
		for(int i = 0; i < bitSet.size(); i++) {
			bitSet.set(i);
		}
	}

}
