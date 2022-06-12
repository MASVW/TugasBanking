import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception 
    {
        ArrayList<Nasabah> nasabah = new ArrayList <Nasabah>();
        ArrayList<String> logMutasi = new ArrayList <String>();
        System.out.println("Selamat Datang Di Aplikasi Perbankan oleh Samuel Zakaria H");
        System.out.println("-----------------------------------------------------------------------");


        String yn = "y";
        Scanner input = new Scanner(System.in);
        init(nasabah);
        do {
            menu();
            
            int pilihan = 0;
            pilihan = input.nextInt();
            
            if(pilihan==1) //tambah data
            {
                String NoRek;

                String NamaNsb;
                long Saldo=0;
                System.out.print("Nama Nasabah \t:\t");
                NamaNsb = input.next();
                System.out.print("Nama Nomor Rekening \t:\t");
                NoRek = input.next();
                System.out.print("Saldo Awal \t:\t");
                Saldo = input.nextLong();
                nasabah.add(new Nasabah(NoRek, NamaNsb, Saldo));
            }

            else if (pilihan==2)//setor uang
            {
                String NoRek;
                long setoran=0;
                System.out.print ("Nomor Rekening \t:\t");
                NoRek = input.next();
                System.out.print ("Nominal Transfer (IDR)\t:\t");
                setoran = input.nextLong();
                int i=0;
                for (Nasabah nasabah2 : nasabah) {
                    if(nasabah2.getNoRek().equals(NoRek)){
                        System.out.println("Nomor rekening ditemukan");
                        System.out.println(nasabah2);
                        Nasabah tmpnsb = nasabah2;
                        tmpnsb.setSaldo(tmpnsb.getSaldo() + setoran);
                        nasabah.set(i, tmpnsb);

                        System.out.println("Setoran Berhasil");
                        logMutasi.add("Setor " + setoran + " ke rekening " + tmpnsb.getNoRek() + " AN 1" + tmpnsb.getNamaNsb());
                    }
                i++;
                }

            }
            else if (pilihan==3)//cetak mutasi
            {
                for (String string : logMutasi){
                    System.out.println(string);
                }
            }
            else if (pilihan==4) //tf uang
            {
                String NoRek3;
                String NoRek4;
                long tf=0;
                System.out.print ("Masukkan Nomor Rekening Kamu \t:\t");
                NoRek3 = input.next();
                for (Nasabah nasabah3 : nasabah) 
                {
                    if(nasabah3.getNoRek().equals(NoRek3))
                    {
                        System.out.println("Nomor rekening Atas Nama " + nasabah3.getNamaNsb());
                        System.out.print ("Masukkan Nomor Rekening yang dituju \t:\t");
                        NoRek4 = input.next();
                        int i=0;
                        for (Nasabah nasabah4 : nasabah) 
                        {
                            if(nasabah4.getNoRek().equals(NoRek4))
                            {
                                System.out.println("Rekening AN "+ nasabah4.getNamaNsb());
                                System.out.print ("Nominal Transfer (IDR)\t:\t");
                                tf = input.nextLong();

                                if(tf<nasabah3.getSaldo() & (nasabah3.getSaldo())<=0)
                                {
                                    Nasabah tmpnsb2 = nasabah3;
                                    tmpnsb2.setSaldo(tmpnsb2.getSaldo()- tf);
                                    Nasabah tmpnsb3 = nasabah4;
                                    tmpnsb3.setSaldo(tmpnsb3.getSaldo() + tf);
                                    nasabah.set(i, tmpnsb2);
                                    logMutasi.add("Transfer " + tf + " ke rekening " + tmpnsb3.getNoRek() + " AN " + tmpnsb3.getNamaNsb());
                                    System.out.println("\n<TRANSAKSI BERHASIL>");
                                    System.out.println("Saldo Tersisah " + tmpnsb2.getSaldo());
                                }
                                else
                                {
                                    System.out.println("\n<TRANSAKSI GAGAL>");
                                    System.out.println("Saldo tidak mencukupi...");  
                                }
                            }
                        }
                        i++;
                    }
                    else { System.out.println("Rekening Belum Terdaftar"); break;}
                }
            }
            else if (pilihan==5){//cetak data nasabah
                cetakNamaNasabah(nasabah);
            }
            else if (pilihan==6){//Keluar
                break;
            }
            else {continue;}
            
            System.out.println("Apakah anda ingin kembali ke menu utama? (y/n)");
            yn = input.next();
        } while (yn.equalsIgnoreCase( "y"));
    }

    public static ArrayList <Nasabah> init(ArrayList <Nasabah> nasabah)
    {
        Nasabah nsb1 = new Nasabah("01020301", "Jodie", 0);
        nasabah.add(nsb1);
        Nasabah nsb2 = new Nasabah("01020302", "Kimberlly", 100000);
        nasabah.add(nsb2);
        Nasabah nsb3 = new Nasabah("01020303", "Samuel", 500000);
        nasabah.add(nsb3);
        Nasabah nsb4 = new Nasabah("01020304", "Jian", 5000000);
        nasabah.add(nsb4);
        nasabah.add(new Nasabah("01020305", "Chai", 2000));
        cetakNamaNasabah(nasabah);
        return nasabah;
    }
    public static void menu()
    {
        System.out.println("Aplikasi Banking by Samuel Zakaria");
        System.out.println("1. Tambah Nasabah Baru");
        System.out.println("2. Transfer Uang");
        System.out.println("3. Cetak Mutasi");
        System.out.println("4. Transfer Uang");
        System.out.println("5. Cetak Data Nasabah");
        System.out.println("6. Keluar");
        System.out.println("Masukkan Pilihan Mu (1/2/3/4/5/6)\t:\t");
    }
    public static void hapusNasabah(ArrayList<Nasabah> nasabah, int idx){
        nasabah.remove(idx);        
    }
    public static void hapusNasabah(ArrayList<Nasabah> nasabah, Nasabah nsb){
        nasabah.remove(nsb);        
    }
    public static void cetakNamaNasabah(ArrayList<Nasabah> nasabah){
        System.out.println("No.Rekening\tNama\t\tSaldo\tNo.kartu\tPIN\tTgl. Daftar");
        System.out.println("-------------------------------------------------------------------------");
        for (Nasabah nsbh : nasabah) {
            System.out.println(nsbh);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}
