package btr.sc.course.chapter1.infrastructure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.vavr.jackson.datatype.VavrModule;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class ServerConfiguration
{
  @Bean
  public ObjectMapper objectMapper()
  {
    val mapper = new ObjectMapper();
    //Vavr模型
    mapper.registerModule(new VavrModule());
    //忽略实体没有的字段
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //驼峰转下划线
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    //格式化输出
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    //禁用日期时间戳
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    //不输出null,"",空字段
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    //日期格式化
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    mapper.setDateFormat(format);

    return mapper;
  }
}
