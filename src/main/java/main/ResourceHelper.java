package main;

import java.io.InputStream;
import java.nio.file.*;

public class ResourceHelper {
    public static String encodeFilename(String filename) {
        try {
            return java.net.URLEncoder.encode(filename, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }

    public static InputStream getResourceAsStream(String filename) {
        return ResourceHelper.class.getClassLoader().getResourceAsStream(ResourceHelper.encodeFilename(filename));
    }

    public static String getResourceAsPath(String filename) {
        return ResourceHelper.class.getClassLoader().getResource(ResourceHelper.encodeFilename(filename)).getPath();
    }
}
