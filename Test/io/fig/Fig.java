package fig;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class Fig {

	private int x = 0;
	
	private int y = 0;
	
	private int width = 0;
	
	private int height = 0;
	
	private Color strokeColor = Color.RED;
	
	private Color fillColor = Color.GREEN;
	
	public List<ByteBuffer> sendProtocol() {
		List<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
		
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(x);
		buffers.add(buffer);
		
		buffer = ByteBuffer.allocate(4);
		buffer.putInt(y);
		buffers.add(buffer);
		
		buffer = ByteBuffer.allocate(4);
		buffer.putInt(width);
		buffers.add(buffer);
		
		buffer = ByteBuffer.allocate(4);
		buffer.putInt(height);
		buffers.add(buffer);
		
		buffer = ByteBuffer.allocate(4);
		buffer.putInt(strokeColor.getRGB());
		buffers.add(buffer);
		
		buffer = ByteBuffer.allocate(4);
		buffer.putInt(fillColor.getRGB());
		buffers.add(buffer);
		
		return buffers;
	}
	
	public void receiveProtocol(InputStream inputStream) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		try {
			inputStream.read(buffer.array());
			setX(buffer.getInt());
			
			buffer.rewind();
			inputStream.read(buffer.array());
			setY(buffer.getInt());
			
			buffer.rewind();
			inputStream.read(buffer.array());
			setWidth(buffer.getInt());
			
			buffer.rewind();
			inputStream.read(buffer.array());
			setHeight(buffer.getInt());
			
			buffer.rewind();
			inputStream.read(buffer.array());
			setHeight(buffer.getInt());
			
			buffer.rewind();
			inputStream.read(buffer.array());
			setStrokeColor(new Color(buffer.getInt()));
			
			buffer.rewind();
			inputStream.read(buffer.array());
			setFillColor(new Color(buffer.getInt()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		Graphics graphics = g.create();
		paintStroke(graphics);
		
		fill(graphics);
		graphics.dispose();
	}
	
	private void paintStroke(Graphics g) {
		g.setColor(strokeColor);
		g.drawRect(x, y, width, height);
	}
	
	private void fill(Graphics g) {
		g.setColor(fillColor);
		g.fillRect(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}
