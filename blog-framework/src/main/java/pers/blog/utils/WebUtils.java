package pers.blog.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 * @author: zyx
 * @create: 2023/8/29
 * @description: 处理HTTP响应
 */
public class WebUtils {
    /**
     * 将指定字符串渲染到客户端
     * @param response
     * @param string
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件下载
     * @param filename
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void setDownLoadHeader(String filename, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fname= URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition","attachment; filename="+fname);
    }
}
