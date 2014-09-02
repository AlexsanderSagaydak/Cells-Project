package com.xb.safe.util;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

public class SetHeaderUtil {
    public static String encodeContentDispositionForDownload(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        StringBuilder contentDisposition = new StringBuilder("attachment; ");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent != null && agent.indexOf("opera") == -1 && agent.indexOf("msie") != -1) { // IE
            contentDisposition.append("filename=\"").append(toHexString(fileName)).append("\"").toString();
        } else if (agent != null && agent.indexOf("opera") != -1) { // Determening Opera index
            int version = -1;
            try {
                int prefixIndex = agent.indexOf("opera ");
                if (prefixIndex < 0) {
                    prefixIndex = agent.indexOf("opera/");
                }
                int startIndex = prefixIndex + 6; // length of "opera " or "opera/"
                int stopIndex = agent.indexOf(".", startIndex);
                if (stopIndex == -1) {
                    stopIndex = agent.indexOf(" ", startIndex);
                }
                version = new Integer(agent.substring(startIndex, stopIndex)).intValue();
            } catch (NumberFormatException ex) { // Error parsing agent header (version is unknown)
            }
            if (version < 9 && version > -1) { // Opera 8.x and before
                contentDisposition.append("filename=\"").append(fileName).append("\"").toString();
            } else { // Opera 9 or later (or unkown) (encoding according to RFC2231)
                contentDisposition.append("filename*=utf8''").append(toHexString(fileName)).toString();
            }
        } else { // Firefox and others
            contentDisposition.append("filename=\"").append(MimeUtility.encodeText(fileName, "utf8", "B")).append("\"").toString();
        }
        return contentDisposition.toString();

    }

    public static String toHexString(String s) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255 && !Character.isWhitespace(c)) {
                sb.append(c);
            } else {
                byte[] b;
                b = Character.toString(c).getBytes("utf8");
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
