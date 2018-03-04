package my.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *
 *
 */
public class copyFileUseNIO {

    public static void main(String[] args) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel inChannel = null;
        FileChannel outChannel =null;
        try {
            //声明源文件和目标文件
            fi=new FileInputStream(new File("D:\\a.txt"));
            fo=new FileOutputStream(new File("D:\\nio.txt"));
            long startTime=System.currentTimeMillis();
            //获得传输通道channel
            inChannel = fi.getChannel();
            outChannel = fo.getChannel();
            //获得容器buffer
            ByteBuffer buffer= ByteBuffer.allocate(1024);

            while(true){
                //判断是否读完文件
                int eof =inChannel.read(buffer);
                if(eof==-1){
                    break;
                }
                //重设一下buffer的position=0，limit=position
                buffer.flip();
                //开始写
                outChannel.write(buffer);
                //写完要重置buffer，重设position=0,limit=capacity
                buffer.clear();
            }
            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println(String.format("程序运行时间:%s",endTime-startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null!=fi){
                    fi.close();
                }
                if(null!=fo){
                    fo.close();
                }
                if(inChannel!=null){
                    inChannel.close();
                }
                if(outChannel!=null){
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
