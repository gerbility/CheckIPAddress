package ipValidation;

public class IpValidation {

	public static void main(String[] args) {

		String ipaddr = null;
		// Use this to check IP
		Boolean isIP = false;
		// Use this to skip all checks
		Boolean isNotIP = false;
		// Use this to check if contains "::"
		Boolean containsDoubleColons = false;
		if ((args != null) && (args.length > 0)) {
			ipaddr = args[0];
			String ipv4s[] = null;
			String ipv6s[] = null;

			if (ipaddr.contains(".")) {
				ipv4s = ipaddr.split("\\.");
			} else if (ipaddr.contains(":")) {
				if (ipaddr.contains("::")) {
					if ((ipaddr.indexOf("::") != ipaddr.lastIndexOf("::")) || (ipaddr.contains(":::"))) {
						isNotIP = true;
					}
					containsDoubleColons = true;
				}
				ipv6s = ipaddr.split(":");

			} else {
				isNotIP = true;
			}

			// IPv4
			if (!isNotIP && (ipv4s != null) && (ipv4s.length == 4)) {
				Boolean isIPv4 = true;
				try {
					for (int i = 0; i <= 3; i++) {
						int ipNum = Integer.parseInt(ipv4s[i]);
						if ((ipNum > 255) || (ipNum < 0)) {
							isIPv4 = false;
						}
					}
				} catch (Exception e) {
					isIPv4 = false;
					System.out.println("IPv4格式錯誤:" + args[0]);
					System.err.println(e);
				}
				if (isIPv4) {
					isIP = true;
				}
			}

			// IPv6
			if ((!isNotIP) && (ipv6s != null) && ((ipv6s.length == 8) 
					|| ((ipv6s.length < 8) && (containsDoubleColons))) 
					&& (!isIP)) {
				Boolean isIPv6 = true;

				try {
					for (int i = 0; i <= (ipv6s.length - 1); i++) {
						if ((ipv6s[i] != null) && (ipv6s[i] != "")) {
							int ipNum = Integer.parseInt(ipv6s[i], 16);
							if ((ipNum > 65535) || (ipNum < 0)) {
								isIPv6 = false;
							}
						}
					}
				} catch (Exception e) {
					isIPv6 = false;
					System.out.println("IPv6格式錯誤:" + args[0]);
					System.err.println(e);
				}
				if (isIPv6) {
					isIP = true;
				}
			}

			if (!isIP) {
				System.out.println("输入字符串 " + args[0] + "不是IP");
			} else {
				System.out.println("输入字符串" + args[0] + "是IP");
			}
		} else {
			System.out.println("输入字符串为空");
		}
	}
}
