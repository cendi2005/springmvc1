package dd.service;


import dd.pojo.VO.BookVO;

public interface BookService {
    BookVO selectByPrimaryKey(Long id);

}
