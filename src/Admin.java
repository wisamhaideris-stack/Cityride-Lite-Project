public class Admin extends User {

    private String password;

    // Constructors
    public Admin() {

        // (Oracle, 2026)
        super();

        password = "";
    }
    public Admin(String name, String password) {
        // (Oracle, 2026)
        super(name);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}