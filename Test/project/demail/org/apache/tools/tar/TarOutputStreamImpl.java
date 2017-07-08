package demail.org.apache.tools.tar;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.tools.tar.TarConstants;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

public class TarOutputStreamImpl extends TarOutputStream {
	public TarOutputStreamImpl(OutputStream os) {
		super(os);
	}

	public void putNextEntry(TarEntry entry) throws IOException {
		if (entry.getName().length() >= TarConstants.NAMELEN) {

			if (longFileMode == LONGFILE_GNU) {
				// create a TarEntry for the LongLink, the contents
				// of which are the entry's name
				TarEntry longLinkEntry = new TarEntry(
						TarConstants.GNU_LONGLINK,
						TarConstants.LF_GNUTYPE_LONGNAME);
				// XXX:modify
				longLinkEntry
						.setSize(entry.getName().getBytes("GBK").length + 1);
				putNextEntry(longLinkEntry);
				write(entry.getName().getBytes("GBK"));
				write(0);
				closeEntry();
			} else if (longFileMode != LONGFILE_TRUNCATE) {
				throw new RuntimeException("file name '" + entry.getName()
						+ "' is too long ( > " + TarConstants.NAMELEN
						+ " bytes)");
			}
		}

		entry.writeEntryHeader(recordBuf);
		this.buffer.writeRecord(recordBuf);

		this.currBytes = 0;

		if (entry.isDirectory()) {
			this.currSize = 0;
		} else {
			this.currSize = entry.getSize();
		}
		currName = entry.getName();
	}
}
