package resty.project.babinsa.Model;

public class User {
    private  String Name;
    private  String Password;
    private String NRP;
    private String Jabatan;

    public User() {
    }

    public User(String name, String nrp, String jabatan, String password) {
        Name = name;
        NRP = nrp;
        Jabatan = jabatan;
        Password = password;
    }

    public String getNRP() {
        return NRP;
    }

    public void setNRP(String nrp) {
        NRP = nrp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getJabatan() {
        return Jabatan;
    }

    public void setJabatan(String jabatan) {
        Jabatan = jabatan;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}