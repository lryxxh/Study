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


public class FileChannelRead_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RandomAccessFile file = new RandomAccessFile("C:/Users/lryxxh/Desktop/figs/FigRectangle.log", "rws");
			final FileChannel channel = file.getChannel();
			
			JFrame frame = new JFrame();
			frame.setSize(800, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JButton button = new JButton("Read");
			frame.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MappedByteBuffer buffer;
					try {
						buffer = channel.map(MapMode.READ_WRITE, 0, 20);
						System.out.println(buffer);
						while (buffer.hasRemaining()) {
							System.out.print((char)buffer.get() + " ");
						}
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
