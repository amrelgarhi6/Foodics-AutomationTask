package utiltites.cli;

import utiltites.logger.Log4JLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMD {
    public static void main(String[] args) throws IOException {
        executeCommandLine("rmdir /Q /S allure-results");
    }

    public static void executeCommandLine(String command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            Log4JLogger.logINFO(CMD.class, new Object() {}.getClass().getEnclosingMethod().getName(), line);
        }
        p.destroy();
    }
}