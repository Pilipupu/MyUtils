package iotest;

import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

import java.io.*;

public class IOTest {
    @Test
    public void testIO() throws IOException {
        FileReader demo = new FileReader("demo");
        BufferedReader bf = new BufferedReader(demo);
        bf.readLine();
    }
}
