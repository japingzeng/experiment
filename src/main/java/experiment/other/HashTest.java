package experiment.other;

import java.util.concurrent.ConcurrentHashMap;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 
 * @ClassName: HashTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年12月14日 上午11:47:19 
 *
 */
public class HashTest {
	
	public static void main(String[] args) {
		ConcurrentHashMap<String, Long> cHashMap = new ConcurrentHashMap<>();
		cHashMap.put("first", 1L);
		Long first = cHashMap.get("first");
	}
	
	
	
	
	
	
	    
	    private int hash(Object k) {
	        int h = 0;
	        h ^= k.hashCode();

	        h += (h <<  15) ^ 0xffffcd7d;
	        h ^= (h >>> 10);
	        h += (h <<   3);
	        h ^= (h >>>  6);
	        h += (h <<   2) + (h << 14);
	        return h ^ (h >>> 16);
	    }

}
