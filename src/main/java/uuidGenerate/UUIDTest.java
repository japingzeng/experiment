package uuidGenerate;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author japing
 * @Date 2017/3/11 12:29
 * @Description:
 */
public class UUIDTest {

    public void executeMultiTask() {
        //跑10次
        long avgTime = 0;
        for (int i = 0; i < 10; i++) {
            List<Callable<String>> callables = new ArrayList<>();
            int num = 1000000;
            //生成num个任务,即生生uuid
            for (int j = 0; j < num; j++) {
                callables.add(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return new UUID().generatorUUID();
                    }
                });
            }
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            try {
                long startTime = generateTime();
                StopWatch wacth = new StopWatch();
                wacth.start();
                List<Future<String>> results = executorService.invokeAll(callables, 20, TimeUnit.SECONDS);
                long singleTime = generateTime() - startTime;
                wacth.stop();
                System.out.println(wacth);
                System.out.println(String.format("完成第%d次的时间为%d毫秒", i+1, singleTime));
                avgTime += singleTime;
                List<String> list = new ArrayList<>();
                Map<String, String> map = new HashMap<>(1000000);
                Thread.sleep(2000);
                //对比是否有重复，数目相同则每重复
                for (Future<String> item : results) {
                    try {
                        String tem = item.get();
                        list.add(tem);
                        map.put(tem, tem);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(String.format("list集合size= %d", list.size()));
                System.out.println(String.format("map中size= %d", map.size()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("完成10次的平均时间为%d毫秒", avgTime / 10));

    }

    public static long generateTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        UUID uuid = new UUID();
        System.out.println(TimeServerNumGen.getMACAddress());
        UUIDTest test = new UUIDTest();
        test.executeMultiTask();
    }
}
