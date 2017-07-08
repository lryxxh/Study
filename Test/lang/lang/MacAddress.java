package lang;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MacAddress {

    public static String hexByte(byte b) {

        String s = "" + Integer.toHexString(b);
        
        if (s.length() > 2) {
            s = s.substring(s.length() - 2);
        }

        return s;

    }

    public static String getMAC() {

        Enumeration<NetworkInterface> el;

        String mac_s = "";

        try {

            el = NetworkInterface.getNetworkInterfaces();

            while (el.hasMoreElements()) {
                NetworkInterface set = el.nextElement();
                System.out.println(set);
                byte[] mac = set.getHardwareAddress();

                if (mac == null || mac.length == 0)

                    continue;

                mac_s = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"

                + hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"

                + hexByte(mac[4]) + "-" + hexByte(mac[5]);

                System.out.println(mac_s + "MACµÿ÷∑");

            }

        } catch (SocketException e) {

            e.printStackTrace();

        }

        return mac_s;

    }

    public static void main(String[] args) {

//        MacAddress m = new MacAddress();
//
//        m.getMAC();

        try {
            NetworkInterface ssInterface = NetworkInterface.getByInetAddress(new Socket("192.168.60.22", 11012).getLocalAddress());
            String mac = getMacAddres(ssInterface.getHardwareAddress());
            System.out.println("--------------" + mac);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMacAddres(byte[] mac) {
        String macAddress = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"

                + hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"

                + hexByte(mac[4]) + "-" + hexByte(mac[5]);

        return macAddress;
        
    }
}
