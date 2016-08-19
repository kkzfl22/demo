package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.liujun.io.nio.trans.bean.ChannelAttachMsg;
import com.liujun.io.nio.trans.conn.TransConnectManager;
import com.liujun.io.nio.trans.console.Config;
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

    /**
     * 做连接管理信息
    * @字段说明 transConn
    */
    private TransConnectManager transConn;

    public MultiplexerAllService(Selector selector, TransConnectManager transConn) {
        // 进行多路选择器的复用
        this.selector = selector;
        this.transConn = transConn;
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
                int readyChannels = selector.select(100);

                if (readyChannels == 0) {
                    continue;
                }

                // 轮循进行检查是否有已经准备就绪的key
                Set<SelectionKey> selectkey = selector.selectedKeys();

                Iterator<SelectionKey> iter = selectkey.iterator();

                while (iter.hasNext()) {
                    SelectionKey sekey = iter.next();

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

                    iter.remove();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {

        if (key.isValid()) {
            // 如果当前的连接已经用于套接字接受操作
            if (key.isAcceptable()) {
                // 得到当前注册的通道
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                ChannelAttachMsg type = (ChannelAttachMsg) key.attachment();

                SocketChannel sc = ssc.accept();

                // 设置为非阻塞
                sc.configureBlocking(false);

                // 前端的数据进行读取，
                sc.register(selector, SelectionKey.OP_WRITE, type);
            }

            // 如果当前的键为连接
            if (key.isConnectable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();

                // 如果已经完成连接
                if (sc.finishConnect()) {
                    ChannelAttachMsg type = (ChannelAttachMsg) key.attachment();

                    // 注册后端连接
                    transConn.regEndConn(new TransProxyFile());

                    sc.register(selector, SelectionKey.OP_WRITE, type);
                }
            }

            // 检查此键的通道是否已准备好进行读取
            if (key.isReadable() || key.isWritable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();

                ChannelAttachMsg type = (ChannelAttachMsg) key.attachment();

                // 如果前端连接
                if (Config.CONFIG_TYPE_FIRST.getKey() == type.getKey()) {

                    if (null != type.getTransChannel()) {
                        // 将A通道中的数据加载的内存A
                        try {
                            type.getTransChannel().getTransProxyA().tranFrom(sc);
                            // 将内存B的数据写入到通道A
                            type.getTransChannel().getTransProxyB().tranTo(sc);
                        } catch (IOException e) {
                            e.printStackTrace();
                            // 发生异常关闭流信息
                            type.getTransChannel().getTransProxyA().close();
                            type.getTransChannel().getTransProxyB().close();
                            throw e;
                        }
                    }
                }
                // 如果为后端连接
                else if (Config.CONFIG_TYPE_END.getKey() == type.getKey()) {
                    if (null != type.getTransChannel()) {
                        try {
                            // 将内存块B的数据读取到通道B
                            type.getTransChannel().getTransProxyB().tranFrom(sc);
                            // 将内存块A的数据写入到通道B
                            type.getTransChannel().getTransProxyA().tranTo(sc);
                        } catch (IOException e) {
                            e.printStackTrace();
                            // 发生异常关闭流信息
                            type.getTransChannel().getTransProxyB().close();
                            type.getTransChannel().getTransProxyA().close();
                            throw e;
                        }
                    }
                }
            }
        }
    }

}
