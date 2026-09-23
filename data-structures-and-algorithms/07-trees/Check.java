import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Minimal test helper shared by the exercises in this topic.
 */
public class Check {
    private static final PrintStream OUT =
            new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8);

    private static int passed = 0;
    private static int failed = 0;

    public static void expect(String name, Object actual, Object expected) {
        String a = fmt(actual);
        String e = fmt(expected);
        if (a.equals(e)) {
            passed++;
            OUT.println("  PASS  " + name);
        } else {
            failed++;
            OUT.println("  FAIL  " + name);
            OUT.println("        expected: " + e);
            OUT.println("        actual:   " + a);
        }
    }

    public static void summary() {
        OUT.println();
        OUT.println(passed + " pass, " + failed + " fail");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static String fmt(Object o) {
        if (o == null) return "null";
        if (o instanceof int[] v) return Arrays.toString(v);
        if (o instanceof long[] v) return Arrays.toString(v);
        if (o instanceof double[] v) return Arrays.toString(v);
        if (o instanceof char[] v) return Arrays.toString(v);
        if (o instanceof boolean[] v) return Arrays.toString(v);
        if (o instanceof Object[] v) return Arrays.deepToString(v);
        return o.toString();
    }
}
