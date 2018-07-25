package dd.controller;

import dd.service.BookService;
import dd.spring.ExampleBean;
import dd.util.redis.JedisClientSingle;
import dd.util.web.WebContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

    static Logger logger = LogManager.getLogger(BookController.class.getName());

    @Resource
    private BookService bookService;
    @Resource
    private ExampleBean exampleBean;
    @Resource
    private JedisClientSingle jedisClientSingle;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){

        logger.info("info");
        logger.error("error");
        logger.debug("debug");
        logger.trace("trace");

        jedisClientSingle.set("hello","workd");
        List<String> list = exampleBean.getWhiteList();
        System.out.println("ExampleBean:================="+list.size());

        System.out.println("master change");
        System.out.println("dev1");
        System.out.println("dev2");
        System.out.println("dev3");
        WebContext webContext = WebContext.get();
        String str = webContext.getBodyJsonString();
        System.out.println(str);
        return "hello";
    }

}
