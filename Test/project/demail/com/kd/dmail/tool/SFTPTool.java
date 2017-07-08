package demail.com.kd.dmail.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;

import org.apache.tools.ant.util.FileUtils;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @author xuzhiqi
 */
public class SFTPTool {
	/**
	 * ����sftp������
	 * 
	 * @param host
	 *            ����
	 * @param port
	 *            �˿�
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return
	 */
	public ChannelSftp connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (Exception e) {

		}
		return sftp;
	}

	/**
	 * �ϴ��ļ�
	 * 
	 * @param directory
	 *            �ϴ���Ŀ¼
	 * @param uploadFile
	 *            Ҫ�ϴ����ļ�
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �ϴ��ļ�
	public void uploadFile(String host, int port, String username,
			String password, String directory, String uploadFile) {
		SFTPTool sf = new SFTPTool();
		ChannelSftp sftp = sf.connect(host, port, username, password);
		try {
			sftp.setFilenameEncoding("gbk");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		FileInputStream input = null;
		try {
			sftp.cd(directory);
			char[] chars = uploadFile.toCharArray();
			StringBuffer sb = new StringBuffer();
			for (char c : chars) {
				if (c == '\\') {
					c = File.separatorChar;
				}
				sb.append(c);
			}
			System.err.println(sb.toString());
			File file = new File(uploadFile);
			input = new FileInputStream(file);
			String fileName = file.getName();
			sftp.cd(directory);
			sftp.put(input, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param directory
	 *            ����Ŀ¼
	 * @param downloadFile
	 *            ���ص��ļ�
	 * @param saveFile
	 *            ���ڱ��ص�·��
	 * @param sftp
	 */
	public void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) {
		FileOutputStream out = null;
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			out = new FileOutputStream(file);
			sftp.get(downloadFile, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedReader getLogBufferedReader(String host, int port,
			String username, String password, String directory,
			String downloadFile, String saveFile) {
		downloadFile(host, port, username, password, directory, downloadFile,
				saveFile);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(saveFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// String line = "";
		// String res = "";
		// BufferedReader reader = null;
		// try {
		// FtpClient ftp = connect(ip, username, password);
		// InputStream tis = ftp.get(serverfilepath);
		// reader = new BufferedReader(new InputStreamReader(tis));
		// } catch (Exception ex) {
		// return null;
		//
		// }
		return reader;
	}

	// �ļ�����
	public void downloadFile(String host, int port, String username,
			String password, String directory, String downloadFile,
			String saveFile) {
		SFTPTool sf = new SFTPTool();
		ChannelSftp sftp = sf.connect(host, port, username, password);
		try {
			sftp.setFilenameEncoding("gbk");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		sf.download(directory, downloadFile, saveFile, sftp);
		// sftp.exit();
		// sftp.quit();
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param directory
	 *            Ҫɾ���ļ�����Ŀ¼
	 * @param deleteFile
	 *            Ҫɾ�����ļ�
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String host, int port, String username, String password,
			String directory, String deleteFile) throws SftpException {
		SFTPTool sf = new SFTPTool();
		ChannelSftp sftp = sf.connect(host, port, username, password);
		try {
			sftp.setFilenameEncoding("gbk");
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �г�Ŀ¼�µ��ļ�
	 * 
	 * @param directory
	 *            Ҫ�г���Ŀ¼
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String host, int port, String username,
			String password, String directory) throws SftpException {
		SFTPTool sf = new SFTPTool();
		ChannelSftp sftp = sf.connect(host, port, username, password);
		try {
			sftp.setFilenameEncoding("gbk");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return sftp.ls(directory);
	}

	public void main(String[] args) throws Exception {
		SFTPTool sf = new SFTPTool();
		String host = "192.168.200.163";
		int port = 22;
		String username = "d5000";
		String password = "root.123";
		String directory = "/home/d5000/cimdata/temp/";
		String uploadFile = "D:\\tmp\\upload.txt";
		String downloadFile = "ss.CIME";
		// try {
		// downloadFile = new String(downloadFile.getBytes(), "gb18030");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		String saveFile = "E:\\ss.CIME";
		String deleteFile = "delete.txt";
		ChannelSftp sftp = sf.connect(host, port, username, password);
		sftp.setFilenameEncoding("gbk");
		// Vector v = sf.listFiles(directory, sftp);
		// for (Object s : v)
		// sf.download(directory, s.toString(), saveFile, sftp);
		// System.out.println(new String(s.toString().getBytes(),"gbk"));
		// sf.upload(directory, uploadFile, sftp);
		sf.download(directory, downloadFile, saveFile, sftp);
		// sf.delete(directory, deleteFile, sftp);
		// try {
		// sftp.cd(directory);
		// sftp.mkdir("ss");
		// System.out.println("finished");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
