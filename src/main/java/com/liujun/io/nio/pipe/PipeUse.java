package com.liujun.io.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeUse {

    private static Pipe pipe;

    public PipeUse() throws IOException {
        pipe = Pipe.open();
    }

    public void writeData(String source) throws IOException {
        Pipe.SinkChannel sink = pipe.sink();

        ByteBuffer buff = ByteBuffer.allocate(1024);
        buff.clear();
        buff.put(source.getBytes());

        buff.flip();
        sink.write(buff);
    }

    public String read() throws IOException {
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = sourceChannel.read(buf);

        String value = new String(buf.array());

        return value;
    }

    public static void main(String[] args) {
        try {
            final PipeUse pipe = new PipeUse();

            Runnable run1 = new Runnable() {
                public void run() {

                    int i = 0;
                    while (true) {
                        try {
                            pipe.writeData("生成" + i);
                            i++;
                            Thread.currentThread().sleep(100);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };

            Runnable run2 = new Runnable() {
                public void run() {

                    while (true) {
                        try {
                            String value = pipe.read();
                            System.out.println(value);
                            Thread.currentThread().sleep(500);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };

            Thread thread1 = new Thread(run1);
            Thread thread2 = new Thread(run2);

            thread1.start();
            thread2.start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
