package dd.spring;

import com.alibaba.fastjson.serializer.SerializerFeature;
import dd.util.web.WebContext;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
//  自定义返回json

public class JsonZipMessageConvert extends AbstractHttpMessageConverter<Object> {


    public static final Charset UTF8 = Charset.forName("utf-8");

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature[] features) {
        this.features = features;
    }

    private Charset charset = UTF8;

    private SerializerFeature[] features = new SerializerFeature[0];



    // 类型
    protected JsonZipMessageConvert() {
        super(new MediaType("application","json",UTF8),new MediaType("application","*+json", UTF8));
    }

    protected JsonZipMessageConvert(MediaType supportedMediaType) {
        super(supportedMediaType);
    }

    protected JsonZipMessageConvert(MediaType... supportedMediaTypes) {
        super(supportedMediaTypes);
    }

    protected JsonZipMessageConvert(Charset defaultCharset, MediaType... supportedMediaTypes) {
        super(defaultCharset, supportedMediaTypes);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        WebContext webContext = WebContext.get();


        String json = webContext.getBodyJsonString();


        OutputStream out = outputMessage.getBody();

        out.write(json.getBytes());

    }
}
