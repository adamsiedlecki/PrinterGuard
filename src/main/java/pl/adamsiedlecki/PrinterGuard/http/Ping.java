package pl.adamsiedlecki.PrinterGuard.http;

import java.io.IOException;
import java.net.InetAddress;

public class Ping {

    private static final int TIMEOUT_MILLIS = 1000;

    public static boolean isActive(String address) {
        InetAddress inet = null;
        boolean isActive = false;
        try {
            inet = InetAddress.getByName(address);
            System.out.println("Sending Ping Request to " + address);
            isActive = inet.isReachable(TIMEOUT_MILLIS);
            String state = isActive ? "active" : "inactive";
            System.out.println("Address: " + address + " is " + state);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return isActive;
    }


}


