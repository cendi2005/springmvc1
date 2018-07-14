package dd.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Book {
    private Long id;

    private String title;

    private BigDecimal price;

    private Date publishdate;

}