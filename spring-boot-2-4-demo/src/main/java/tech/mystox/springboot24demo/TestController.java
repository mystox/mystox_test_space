package tech.mystox.springboot24demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mystox on 2024/6/12, 15:51.
 * company:
 * description:
 * update record:
 */
@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private Integer port;
    @PostMapping("/index")
    public Integer test() {
        logger.info(port+"");
        return port;
    }
}
