package nettyTest.NIOTest.util;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

/**
 * Created by Administrator on 2017/3/28.
 */
public class HttpHeaderUtil {

    public static void setContentTypeHeader(HttpResponse response, File file) {
        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, mimetypesFileTypeMap.getContentType(file.getPath()));
    }

    public static void setContentLength(HttpResponse response, long fileLength) {
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, fileLength);
    }

    public static void setContentLength(HttpRequest request, long fileLength) {
        request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, fileLength);
    }

    public static boolean isKeepAlive(HttpRequest request) {
        return request.headers().contains(HttpHeaders.Names.CONNECTION);
    }
}
