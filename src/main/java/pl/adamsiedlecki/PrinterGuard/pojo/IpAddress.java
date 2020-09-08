package pl.adamsiedlecki.PrinterGuard.pojo;

public class IpAddress {

    // {"ip":"66.249.75.9","country":"United States","cc":"US"}

    private String ip;
    private String country;
    private String cc;

    public IpAddress() {
    }

    public IpAddress(String ip, String country, String cc) {
        this.ip = ip;
        this.country = country;
        this.cc = cc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
