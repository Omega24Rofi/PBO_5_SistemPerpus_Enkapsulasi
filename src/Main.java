import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // deklarasi Array list untuk kelas perpustakaan
    public static Perpustakaan perpustakaan = new Perpustakaan();
    // deklarasi fungsi untuk user dengan role Pengunjung
    public static void forPengunjung( ArrayList<User>daftarPusktakawan, String CurrentUser){
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("=".repeat(30));
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Kembalikan buku");
            System.out.println("3. Daftar buku");
            System.out.println("4. Quit");
            System.out.print("Masukkan pilihan : ");
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    perpustakaan.rakBuku();
                    System.out.print("Masukkan nomor judul buku yang ingin dipinjam : ");
                    int selectBuku = input.nextInt();
                    input.nextLine();
                    System.out.println("=".repeat(30));
                    User pustakawanNow = daftarPusktakawan.get(0);
                    if(perpustakaan.daftarBuku.size()>0 && selectBuku <= perpustakaan.daftarBuku.size()){
                        System.out.print("Berapa hari ingin meminjam :");
                        int lamaPinjam = input.nextInt();
                        if(selectBuku == 0){
                            quit =  true;
                            break;
                        } else{
                            Buku bukuPilih = perpustakaan.daftarBuku.get(selectBuku - 1);
                            System.out.println(bukuPilih);
                            bukuPilih.isPinjam(pustakawanNow, CurrentUser, lamaPinjam);
                            break;
                        }
                    } else{
                        System.out.println("Belum ada buku");
                    } 
                    break;
                case 2:
                    System.out.println("Masukkan judul buku : ");
                    input.nextLine();
                    boolean bukuTidakditemukan = false;
                    String judulReturn = input.nextLine();
                    for (Buku buku : perpustakaan.daftarBuku){
                        if(buku.getJudul().equals(judulReturn)){
                            int updatedStok = buku.getStok() + 1;
                            buku.setStok(updatedStok);
                            bukuTidakditemukan = true;
                            System.out.println("Buku berhasil dikembalikan.");
                            break;
                        }
                    } 
                    if(!bukuTidakditemukan){
                        System.out.println("Buku tidak terdaftar");
                    }
                    break;
                case 3:
                    perpustakaan.rakBuku();
                    break;
                case 4:
                    System.out.println("=".repeat(30));
                    quit = true;
                    break;
                default:
                    System.out.println("Input Invalid");
                    break;
            }
        }
        
    }
    // deklarasi fungsi untuk user dengan role Pustakawan
    public static void forPustakawan(ArrayList<Buku>daftarBuku){
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("=".repeat(30));
            System.out.println("1. Tambah buku");
            System.out.println("2. Daftar buku");
            System.out.println("3. Quit");
            System.out.print("Masukkan Pilihan : ");
            int pilihan = input.nextInt();
            input.nextLine();
            if (pilihan == 1) {
                System.out.print("Masukkan judul buku : ");
                String judul = input.nextLine();
                System.out.print("Masukkan penulis buku : ");
                String penulis = input.nextLine();
                System.out.print("Masukkan stok buku : ");
                int stokBuku = input.nextInt();
                input.nextLine();
                if (pilihan==1) {
                    boolean found = false;
                    for (Buku bukuItem : daftarBuku) {
                        if (bukuItem.getJudul().equals(judul)) {
                            int updatedStok = bukuItem.getStok() + stokBuku;
                            bukuItem.setStok(updatedStok);
                            found = true;
                            break;
                        }
                    }
            
                    if (!found) {
                        Buku buku = new Buku(judul, penulis, stokBuku);
                        perpustakaan.tambahBuku(buku);
                    }
                }
                }
                else if(pilihan == 2){
                    perpustakaan.rakBuku();
                    System.out.println("=".repeat(30));
                } else if (pilihan == 3) {
                    quit = true;
                    System.out.println("=".repeat(30));
                } else {
                
                System.out.println("Input invalid");
                }
        
        }
    }
    // deklarasi fungsi untuk menu login / menu awal
    public static void menuLogin() {
        Scanner input = new Scanner(System.in);
        ArrayList<User> daftarUser = new ArrayList<>();
        ArrayList<User> daftarPustakawan = new ArrayList<>();
        boolean quit = false;

        while (!quit) {
            System.out.println("=".repeat(30));
            System.out.println("1. Daftar");
            System.out.println("2. Masuk");
            System.out.println("3. Quit");
            System.out.print("Masukkan Pilihan : ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.println("=".repeat(30));
                    System.out.println("Masukkan nama User : ");
                    String name = input.nextLine();
                    System.out.println("Masukkan role User (Pengunjung/Pustakawan): ");
                    String role = input.nextLine();
                    User user = new User(name, role);
                    daftarUser.add(user);
                    if(role.equals("Pustakawan")){
                        daftarPustakawan.add(user);
                    }
                    break;

                case 2:
                    System.out.println("=".repeat(30));
                    System.out.println("Login");
                    System.out.println("Masukkan nama : ");
                    String Cekname = input.nextLine();
                    System.out.println("Masukkan role (Pengunjung/Pustakawan): ");
                    String Cekrole = input.nextLine();

                    boolean loggedIn = false;
                    for (User CurrentUser : daftarUser) {
                        if (CurrentUser.getNama().equals(Cekname) && CurrentUser.getRole().equals(Cekrole)) {
                            loggedIn = true;
                            System.out.println("Login berhasil sebagai " + Cekrole);
                            // System.out.println("=".repeat(30));
                            if (CurrentUser.getRole().equals("Pustakawan")) {
                                forPustakawan(perpustakaan.daftarBuku);
                            } else if(CurrentUser.getRole().equals("Pengunjung")){
                                forPengunjung(daftarPustakawan, CurrentUser.getNama());
                            }
                        }
                    }

                    if (!loggedIn) {
                        System.out.println("Login gagal, nama atau role salah.");
                    }
                    break;

                case 3:
                    quit = true;
                    break;

                default:
                    System.out.println("Input invalid");
                    break;
            }
        }

        for (User user : daftarUser) {
            System.out.println("Nama : " + user.getNama() + ", Role : " + user.getRole());
        }
    }
    public static void main(String[] args){
        // panggil fungsi menulogin
        menuLogin();
}
}
