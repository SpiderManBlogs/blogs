package com.spiderman.blogsweb.file.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FfmpegUtil {

    private static final Logger log = LogManager.getLogger(FfmpegUtil.class.getName());

    public static boolean newRunCommand(List<String> command) {
        log.debug("相关命令 command:{}",command);
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            final StringBuilder sb = new StringBuilder();
            final InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            inputStream.close();
            return true;
        } catch (Exception e) {
            log.error("ffmpeg执行异常" + e.getMessage());
            throw new RuntimeException("ffmpeg执行异常" + e.getMessage());
        }
    }


}
