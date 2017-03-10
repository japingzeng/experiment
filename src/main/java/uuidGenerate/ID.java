package uuidGenerate;

import org.omg.CORBA.portable.IDLEntity;

import javax.annotation.Resource;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ID implements Comparable<ID>, Externalizable, Cloneable{
    private long time;
    private long serverFlag;
    @Resource
    private TimeServerNumGen timeServerNumGen;

    public ID() {
    }

    public int compareTo(ID t) {
        return this == t?0:(this.time > t.time?1:(this.time < t.time?-1:(this.serverFlag > t.serverFlag?1:(this.serverFlag < t.serverFlag?-1:0))));
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.time);
        out.writeLong(this.serverFlag);
    }

    public void readExternal(ObjectInput in) throws IOException {
        this.time = in.readLong();
        this.serverFlag = in.readLong();
    }

    public final String toStringWithoutSeparator() {
        return this.toAppendableWithoutSeparator((Appendable)null).toString();
    }

    public Appendable toAppendableWithoutSeparator(Appendable a) {
        Object out = a;
        if(a == null) {
            out = new StringBuilder(36);
        }

        try {
            Hex.append((Appendable)out, (int)(this.time >> 32));
            Hex.append((Appendable)out, (short)((int)(this.time >> 16)));
            Hex.append((Appendable)out, (short)((int)this.time));
            Hex.append((Appendable)out, (short)((int)(this.serverFlag >> 48)));
            Hex.append((Appendable)out, this.serverFlag, 12);
        } catch (Exception var4) {
            ;
        }

        return (Appendable)out;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(long serverFlag) {
        this.serverFlag = serverFlag;
    }

    public TimeServerNumGen getTimeServerNumGen() {
        return timeServerNumGen;
    }

    public void setTimeServerNumGen(TimeServerNumGen timeServerNumGen) {
        this.timeServerNumGen = timeServerNumGen;
    }
}
