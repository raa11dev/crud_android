package com.raa11dev.crudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText Anim,Anama,Akelas,Aprodi;
    Button simpan, tampil, edit, hapus, logout;
    CRUD crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //EditText
        Anim = (EditText) findViewById(R.id.nim);
        Anama = (EditText) findViewById(R.id.nama);
        Akelas = (EditText) findViewById(R.id.kelas);
        Aprodi = (EditText) findViewById(R.id.prodi);
        //Button
        logout = (Button)findViewById(R.id.logout);
        simpan = (Button)findViewById(R.id.simpan);
        tampil = (Button)findViewById(R.id.tampil);
        hapus = (Button)findViewById(R.id.hapus);
        edit = (Button)findViewById(R.id.edit);
        //crud
        crud = new CRUD(this);
        //btnlogout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //btnsimpan
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = Anim.getText().toString();
                String nama = Anama.getText().toString();
                String kelas = Akelas.getText().toString();
                String prodi = Aprodi.getText().toString();
                if (validasi_nim(false)){
                    if(validasi_nama(false)){
                        if (validasi_kelas(false)){
                            if(validasi_prodi(false)){
                                Cursor res = crud.cekData(nim);
                                if (res.getCount() == 1) {
                                    nim_ada();
                                    return;
                                } else {
                                    boolean create = crud.createData(nim, nama, kelas, prodi);
                                    if (create) {
                                        sukses("Simpan Data");
                                    } else {
                                        gagal("Simpan Data");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        //btnupdate
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = Anim.getText().toString();
                String nama = Anama.getText().toString();
                String kelas = Akelas.getText().toString();
                String prodi = Aprodi.getText().toString();
                if (validasi_nim(false)){
                    if(validasi_nama(false)){
                        if (validasi_kelas(false)){
                            if(validasi_prodi(false)){
                                if (validasi(false)) {
                                    boolean updates = crud.update(nim, nama, kelas, prodi);
                                    if (updates) {
                                        sukses("Edit Data");
                                    } else {
                                        gagal("Edit Data");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        //btnhapus
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validasi_nim(false)) {
                    if (validasi(false)) {
                        boolean delete = crud.delete(Anim.getText().toString());
                        if (delete) {
                            sukses("Hapus Data");
                        } else {
                            gagal("Hapus Data");
                        }
                    }
                }
            }
        });
        //btntampildata
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = crud.getAllData();
                if (cursor.getCount() == 0){
                    showMessage("Tampil Biodata","Belum Ada Data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext() ) {
                    buffer.append("NIM : " + cursor.getString(0) + "\n");
                    buffer.append("Name : " + cursor.getString(1) + "\n");
                    buffer.append("Kelas : " + cursor.getString(2) + "\n");
                    buffer.append("Prodi : " + cursor.getString(3) + "\n\n");
                }
                // show all data
                showMessage("Tampil Biodata",buffer.toString());
            }
        });
    }
    //show message tampil data
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    //show message sukses
    private void sukses(String info) {
        bersih();
        Toast.makeText(this, info + " Berhasil", Toast.LENGTH_LONG).show();
    }
    //show message gagal
    private void gagal(String info) {
        Toast.makeText(this, info + " Gagal", Toast.LENGTH_LONG).show();
    }
    //validasi cek nim ada di database atau tidak
    private boolean validasi(boolean isValidasi) {
        String txtInput = Anim.getText().toString();
        if (!isValidasi && txtInput.isEmpty()) {
            Toast.makeText(this, "NIM Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return false;
        }
        //nilai input harus ada di database sebelumnya
        if (!crud.getDataByInput(txtInput)) {
            Toast.makeText(this, "NIM Tidak Ada Didatabase", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    //bersihakan data
    public void bersih(){
        Anim.setText("");
        Anama.setText("");
        Akelas.setText("");
        Aprodi.setText("");
    }
    private boolean validasi_nim(boolean Vnim){
        String nim = Anim.getText().toString();
        if (!Vnim && nim.isEmpty()) {
            Toast.makeText(this, "NIM tidak boleh kosong !", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private boolean validasi_nama(boolean Vnama){
        String nama = Anama.getText().toString();
        if (!Vnama && nama.isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong !", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private boolean validasi_kelas(boolean Vkelas){
        String kelas = Akelas.getText().toString();
        if (!Vkelas && kelas.isEmpty()) {
            Toast.makeText(this, "Kelas tidak boleh kosong !", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private boolean validasi_prodi(boolean Vprodi){
        String prodi = Aprodi.getText().toString();
        if (!Vprodi && prodi.isEmpty()) {
            Toast.makeText(this, "Prodi tidak boleh kosong !", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void nim_ada(){
        Toast.makeText(this, "NIM sudah ada di database !", Toast.LENGTH_LONG).show();
    }
}
