package uuidGenerate;

import idgenerate.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

@Service("timeServerNumGen")
public class TimeServerNumGen {
	private static AtomicLong lastTime = new AtomicLong(-9223372036854775808L);
    private static String macAddress = null;
    /**
     * 集群id，标识集群
     */
    @Value("${jimi.ws.clusterId}")
    private long clusterId = 1L;
    /**
     * 集群id所占bit位数
     */
    private static long clusterIdBits = 3L;
    /**
     * 业务线id
     */
    @Value("${jimi.ws.taskId}")
    private long taskId = 1L;
    /**
     * 业务线id所占bits位数
     */
    private static long taskIdBits = 3L;
    /**
     * 每一个毫秒产生的sequenceId
     */
    private static long sequenceIdBits = 16;
    private static long sequenceIdMask = -1L ^ (-1L << sequenceIdBits);
    /**
	 * 每一个毫秒产生的sequenceId
	 */
	private static AtomicLong sequenceId = new AtomicLong(0L);

    /**
     * 业务id左移位数
     */
    private static long taskIdLeftShift = sequenceIdBits;
    /**
     * 集群id左移位数
     */
    private static long clusterIdLeftShift = taskIdBits + taskIdLeftShift;
    /**
     * 时间向左移动位数
     */
    private static long timeLeftShift = clusterIdBits + clusterIdLeftShift;

    private static long serverFlagId = -9223372036854775808L;

    public TimeServerNumGen() {
    }

    public static long getserverFlagId() {
        return serverFlagId;
    }
    
    public long createTime() {
    	long currentTime;
    	//当前时间等于上次生成id时间，需要在同一毫秒内进行序列id的自增
    	long currentSequenceId;
    	while (true) {
    		currentTime = System.currentTimeMillis();
        	long lastGenerateTime = lastTime.get();
        	//服务器时钟后退，抛出异常
        	if (currentTime < lastGenerateTime) {
        		throw new RuntimeException(String.format("时钟后退%d,拒绝生成id", lastGenerateTime - currentTime));
        	}
        	if (currentTime == lastGenerateTime) {
        		while (true) {
        			currentSequenceId = sequenceId.get();
        			//tempSequenceId自增1，与22位掩码 1111111111111111111111相与，去掉高位
        			long tempSequenceId = (currentSequenceId + 1) & sequenceIdMask;
        			//因为设定同一毫秒内最多生成2^16个（0 - (2^16-1)个）,超出后sequenceId会从头再次开始，那么这时候将产生重复的sequenceId
        			//判断是否为0，为0则表示已超过2^16个了，因为当前时间与上次生成时间相同，所以sequenceId肯定是不为0的
        			if (tempSequenceId == 0) {
        				currentTime = nextTimeMillis(lastGenerateTime);
        				if (lastTime.compareAndSet(lastGenerateTime, currentTime)) {
        					if (sequenceId.compareAndSet(currentSequenceId, tempSequenceId)) {
        						//当前id设置为0，从新的时间开始的0号id
        						currentSequenceId = tempSequenceId;
        						break;
        					}
        				}			
        			} else if (sequenceId.compareAndSet(tempSequenceId, tempSequenceId + 1)) {
        				currentSequenceId = tempSequenceId + 1;
        				break;
        			}	
        		}
        	} else if (lastTime.compareAndSet(lastGenerateTime, currentTime)){
        		//当前时间第一次分配id
        		currentSequenceId = 0L;
        		break;
        	}
    	}
    	//拼接时间 + 毫秒内的序列id currentSequenceId
    	long result = (currentTime << timeLeftShift) |
                      (clusterId << clusterIdLeftShift) |
                      (taskId << taskIdLeftShift ) | currentSequenceId;
    	return result;
    }
    
 
    public static String getMACAddress() {
        return macAddress;
    }

