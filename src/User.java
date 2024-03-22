public class User{
    private String nama;
    private String role;

    // metohod untuk memasukan user baru
    public User(String nama, String role){
        this.nama = nama;
        this.role = role;
    }

    public String getNama(){
        return this.nama;
    }

    public String getRole(){
        return this.role;
    }
}