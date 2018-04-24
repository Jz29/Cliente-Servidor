import java.io.*;
import java.net.*;

public class EchoTest {
    public static void main(String[] args) {
        try {
           Runtime run = Runtime.getRuntime();
           run.exec("echo " + "ola mundo " + "> " + "w.txt");
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
    }
}
