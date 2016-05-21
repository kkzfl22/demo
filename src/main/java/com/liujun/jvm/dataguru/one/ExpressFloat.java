package com.liujun.jvm.dataguru.one;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * 
 * 将一个float型转化为内存存储格式的步骤为：
 * 
 * （1）先将这个实数的绝对值化为二进制格式。
 * 
 * （2）将这个二进制格式实数的小数点左移或右移n位，直到小数点移动到第一个有效数字的右边。
 * 
 * （3）从小数点右边第一位开始数出二十三位数字放入第22到第0位。
 * 
 * （4）如果实数是正的，则在第31位放入“0”，否则放入“1”。
 * 
 * （5）如果n是左移得到的，说明指数是正的，第30位放入“1”。如果n是右移得到的或n=0，则第30位放入“0”。
 * 
 * （6）如果n是左移得到的，则将n减去1后化为二进制，并在左边加“0”补足七位，放入第29到第23位。如果n是右移得到的或n=0，则将n化为二进制后在左边加
 * “0”补足七位，再各位求反，再放入第29到第23位。
 * 
 * @author Think
 * 
 */
public class ExpressFloat
{

	public String parseFloatTwo(String value)
	{
		StringBuilder result = new StringBuilder();

		String sign = null;

		// 检查首位是否为负数
		String firatChar = value.substring(0, 1);

		if ("-".equals(firatChar))
		{
			sign = "1";
			value = value.substring(1);
		} else
		{
			sign = "0";
		}

		String[] tmpvalue = value.split("\\.");
		int intValue = Integer.parseInt(tmpvalue[0]);
		float floatValue = Float.parseFloat("0." + tmpvalue[1]);

		// （1）先将这个实数的绝对值化为二进制格式。
		String intBinary = this.intTobinary(intValue);
		String floatBinary = this.deciTobinary(floatValue, 24);

		// 二进制表示的数字
		String binaryStr = intBinary + "." + floatBinary;

		System.out.println("binary value :" + binaryStr);

		// （2）将这个二进制格式实数的小数点左移或右移n位，直到小数点移动到第一个有效数字的右边。
		boolean lrFlag = false;
		// 如果左边的数据大于0，则进行左移
		if (intValue > 0)
		{
			lrFlag = true;
		}

		Map<String, Object> map = this.moveBitBinary(binaryStr, lrFlag);
		System.out.println("map value :" + map);

		// 得到指数
		// 移动的位数
		int mvoeBit = Integer.parseInt(String.valueOf(map.get("movieBig")));
		// 转动后的值
		String moveValue = String.valueOf(map.get("binaryStr"));
		// 计算指数
		String indexValue = this.getIndex(mvoeBit, lrFlag);

		result.append(sign);
		result.append(" ");
		result.append(indexValue);
		result.append(" ");
		result.append(moveValue);

		System.out.println("result value :" + result.toString());

		return result.toString();
	}

	/**
	 * 得到二进制的指数信息
	 * 
	 * @param moveBint
	 *            转移的位数
	 * @param lrFlag
	 *            左移或者右移的标识
	 * @return 二进制的指数信息
	 */
	public String getIndex(int moveBint, boolean lrFlag)
	{
		// 如果为左移
		if (lrFlag)
		{
			moveBint = moveBint - 1;
			String twoBing = this.intTobinary(moveBint);

			if (twoBing.length() < 7)
			{
				int index = 0;
				StringBuilder zore = new StringBuilder();
				while (twoBing.length() + index < 7)
				{
					zore.append("0");
					index += 1;
				}

				twoBing = "1" + zore.toString() + twoBing;
			}

			return twoBing;

		}
		// 如果为右移
		else
		{

			String twoBing = this.intTobinary(moveBint);

			if (twoBing.length() < 7)
			{
				int index = 0;
				StringBuilder zore = new StringBuilder();
				while (twoBing.length() + index < 7)
				{
					zore.append("0");
					index += 1;
				}

				twoBing = zore.toString() + twoBing;
			}

			// 进行取反
			twoBing = this.useRev(twoBing);

			twoBing = "0" + twoBing;

			return twoBing;

		}

	}

	/**
	 * 进行取反操作
	 * 
	 * @param value
	 * @return
	 */
	public String useRev(String value)
	{
		char[] revArray = value.toCharArray();

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < revArray.length; i++)
		{
			if (revArray[i] == '0')
			{
				result.append("1");
			} else
			{
				result.append("0");
			}
		}

		return result.toString();
	}

	/**
	 * 进行数据移动
	 * 
	 * @param binaryStr
	 *            二进制字符串
	 * @param lrFlag
	 *            左移还是右移标识 true 左移 false 右移
	 * @return 移动后的结果
	 */
	private Map<String, Object> moveBitBinary(String binaryStr, boolean lrFlag)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		if (lrFlag)
		{
			String first = binaryStr.substring(0, 1);
			if ("1".equals(first))
			{
				// 计算左移的位数
				int left = binaryStr.indexOf(".") - 1;
				map.put("movieBig", left);

				// 去掉原来的小数据，换成现在的小数
				String[] binaryArr = binaryStr.split("\\.");

				String binaryValue = binaryArr[0].substring(1) + binaryArr[1];
				map.put("binaryStr", binaryValue.substring(0, 23));
			}
		} else
		{
			// 右移将每一位得都检查
			char[] binaryArray = binaryStr.substring(2).toCharArray();

			int useOk = -1;

			for (int i = 0; i < binaryArray.length; i++)
			{
				// 如果当前位为1，说明为有效数字
				if (binaryArray[i] == '1')
				{
					useOk = i + 1;
					break;
				}
			}

			map.put("movieBig", useOk);

			binaryStr = binaryStr.substring(2).substring(useOk - 1);

			binaryStr = binaryStr.substring(1);

			map.put("binaryStr", binaryStr.substring(0, 23));

		}

		return map;
	}

	/**
	 * 将整数转化为二进制
	 * 
	 * @param value
	 * @return
	 */
	private String intTobinary(int value)
	{
		StringBuilder result = new StringBuilder();

		int tmpValue = value;

		if (tmpValue > 0)
		{
			if (tmpValue > 1)
			{
				while (tmpValue > 1)
				{
					if (tmpValue % 2 == 1)
					{
						if (tmpValue / 2 == 1)
						{
							result.append("11");
						} else
						{
							result.append("1");
						}
					} else
					{
						if (tmpValue / 2 == 1)
						{
							result.append("01");
						} else
						{
							result.append("0");
						}
					}

					tmpValue = tmpValue / 2;
				}
			} else
			{
				result.append("1");
			}
			result = result.reverse();
		} else
		{
			result.append("0");
		}

		return result.toString();
	}

	/**
	 * 将小数转化为二进制
	 * 
	 * @param value
	 * @return
	 */
	public String deciTobinary(float value, int maxvalue)
	{
		StringBuilder result = new StringBuilder();

		float tmpValue = value;
		int tmpsize = 0;

		boolean isOk = false;

		while (tmpValue >= 0.0f && tmpsize <= maxvalue)
		{
			tmpValue = tmpValue * 2;

			if (tmpValue >= 1.0f)
			{
				result.append("1");
				tmpValue = tmpValue - 1.0f;
				isOk = true;
			} else
			{
				result.append("0");
			}

			if (isOk)
			{
				tmpsize += 1;
			}
		}

		return result.toString();
	}

	public static void main(String[] args)
	{

		ExpressFloat exp = new ExpressFloat();
		System.out.println(exp.parseFloatTwo("-101.025"));
	}

}
