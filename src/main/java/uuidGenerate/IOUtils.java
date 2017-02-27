package uuidGenerate;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	public IOUtils() {
    }

    public static void close(Closeable... closeables) {
        if(closeables != null) {
            Closeable[] arr = closeables;
            int len = closeables.length;

            for(int i = 0; i < len; ++i) {
                Closeable closeable = arr[i];

                try {
                    if(closeable != null) {
                        closeable.close();
                    }
                } catch (IOException var6) {
                    ;
                }
            }
        }

    }

}
