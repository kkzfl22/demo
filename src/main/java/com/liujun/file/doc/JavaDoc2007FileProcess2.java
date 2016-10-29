package com.liujun.file.doc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class JavaDoc2007FileProcess2 {

    public static void main(String[] args) {

        String url = JavaDoc2007FileProcess2.class.getClassLoader().getResource("com/liujun/file/doc/02-2003.doc")
                .getPath();

        JavaDoc2007FileProcess2 read = new JavaDoc2007FileProcess2();
        read.word2007More();

    }

    public void word2007More() {
        /***********************office word 2007版本以上解析********************************/
        // File file = new File("C:/Users/wangshuaifei/Desktop/test.doc");
        // String imagePath = "C:/Users/wangshuaifei/Desktop/pic";
        try {
            String url = JavaDoc2007FileProcess2.class.getClassLoader()
                    .getResource("com/liujun/file/doc/term_test2.docx").getPath();

            // File file = new File("C:/Users/wangshuaifei/Desktop/word.docx");
            File file = new File(url);
            String imagePath = "D:\\java\\test\\out.docx";
            // OPCPackage oPCPackage =
            // POIXMLDocument.openPackage(file.getPath());
            // XWPFDocument xwpf = new XWPFDocument(oPCPackage);

            XWPFDocument xwpf = new XWPFDocument(POIXMLDocument.openPackage(url));

            // 获取页面中的表格
            Iterator<XWPFTable> it = xwpf.getTablesIterator();
            while (it.hasNext()) {
                // 循环页面中的表格
                XWPFTable table = (XWPFTable) it.next();
                // 此表格中共有多少行，包括嵌套的总行数
                int rcount = table.getNumberOfRows();
                System.out.println(rcount);
                StringBuffer str = new StringBuffer();
                for (int i = 0; i < rcount; i++) {
                    // 获取表格中的行
                    XWPFTableRow row = table.getRow(i);
                    // 获取行中共有多少列
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (int c = 0; c < cells.size(); c++) {
                        // 获取列
                        XWPFTableCell cell = cells.get(c);
                        // System.out.println("Paragraphs:----"+cell.getParagraphs().size());
                        // 获取列中的段落
                        for (int j = 0; j < cell.getParagraphs().size(); j++) {
                            // 获取段落中的字符，包括空格（有待验证，我也不是很理解）,每个字符为一个XWPFRun对象
                            List<XWPFRun> runs = cell.getParagraphs().get(j).getRuns();
                            for (int j2 = 0; j2 < runs.size(); j2++) {
                                // 获取单个对象
                                XWPFRun r = runs.get(j2);
                                // 获取字符，此位置不知道干嘛用的，0和-1都能用
                                String text = r.getText(r.getTextPosition());
                                str.append(text);
                                // System.out.println(text);
                                System.out.println(r.getUnderline().toString());
                                System.out.println(r.getSubscript().toString());
                                System.out.println(r.getFontFamily());
                                System.out.println(r.getFontSize());
                                System.out.println(r.isBold());
                                System.out.println(r.getColor());
                                // 如果字符为空，可能是附件一类的文件，比如图片之类的，需要另外解析,此处处理为图片
                                if (text == null) {
                                    List<XWPFPicture> piclist = r.getEmbeddedPictures();
                                    for (int k = 0; k < piclist.size(); k++) {
                                        XWPFPicture pic = piclist.get(k);
                                        // pic.getPictureData().getData();
                                        byte[] picbyte = pic.getPictureData().getData();
                                        // 将图片写入本地文件
                                        FileOutputStream fos = new FileOutputStream(
                                                imagePath + "/" + file.getName() + j + ".jpg");
                                        fos.write(picbyte);
                                        System.out.println("EmbeddedPictures:----" + r.getEmbeddedPictures().size());
                                    }

                                }

                            }
                            str.append("\n");
                        }
                    }
                }
                System.out.println(str.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
