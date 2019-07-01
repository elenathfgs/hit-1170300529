package logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyLogger {
  public static final Logger _LOG = Logger.getLogger("MyLog");
  private static final String outputURL = "src/../log/log.txt";

  /**.
   * initial the logger, set the handler and the formatter
   * @param logger the logger to be initiated
   * @throws IOException may not find the file
   */
  public static void init(Logger logger) throws IOException {
    FileHandler fileHandler = new FileHandler(outputURL);

    // **set the format of the output file**
    fileHandler.setFormatter(new SimpleFormatter());

    _LOG.addHandler(fileHandler);
    _LOG.info("日志系统初始化完成");
  }

  /**.
   * get the timeStamp in the log as a certain format-> "yyyy-MM-dd HH:mm:ss"
   * 
   * @throws ParseException may not parse the regex
   */
  public static Date getSimpleFormatTime(String input) throws ParseException {
    Pattern logTimePattern = Pattern.compile(
        "(\\d\\d?)月 (\\d\\d?), (\\d\\d\\d\\d) (\\d\\d?):(\\d\\d?):(\\d\\d?) ([\\u4E00-\\u9FA5])午.*?"
        );
    Matcher logTimeMatcher = logTimePattern.matcher(input);
    if (logTimeMatcher.find()) {
      String month = logTimeMatcher.group(1);
      if (month.length() == 1) {
        month = "0" + month;
      }

      String day = logTimeMatcher.group(2);
      if (day.length() == 1) {
        day = "0" + day;
      }

    
      String hour = logTimeMatcher.group(4);
      if (logTimeMatcher.group(6).equals("下")) {
        int intHour = Integer.parseInt(hour);
        intHour += 12;
        hour = String.valueOf(intHour);
      }

      if (hour.length() == 1) {
        hour = "0" + hour;
      }
      String minute = logTimeMatcher.group(5);
      if (minute.length() == 1) {
        minute = "0" + minute;
      }
      String second = logTimeMatcher.group(6);
      if (second.length() == 1) {
        second = "0" + second;
      }

      String year = logTimeMatcher.group(3);
      String dateFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = simpleDateFormat.parse(dateFormat);

      return date;
    }

    return null;
  }

}
