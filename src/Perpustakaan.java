import java.util.ArrayList;

public class Perpustakaan {
    public ArrayList<Buku> daftarBuku= new ArrayList<Buku>();

    // method untuk menambahkan buku ke dalam daftar buku
    public void tambahBuku(Buku newBuku){
        this.daftarBuku.add(newBuku);
    }

    // method untuk menampilkan list buku
    public void rakBuku() {
        if(this.daftarBuku.size()>0){
            int idx = 0;
            System.out.println("Berikut adalah daftar buku yang ada :");
            for (Buku buku : daftarBuku){
                System.out.println((idx+1) + ". " + buku.getJudul() + " : " + buku.getStok());
                idx++;
        }
        } else{
            System.out.println("Belum ada buku yang terdaftar.");
        }
        
    }
}
