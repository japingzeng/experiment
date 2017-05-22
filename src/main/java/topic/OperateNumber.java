package topic;

/**
 * @Author japing
 * @Date 2017/5/15 14:33
 * @Description: 输入数据封装对象
 */
public class OperateNumber {

    private static final int ORIGIN = 0;
    private static final int INTERMEDIATE = 1;
    private int aint;
    /**
     * 0：表示原始输入数据，1表示中间计算过程数据
     */
    private int flag;

    public OperateNumber(int aint, int flag) {
        this.aint = aint;
        this.flag = flag;
    }

    public int getAint() {
        return aint;
    }

    public void setAint(int aint) {
        this.aint = aint;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public static int getORIGIN() {
        return ORIGIN;
    }

    public static int getINTERMEDIATE() {
        return INTERMEDIATE;
    }
}
