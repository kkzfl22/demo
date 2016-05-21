package com.liujun.io.zip.gzip;

public class FileTarBean
{
	/**
	 * 打包带的路径
	 */
	private String basePath;
	
	/**
	 * 文件路径
	 */
	private String filePath;

	public String getBasePath()
	{
		return basePath;
	}

	public void setBasePath(String basePath)
	{
		this.basePath = basePath;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("FileTarBean [basePath=");
		builder.append(basePath);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append("]");
		return builder.toString();
	}
	
	
}
