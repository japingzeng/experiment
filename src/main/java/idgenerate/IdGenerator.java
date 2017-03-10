package idgenerate;

import idgenerate.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @ClassName: IdGenerate 
 * @Description: TODO(id生成器,生成的是一个63位的long型整数) 
 * @author japing 
 * @date 2016年12月8日 下午3:31:24 
 *
 */
public class IdGenerator {
	
	
	/**
	 * 集群id，标识集群
	 */
	private long clusterId;
	/**
	 * 集群id所占bit位数
	 */
	private long clusterIdBits = 3L;
	/**
	 * 具体服务器id
	 */
	private long serverId = 1L;
	/**
	 * 服务器id所占bit位数
	 */
	private long serverIdBits = 7L;
	/**
	 * 业务线id
	 */
	private long taskId = 1L;
	/**
	 * 业务线id所占bits位数
	 */
	private long taskIdBits = 3L;
	/**
	 * 每一个毫秒产生的sequenceId
	 */
	private long sequenceId = 0L;
	/**
	 * sequenceId占用7bit，在sequenceId前预留了
	 * 2位bit，将来可能会用，7bit (2^7-1) = 127, 
	 * 0-127,意味着每一毫秒可以生成128个序列号,足以满足现状需求
	 */
	private long sequenceIdBits = 7L;
	/**
	 * 预留的两位bit
	 */
	private long reservedBits = 2L;
	/**
	 * 时间向左移动位数
	 */
	private long timeLeftShift = clusterIdBits + serverIdBits + taskIdBits + reservedBits + sequenceIdBits;
	/**
	 * 集群id左移位数
	 */
	private long clusterIdLeftShift = serverIdBits + taskIdBits + reservedBits + sequenceIdBits;
	/**
	 * 服务器id左移位数
	 */
	private long serverIdLeftShift = taskIdBits + reservedBits + sequenceIdBits;
	/**
	 * 业务id左移位数
	 */
	private long taskIdLeftShift = reservedBits + sequenceIdBits;
	/**
	 * 与 sequenceId 做相与的操作，保证的到的sequenceId是7位的
	 */
	private long sequenceIdMask = -1L ^ (-1L << sequenceIdBits);
	/**
	 * 上次生成id的时间
	 */
	private long lastGenerateTime = -1L;
	
	/**
	 * 
	 * @param clusterId
	 * @param serverId
	 * @param taskId
	 */
	public IdGenerator(long clusterId, long serverId, long taskId) {
		this.clusterId = clusterId;
		this.serverId = serverId;
		this.taskId = taskId;
	}
	
	public synchronized long createId() {
		long currentTime = DateUtils.timeGenerate();
		//服务器时钟后退，抛出异常
		if (currentTime < lastGenerateTime) {
			throw new RuntimeException(String.format("时钟后退%d,拒绝生成id", lastGenerateTime - currentTime));
		}
		//当前时间等于上次生成id时间，需要在同一毫秒内进行序列id的自增
		if (currentTime == lastGenerateTime) {
			//sequenceId自增1，与七位掩码 1111111相与，去掉高位
			sequenceId = (sequenceId + 1) & sequenceIdMask;
			//因为设定同一毫秒内最多生成128个（0-127）,超出后sequenceId会从头再次开始，那么这时候将产生重复的sequenceId
			//判断是否为0，为0则表示已超过128个了，因为当前时间与上次生成时间相同，所以sequenceId肯定是不为0的
			if (0 == sequenceId) {
				currentTime = nextTimeMillis(lastGenerateTime);
			}
		} else {
			sequenceId = 0L;
		}
		
		//拼接各部分数据，时间 + 集群id + 服务器id + 业务线id + sequenceId
		long result = (currentTime << timeLeftShift) |
				      (clusterId << clusterIdLeftShift) |
				      (serverId << serverIdLeftShift) |
				      (taskId << taskIdLeftShift) |
				      sequenceId;
		lastGenerateTime = currentTime;
		return result;
	}
	
	/**
	 * 
	 * @param lastGenerateTime
	 * @return
	 * @Description: TODO(自悬到大于上次生成id的时间) 
	 * @author japing 
	 * @date 2016年12月8日 下午5:00:59
	 */
	protected long nextTimeMillis(long lastGenerateTime) {
		long currTime = DateUtils.timeGenerate();
		while (currTime <= lastGenerateTime) {
			currTime = DateUtils.timeGenerate();
		}
		return currTime;
	}
	
	private static class IdGeneratorHolder {
		private static IdGenerator instance = new IdGenerator(1, 1, 1);	
	}
	
	public static IdGenerator getInstanceGenerator() {
		return IdGeneratorHolder.instance;
	}
	
	
}
