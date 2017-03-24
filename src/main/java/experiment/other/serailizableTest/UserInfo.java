package experiment.other.serailizableTest;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class UserInfo implements Serializable{


    private static final long serialVersionUID = 5034515628350900712L;
    private String userName;
    private int userId;
    private Integer phoneNum;
    private String address;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] encode() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byte[] value = this.userName.getBytes();
        byteBuffer.putInt(value.length);
        byteBuffer.put(value);
        byteBuffer.putInt(this.userId);
        byteBuffer.flip();
        value = null;
        byte[] result = new byte[byteBuffer.remaining()];
        byteBuffer.get(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.setUserName("heloo");
        info.setUserId(100);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println(b.length);
        System.out.println(info.encode().length);

    }
}
