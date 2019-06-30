package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {

  private static final String PATTERN_STR = "\\{[A-Z]+\\}";

  public String colorize(String message) {
    return message.replaceAll("&([0-9a-fr])", "\u00A7$1");
  }

  public String interpolate(String message, Map<String, String> values) {
    Pattern pattern = Pattern.compile(PATTERN_STR);
    Matcher matcher = pattern.matcher(message);
    StringBuilder builder = new StringBuilder();
    int prevEnd = 0;
    while (matcher.find()) {
      String matched = matcher.group(0);
      String key = matched.substring(1, matched.length() - 1);
      builder.append(message.substring(prevEnd, matcher.start()));
      builder.append(values.get(key));
      prevEnd = matcher.end();
    }
    if (prevEnd != message.length()) {
      builder.append(message.substring(prevEnd));
    }
    return builder.toString();
  }

  public String format(String message, Map<String, String> values) {
    return colorize(interpolate(message, values));
  }

}
