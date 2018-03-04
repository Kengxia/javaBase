package my.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/1.
 */
public class copyFileUseIO {


    public static void main(String[] args) {

        File inFile = new File("D://a.txt");
        File outFile = new File("D://io.txt");
        FileInputStream in =null;
        FileOutputStream out =null;
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            in =  new FileInputStream(inFile);
            out =  new FileOutputStream(outFile);
            long startTime=System.currentTimeMillis();
            while((len=in.read(buff)) !=-1 ){
                out.write(buff,0,len);
            }
            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println(String.format("程序运行时间:%s",endTime-startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
