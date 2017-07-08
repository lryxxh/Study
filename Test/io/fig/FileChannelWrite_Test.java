package fig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class FileChannelWrite_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final RandomAccessFile file = new RandomAccessFile("C:/Users/lryxxh/Desktop/FigRectangle.log", "rw");
			final FileChannel channel = file.getChannel();
			
			JFrame frame = new JFrame();
			frame.setSize(800, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JButton button = new JButton("Write");
			frame.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MappedByteBuffer buffer;
					try {
						buffer = channel.map(MapMode.PRIVATE, 0, 4);
						String string = JOptionPane.showInputDialog("test");
						System.out.println(buffer);
						for(int i = 0; i<buffer.capacity();i++) {
							System.out.println(buffer);
							buffer.put(string.getBytes());
						}
//						buffer.rewind();
//						buffer.force();
						channel.write(buffer);
						channel.close();
						file.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
			frame.setVisible(true);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
