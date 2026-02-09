package dbms;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CompilerUtil {

    public static void compile(String className) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {
            System.out.println("JDK not found. Compilation skipped.");
            return;
        }

        String filePath = "generated/" + className + ".java";
        int result = compiler.run(null, null, null, filePath);

        if (result == 0) {
            System.out.println("Compilation successful for " + className);
        } else {
            System.out.println("Compilation failed for " + className);
        }
    }
}
