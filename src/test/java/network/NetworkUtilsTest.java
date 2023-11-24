package network;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkUtilsTest {
    @Test
    public void testipv4StringToLong() throws UnknownHostException {
        String ip = "10.211.55.15";
        InetAddress ia = InetAddress.getByName(ip);
        for (byte address : ia.getAddress()) {
            System.out.println(address);
        }
        System.out.println(NetworkUtils.ipv4StringToLong(ip));

    }

    @Test
    public void testisIpv4InRange() {
        System.out.println(NetworkUtils.isIpv4InRange("10.211.55.15", "10.211.55.1", "10.211.55.20"));
        System.out.println(NetworkUtils.isIpv4InRange("10.211.55.15", "10.211.54.1", "10.211.55.20"));
    }

    @Test
    public void testGetFreeIpInRange() {
        List<String> usedIps = Arrays.asList("10.211.55.15","10.211.55.16");
        for (String s : NetworkUtils.getFreeIpInRange("10.211.55.15", "10.211.55.80", usedIps, 10, "10.211.55.18")) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetCidrFromIpMask() {
        System.out.println(NetworkUtils.getCidrFromIpMask("10.211.10.15", "255.255.255.0"));
        System.out.println(NetworkUtils.getCidrFromIpMask("10.211.10.14", "255.255.255.0"));
    }

    @Test
    public void testIsBelongToSameSubnet() {
        System.out.println(NetworkUtils.isBelongToSameSubnet("10.211.55.14", "10.211.55.15", "255.255.255.0"));
        System.out.println(NetworkUtils.isBelongToSameSubnet("10.211.55.14", "10.211.55.15", "255.255.0.0"));
    }
}
