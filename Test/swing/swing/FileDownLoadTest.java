package swing;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileDownLoadTest {

	private static final int TCOUNT = 10;

	private CountDownLatch latch = new CountDownLatch(TCOUNT);

	private long completeLength = 0;

	private long fileLength;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

//		long begin = System.currentTimeMillis();
//		new FileDownLoadTest().download("http://test1.com");
//		System.out.println(System.currentTimeMillis() - begin);
		File file = new File("C:/Users/liurenyong/Desktop/AppFuncID.conf");
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes2 = new byte[90];
		while (fis.read(bytes2) != -1) {
			for(int i = 0; i < bytes2.length; i++) {
				System.out.print(bytes2[i] +" ");
			}
			bytes2 = new byte[90];
			System.out.println("........" + bytes2.length);
		}
		long length = file.length();
		System.out.println();
		System.out.println("===========" + length);
		long position = length / 3;
//		byte[] bytes = new byte[(int)position];
		ByteBuffer buffer = ByteBuffer.allocate((int)position);
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
		randomAccessFile.seek(0);
//		byte[] bytes = buffer.array();
		byte[] bytes = new byte[(int)position];
		randomAccessFile.read(bytes);
		for (int i = 0;i < bytes.length;i++) {
			System.out.print(bytes[i] + " ");
		}
		System.out.println();
		RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rws");
		randomAccessFile2.seek(position);
		bytes = new byte[(int)position];
		randomAccessFile2.read(bytes);
		for (int i = 0;i < bytes.length;i++) {
			System.err.print(bytes[i] + " ");
		}
		System.out.println();
		RandomAccessFile randomAccessFile3 = new RandomAccessFile(file, "rws");
		randomAccessFile3.seek(2 * position);
		bytes = new byte[(int)position];
		randomAccessFile3.read(bytes);
		for (int i = 0;i < bytes.length;i++) {
			System.out.print(bytes[i] + " ");
		}
		
		RandomAccessFile randomAccessFile4 = new RandomAccessFile(file, "rws");
		randomAccessFile4.seek(3 * position);
		bytes = new byte[(int)position];
		randomAccessFile4.read(bytes);
		for (int i = 0;i < bytes.length;i++) {
			System.err.print(bytes[i] + " ");
		}
	}

	public void download(String address) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(TCOUNT);
//		URL url = new URL(address);
//		URLConnection cn = url.openConnection();
//		cn.setRequestProperty("Referer", "http:// www.2cto.com ");
//		fileLength = cn.getContentLength();
		File srcfile = new File("F:/安装/eclipe/eclipse-rcp-juno-SR1-win32-x86_64.zip");
		fileLength = srcfile.length();
		long packageLength = fileLength / TCOUNT;
		long leftLength = fileLength % TCOUNT;
		RandomAccessFile file = new RandomAccessFile("test.rar", "rw");
		// 计算每个线程请求文件的开始和结束位置
		long pos = 0;
		long endPos = pos + packageLength;
		for (int i = 0; i < TCOUNT; i++) {
			if (leftLength > 0) {
				endPos++;
				leftLength--;
			}
			service.execute(new DownLoadThread(srcfile, file, pos, endPos));
			pos = endPos;
		}
		latch.await();
	}

	class DownLoadThread implements Runnable {
		private File srcFile = null;
		private URL url;
		private RandomAccessFile file;
		private long from;
		private long end;

		DownLoadThread(URL url, RandomAccessFile file, long from, long end) {
			this.url = url;
			this.file = file;
			this.from = from;
			this.end = end;
		}
		DownLoadThread(File srcFile, RandomAccessFile file, long from, long end) {
			this.srcFile = srcFile;
			this.file = file;
			this.from = from;
			this.end = end;
		}

		public void run() {
			long pos = from;
			byte[] buf = new byte[512];
			try {
				HttpURLConnection cn = (HttpURLConnection) url.openConnection();
				cn.setRequestProperty("Range", "bytes=" + from + "-" + end);
				if (cn.getResponseCode() != 200 && cn.getResponseCode() != 206) {
					run();
					return;
				}
				BufferedInputStream bis = new BufferedInputStream(
						cn.getInputStream());
				int len;
				while ((len = bis.read(buf)) != -1) {
					synchronized (file) {
						file.seek(pos);
						file.write(buf, 0, len);
					}
					pos += len;
					completeLength += len;
					System.out.println(completeLength * 100 / fileLength + "%");
				}
				cn.disconnect();
				latch.countDown();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
}