    static String getFirstLineOfCommand(String... commands) throws IOException {
        Process p = null;
        BufferedReader reader = null;

        String var3;
        try {
            p = Runtime.getRuntime().exec(commands);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()), 128);
            var3 = reader.readLine();
        } finally {
            if(p != null) {
                IOUtils.close(new Closeable[]{reader, p.getErrorStream(), p.getOutputStream()});
                p.destroy();
            }

        }

        return var3;
    }

    static {
        try {
            Class.forName("java.net.InterfaceAddress");
            macAddress = Class.forName(TimeServerNumGen.class.getSimpleName() + "$HardwareAddressLookup").newInstance().toString();
        } catch (ExceptionInInitializerError var17) {
            ;
        } catch (ClassNotFoundException var18) {
            ;
        } catch (LinkageError var19) {
            ;
        } catch (IllegalAccessException var20) {
            ;
        } catch (InstantiationException var21) {
            ;
        } catch (SecurityException var22) {
            ;
        }

        if(macAddress == null) {
            Process ex = null;
            BufferedReader in = null;

            try {
                String ex1 = System.getProperty("os.name", "");
                String osver = System.getProperty("os.version", "");
                String l;
                if(ex1.startsWith("Windows")) {
                    ex = Runtime.getRuntime().exec(new String[]{"ipconfig", "/all"}, (String[])null);
                } else if(!ex1.startsWith("Solaris") && !ex1.startsWith("SunOS")) {
                    if((new File("/usr/sbin/lanscan")).exists()) {
                        ex = Runtime.getRuntime().exec(new String[]{"/usr/sbin/lanscan"}, (String[])null);
                    } else if((new File("/sbin/ifconfig")).exists()) {
                        ex = Runtime.getRuntime().exec(new String[]{"/sbin/ifconfig", "-a"}, (String[])null);
                    }
                } else if(osver.startsWith("5.11")) {
                    ex = Runtime.getRuntime().exec(new String[]{"dladm", "show-phys", "-m"}, (String[])null);
                } else {
                    l = getFirstLineOfCommand(new String[]{"uname", "-n"});
                    if(l != null) {
                        ex = Runtime.getRuntime().exec(new String[]{"/usr/sbin/arp", l}, (String[])null);
                    }
                }

                if(ex != null) {
                    in = new BufferedReader(new InputStreamReader(ex.getInputStream()), 128);
                    l = null;

                    while((l = in.readLine()) != null) {
                        macAddress = MACAddressParser.parse(l);
                        if(macAddress != null && Hex.parseShort(macAddress) != 255) {
                            break;
                        }
                    }
                }
            } catch (SecurityException var23) {
                ;
            } catch (IOException var24) {
                ;
            } finally {
                if(ex != null) {
                    IOUtils.close(new Closeable[]{in, ex.getErrorStream(), ex.getOutputStream()});
                    ex.destroy();
                }

            }
        }

        if(macAddress != null) {
            serverFlagId |= Hex.parseLong(macAddress);
        } else {
            try {
                byte[] ex2 = InetAddress.getLocalHost().getAddress();
                serverFlagId |= (long)(ex2[0] << 24) & 4278190080L;
                serverFlagId |= (long)(ex2[1] << 16 & 16711680);
                serverFlagId |= (long)(ex2[2] << 8 & '\uff00');
                serverFlagId |= (long)(ex2[3] & 255);
            } catch (UnknownHostException var16) {
                serverFlagId |= (long)(Math.random() * 2.147483647E9D);
            }
        }

        serverFlagId |= (long)(Math.random() * 16383.0D) << 48;
    }

    static class HardwareAddressLookup {
        HardwareAddressLookup() {
        }

        public String toString() {
            String out = null;
            try {
                Enumeration ex = NetworkInterface.getNetworkInterfaces();
                if(ex != null) {
                    while(ex.hasMoreElements()) {
                        NetworkInterface iface = (NetworkInterface)ex.nextElement();
                        byte[] hardware = iface.getHardwareAddress();
                        if(hardware != null && hardware.length == 6 && hardware[1] != -1) {
                            out = Hex.append(new StringBuilder(36), hardware).toString();
                            break;
                        }
                    }
                }
            } catch (SocketException var5) {
                ;
            }
            return out;
        }
    }
    
    protected static long nextTimeMillis(long lastGenerateTime) {
 		long currTime = System.currentTimeMillis();
 		while (currTime <= lastGenerateTime) {
 			currTime = DateUtils.timeGenerate();
 		}
 		return currTime;
 	}

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
