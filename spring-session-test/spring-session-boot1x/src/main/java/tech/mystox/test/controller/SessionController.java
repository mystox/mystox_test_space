package tech.mystox.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * \* @Author: mystox
 * \* Date: 2019/11/18 11:11
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/session")
public class SessionController {


    @RequestMapping("/getSessionId")
    public String getSessionId(HttpSession session) {
        String id = session.getId();
        System.out.println(id);
        return id;
    }

}