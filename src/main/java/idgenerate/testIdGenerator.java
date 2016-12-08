package idgenerate;

import idgenerate.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;


/**
 * 
 * @ClassName: testIdGenerator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年12月8日 下午5:09:39 
 *
 */
public class testIdGenerator {
	
	private  IdGenerator idGenerator = IdGenerator.getInstanceGenerator();
	
	
	public void multiTaskTest() {
		long avgTime = 0L;
		//跑10遍
		for (int i = 0; i < 10; i++) {
			List<Callable<Long>> callables = new ArrayList<Callable<Long>>();
			//生成100W个id
			for (int k = 0; k < 1000000; k++) {
				callables.add(new Callable<Long>() {
							@Override
							public Long call() throws Exception {	
								return idGenerator.createId();
							}
				});
			}
			ExecutorService executorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			try {
				long tempTime = DateUtils.timeGenerate();
				List<Future<Long>> results = executorPool.invokeAll(callables, 10000000
						, TimeUnit.SECONDS);
				long singleAvgTime = DateUtils.timeGenerate() - tempTime;
				System.out.println(String.format("完成第%d次需要的时间%d毫秒", i+1,singleAvgTime));
				avgTime += singleAvgTime;
				List<Long> list = new ArrayList<Long>();
				Map<Long, Long> map = new HashMap<Long, Long>(15000000);
				for (int j = 0; j < results.size(); j++) {
					Long temp = results.get(j).get();
//					if (list.contains(temp)) {
//						System.out.println(Long.toBinaryString(temp.longValue()));
//					} else {
//						list.add(temp);
//					}
					list.add(temp);
					
					map.put(temp, temp);
				}
				System.out.println(String.format("list的size为%d", list.size()));
				System.out.println(String.format("map的size为%d", map.size()));
				executorPool.shutdown();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println(String.format("完成10次的平均时间为%d毫秒秒", avgTime / 10));	
	}
	
	public static void main(String[] args) {
		testIdGenerator test = new testIdGenerator();
		test.multiTaskTest();
	}

}
