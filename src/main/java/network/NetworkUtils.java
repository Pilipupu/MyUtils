package network;

import org.apache.commons.net.util.SubnetUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkUtils {
    public static boolean isIpv4Address(String ip) {
        String PATTERN =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    private static void validateIp(String ip) {
        if (!isIpv4Address(ip)) {
            throw new IllegalArgumentException(String.format("%s is not a valid ipv4 address", ip));
        }
    }

    public static long bytesToLong(byte[] bytes) {
        long value = 0;
        for (byte b : bytes) {
            value = (value << 8) + (b & 0xff);
        }
        return value;
    }

    public static long ipv4StringToLong(String ip) {
        validateIp(ip);

        try {
            InetAddress ia = InetAddress.getByName(ip);
            byte[] bytes = ia.getAddress();
            return bytesToLong(bytes);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(String.format("%s is not a valid ipv4 address", ip), e);
        }
    }

    public static String longToIpv4String(long ip) {
        byte[] bytes = ByteBuffer.allocate(4).putInt((int) ip).array();
        try {
            InetAddress ia = InetAddress.getByAddress(bytes);
            return ia.getHostAddress();
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(String.format("%s cannot convert to a valid ipv4 address", ip), e);
        }
    }

    public static boolean isIpv4InRange(String ip, String startIp, String endIp) {
        long ipl = ipv4StringToLong(ip);
        long startIpl = ipv4StringToLong(startIp);
        long endIpl = ipv4StringToLong(endIp);
        return (ipl <= endIpl && ipl >= startIpl);
    }

    public static List<String> getFreeIpInRange(String startIp, String endIp, List<String> usedIps, int limit, String start) {
        long s = ipv4StringToLong(startIp);
        long e = ipv4StringToLong(endIp);
        long f = ipv4StringToLong(start);
        List<String> res = new ArrayList<String>();
        for (long i = Math.max(s, f); i<=e; i++) {
            String ip = longToIpv4String(i);
            if (!usedIps.contains(ip)) {
                res.add(ip);
            }

            if (res.size() >= limit) {
                break;
            }
        }

        return res;
    }

    public static String getNetworkAddressFromCidr(String cidr) {
        SubnetUtils n = new SubnetUtils(cidr);
        return String.format("%s/%s", n.getInfo().getNetworkAddress(), cidr.split("\\/")[1]);
    }

    public static String getCidrFromIpMask(String ip, String mask) {
        try {
            InetAddress netmask = InetAddress.getByName(mask);
            return getNetworkAddressFromCidr(String.format("%s/%s", ip, convertNetmaskToCIDR(netmask)));
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(String.format("%s is not a valid ipv4 netmask", ip));
        }
    }

    public static int convertNetmaskToCIDR(InetAddress netmask){

        byte[] netmaskBytes = netmask.getAddress();
        int cidr = 0;
        boolean zero = false;
        for (byte b : netmaskBytes) {
            int mask = 0x80;

            for (int i = 0; i < 8; i++) {
                int result = b & mask;
                if (result == 0) {
                    zero = true;
                } else if (zero) {
                    throw new IllegalArgumentException("invalid netmask");
                } else {
                    cidr++;
                }
                mask >>>= 1;
            }
        }
        return cidr;
    }

    public static boolean isBelongToSameSubnet(String addr1, String addr2, String mask) {
        byte[] addr1Byte = new byte[0];
        byte[] addr2Byte = new byte[0];
        byte[] maskByte = new byte[0];
        try {
            addr1Byte = InetAddress.getByName(addr1).getAddress();
            addr2Byte = InetAddress.getByName(addr2).getAddress();
            maskByte = InetAddress.getByName(mask).getAddress();
        } catch (UnknownHostException e) {
            return false;
        }

        for (int i = 0; i < addr1Byte.length; i++)
            if ((addr1Byte[i] & maskByte[i]) != (addr2Byte[i] & maskByte[i]))
                return false;
        return true;
    }
}
