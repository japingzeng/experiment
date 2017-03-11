package uuidGenerate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Administrator on 2017/3/10.
 */
public class UUID implements Comparable<UUID>, Externalizable, Cloneable{
    private long timeFlag;
    private long serverFlag;

    public UUID() {
        this.timeFlag = TimeServerNumGen.createTime();
        this.serverFlag = TimeServerNumGen.getserverFlagId();
    }

    public UUID(long timeFlag, long serverFlag) {
        this.timeFlag = timeFlag;
        this.serverFlag = serverFlag;
    }

    public int compareTo(UUID t) {
        return this == t?0:(this.timeFlag > t.timeFlag?1:(this.timeFlag < t.timeFlag?-1:(this.serverFlag > t.serverFlag?1:(this.serverFlag < t.serverFlag?-1:0))));
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.timeFlag);
        out.writeLong(this.serverFlag);
    }

    public void readExternal(ObjectInput in) throws IOException {
        this.timeFlag = in.readLong();
        this.serverFlag = in.readLong();
    }

    public final String generatorUUID() {
        return this.toAppendable((Appendable)null).toString();
    }

    public Appendable toAppendable(Appendable a) {
        Object out = a;
        if(a == null) {
            out = new StringBuilder(36);
        }
        //标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx (8-4-4-4-12)
        try {
            HexUtil.append((Appendable)out, (int)(this.timeFlag >> 32));
            HexUtil.append((Appendable)out, (short)(this.timeFlag >> 16));
            HexUtil.append((Appendable)out, (short)(this.timeFlag));
            HexUtil.append((Appendable)out, (short)((int)(this.serverFlag >> 48)));
            HexUtil.append((Appendable)out, this.serverFlag, 12);
        } catch (Exception var4) {
            ;
        }
        return (Appendable)out;
    }
    
    public long getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(long serverFlag) {
        this.serverFlag = serverFlag;
    }

}
