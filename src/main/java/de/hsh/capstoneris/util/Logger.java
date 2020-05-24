package de.hsh.capstoneris.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Logger {
    // [DATA] [DATABASE]
    // [REST] [REST]
    // [SOCK] [SOCKET]
    // [SYS ] [SYSTEM]
    public static boolean enabled = true;

    public static void log(Service service, String message) {
        if (!enabled) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
        builder.append("[").append(s).append("]");
        switch (service) {
            case DB:
                builder.append("[DATA]");
                break;
            case REST:
                builder.append("[REST]");
                break;
            case SOCKET:
                builder.append("[SOCK]");
                break;
            case SYSTEM:
                builder.append("[MISC]");
                break;
        }

        builder.append(": ").append(message);
        System.out.println(builder.toString());
    }

    public static void log(Service service, String message, String color) {
        if (!enabled) {
            return;
        }
        System.out.print(color);
        log(service, message);
        System.out.print(ConsoleColors.RESET);
    }
}
