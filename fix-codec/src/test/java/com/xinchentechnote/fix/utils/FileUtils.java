package com.xinchentechnote.fix.utils;

import com.google.common.io.CharStreams;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileUtils {
  public static String readFileToString(String filePath) throws Exception {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (null == classLoader) {
      classLoader = FileUtils.class.getClassLoader();
    }
    try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
      if (inputStream == null) {
        throw new FileNotFoundException("Resource not found: " + filePath);
      }
      return CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }
  }
}
