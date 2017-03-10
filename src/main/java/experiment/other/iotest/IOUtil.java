package experiment.other.iotest;

import org.apache.commons.io.Charsets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author japing
 * @Date 2017/3/9 14:14
 * @Description:
 */
public class IOUtil {

    public static List<String> readLines(InputStream input, Charset charset) throws Exception{
        InputStreamReader isr = new InputStreamReader(input);
        return readLines((Reader)isr);
    }

    public static List<String> readLines(InputStream input, String charset) throws Exception{
        return readLines(input, Charsets.toCharset(charset));
    }

    public static List<String> readLines(InputStream input) throws Exception{
        return readLines(input, Charset.defaultCharset());
    }

    public static List<String> readLines(Reader reader) throws Exception{
        BufferedReader br = new BufferedReader(reader);
        List list = new ArrayList();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            list.add(line);
        }
        return list;
    }
}
