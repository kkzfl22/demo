package com.liujun.thread.reactor.demo.marythread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 进行通道数据读取操作
 * 
 * @author liujun
 * 
 * @date 2015年4月28日
 * @vsersion 0.0.1
 */
public class SocketHandler implements OperatorInf {

	/**
	 * 日志对象
	 */
	private Logger log = Logger.getLogger(SocketHandler.class);

	/**
	 * 通道对象
	 */
	private final SocketChannel socketChann;

	/**
	 * 通道选择key
	 */
	private final SelectionKey selectKey;

	/**
	 * 构造通告初始化信息
	 * 
	 * @param sctorParam
	 *            通道选择器对象
	 * @param scParam
	 *            通道对象
	 * @throws IOException
	 */
	public SocketHandler(Selector sctorParam, SocketChannel scParam) throws IOException {
		this.socketChann = scParam;
		socketChann.configureBlocking(false);
		// 将当前读取数据的信息注册到通道中
		this.selectKey = socketChann.register(sctorParam, SelectionKey.OP_READ);
		// 将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。
		selectKey.attach(this);
		// 将当前通道的数据标识为可读
		selectKey.interestOps(SelectionKey.OP_READ);
		// 使尚未返回的第一个选择操作立即返回。
		sctorParam.wakeup();
	}

	/**
	 * 数据储储
	 */
	private Map<Long, Long> currMap = new ConcurrentHashMap<Long, Long>();

	@Override
	public void oper() throws Exception {

		if (selectKey.isReadable()) {
			ByteBuffer buffByt = ByteBuffer.allocate(512);

			StringBuilder showMsg = new StringBuilder();

			// 读取数据
			try {
				while ((socketChann.read(buffByt)) > 0) {
					buffByt.flip();
					showMsg.append(new String(buffByt.array()));
					buffByt.clear();
				}

				log.info("server socketHandler msg :" + showMsg.toString());

				if (null != showMsg.toString() && !"".equals(showMsg.toString())) {
					currMap.put(Long.valueOf(showMsg.toString().trim()), Long.valueOf(showMsg.toString().trim()));

					// 改变当前通道状态
					socketChann.register(selectKey.selector(), SelectionKey.OP_WRITE);
					selectKey.attach(this);
					selectKey.interestOps(SelectionKey.OP_WRITE);
				}

			} catch (IOException e) {
				e.printStackTrace();
				log.error("socketHandler read error:", e);
				// 异常处理有问题
				throw e;
			}

		} else if (selectKey.isWritable()) {
			ByteBuffer buffByt = ByteBuffer.allocate(64);

			Iterator<Entry<Long, Long>> iter = currMap.entrySet().iterator();

			while (iter.hasNext()) {
				// 读取数据
				try {
					String msg = String.valueOf(iter.next().getKey());
					buffByt.put((msg).toString().getBytes());

					log.info("socketReadHandler rspone msg :" + msg);

					buffByt.flip();
					socketChann.write(buffByt);
					buffByt.clear();
					// num++;

					// 改变通道信息
					socketChann.register(selectKey.selector(), SelectionKey.OP_READ);
					selectKey.attach(this);
					selectKey.interestOps(SelectionKey.OP_READ);

				} catch (IOException e) {
					e.printStackTrace();
					// 异常处理有问题
					log.error("SocketHandler write error:", e);
					throw e;
				}
				iter.remove();
			}
		}
	}

	public SocketChannel getSocketChann() {
		return socketChann;
	}

	public SelectionKey getSelectKey() {
		return selectKey;
	}

}
