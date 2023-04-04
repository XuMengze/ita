package com.ita.alg.hodgepodge;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class IPAddress {
    public String solve(String IP) {
        // write code here
        if (IP.contains(":") && isIPV6(IP)) {
            return "IPV6";
        }
        if (IP.contains(".") && isIPV4(IP)) {
            return "IPV4";
        }
        return "Neither";
    }

    private boolean isIPV4(String ipv4Address) {
        String[] addresses = ipv4Address.split("\\.");
        if (addresses.length != 4) {
            return false;
        }
        for (String add : addresses) {
            if (!add.equals("0") && add.startsWith("0")) {
                return false;
            }
            int singleInt;
            try {
                singleInt = Integer.parseInt(add);
            } catch (Exception ignore) {
                return false;
            }
            if (singleInt > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean isIPV6(String ipv6Address) {
        if (ipv6Address.endsWith(":")){
            return false;
        }
        String[] addresses = ipv6Address.split(":");
        if (addresses.length != 8) {
            return false;
        }
        for (String add : addresses) {
            if (add.length() > 5 || add.isEmpty()) {
                return false;
            }
            if (add.length() > 1 && add.equals(String.join(".", Collections.nCopies(add.length(), "0")))) {
                return false;
            }
            Optional<Boolean> hasWrongCode =
                    Arrays.stream(add.split("")).map(s -> (s.charAt(0) >= 'G' && s.charAt(0) <= 'Z') || (s.charAt(0) >= 'g' && s.charAt(0) <= 'z')).reduce(Boolean::logicalOr);
            if (hasWrongCode.isPresent() && hasWrongCode.get()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IPAddress handler = new IPAddress();
//        assert handler.isIPV6("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
//        assert handler.isIPV6("2001:db8:85a3:0:0:8A2E:0370:7334");
//        assert !handler.isIPV6("2001:0db8:85a3::8A2E:0370:7334");
//        assert !handler.isIPV6("02001:0db8:85a3:0000:0000:8a2e:0370:7334");
        handler.isIPV6("02001:0db8:85a3:0:0:8A2E:0370:7334");
    }
}
