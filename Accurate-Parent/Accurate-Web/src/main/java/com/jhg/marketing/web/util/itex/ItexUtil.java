package com.jhg.marketing.web.util.itex;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lxl
 * @since 2019/7/9 10:36
 */
public class ItexUtil {
    // 利用模板生成pdf
    public static void fillTemplate() {
        // 模板路径
        String templatePath = "C:/Users/admin/Desktop/test.pdf";
        // 生成的新文件路径
        String newPDFPath = "C:/Users/admin/Desktop/result.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            // 输出流
            out = new FileOutputStream(newPDFPath);
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            String[] str = {"123456789", "TOP__ONE", "男", "1991-01-01", "130222111133338888", "河北省保定市"};
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                System.out.println(name);
                form.setField(name, str[i++]);
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }
    }

    public static void main(String[] args) {
        fillTemplate();
    }
}
