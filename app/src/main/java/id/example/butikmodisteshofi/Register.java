package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.ResponseAllPelanggan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText namaEt, no_hpEt, emailEt, alamatEt, usernameEt, passwordEt, konfirmasi_passwordEt;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        namaEt = findViewById(R.id.txt_nama_register);
        no_hpEt = findViewById(R.id.txt_no_hp_register);
        emailEt = findViewById(R.id.txt_email_register);
        alamatEt = findViewById(R.id.txt_alamat_register);
        usernameEt = findViewById(R.id.txt_username_register);
        passwordEt = findViewById(R.id.txt_password_register);
        konfirmasi_passwordEt = findViewById(R.id.txt_konfirmasi_password_register);

        btnRegister =(Button)findViewById(R.id.btnregister);

        klik();
    }

    public void klik(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
                Call<ResponseAllPelanggan> respon = apiEndPoint.setRegisterPelanggan
                        (namaEt.getText().toString(), no_hpEt.getText().toString(), emailEt.getText().toString(), alamatEt.getText().toString(),
                                usernameEt.getText().toString(), passwordEt.getText().toString(), konfirmasi_passwordEt.getText().toString() );
                respon.enqueue(new Callback<ResponseAllPelanggan>() {
                    @Override
                    public void onResponse(Call<ResponseAllPelanggan> call, Response<ResponseAllPelanggan> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseAllPelanggan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), " Akun Terdaftar "
                                , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}