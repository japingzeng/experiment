package uuidGenerate;

import java.io.IOException;

public class HexUtil {
	
	private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public HexUtil() {
    }

    public static Appendable append(Appendable a, short in) {
        return append(a, (long)in, 4);
    }

    public static Appendable append(Appendable a, short in, int length) {
        return append(a, (long)in, length);
    }

    public static Appendable append(Appendable a, int in) {
        return append(a, (long)in, 8);
    }

    public static Appendable append(Appendable a, int in, int length) {
        return append(a, (long)in, length);
    }

    public static Appendable append(Appendable a, long in) {
        return append(a, in, 16);
    }

    public static Appendable append(Appendable a, long in, int length) {
        try {
            for(int ex = (length << 2) - 4; ex >= 0; ex -= 4) {
                a.append(DIGITS[(byte)((int)(in >> ex)) & 15]);
            }
        } catch (IOException var5) {
            ;
        }

        return a;
    }

    public static Appendable append(Appendable a, byte[] bytes) {
        try {
            byte[] ex = bytes;
            int len = bytes.length;

            for(int i = 0; i < len; ++i) {
                byte b = ex[i];
                a.append(DIGITS[(byte)((b & 240) >> 4)]);
                a.append(DIGITS[(byte)(b & 15)]);
            }
        } catch (IOException var6) {
            ;
        }

        return a;
    }

    public static long parseLong(CharSequence s) {
        long out = 0L;
        byte shifts = 0;

        for(int i = 0; i < s.length() && shifts < 16; ++i) {
            char c = s.charAt(i);
            if(c > 47 && c < 58) {
                ++shifts;
                out <<= 4;
                out |= (long)(c - 48);
            } else if(c > 64 && c < 71) {
                ++shifts;
                out <<= 4;
                out |= (long)(c - 55);
            } else if(c > 96 && c < 103) {
                ++shifts;
                out <<= 4;
                out |= (long)(c - 87);
            }
        }

        return out;
    }

    public static short parseShort(String s) {
        short out = 0;
        byte shifts = 0;

        for(int i = 0; i < s.length() && shifts < 4; ++i) {
            char c = s.charAt(i);
            if(c > 47 && c < 58) {
                ++shifts;
                out = (short)(out << 4);
                out = (short)(out | c - 48);
            } else if(c > 64 && c < 71) {
                ++shifts;
                out = (short)(out << 4);
                out = (short)(out | c - 55);
            } else if(c > 96 && c < 103) {
                ++shifts;
                out = (short)(out << 4);
                out = (short)(out | c - 87);
            }
        }

        return out;
    }

}
