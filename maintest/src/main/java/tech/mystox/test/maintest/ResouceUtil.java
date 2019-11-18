package tech.mystox.test.maintest;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mystoxlol on 2019/4/11, 11:17.
 * company: kongtrolink
 * description:
 * update record:
 */
public class ResouceUtil {
    public static void main(String[] args) throws IOException {

        boolean fileURL = ResourceUtils.isFileURL(ResourceUtils.getURL("http://omc.kongtrolink.com:32704/Engine/Test/PMUZ12/CassEngine.bin"));
        URL url = new URL("http://omc.kongtrolink.com:32704/Engine/Test/PMUZ12/CassEngine.bin");
        URLConnection urlc = url.openConnection();
        urlc.setConnectTimeout(100000);
        urlc.setReadTimeout(100000);
        InputStream is = urlc.getInputStream();
        File file = new File("abc.bin");
        file.createNewFile();
        System.out.println(file.getAbsolutePath());
        FileUtils.copyInputStreamToFile(is,file);
        is.close();
        System.out.println(fileURL);
//        File file = ResourceUtils.getFile();
    }
}
