package com.liujun.io.nio.trans.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirstHandleFrom implements Runnable {

    /**
     * 服务器的地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 多路复用选择器对象
     */
    private Selector selector;

    /**
     * socket通道
     */
    private SocketChannel sc;

    /**
     * 当前的是否停止,默认false，开始状态
     */
    private AtomicBoolean stop = new AtomicBoolean(false);

    public FirstHandleFrom(String host, int port) {
        this.host = host == null ? "www.liujun.com" : host;
        this.port = port;

        try {
            selector = Selector.open();
            // 打开客户端流通道
            sc = SocketChannel.open();
            // 设置为非阻塞模式
            sc.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 进行与服务器的发送对话
        try {
            this.doConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 进行消息的接收
        while (!stop.get()) {
            try {
                // 设置1秒轮循一次
                selector.select(1000);
                // 检查是否有数据接入
                Set<SelectionKey> setKey = selector.selectedKeys();
                // 进行遍历
                Iterator<SelectionKey> iterKey = setKey.iterator();

                while (iterKey.hasNext()) {
                    SelectionKey itemKey = iterKey.next();

                    iterKey.remove();

                    try {
                        this.receive(itemKey);
                    } catch (Exception e) {
                        e.printStackTrace();
                        itemKey.cancel();
                        if (itemKey.channel() != null) {
                            itemKey.channel().close();
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void receive(SelectionKey itemKey) throws IOException, InterruptedException {
        // 如果当前键是有效的
        if (itemKey.isValid()) {

            // 如果当前的键为连接
            if (itemKey.isConnectable()) {
                // 得到服务器端的通道
                SocketChannel sc = (SocketChannel) itemKey.channel();
                // 如果已经完成连接
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_CONNECT);
                }
            }
            // 如果完成连接操作
            if (itemKey.isConnectable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) itemKey.channel();
                // 如果为后端,需要向通道中写入数据
                sc.register(selector, SelectionKey.OP_WRITE);
            }

            // 如果当前通道为读取操作
            if (itemKey.isReadable() || itemKey.isWritable()) {
                ByteBuffer buf = ByteBuffer.allocate(512);
                int readBSize = sc.read(buf);

                if (readBSize > 0) {
                    buf.flip();

                    byte[] bytebuff = new byte[buf.remaining()];

                    buf.get(bytebuff);

                    String body = new String(bytebuff, "UTF-8");

                    System.out.println("A客户端收到回复:" + body);
                }

                // 进行数据发送
                doSend(sc);
            }

        }
    }

    /**
     * 进行连接
     * 
     * @throws IOException
     * @throws InterruptedException 
     */
    private void doConnection() throws IOException, InterruptedException {
        // 如果服用服务器的连接已经连接成功，需要将读取消息注册到多路复用器上，然后发送消息
        if (sc.connect(new InetSocketAddress(host, port))) {
            // 注册读取操作到多路复用器上
            sc.register(selector, SelectionKey.OP_READ);
        } else {
            // 注册连接
            sc.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    int index = 0;

    private void doSend(SocketChannel sc) throws IOException, InterruptedException {

        if (index < 100) {
            String value = "这是来源数据的信息" + index;

            int length = value.getBytes().length;

            ByteBuffer buff = ByteBuffer.allocate(length);

            buff.put(value.getBytes());

            buff.flip();

            sc.write(buff);

            System.out.println("当前第:" + index + "发送");
            // 每隔3秒发送一次
            Thread.currentThread().sleep(1000);

            index++;
        }
    }

}
