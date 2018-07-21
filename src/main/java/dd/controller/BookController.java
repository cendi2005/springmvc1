package dd.controller;

import dd.service.BookService;
import dd.service.impl.MyService;
import dd.spring.ExampleBean;
import dd.util.redis.JedisClientSingle;
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

    private static final Logger logger = LogManager.getLogger(BookController.class);


    @Resource
    private BookService bookService;

    @Resource
    private MyService myService;

    @Resource
    private ExampleBean exampleBean;


    @Resource
    private JedisClientSingle jedisClientSingle;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){

        jedisClientSingle.set("hello","workd");
        myService.test();
        List<String> list = exampleBean.getWhiteList();
        System.out.println("ExampleBean:================="+list.size());

        bookService.insert(null);
        System.out.println("master change");
        System.out.println("dev1");
        System.out.println("dev2");

        return "hello"+bookService.selectByPrimaryKey(1L);
    }

}
