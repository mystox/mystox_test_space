package tech.mystox.test;


import com.aspose.cells.License;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * excel表格转换pdf
 *
 * @date 2023/10/23 10:58
 */
public class ExcelConvertPdfUtil {



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

    /**
     * excel 转为pdf 输出。
     *
     * @param sourceFilePath excel文件
     * @param desFilePathd   pad 输出文件目录
     */
    public static void excel2pdf(String sourceFilePath, String desFilePathd) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        System.out.println("开始转换excel==start");
        FileOutputStream fileOS = null;
        long old = System.currentTimeMillis();
        try {
            // 原始excel路径
            Workbook wb = new Workbook(sourceFilePath);

            fileOS = new FileOutputStream(desFilePathd);
            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);
            int[] autoDrawSheets = {3};
            //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
            autoDraw(wb, autoDrawSheets);
            int[] showSheets = {0};
            //隐藏workbook中不需要的sheet页。
            printSheetPage(wb, showSheets);
            wb.save(fileOS, pdfSaveOptions);
            fileOS.flush();
            long now = System.currentTimeMillis();
            System.out.println("共耗时:{}"+ ((now - old) / 1000.0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOS != null) {
                try {
                    fileOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 隐藏workbook中不需要的sheet页。
     *
     * @param wb
     * @param page 显示页的sheet数组
     */
    public static void printSheetPage(Workbook wb, int[] page) {
        for (int i = 1; i < wb.getWorksheets().getCount(); i++) {
            wb.getWorksheets().get(i).setVisible(false);
        }
        if (null == page || page.length == 0) {
            wb.getWorksheets().get(0).setVisible(true);
        } else {
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).setVisible(true);
            }
        }
    }

    /**
     * 设置打印的sheet 自动拉伸比例
     *
     * @param wb
     * @param page 自动拉伸的页的sheet数组
     */
    public static void autoDraw(Workbook wb, int[] page) {
        if (null != page && page.length > 0) {
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
            }
        }
    }


    public static void main(String[] args) {
        String inputExcelPath = "H:\\IDM_DIR\\债权人债权汇总信息表.xlsx";
        String outputPdfPath = "C:\\Users\\mystox\\Desktop\\excel.pdf";
        excel2pdf(inputExcelPath, outputPdfPath);

    }
}