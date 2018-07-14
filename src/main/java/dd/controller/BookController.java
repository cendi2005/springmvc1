package dd.controller;

import dd.service.BookService;
import dd.util.redis.JedisClientSingle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/book")
public class BookController {


    @Resource
    private BookService bookService;


    @Resource
    private JedisClientSingle jedisClientSingle;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){

        jedisClientSingle.set("hello","workd");
        return "hello"+bookService.selectByPrimaryKey(1L);
    }

}
