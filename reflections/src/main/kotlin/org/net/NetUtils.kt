package org.net

import org.apache.commons.validator.routines.InetAddressValidator

object NetUtils {

    fun isValidIp4(ip: String) : Boolean {
        val validator = InetAddressValidator.getInstance();
        return validator.isValidInet4Address(ip);
    }

}