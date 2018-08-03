package btr.sc.course.chapter3.core.infrastructure;

import com.google.common.io.ByteStreams;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Cleanup;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignConfiguration
{
  @Bean
  public ErrorDecoder errorDecoder()
  {
    return new ErrorDecoder()
    {
      @Override
      public Exception decode(final String methodKey, final Response response)
      {
        switch (response.status())
        {
          case 400:
          case 401:
          case 403:
            try
            {
              @Cleanup
              val is = response.body().asInputStream();

              return new HystrixBadRequestException(new String(ByteStreams.toByteArray(is)));
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
          default : return feign.FeignException.errorStatus(methodKey, response);
        }
      }
    };
  }
}
