package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.demoretrofit.interfaces.JPHapi;
import com.example.demoretrofit.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textApi = findViewById(R.id.jph_text);
        getPosts();
    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://jsonplaceholder.typicode.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        JPHapi jphApi = retrofit.create(JPHapi.class);

        Call<List<Posts>> call = jphApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    textApi.setText("Codigo: "+response.code());
                    return;
                }

                List<Posts> listPosts = response.body();
                for(Posts post:listPosts) {
                    String content = "";
                    content += "userId: " + post.getUserId() + "\n";
                    content += "id: " + post.getId() + "\n";
                    content += "title: " + post.getTitle() + "\n";
                    content += "body: " + post.getBody() + "\n\n";
                    textApi.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                textApi.setText(t.getMessage());
            }
        });
    }
}
