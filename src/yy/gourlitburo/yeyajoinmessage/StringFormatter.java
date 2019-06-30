package yy.gourlitburo.yeyajoinmessage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {

  private static final String PATTERN_INTERP = "#\\{[A-Z]+\\}";
  private static final Pattern patternInterp = Pattern.compile(PATTERN_INTERP);
  private static final String PATTERN_COLOR = "&([0-9a-flmnor])";
  private static final Pattern patternColor = Pattern.compile(PATTERN_COLOR);

  public String colorize(String message) {
    return patternColor.matcher(message).replaceAll("\u00A7$1");
  }

  public String interpolate(String message, Map<String, String> values) {
    Matcher matcher = patternInterp.matcher(message);
    StringBuilder builder = new StringBuilder();
    int prevEnd = 0;
    while (matcher.find()) {
      String matched = matcher.group(0);
      String key = matched.substring(2, matched.length() - 1);
      if (values.containsKey(key)) {
        builder.append(message.substring(prevEnd, matcher.start()));
        builder.append(values.get(key));
        prevEnd = matcher.end();
      }
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
