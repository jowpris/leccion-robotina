package com.example.joel.serviciosandroidphp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    ListView listadoUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listadoUsuarios=(ListView) findViewById(R.id.listadoUsuarios);

    }

    public void obtenerDatos(){
        AsyncHttpClient cliente= new AsyncHttpClient();
        String url="https://jsonplaceholder.typicode.com/posts";

        cliente.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    caragarUsuarios(obtenerDatosJson(new String (responseBody)));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void caragarUsuarios(ArrayList<String> usuarios){
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usuarios);
        listadoUsuarios.setAdapter(adaptador);
    }

    public ArrayList<String> obtenerDatosJson(String respuesta){
        ArrayList<String> usuarios = new ArrayList<String>();

        try{
            JSONArray jsonArray= new JSONArray(respuesta);
            String texto="";

            for (int i=0; i<jsonArray.length(); i++){
                texto= jsonArray.getJSONObject(i).getString("nombre");

                usuarios.add(texto);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return usuarios;
    }


}
