package com.liujun.io.zip.gzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import com.liujun.util.IOutils;

public class TarGzCompress
{
	public void makeFolderTarPackage(List<FileTarBean> fileList, String outPutPath)
	{
		OutputStream outputStream = null;
		TarArchiveOutputStream tarOuput = null;

		try
		{
			outputStream = new FileOutputStream(outPutPath + ".tar");
			tarOuput = new TarArchiveOutputStream(outputStream);

			byte[] tarBuffer = new byte[512];

			for (FileTarBean file : fileList)
			{
				File tarReadFile = new File(file.getFilePath());

				TarArchiveEntry tarEntry = null;

				if (tarReadFile.isFile())
				{
					tarEntry = new TarArchiveEntry(file.getBasePath());
					tarEntry.setSize(tarReadFile.length());
				} else
				{
					tarEntry = new TarArchiveEntry(file.getBasePath() + File.separator);
				}

				// tarOuput.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);

				tarOuput.putArchiveEntry(tarEntry);

				if (tarReadFile.isFile())
				{
					// 写入文件
					FileInputStream input = new FileInputStream(tarReadFile);

					int size = -1;

					while ((size = input.read(tarBuffer)) != -1)
					{
						tarOuput.write(tarBuffer, 0, size);
					}

					input.close();
				}

				tarOuput.closeArchiveEntry();
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOutils.closeStream(tarOuput);
			IOutils.closeStream(outputStream);
		}

		FileInputStream input = null;

		OutputStream outputStream2 = null;
		GZIPOutputStream gzipOutput = null;

		try
		{
			input = new FileInputStream(outPutPath + ".tar");
			outputStream2 = new FileOutputStream(outPutPath + ".tar.gz");
			gzipOutput = new GZIPOutputStream(outputStream2);

			byte[] tarBuffer = new byte[512];

			int size = -1;

			while ((size = input.read(tarBuffer)) != -1)
			{
				gzipOutput.write(tarBuffer, 0, size);
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOutils.closeStream(gzipOutput);
			IOutils.closeStream(outputStream2);
			IOutils.closeStream(input);
		}

		// 删除tar文件
		new File(outPutPath + ".tar").delete();
	}

	public void makeFileTarPackage(String FilePath, String outPutPath)
	{
		OutputStream outputStream = null;
		TarArchiveOutputStream tarOuput = null;

		try
		{
			outputStream = new FileOutputStream(outPutPath + ".tar");
			tarOuput = new TarArchiveOutputStream(outputStream);

			byte[] tarBuffer = new byte[512];

			File tarReadFile = new File(FilePath);

			TarArchiveEntry tarEntry = null;
			tarEntry = new TarArchiveEntry(tarReadFile.getName());
			tarEntry.setSize(tarReadFile.length());

			tarOuput.putArchiveEntry(tarEntry);

			if (tarReadFile.isFile())
			{
				// 写入文件
				FileInputStream input = new FileInputStream(tarReadFile);

				int size = -1;

				while ((size = input.read(tarBuffer)) != -1)
				{
					tarOuput.write(tarBuffer, 0, size);
				}

				input.close();
			}

			tarOuput.closeArchiveEntry();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOutils.closeStream(tarOuput);
			IOutils.closeStream(outputStream);
		}

		FileInputStream input = null;

		OutputStream outputStream2 = null;
		GZIPOutputStream gzipOutput = null;

		try
		{
			input = new FileInputStream(outPutPath + ".tar");
			outputStream2 = new FileOutputStream(outPutPath + ".tar.gz");
			gzipOutput = new GZIPOutputStream(outputStream2);

			byte[] tarBuffer = new byte[512];

			int size = -1;

			while ((size = input.read(tarBuffer)) != -1)
			{
				gzipOutput.write(tarBuffer, 0, size);
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOutils.closeStream(gzipOutput);
			IOutils.closeStream(outputStream2);
			IOutils.closeStream(input);
		}

		// 删除tar文件
		new File(outPutPath + ".tar").delete();
	}

	public static void main(String[] args)
	{

		TarGzCompress tar = new TarGzCompress();
		List<FileTarBean> list = new ArrayList<FileTarBean>();
		//
		tar.getPath("D:\\java\\work\\demo\\framework\\demo\\src\\main\\java\\", list, null, null);
		System.out.println(list);
		//
		//tar.makeFolderTarPackage(list, "D:\\java\\work\\demo\\framework\\demo\\src\\main\\java\\com\\liujun\\output");
		tar.makeFileTarPackage("D:\\java\\work\\demo\\framework\\demo\\src\\main\\java\\com\\liujun\\io\\zip\\gzip\\FileTarBean.java", "D:\\java\\work\\demo\\framework\\demo\\src\\main\\java\\com\\liujun\\output");
		
		//
		// System.out.println(list);

	}

	/**
	 * 得到目录下所有的文件列表
	 * 
	 * @param path
	 *            路径信息
	 * @param list
	 *            存文件的集体信息
	 */
	public void getPath(String path, List<FileTarBean> list, String basePath, String outPath)
	{
		File filePath = new File(path);

		if (basePath == null)
		{
			basePath = filePath.getPath().substring(0, filePath.getPath().lastIndexOf(java.io.File.separator) + 1);
		}

		if (filePath.isDirectory())
		{
			// 提取相对路径
			String absPath = filePath.getPath().substring(basePath.length() - 1);

			if (basePath == filePath.getPath())
			{
				outPath = "\\";
			} else
			{
				outPath = absPath;
			}

			FileTarBean tarBean = new FileTarBean();
			tarBean.setBasePath(outPath);
			tarBean.setFilePath(filePath.getPath());

			list.add(tarBean);

			File[] listPath = filePath.listFiles();

			for (File file : listPath)
			{
				this.getPath(file.getPath(), list, basePath, outPath);
			}
		} else
		{
			// 提取相对路径
			String absPath = filePath.getPath().substring(basePath.length() - 1);

			FileTarBean tarBean = new FileTarBean();
			tarBean.setBasePath(absPath);
			tarBean.setFilePath(filePath.getPath());

			list.add(tarBean);
		}
	}

}
