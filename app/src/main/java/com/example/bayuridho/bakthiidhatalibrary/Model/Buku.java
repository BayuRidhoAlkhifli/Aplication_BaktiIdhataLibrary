package com.example.bayuridho.bakthiidhatalibrary.Model;

public class Buku {
    public String idRak,idSekat,idBuku,judulBuku,penulis,penerbit,stok,deskripsi;
    public byte[] sampul;

    public Buku(String idRak, String idSekat, String idBuku, String judulBuku, String penulis, String penerbit, String stok, String deskripsi, byte[] sampul) {
        this.idRak = idRak;
        this.idSekat = idSekat;
        this.idBuku = idBuku;
        this.judulBuku = judulBuku;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stok = stok;
        this.deskripsi = deskripsi;
        this.sampul = sampul;
    }

    public Buku() {
    }

    public String getIdRak() {
        return idRak;
    }

    public void setIdRak(String idRak) {
        this.idRak = idRak;
    }

    public String getIdSekat() {
        return idSekat;
    }

    public void setIdSekat(String idSekat) {
        this.idSekat = idSekat;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public byte[] getSampul() {
        return sampul;
    }

    public void setSampul(byte[] sampul) {
        this.sampul = sampul;
    }
}
