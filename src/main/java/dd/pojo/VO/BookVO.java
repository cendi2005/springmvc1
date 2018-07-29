package dd.pojo.VO;

import org.springframework.stereotype.Component;

@Component
public class BookVO {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
