package graduate_information;

public class College {
    private String cno;
    private String cname;
    private String loc;

    public College() {
    }

    public College(String cno, String cname, String loc) {
        this.cno = cno;
        this.cname = cname;
        this.loc = loc;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "College{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
