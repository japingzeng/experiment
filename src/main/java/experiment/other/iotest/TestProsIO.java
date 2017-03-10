package experiment.other.iotest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/9.
 */
public class TestProsIO {

    public static void main(String[] args) {
        Properties pro = ProsUtil.loadPros("config/skill.properties");
        Map<String, String> skillMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : pro.entrySet()) {
            skillMap.put((String)entry.getKey(), (String)entry.getValue());
        }
        Iterator<Map.Entry<String, String>> entryIterator = skillMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
