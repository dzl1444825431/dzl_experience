package com.dzl.groovy.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUitl {

	public static void copy(String src, String target) {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new BufferedInputStream(new FileInputStream(src));
			fos = new BufferedOutputStream(new FileOutputStream(target));
			byte[] buf = new byte[4096];
			int i;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis);
			close(fos);
		}
	}

	public static boolean copy(File src, String target) {
		InputStream fis = null;
		OutputStream fos = null;
		File targetFile = new File(target);
		if (targetFile.exists()) {
			return false;
		}
		try {
			fis = new BufferedInputStream(new FileInputStream(src));
			fos = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] buf = new byte[4096];
			int i;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis);
			close(fos);
		}
		return true;
	}

	public static void close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
