package id.example.butikmodisteshofi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import id.example.butikmodisteshofi.Utility.PrefManager;
import id.example.butikmodisteshofi.api.ApiEndPoint;
import id.example.butikmodisteshofi.api.ApiRetrofit;
import id.example.butikmodisteshofi.model.DataItem;
import id.example.butikmodisteshofi.model.ResponseAllPelanggan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Button btnlogin;
        Button btnregister;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        btnregister = (Button) findViewById(R.id.btnregister) ;


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Register.class);
                Login.this.startActivity(intent);
                finish();

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLoginApi();

            }
        });
    }

    public void getLoginApi(){
        try {
            ApiEndPoint apiEndPoint = ApiRetrofit.getclient().create(ApiEndPoint.class);
            Call<ResponseAllPelanggan> call = apiEndPoint.getPelanggan() ;
            call.enqueue(new Callback<ResponseAllPelanggan>() {
                @Override
                public void onResponse(Call<ResponseAllPelanggan> call, Response<ResponseAllPelanggan> response) {
                    if (response.isSuccessful()){


                        List<DataItem> pelanggan = response.body().getData();
                        for (int i = 0; i < pelanggan.size();i++){
                            String usernameKey = username.getText().toString();
                            String passwordKey = password.getText().toString();
                            if (usernameKey.equals(response.body().getData().get(i).getNama()) && passwordKey.equals(response.body().getData().get(i).getPassword())) {
                                //jika login berhasil
                                Toast.makeText(getApplicationContext(), "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                                PrefManager prf = new PrefManager(getApplicationContext());
                                prf.setInt("idpelanggan", response.body().getData().get(i).getId());
                                Intent intent = new Intent(Login.this, Beranda.class);
                                Login.this.startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "username dan password salah", Toast.LENGTH_SHORT).show();

                                //jika login gagal
                            }

                        }





//                        PrefManager prf = new PrefManager(getApplicationContext());
//                        prf.setString("email",email);
//                        Intent intent = new Intent(Login.this, Beranda.class);
//                        startActivity(intent);
//                        finish();
                    } else {
                    }
//                    Toast.makeText(getApplicationContext(), " Gagal Login "
//                            , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseAllPelanggan> call, Throwable t) {
                    Log.d("eror","salah");

                }
            });
        }
        catch (Exception ex){

        }
    }
}

