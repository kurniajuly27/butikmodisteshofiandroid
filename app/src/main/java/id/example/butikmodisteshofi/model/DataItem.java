package id.example.butikmodisteshofi.model;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("ukuran")
	private String ukuran;

	@SerializedName("warna")
	private String warna;

	@SerializedName("harga_agen")
	private String hargaAgen;

	@SerializedName("harga")
	private String harga;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("id")
	private int id;

	@SerializedName("stok_barang")
	private String stokBarang;

	@SerializedName("penjualan_id")
	private int penjualanid;

	@SerializedName("pelanggan_id")
	private int pelangganid;

	@SerializedName("barang_id")
	private int barangid;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("total")
	private int total;

	@SerializedName("tanggal")
	private String tanggal;

	public void setUkuran(String ukuran){
		this.ukuran = ukuran;
	}

	public String getUkuran(){
		return ukuran;
	}

	public void setWarna(String warna){
		this.warna = warna;
	}

	public String getWarna(){
		return warna;
	}

	public void setHargaAgen(String hargaAgen){
		this.hargaAgen = hargaAgen;
	}

	public String getHargaAgen(){
		return hargaAgen;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStokBarang(String stokBarang){
		this.stokBarang = stokBarang;
	}

	public String getStokBarang(){
		return stokBarang;
	}

	@Override
 	public String toString(){
		return
			"DataItem{" +
			"ukuran = '" + ukuran + '\'' +
			",warna = '" + warna + '\'' +
			",harga_agen = '" + hargaAgen + '\'' +
			",harga = '" + harga + '\'' +
			",foto = '" + foto + '\'' +
			",updated_at = '" + updatedAt + '\'' +
			",created_at = '" + createdAt + '\'' +
			",nama_barang = '" + namaBarang + '\'' +
			",id = '" + id + '\'' +
			",stok_barang = '" + stokBarang + '\'' +
			",penjualan_id = '" + penjualanid + '\'' +
			",pelanggan_id = '" + pelangganid + '\'' +
			",barang_id = '" + barangid +'\'' +
			",jumlah = '" + jumlah +'\'' +
			",total = '" + total + '\'' +
			",tanggal = '" + tanggal + '\'' +
			"}";
		}

	public int getPenjualanid() {
		return penjualanid;
	}

	public void setPenjualanid(int penjualanid) {
		this.penjualanid = penjualanid;
	}

	public int getPelangganid() {
		return pelangganid;
	}

	public void setPelangganid(int pelangganid) {
		this.pelangganid = pelangganid;
	}

	public int getBarangid() {
		return barangid;
	}

	public void setBarangid(int barangid) {
		this.barangid = barangid;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
}