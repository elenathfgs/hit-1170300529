package log;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import logger.MyLogger;
import org.junit.jupiter.api.Test;



class MyloggerTest {

  @Test
  void testInit() throws IOException {
    MyLogger.init(MyLogger._LOG);
  }

  @Test
  void testGetSimpleFormatTime() {
    try {
      Date date = MyLogger.getSimpleFormatTime("5月 14, 2019 10:54:51 上午 logger.MyLogger Init");
      assertTrue(date != null);
      date = MyLogger.getSimpleFormatTime("月 14, 2019 10:54:51 上午 logger.MyLogger Init");
      assertTrue(date == null);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      System.out.println("日志测试错误，输入不匹配");
      e.printStackTrace();
    }
  }

}
