import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class IODemo {
    @Test
    public void testIO() throws IOException, URISyntaxException {
        URL resource = Resources.getResource("test.json");
        File file = new File(resource.toURI());
        FileInputStream is = new FileInputStream(file);

        int read = is.read();
        System.out.println(read);
    }
}
