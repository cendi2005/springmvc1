package dd.spring;

import lombok.Data;

@Data
public class JsonResult {
    private String code;
    private String message;
    private String  jsonData;
}
