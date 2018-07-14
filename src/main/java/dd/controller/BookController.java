package dd.controller;
import dd.service.BookService;
import dd.util.redis.JedisClientSingle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;


@Controller
@RequestMapping("/book")
public class BookController {

    private static final Logger logger = LogManager.getLogger(BookController.class);


    @Resource
    private BookService bookService;


    @Resource
    private JedisClientSingle jedisClientSingle;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){

        jedisClientSingle.set("hello","workd");
        logger.debug("============debug:hello");
        logger.info("============info:hello");
        logger.error("============error:hello");
        logger.trace("============trace:hello");
        logger.warn("============warn:hello");

        return "hello"+bookService.selectByPrimaryKey(1L);
    }

}
