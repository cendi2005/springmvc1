package dd.service.impl;

import dd.mapper.BookMapper;
import dd.pojo.Book;
import dd.pojo.VO.BookVO;
import dd.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Override
    public BookVO selectByPrimaryKey(Long id) {
        Book book = bookMapper.selectByPrimaryKey(id);
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book,bookVO);
        return bookVO;
    }

    @Override
    public int insert(Book record) {
        Book book = new Book();
        book.setTitle(""+new Random().nextInt(1000));
        return bookMapper.insertSelective(book);
    }
}
