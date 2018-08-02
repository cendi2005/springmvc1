package dd.controller;

import dd.service.BookService;
import dd.spring.ExampleBean;
import dd.util.redis.JedisClientSingle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


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
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(HttpServletRequest request){

        String name = request.getParameter("name");

        logger.info("===========name========:"+name);
        logger.info("===========name1========:"+name);

//        logger.info("info");
//        logger.info("info");
//        logger.error("error");
//        logger.debug("debug");
//        logger.trace("trace");
//
//        jedisClientSingle.set("hello","workd");


            String key = jedisClientSingle.setnx("hello","world");
            logger.info("setnx key :"+key);



        logger.info("redis get "+jedisClientSingle.get("hello"));

//        List<String> list = exampleBean.getWhiteList();
//        System.out.println("ExampleBean:================="+list.size());
//
//        System.out.println("master change");
//        System.out.println("dev1");
//        System.out.println("dev2");
//        System.out.println("dev3");
//        WebContext webContext = WebContext.get();
//        String str = webContext.getBodyJsonString();

//        BookVO bookVO = bookService.selectByPrimaryKey(1L);
        return "hello world!";
    }

}
