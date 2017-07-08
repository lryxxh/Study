package demail.com.kd.dmail.tool;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.tools.tar.TarEntry;

import demail.org.apache.tools.tar.TarOutputStreamImpl;


/**
 * 
 * @author xuzhiqi
 */
public class DMailTarUtils {
	private static int BUFFER = 1024 * 4; // �����С

	private static byte[] B_ARRAY = new byte[BUFFER];

	/*
	 * �������ܣ���������ļ����ļ��� ������inputFileName Ҫ������ļ��л��ļ���·�� targetFileName �������ļ�·��
	 */
	public static void execute(String inputFileName, String targetFileName) {
		// String inputFileName = "F://ServiceManage.war//ServiceManage.war";
		// String targetFileName = "F://ServiceManage.tar";
		File inputFile = new File(inputFileName);
		String base = inputFileName
				.substring(inputFileName.lastIndexOf("/") + 1);
		TarOutputStreamImpl out = getTarOutputStream(targetFileName);
		tarPack(out, inputFile, base);
		try {
			if (null != out) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		compress(new File(targetFileName));
	}

	public static void main(String args[]) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("E:\\����\\Java������\\[����ǳ��Software.Development.�������].O'Reilly.-.Head.First.Software.Development.pdf");
		list.add("E:\\������վ\\Automatic Standard Test System��͸���Խ�� 1.2.doc");
		DMailTarUtils.execute(list, "temp.tar");
	}

	/*
	 * �������ܣ��������ļ����ļ��� ������inputFileNameList Ҫ������ļ��л��ļ���·�����б� targetFileName
	 * �������ļ�·��
	 */
	public static void execute(List<String> inputFileNameList,
			String targetFileName) {
//		String floderPath = targetFileName.substring(0,
//				targetFileName.lastIndexOf("/"));
//		if (floderPath != null && floderPath != "") {
//			File file = new File(floderPath);
//			if (!file.isDirectory()) {
//				file.mkdirs();
//			}
//		}
		TarOutputStreamImpl out = getTarOutputStream(targetFileName);
		for (String inputFileName : inputFileNameList) {
			File inputFile = new File(inputFileName);
			String base = inputFileName.substring(inputFileName
					.lastIndexOf("/") + 1);
			tarPack(out, inputFile, base);
		}
		try {
			if (null != out) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		compress(new File(targetFileName));
		File f = new File(targetFileName);
		f.delete();
	}

	/*
	 * �������ܣ������tar�ļ� ������out ����������ļ����� inputFile Ҫѹ�����ļ��л��ļ� base ����ļ��е�·��
	 */

	private static void tarPack(TarOutputStreamImpl out, File inputFile, String base) {
		if (inputFile.isDirectory()) // ����ļ���
		{
			packFolder(out, inputFile, base);
		} else
		// ����ļ�
		{
			packFile(out, inputFile, base);
		}
	}

	/*
	 * �������ܣ������ļ����µ����ݣ���������ļ��У��͵���tarPack���� ������out ����������ļ����� inputFile Ҫѹ�����ļ��л��ļ�
	 * base ����ļ��е�·��
	 */
	private static void packFolder(TarOutputStreamImpl out, File inputFile,
			String base) {
		File[] fileList = inputFile.listFiles();
		try {
			// �ڴ���ļ������·��
			out.putNextEntry(new TarEntry(base + "/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		base = base.length() == 0 ? "" : base + "/";
		for (File file : fileList) {
			tarPack(out, file, base + file.getName());
		}
	}

	/*
	 * �������ܣ�����ļ� ������out ѹ���������ļ����� inputFile Ҫѹ�����ļ��л��ļ� base ����ļ��е�·��
	 */
	private static void packFile(TarOutputStreamImpl out, File inputFile,
			String base) {
		TarEntry tarEntry = new TarEntry(base);

		// ���ô���ļ��Ĵ�С����������ã���������ݵ��ļ�ʱ���ᱨ��
		tarEntry.setSize(inputFile.length());
		try {
			out.putNextEntry(tarEntry);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int b = 0;

		try {
			while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err
					.println("NullPointerException info ======= [FileInputStream is null]");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.closeEntry();
				}
			} catch (IOException e) {

			}
		}
	}

	/*
	 * �������ܣ��Ѵ����tar�ļ�ѹ����gz��ʽ ������srcFile Ҫѹ����tar�ļ�·��
	 */
	private static void compress(File srcFile) {
		File target = new File(srcFile.getAbsolutePath() + ".gz");
		FileInputStream in = null;
		GZIPOutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new GZIPOutputStream(new FileOutputStream(target));
			int number = 0;
			while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, number);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}

				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * �������ܣ���ô�����ļ����� ������targetFileName ������ļ���·��
	 */
	private static TarOutputStreamImpl getTarOutputStream(String targetFileName) {
		// �������ļ�û��.tar��׺�������Զ�����
		targetFileName = targetFileName.endsWith(".tar") ? targetFileName
				: targetFileName + ".tar";
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(targetFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				fileOutputStream);
		TarOutputStreamImpl out = new TarOutputStreamImpl(bufferedOutputStream);

		// �������������Σ���ѹ�����е�·���ֽ�������100 byteʱ���ͻᱨ��
		out.setLongFileMode(TarOutputStreamImpl.LONGFILE_GNU);
		return out;
	}

	/*
	 * �������ܣ���ֵ�ļ���ָ��Ŀ¼
	 */
	public static boolean copyFile(String fileFromPath, String fileToPath) {
		boolean b = false;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fileFromPath);
			out = new FileOutputStream(fileToPath);
			int length = in.available();
			int len = (length % 1024 == 0) ? (length / 1024)
					: (length / 1024 + 1);
			byte[] temp = new byte[1024];
			for (int i = 0; i < len; i++) {
				in.read(temp);
				out.write(temp);
			}
			b = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

}
