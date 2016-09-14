/**
 * 2010-4-20
 */
package com.liujun.io.zip.ts;

import static org.junit.Assert.assertEquals;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Tar测试
 * 
 * @author <a href="mailto:zlex.dongliang@gmail.com">梁栋</a>
 * @since 1.0
 */
public class TarUtilsTest {
	private String inputStr;
	private String name = "data.xml";

	@Before
	public void before() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("\r\n");
		sb.append("<dataGroup>");
		sb.append("\r\n\t");
		sb.append("<dataItem>");
		sb.append("\r\n\t\t");
		sb.append("<data>");
		sb.append("Test");
		sb.append("</data>");
		sb.append("\r\n\t");
		sb.append("<dataItem>");
		sb.append("\r\n");
		sb.append("</dataGroup>");

		inputStr = sb.toString();
	}

	@Test
	public void testArchiveFile() throws Exception {

		byte[] contentOfEntry = inputStr.getBytes();

		String path = "D:\\java\\work\\demo\\framework\\demo\\src\\main\\java\\com\\liujun\\io";

		// FileOutputStream fos = new FileOutputStream(path);
		//
		// fos.write(contentOfEntry);
		// fos.flush();
		// fos.close();

		TarUtils.archive(path);

		TarUtils.dearchive(path + ".tar");

		File file = new File(path);

		FileInputStream fis = new FileInputStream(file);

		DataInputStream dis = new DataInputStream(fis);

		byte[] data = new byte[(int) file.length()];

		dis.readFully(data);

		fis.close();

		String outputStr = new String(data);
		assertEquals(inputStr, outputStr);

	}

	@Test
	public void testArchiveDir() throws Exception {
		String path = "d:/fd";
		TarUtils.archive(path);

		TarUtils.dearchive(path + ".tar", "d:/fds");
	}

}
