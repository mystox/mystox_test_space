package tech.mystox.test.maintest;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mystox on 2023/5/24, 10:02.
 * company:
 * description:进程环境
 *
 * update record:
 */
public class EnvironmentTest {
    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> environment = processBuilder.environment();
        System.out.println(environment);
        Process start = processBuilder.start();

    }
}
