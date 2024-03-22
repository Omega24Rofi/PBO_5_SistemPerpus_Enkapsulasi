import java.util.ArrayList;

public class Buku {
    private int stok = 0;
    private String Judul;
    private String Penulis;
    private boolean status = true;
    private int lamaPinjam = 0;
    private ArrayList<ArrayList<Object>> peminjam = new ArrayList<ArrayList<Object>>();

    // method untuk membuat buku baru
    public Buku(String Judul, String Penulis, int stok){
        this.Judul = Judul;
        this.Penulis = Penulis;
        this.stok = stok;
    }

    // method untuk memanggil variabel yang telah dienkapsulasi
    public int getStok(){
        return this.stok;
    }

    public void setStok(int stok){
        this.stok = stok;
    }

    public int getLamapinjam(){
        return this.lamaPinjam;
    }

    public String getJudul(){
        return this.Judul;
    }

    public String getPenulis(){
        return this.Penulis;
    }

    public boolean getStatus(){
        return this.status;
    }

    public ArrayList<ArrayList<Object>> getPeminjam(){
        return this.peminjam;
    }

    // method untuk meminjam buku
    public void isPinjam(User Pustakawan, String Pengunjung, int lamaPinjam){
        if(!this.status){
            System.out.println("Buku sedang dipinjam");
        } else{
            boolean sudahDipinjam = false;
            for(int i = 0; i < this.peminjam.size(); i++){
                if(this.peminjam.get(i).get(1).equals("Pengunjung")){
                    sudahDipinjam = true;
                }
            }
            if(sudahDipinjam){
                System.out.println("Anda sudah meminjam buku ini");
            } else{
                ArrayList<Object> dataPeminjam = new ArrayList<Object>();
                dataPeminjam.add(Pustakawan);
                dataPeminjam.add(Pengunjung);
                dataPeminjam.add(lamaPinjam);
                peminjam.add(dataPeminjam);
            }
            int stokSaatini = this.stok - this.peminjam.size();
            this.stok = this.stok - 1;
            System.out.println("Buku berhasil dipinjam");
            System.out.println("Stok Buku " + Judul + " tersisa : " + stokSaatini);
        }
    }

    public String toString(){
        String kabar = " '";
        if(status == true){
            kabar = "Buku sedang dipinjam!";
        } else {
            kabar = "Buku belum dipinjam!";
        }

        return "=====Informasi Buku=====" + "\n" +
                "Judul : " + this.Judul +"\n" +
                "Penulis : " + this.Penulis +"\n" +
                "Status : " + kabar;
    }
}
