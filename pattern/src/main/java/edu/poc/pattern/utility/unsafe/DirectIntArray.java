package edu.poc.pattern.utility.unsafe;

class DirectIntArray {

	// private final static long INT_SIZE_IN_BYTES = 4;
	//
	// private final long startIndex;
	//
	// public DirectIntArray(long size) {
	// startIndex = unsafe.allocateMemory(size * INT_SIZE_IN_BYTES);
	// unsafe.setMemory(startIndex, size * INT_SIZE_IN_BYTES, (byte) 0);
	// }
	// }
	//
	// public void setValue(long index, int value) {
	// unsafe.putInt(index(index), value);
	// }
	//
	// public int getValue(long index) {
	// return unsafe.getInt(index(index));
	// }
	//
	// private long index(long offset) {
	// return startIndex + offset * INT_SIZE_IN_BYTES;
	// }
	//
	// public void destroy() {
	// unsafe.freeMemory(startIndex);
	// }
}
