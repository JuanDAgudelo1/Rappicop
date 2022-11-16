package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;

public class AgregarDireccion extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="mesagge";
    Button agregar;
    EditText nombre, dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_direccion);

        DatabaseHelper rapidb = new DatabaseHelper(AgregarDireccion.this);
        Logica iu = new Logica(AgregarDireccion.this);
        Intent intent=getIntent();
        String id = intent.getStringExtra(EXTRA_MESSAGE);

        nombre = (EditText) findViewById(R.id.namedir);
        dir = (EditText) findViewById(R.id.ubi);
        agregar = (Button) findViewById(R.id.agregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = iu.idbusca(Integer.parseInt(id));
                long di = iu.agregaUbi(user,nombre.getText().toString(),dir.getText().toString());

                if(di > 0){
                    Toast.makeText(AgregarDireccion.this, "Agregado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AgregarDireccion.this, listadir.class);
                    i.putExtra(listadir.EXTRA_CODE, id);
                    startActivity(i);
                }else {
                    Toast.makeText(AgregarDireccion.this, "No fue agregada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
