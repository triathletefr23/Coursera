import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class BurrowsWheelerShould {

    private static String DECODED_INPUT = "ABRACADABRA!";

    private static byte[] ENCODED_INPUT = { 0x0, 0x0, 0x0, 0x3,
            0x41, 0x52, 0x44, 0x21, 0x52, 0x43, 0x41, 0x41, 0x41,
            0x41, 0x42, 0x42 };

    @Test
    public void ReturnEncodedWord() {
        InputStream standardIn = System.in;
        PrintStream standardOut = System.out;

        try {
            // setup new input
            System.setIn(new ByteArrayInputStream(DECODED_INPUT.getBytes()));
            // create new output stream as byte array and assign to standard
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));

            BurrowsWheeler.transform();
            byte[] encoded = baos.toByteArray();
            assertEquals(ENCODED_INPUT.length, encoded.length);
            // first
            assertEquals(ENCODED_INPUT[0], encoded[0]);
            assertEquals(ENCODED_INPUT[1], encoded[1]);
            assertEquals(ENCODED_INPUT[2], encoded[2]);
            assertEquals(ENCODED_INPUT[3], encoded[3]);
            // letters
            assertEquals(ENCODED_INPUT[4], encoded[4]);
            assertEquals(ENCODED_INPUT[5], encoded[5]);
            assertEquals(ENCODED_INPUT[6], encoded[6]);
            assertEquals(ENCODED_INPUT[7], encoded[7]);
            assertEquals(ENCODED_INPUT[8], encoded[8]);
            assertEquals(ENCODED_INPUT[9], encoded[9]);
            assertEquals(ENCODED_INPUT[10], encoded[10]);
            assertEquals(ENCODED_INPUT[11], encoded[11]);
            assertEquals(ENCODED_INPUT[12], encoded[12]);
            assertEquals(ENCODED_INPUT[13], encoded[13]);
            assertEquals(ENCODED_INPUT[14], encoded[14]);
            assertEquals(ENCODED_INPUT[15], encoded[15]);
        }
        finally {
            // return standard input and output
            System.setIn(standardIn);
            System.setOut(standardOut);
        }
    }

    @Test
    public void ReturnDecodedWord() {
        // backup standard in and out
        InputStream standardIn = System.in;
        PrintStream standardOut = System.out;
        try {
            // setup new input with encoded message
            System.setIn(new ByteArrayInputStream(ENCODED_INPUT));
            // create new output stream as byte array and assign to standard
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));

            BurrowsWheeler.inverseTransform();
            String decoded = baos.toString();
            // check length and chars
            assertEquals(DECODED_INPUT.length(), decoded.length());
            assertEquals(DECODED_INPUT, decoded);
        }
        finally {
            // return standard input and output
            System.setIn(standardIn);
            System.setOut(standardOut);
        }
    }
}
