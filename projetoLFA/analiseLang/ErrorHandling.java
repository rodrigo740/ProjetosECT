//package lfa1920-g12;
import lib.*;
import static java.lang.System.*;
import java.io.PrintStream;
import org.antlr.v4.runtime.ParserRuleContext;

public class ErrorHandling {

   public static void newLine() {
      logFile.println();
      logFile.flush();
   }

   public static void printInfo(String text) {
      assert text != null && text.length() > 0;
      printMessage(text, 1);
   }

   public static void printWarning(String text) {
      assert text != null && text.length() > 0;
      warningCount++;
      printMessage(text, 2);
   }

   public static void printError(String text) {
      assert text != null && text.length() > 0;
      errorCount++;
      printMessage(text, 3);
   }

   public static void printInfo(int line, String text) {
      assert line > 0;
      assert text != null && text.length() > 0;
      printMessage(line, text, 1);
   }

   public static void printWarning(int line, String text) {
      assert line > 0;
      assert text != null && text.length() > 0;

      warningCount++;
      printMessage(line, text, 2);
   }

   public static void printError(int line, String text) {
      assert line > 0;
      assert text != null && text.length() > 0;

      errorCount++;
      printMessage(line, text, 3);
   }

   public static void printInfo(ParserRuleContext ctx, String text) {
      assert ctx != null;
      assert text != null && text.length() > 0;

      printMessage(ctx, text, 1);
   }

   public static void printWarning(ParserRuleContext ctx, String text) {
      assert ctx != null;
      assert text != null && text.length() > 0;

      warningCount++;
      printMessage(ctx, text, 2);
   }

   public static void printError(ParserRuleContext ctx, String text) {
      assert ctx != null;
      assert text != null && text.length() > 0;

      errorCount++;
      printMessage(ctx, text, 3);
   }

   public static void registerError() {
      errorCount++;
   }

   public static boolean error() {
      return errorCount > 0;
   }

   public static int errorCount() {
      return errorCount;
   }

   public static int warningCount() {
      return warningCount;
   }

   public static void redirectLogFile(PrintStream logFile) {
      assert logFile != null;

      ErrorHandling.logFile = logFile;
   }

   public static void reset() {
      errorCount = 0;
      warningCount = 0;
   }

   public static final String RED = "\033[0;31m";
   public static final String GREEN = "\033[0;32m";
   public static final String YELLOW = "\033[0;33m";
   public static final String BLUE = "\033[0;34m";
   public static final String BOLD = "\033[1;38m";
   public static final String RESET = "\033[0m";

   protected static final String[] prefixMsg = { "INFO", "WARNING", "ERROR" };
   protected static final String[] prefixFormat = { BLUE, YELLOW, RED };

   protected static void printMessage(String text, int type) {
      logFile.printf("[%s%s%s] %s\n", prefixFormat[type - 1], prefixMsg[type - 1], RESET, text);
      logFile.flush();
   }

   protected static void printMessage(int line, String text, int type) {
      logFile.printf("[%s%s%s at line %d] %s\n", prefixFormat[type - 1], prefixMsg[type - 1], RESET, line, text);
      logFile.flush();
   }

   protected static void printMessage(ParserRuleContext ctx, String text, int type) {
      printMessage(ctx.getStart().getLine(), text, type);
   }

   protected static PrintStream logFile = out; // default
   protected static int errorCount = 0;
   protected static int warningCount = 0;
}
