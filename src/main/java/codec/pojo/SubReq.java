package codec.pojo;

import java.io.Serializable;

/**
 * @Author japing
 * @Date 2017/3/24 19:53
 * @Description:
 */
public class SubReq implements Serializable{
    private static final long serialVersionUID = -8130185946397487317L;

    private String userName;
    private int userId;

    @Override
    public String toString() {
        return "[" + "userName: " + userName + "]";
    }
}
