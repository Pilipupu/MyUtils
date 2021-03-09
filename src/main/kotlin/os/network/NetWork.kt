package os.network

import org.apache.commons.net.util.SubnetUtils
import org.apache.commons.validator.routines.InetAddressValidator
import java.net.NetworkInterface

interface EtherDevice {
    fun up()
    fun down()
    fun setIPv4(ip: String)
    fun cleanIPv4()
    fun exists(): Boolean
    fun delete()
    fun deviceName(): String
    fun setType(type: String)
    fun ipAddresses(): List<IPAddr>
    fun getAddressByIP(ip: String): IPAddr?
}

interface IPAddr {
    fun ipv4(): String
    fun netmask(): String
    fun network(): String
    fun cidrString(): String
    fun cidrLength(): Int
    fun broadcast(): String
    fun address(): String
    fun subnet(): SubnetUtils.SubnetInfo
}

object NetWork {

    fun isValidIp4(ip: String): Boolean {
        val validator = InetAddressValidator.getInstance();
        return validator.isValidInet4Address(ip);
    }

    fun getEtherDeviceByIP(ip: String): NetworkInterface? {
        val eth = NetworkInterface.getNetworkInterfaces().toList().find {
            it.inetAddresses.toList().any { addr ->
                addr.hostAddress == ip
            }
        }
        return eth
    }
}

fun getEtherDeviceByIP1(ip: String): NetworkInterface? {
    val eth = NetworkInterface.getNetworkInterfaces().toList().find {
        it.inetAddresses.toList().any { addr ->
            addr.hostAddress == ip
        }
    }

    return eth
}