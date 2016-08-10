package com.liujun.io.nio.sockettofile.server.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.liujun.io.nio.sockettofile.server.bean.ProcessMsg;
import com.liujun.io.nio.sockettofile.server.console.Config;
import com.liujun.io.nio.sockettofile.tran.FromTo;

public class ProcessChannel implements Runnable {

    /**
     * 进来进行捉
    * @字段说明 control
    */
    private CyclicBarrier control = new CyclicBarrier(1);

    private List<ProcessMsg> listMsg = new ArrayList<>();

    @Override
    public void run() {

        String url = FromTo.class.getClassLoader().getResource("com/liujun/io/nio/sockettofile/server/service")
                .getPath();

        url = url + "/proces.data";

        RandomAccessFile randomFile = null;
        FileChannel channel = null;

        try {
            randomFile = new RandomAccessFile(url, "rw");
            channel = randomFile.getChannel();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        SocketChannel sock = null;

        while ((sock = LinkedChannel.getInstance().getChannel()) != null) {

            ByteBuffer buffer = ByteBuffer.allocate(8);

            try {

                sock.read(buffer);

                buffer.flip();

                int flag = buffer.getInt();
                int size = buffer.getInt();

                // 如果当前为读取通道
                if (Config.FLAG_TYPE_FROM.getFlag() == flag) {
                    channel.transferFrom(sock, 0, size);
                    ProcessMsg msg = new ProcessMsg(flag, size, channel);
                    listMsg.add(msg);
                    control.await();
                }
                // 如果当前为写入通道通道
                else if (Config.FLAG_TYPE_TO.getFlag() == flag) {
                    ProcessMsg msg = listMsg.remove(0);

                    msg.getChanninfo().transferTo(0, msg.getSize(), sock);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

}
