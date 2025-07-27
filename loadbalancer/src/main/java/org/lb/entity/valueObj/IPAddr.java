package org.lb.entity.valueObj;

public class IPAddr {

  private String ipAddr;
  private int portNum;

  public IPAddr(String ipAddr, int portNum) throws IllegalStateException {
    String[] splitAddr = ipAddr.split("\\.");
    if (splitAddr.length > 4) {
      throw new IllegalStateException("Invalid Ip address passed");
    }

    for (String item : splitAddr) {
      try {
        int num = Integer.parseInt(item);
        if (num > 255 || num < 0) {
          throw new IllegalStateException("Invalid Ip address passed");
        }
      } catch (NumberFormatException ex) {
        throw new IllegalStateException("Invalid Ip address passed");
      }
    }

    this.ipAddr = ipAddr;
    this.portNum = portNum;
  }

  public String getIpAddr() {
    return ipAddr;
  }

  public int getPortNum() {
    return portNum;
  }
}
