package utiltites.cli;

import utiltites.exceptions.Exceptions;
import utiltites.logger.Log4JLogger;
import web.utilities.reader_manager.properties_reader.ConfigUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {
     public static void executeCommandLine(String command) {
        ProcessBuilder builder;
        String os = ConfigUtils.get_OperatingSystem().toLowerCase();

        if (os.contains("win")) {
            builder = new ProcessBuilder("cmd.exe", "/c", command);
        } else {
            builder = new ProcessBuilder("bash", "-c", command);
        }

        builder.redirectErrorStream(true);

        try {
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                Log4JLogger.logINFO(CLI.class, new Object() {}.getClass().getEnclosingMethod().getName(), line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            Exceptions.handle(CLI.class, e);
        }
    }


}