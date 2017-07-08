package io;
import java.io.IOException;

public class InputStreamTest {

	private int start = 0;
	private int ready = 0;

	/**
	 * 要返回的byte[].
	 */
	byte[] returnBytes = null;

	/**
	 * 目前是第一缓冲区还是第二缓冲区.<br>
	 * true : 第一缓冲区<br>
	 * false : 第二缓冲区<br>
	 */
	private boolean flag = true;
	private int totalIndex = 0;

	/**
	 * 第一缓冲区.
	 */
	byte[] byteBufferFirst = new byte[BUFFER_SIZE];

	byte[] data = new byte[] {
			-55,-93,-75,-62,-73,-58,-67,-36,-55,-15,-66,-59,-73,-94,-55,-28,-63,-6,-66,-19,-73,-25,-54,-57,-53,-68,-65,-68,-68,-72,
			-73,-42,-56,-8,-64,-83,-68,-11,-73,-54,-75,-60,-56,-8,-64,-83,-73,-65,-68,-28,-65,-88,-64,-83,-53,-71,-68,-45,-73,-47,
			-75,-62,-64,-43,-75,-60,-56,-10,-67,-8,-64,-83,-56,-8,-75,-60,-65,-86,-73,-94,-67,-8,-64,-83,-56,-8,-68,-78,-73,-25,
			-64,-83,-56,-8,-55,-93,-75,-62,-73,-58,-67,-36,-55,-15,-66,-59,-73,-94,-55,-28,-63,-6,-66,-19,-73,-25,-54,-57,-53,-68,
			-65,-68,-68,-72,-73,-42,-56,-8,-64,-83,-68,-11,-73,-54,-75,-60,-56,-8,-64,-83,-73,-65,-68,-28,-65,-88,-64,-83,-53,-71,
			-68,-45,-73,-47,-75,-62,-64,-43,-75,-60,-56,-10,-67,-8,-64,-83,-56,-8,-75,-60,-65,-86,-73,-94,-67,-8,-64,-83,-56,-8,
			-68,-78,-73,-25,-64,-83,-56,-8,-55,-93,-75,-62,-73,-58,-67,-36,-55,-15,-66,-59,-73,-94,-55,-28,-63,-6,-66,-19,-73,-25,
			-54,-57,-53,-68,-65,-68,-68,-72,-73,-42,-56,-8,-64,-83,-68,-11,-73,-54,-75,-60,-56,-8,-64,-83,-73,-65,-68,-28,-65,-88,
			-64,-83,-53,-71,-68,-45,-73,-47,-75,-62,-64,-43,-75,-60,-56,-10,-67,-8,-64,-83,-56,-8,-75,-60,-65,-86,-73,-94,-67,-8,
			-64,-83,-56,-8,-68,-78,-73,-25,-64,-83,-56,-8,-55,-93,-75,-62,-73,-58,-67,-36,-55,-15,-66,-59,-73,-94,-55,-28,-63,-6,
			-66,-19,-73,-25,-54,-57,-53,-68,-65,-68,-68,-72,-73,-42,-56,-8,-64,-83,-68,-11,-73,-54,-75,-60,-56,-8,-64,-83,-73,-65,
			-68,-28,-65,-88,-64,-83,-53,-71,-68,-45,-73,-47,-75,-62,-64,-43,-75,-60,-56,-10,-67,-8,-64,-83,-56,-8,-75,-60,-65,-86,
			-73,-94,-67,-8,-64,-83,-56,-8,-68,-78,-73,-25,-64,-83,-56,-8};

	/**
	 * 第二缓冲区.
	 */
	byte[] byteBufferSecond = new byte[BUFFER_SIZE];
	private final static int BUFFER_SIZE = 50;

	public static void main(String[] args) {
		try {
			InputStreamTest test = new InputStreamTest();
			test.readInputStream(60);
			test.readInputStream(60);
			test.readInputStream(40);
			test.readInputStream(50);
			test.readInputStream(50);
			test.readInputStream(20);
			test.readInputStream(70);
			test.readInputStream(26);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * 读取长度为length的字节.<br>
	 * 如果当前内存中已经读取的长度 + 要读取的长度 小于缓冲区的长度.<br>
	 * 那么直接读满缓冲区(当输入流的字节长度大于缓冲区长度时,否则读取输入流的长度).<br>
	 * 否则(如果当前内存中已经读取的长度 + 要读取的长度大于缓冲区的长度.<br>
	 * 那么按照上面的处理方式把字节流读取到第二缓冲区.并重新设置start,ready等.直到流中的字节读完).<br>
	 * 
	 * @param length
	 * @throws IOException
	 */
	public void readInputStream(int length) throws IOException {
		returnBytes = new byte[length];
//		loopInputStream(length);
		loop(length);
		for (int i = 0; i < length; i++) {
			System.err.print(returnBytes[i] +",");
		}
		System.out.println();
	}

	public void loopInputStream(int length) throws IOException {
		byte[] tempBytes = null;
		int readLength = 0;
		if (flag) {
			tempBytes = byteBufferFirst;
		} else {
			tempBytes = byteBufferSecond;
		}
		if (start + length <= BUFFER_SIZE) {
			if (start == 0) {
				readLength = read(tempBytes, start, BUFFER_SIZE);
			}
			if (flag) {
				byteBufferFirst = tempBytes;
			} else {
				byteBufferSecond = tempBytes;
			}
			System.arraycopy(tempBytes, start, returnBytes, 0, length);
			start += length;
			ready = 0;
		} else {
			System.arraycopy(tempBytes, start, returnBytes, ready, BUFFER_SIZE
					- start);
			ready += BUFFER_SIZE - start;
			start = 0;
			flag = !flag;
			loopInputStream(length - (BUFFER_SIZE - ready));
		}
	}

	public void loop(int needLength) {
		byte tempBuffer[] = null;
		int readLength = 0;
		if (flag) {
			tempBuffer = byteBufferFirst;
		} else {
			tempBuffer = byteBufferSecond;
		}
		if (start + needLength <= BUFFER_SIZE) {
			if (start == 0) {
				if (flag) {
					readLength = read(byteBufferFirst, start, BUFFER_SIZE);
					tempBuffer = byteBufferFirst;
				} else {
					readLength = read(byteBufferSecond, start, BUFFER_SIZE);
					tempBuffer = byteBufferSecond;
				}
			}
			System.arraycopy(tempBuffer, start, returnBytes, ready, needLength);
			start += needLength;
			ready = 0;
			totalIndex += needLength;
		} else {
			int lastLength = BUFFER_SIZE - start; 
			if (start == 0) {
				if (flag) {
					readLength = read(byteBufferFirst, start, BUFFER_SIZE);
					tempBuffer = byteBufferFirst;
				} else {
					readLength = read(byteBufferSecond, start, BUFFER_SIZE);
					tempBuffer = byteBufferSecond;
				}
			} 
			System.arraycopy(tempBuffer, start, returnBytes, ready, lastLength);
			ready += lastLength;
			start = 0;
			totalIndex += lastLength;
			flag = !flag;
			loop(needLength - (lastLength));
			
		}
	}
	private int read(byte[] srcBytes, int offset, int length) {
		int realLength = length;
		if (totalIndex + length <= data.length) {
			System.arraycopy(data, totalIndex, srcBytes, offset, length);
		} else {
			realLength = data.length - totalIndex;
			System.arraycopy(data, totalIndex, srcBytes, offset, realLength);
		}
		return realLength;
	}
	
	boolean isNeedreRead = false;

}
