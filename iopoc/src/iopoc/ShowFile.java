package iopoc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile {

    public static void main(final String args[]) {

        int i;

        // First, confirm that a filename has been specified.
        if (args.length != 1) {
            System.out.println("Usage: ShowFile filename");
            return;
        }

        // The following code uses a try-with-resources statement to open
        // a file and then automatically close it when the try block is left.
        try (FileInputStream fin = new FileInputStream(args[0])) {
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            }
            while (i != -1);
        } catch (final FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (final IOException e) {
            System.out.println("An I/O Error Occurred");
        }
    }

}
