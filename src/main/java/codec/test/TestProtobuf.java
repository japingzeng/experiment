package codec.test;

import codec.potobuf.SubReqProto;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by Administrator on 2017/3/24.
 */
public class TestProtobuf {

    private static byte[] encode(SubReqProto.SubReq subReq) {
        return subReq.toByteArray();
    }

    private static SubReqProto.SubReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubReqProto.SubReq.parseFrom(body);
    }

    private static SubReqProto.SubReq createSubReq() {
        SubReqProto.SubReq.Builder builder = SubReqProto.SubReq.newBuilder();
        builder.setUserName("test");
        builder.setUserId(1);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubReqProto.SubReq req = createSubReq();
        System.out.println("before encode: " + req.toString());
        SubReqProto.SubReq req1 = decode(encode(req));
        System.out.println("after encode: " + req.toString());
        System.out.println("Assert equal: " + req1.equals(req));

    }

}
