package com.liujun.system;

import java.io.Serializable;

/**
 * 操作系统内存的信息
 * @author liujun
 *
 */
public class SystemBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 可用内存
	 */
	private long totalMemory;
	
	/**
	 * 剩余内存
	 */
	private long freeMemory;
	
	/**
	 * 最大可使用内存
	 */
	private long maxMemory;
	
	
	/**
	 * 操作系统名称
	 */
	private String osName;
	
	/**
	 * 总的物理内存
	 */
	private long totalPhyMemory;
	
	/**
	 * 剩余的物理内存
	 */
	private long freePhyMemory;
	
	
	/**
	 * 剩余的物理内存
	 */
	private long usePhyMemory;
	
	
	/**
	 * 线程数
	 */
	private long totalThreads;
	
	
	/**
	 * cpu使用率
	 */
	private double cpuRatio;

	public long getTotalMemory()
	{
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory)
	{
		this.totalMemory = totalMemory;
	}

	public long getFreeMemory()
	{
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory)
	{
		this.freeMemory = freeMemory;
	}

	public long getMaxMemory()
	{
		return maxMemory;
	}

	public void setMaxMemory(long maxMemory)
	{
		this.maxMemory = maxMemory;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getOsName()
	{
		return osName;
	}

	public void setOsName(String osName)
	{
		this.osName = osName;
	}

	public long getTotalPhyMemory()
	{
		return totalPhyMemory;
	}

	public void setTotalPhyMemory(long totalPhyMemory)
	{
		this.totalPhyMemory = totalPhyMemory;
	}

	public long getFreePhyMemory()
	{
		return freePhyMemory;
	}

	public void setFreePhyMemory(long freePhyMemory)
	{
		this.freePhyMemory = freePhyMemory;
	}

	public long getUsePhyMemory()
	{
		return usePhyMemory;
	}

	public void setUsePhyMemory(long usePhyMemory)
	{
		this.usePhyMemory = usePhyMemory;
	}

	public long getTotalThreads()
	{
		return totalThreads;
	}

	public void setTotalThreads(long totalThreads)
	{
		this.totalThreads = totalThreads;
	}

	public double getCpuRatio()
	{
		return cpuRatio;
	}

	public void setCpuRatio(double cpuRatio)
	{
		this.cpuRatio = cpuRatio;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SystemBean [totalMemory=");
		builder.append(totalMemory);
		builder.append(", freeMemory=");
		builder.append(freeMemory);
		builder.append(", maxMemory=");
		builder.append(maxMemory);
		builder.append(", osName=");
		builder.append(osName);
		builder.append(", totalPhyMemory=");
		builder.append(totalPhyMemory);
		builder.append(", freePhyMemory=");
		builder.append(freePhyMemory);
		builder.append(", usePhyMemory=");
		builder.append(usePhyMemory);
		builder.append(", totalThreads=");
		builder.append(totalThreads);
		builder.append(", cpuRatio=");
		builder.append(cpuRatio);
		builder.append("]");
		return builder.toString();
	}
	
}
