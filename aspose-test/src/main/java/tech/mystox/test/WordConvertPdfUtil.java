package tech.mystox.test;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * word转换pdf
 * @date 2023/10/23 09:50
 */
public class WordConvertPdfUtil {

    public static boolean getLicense() {
        boolean result = false;
        try {
            // license.xml应放在
            InputStream is = WordConvertPdfUtil.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF
     * @param inPath 源文件路径
     * @param outPath 新pdf文件路径
     */
    public static void doc2pdf(String inPath, String outPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        System.out.println("开始转换doc==start");
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            // 新建一个空白pdf文档
            File file = new File(outPath);
            os = new FileOutputStream(file);
            // Address是将要被转化的word文档
            Document doc = new Document(inPath);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            doc.save(os, SaveFormat.PDF);
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            // 转化用时
            System.out.println("共耗时:{}"+((now - old) / 1000.0) );
        } catch (Exception e) {
            System.out.println("转换pdf报错==[{}]"+e);
        }
        finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }








    public static void main(String[] args) {
        String inPath = "H:\\TencentStore\\Wechat\\WeChat Files\\wxid_0830538305512\\FileStorage\\File\\2024-04\\小火鸟智慧破产平台需求文档-20240229(2).docx";
        String outPath ="C:\\Users\\mystox\\Desktop\\word.pdf";
        doc2pdf(inPath,outPath);
    }

}
