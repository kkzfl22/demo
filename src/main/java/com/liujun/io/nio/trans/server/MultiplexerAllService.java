package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.liujun.io.nio.trans.console.Config;
import com.liujun.io.nio.trans.tran.TransProxyEndFile;
import com.liujun.io.nio.trans.tran.TransProxyFile;

/**
 * 后端数据读取操作
* 源文件名：MultiplexerEndService.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月11日
* 修改作者：liujun
* 修改日期：2016年8月11日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class MultiplexerAllService implements Runnable {

    /**
     * 多路选择器
     */
    private Selector selector;

    /**
     * 多路通道
     */
    ServerSocketChannel serverchannel;

    /**
     * 用来控制整个服务器的开始操作,true,表示开启，false 关闭
     */
    private AtomicBoolean stop = new AtomicBoolean(false);

    public MultiplexerAllService(Selector selector) {
        // 进行多路选择器的复用
        this.selector = selector;
    }

    public void stop() {
        this.stop.set(true);
    }

    @Override
    public void run() {
        // 如果当前开始不是关闭的才能运行
        while (!stop.get()) {
            try {
                // 在此通道阻塞1000毫秒
                selector.select(1000);
                // 轮循进行检查是否有已经准备就绪的key
                Set<SelectionKey> selectkey = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectkey.iterator();

                while (iter.hasNext()) {
                    SelectionKey sekey = iter.next();
                    iter.remove();

                    // 将当前的数据交给对应的方法来处理
                    try {
                        this.handleInput(sekey);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (null != sekey) {
                            sekey.cancel();
                            if (sekey.channel() != null) {
                                sekey.channel().close();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 前端写入标识
    * @字段说明 firstWristFlag
    */
    private AtomicBoolean firstWristFlag = new AtomicBoolean(false);

    /**
     * 前端写入标识
     * @字段说明 firstWristFlag
     */
    private AtomicBoolean endWristFlag = new AtomicBoolean(false);

    private void handleInput(SelectionKey key) throws IOException {
        if (null != key && key.isValid()) {

            // 如果当前的键为连接
            if (key.isConnectable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();
                // 如果已经完成连接
                if (sc.finishConnect()) {
                    Integer type = (Integer) key.attachment();
                    sc.register(selector, SelectionKey.OP_CONNECT, type);
                }
            }
            // 如果完成连接操作
            if (key.isConnectable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();

                Integer type = (Integer) key.attachment();

                if (Config.CONFIG_TYPE_END.getKey() == type) {
                    // 如果为后端,需要向通道中写入数据
                    sc.register(selector, SelectionKey.OP_WRITE, type);
                }
            }

            // 如果当前的连接已经用于套接字接受操作
            if (key.isAcceptable()) {
                // 得到当前注册的通道
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                // 得到socket通信通道
                SocketChannel sc = ssc.accept();

                // 设置为非阻塞
                sc.configureBlocking(false);

                Integer type = (Integer) key.attachment();

                if (Config.CONFIG_TYPE_FIRST.getKey() == type) {
                    // 前端的数据进行读取，
                    sc.register(selector, SelectionKey.OP_READ, type);
                }

            }
            // 检查此键的通道是否已准备好进行读取
            if (key.isReadable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();

                Integer type = (Integer) key.attachment();

                // 如果为前端
                if (Config.CONFIG_TYPE_FIRST.getKey() == type) {
                    // 首先读取前端的数据生成到文件
                    try {
                        TransProxyFile.getInstance().tranFrom(sc);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 发生异常关闭流信息
                        TransProxyFile.getInstance().close();
                        throw e;
                    }

                    sc.register(selector, SelectionKey.OP_WRITE, type);

                }
                // 如果为后端
                if (Config.CONFIG_TYPE_END.getKey() == type) {
                    // 从后端捞取到数据，并写入通道中
                    try {
                        TransProxyEndFile.getInstance().tranFrom(sc);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 发生异常关闭流信息
                        TransProxyEndFile.getInstance().close();
                        throw e;
                    }

                    firstWristFlag.set(true);
                }

            }
            // 数据的写入准备
            if (key.isWritable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();

                Integer type = (Integer) key.attachment();

                // 如果为前端
                if (Config.CONFIG_TYPE_FIRST.getKey() == type) {
                    // 将数据中后端通道中写入前端
                    boolean writeRsp = false;
                    try {
                        writeRsp = TransProxyEndFile.getInstance().tranTo(sc);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 发生异常关闭流信息
                        TransProxyEndFile.getInstance().close();
                        throw e;
                    }

                    if (writeRsp) {
                        sc.register(selector, SelectionKey.OP_READ, type);
                    }

                }
                // 如果为后端
                if (Config.CONFIG_TYPE_END.getKey() == type) {
                    // 进行前端已经到中间状态的数据向后端传输
                    boolean transRsp = false;
                    try {
                        transRsp = TransProxyFile.getInstance().tranTo(sc);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 发生异常关闭流信息
                        TransProxyFile.getInstance().close();
                        throw e;
                    }

                    if (transRsp) {
                        sc.register(selector, SelectionKey.OP_READ, type);
                    }
                }

            }
        }
    }

}
