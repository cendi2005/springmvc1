package dd.service;


import dd.pojo.Book;
import dd.pojo.VO.BookVO;
import dd.util.db.DataSource;

public interface BookService {

    @DataSource("slave")
    BookVO selectByPrimaryKey(Long id);


    @DataSource("master")
    int insert(Book record);



}
