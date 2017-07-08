package io;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import d5000enhance.DataUnit;

public class ObjectInputStreamTest {

	public static void main(String[] args) {
		try {
			ObjectInputStream aInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\LRY\\Desktop\\ÎÄµµ\\a.txt"));
			ObjectInputStream bInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\LRY\\Desktop\\ÎÄµµ\\a1.txt"));
			ObjectInputStream cInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\LRY\\Desktop\\ÎÄµµ\\a2.txt"));

			Vector<String> descrs = (Vector<String>) aInputStream.readObject();
			Vector<String> psids = (Vector<String>) bInputStream.readObject();
			Vector<Vector<DataUnit>> data = (Vector<Vector<DataUnit>>) cInputStream.readObject();
			aInputStream.close();
			bInputStream.close();
			cInputStream.close();

			System.out.println("begin time ");
			int size = data.size();
			Vector rightRows = new Vector();
			long time = System.currentTimeMillis();
			boolean isMarch = false;
			for (int j = 0; j < size; j++) {
				Vector dataitem = data.get(j);
				String descrResult = dataitem.get(0).toString();
				String psidResult = dataitem.get(1).toString();
				int descrIndex = descrs.indexOf(descrResult);
				int psidIndex = psids.indexOf(psidResult);
				if (psidIndex == descrIndex && descrIndex != -1) {
					rightRows.add(descrIndex);
				}
				// for (int i = 0; i < descrs.size(); i++) {
				// String descr = descrs.get(i);
				// String pisd = psids.get(i);
				// if(!descr.equals(descrResult)&& !pisd.equals(psidResult) ||
				// !descr.equals(descrResult)) {
				//
				// }
				// // if (descr.equals(descrResult)) {
				// // isMarch = true;
				// // if (!pisd.equals(dataitem.get(1).toString())) {
				// // wrongRows.add(i);
				// // }
				// // break;
				// // } else {
				// // continue;
				// // }
				// }
				// if (!isMarch) {
				// wrongRows.add(i);
				// }
			}

			System.out.println("total time " + (System.currentTimeMillis() - time));
			System.out.println(rightRows);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
