package dd.spring;

import java.util.List;

public class ExampleBean {

    public String getBeanTwo() {
        return beanTwo;
    }

    public void setBeanTwo(String beanTwo) {
        this.beanTwo = beanTwo;
    }

    public String getIntegerProperty() {
        return integerProperty;
    }

    public void setIntegerProperty(String integerProperty) {
        this.integerProperty = integerProperty;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    private String beanTwo;
    private String integerProperty;
    private List<String> whiteList;

}
