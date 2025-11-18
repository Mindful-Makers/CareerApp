import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 test class for the CareerApp Launcher.
 * @version 1
 */
public class LauncherTest {

    /** Captures standard output for test assertions. */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /** Stores the original System.out stream for restoration. */
    private final PrintStream originalOut = System.out;

    /** Stores the original System.in stream for restoration. */
    private InputStream originalIn;

    /** CareerAppLauncher under test with injected components. */
    private CareerAppLauncher launcher;

    /**
     * Set up test environment, redirect I/O.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        originalIn = System.in;
    }

    /**
     * Restores original console input and output streams.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Tests that the launcher can exit.
     */
    @Test
    public void testExit() {
        ByteArrayInputStream in = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        launcher = new CareerAppLauncher(scanner);

        launcher.run();

        String output = outContent.toString();
        assertTrue(output.contains("Goodbye"), 
                   "Should display goodbye message when exiting");
    }
}

