package com.liujun.file.doc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import com.liujun.util.IOutils;

public class JavaDoc2003FileProcess {

    public static void main(String[] args) {

        String url = JavaDoc2003FileProcess.class.getClassLoader().getResource("com/liujun/file/doc/02-2003.doc")
                .getPath();

        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();

        Map<String, String> map = null;
        for (int i = 1; i <= 6; i++) {
            map = new HashMap<String, String>();
            map.put("#{seq" + i + "}", String.valueOf(i));
            map.put("#{bound" + i + "}", "终端品牌" + String.valueOf(i));
            map.put("#{model" + i + "}", "终端型号" + String.valueOf(i));
            map.put("#{num" + i + "}", "数量" + String.valueOf(i));
            map.put("#{dev" + i + "}", "设备序列号" + String.valueOf(i));
            map.put("#{remark" + i + "}", "备注" + String.valueOf(i));
            listMap.add(map);
        }

        // JavaDocFileProcess read = new JavaDocFileProcess();
        // read.word2007More();

        replace2003Doc(url, "D:\\java\\test\\out.doc", listMap);
    }

    /**
     * 读取word模板并替换变量,仅针对word2003以前的版本，相对简单
     * @param srcPath
     * @param map
     * @return
     */
    public static void replace2003Doc(String srcPath, String outpath, List<Map<String, String>> listMap) {

        FileInputStream fis = null;

        HWPFDocument doc = null;

        OutputStream outs = null;

        ByteArrayOutputStream ostream = new ByteArrayOutputStream();

        try {
            // 读取word模板
            fis = new FileInputStream(new File(srcPath));
            doc = new HWPFDocument(fis);

            // 读取word文本内容
            Range bodyRange = doc.getRange();

            for (Map<String, String> mapDoc : listMap) {
                // 替换文本内容
                for (Map.Entry<String, String> entry : mapDoc.entrySet()) {
                    bodyRange.replaceText(entry.getKey(), entry.getValue());
                }
            }

            doc.write(ostream);
            // 输出word文件
            outs = new FileOutputStream(outpath);
            outs.write(ostream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOutils.closeStream(outs);
            IOutils.closeStream(ostream);
            IOutils.closeStream(doc);
            IOutils.closeStream(fis);
        }

    }

}
