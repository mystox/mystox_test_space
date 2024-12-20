package tech.mystox.test;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * ppt转pdf工具类
 *
 * @date 2023/10/23 18:40
 */
public class PptConvertPdfUtil {

    public static boolean getLicense() {
        boolean result = false;
        try {
            //  license.xml应放在.
            InputStream is = ExcelConvertPdfUtil.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void ppt2Pdf(String inPath,String outPath){
        // 验证License 去除水印
        if (!getLicense()) {
            return ;
        }
        long start = System.currentTimeMillis();
        try {
            FileInputStream fileInput = new FileInputStream(inPath);
            Presentation pres = new Presentation(fileInput);
            FileOutputStream out = new FileOutputStream(new File(outPath));
            pres.save(out, SaveFormat.Pdf);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end =System.currentTimeMillis();
        // 转化用时
        System.out.println("pdf转换成功，共耗时：{}" + ((end - start) / 1000.0) + "秒");

    }
    public static void main(String[] args) {
        String inPath = "H:\\TencentStore\\Wechat\\WeChat Files\\wxid_0830538305512\\FileStorage\\File\\2024-02\\终端内部设计思路.pptx";
        String outPath ="C:\\Users\\mystox\\Desktop\\ppt.pdf";
        ppt2Pdf(inPath,outPath);
    }

}
