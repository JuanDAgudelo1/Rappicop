package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.NotificationService;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Oferta;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;

public class PedidosPorDomiciliario extends AppCompatActivity {

    Logica iu = new Logica(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_por_domiciliario);

        TextView usu = findViewById(R.id.usuario);
        LinearLayout layout = findViewById(R.id.layoutOfertaAceptada);
        TextView txtOfertaAceptada = findViewById(R.id.txtOfertaAceptada);
        Button btnCambiarEstado = findViewById(R.id.btnCambiarEstado);
        Button btnVerMapa = findViewById(R.id.btnVerMapa);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        usu.setText(user.getUsuario());

        ArrayList<OfertaAceptada> listaofertas = iu.consultaPedidosPorDomicilario(user.getUsuario());

        if (!listaofertas.isEmpty()) {
            layout.setVisibility(View.VISIBLE);

            OfertaAceptada ofertaAceptada = listaofertas.get(0);

            String mensajeBtnCambiarEstado = "CAMBIAR ESTADO";
            switch (ofertaAceptada.getEstado()) {
                case "aceptada":
                    mensajeBtnCambiarEstado += ":\n ENTREGANDO PEDIDO";
                    break;
                case "entregando":
                    mensajeBtnCambiarEstado += ":\n FINALIZAR PEDIDO";
                    break;
            }
            btnCambiarEstado.setText(mensajeBtnCambiarEstado);

            String mensaje = "Ubicación: " + ofertaAceptada.getUbicacion() +
                    "\nCliente: " + ofertaAceptada.getCliente() +
                    "\nCantidad: " + ofertaAceptada.getCantidad() +
                    "\nPrecio: " + ofertaAceptada.getPrecio();
            txtOfertaAceptada.setText(mensaje);

            Oferta oferta = iu.consultaOfertaPorNombre(ofertaAceptada.getOferta());

            btnCambiarEstado.setOnClickListener(click -> {
                switch (ofertaAceptada.getEstado()) {
                    case "aceptada":
                        boolean modificado = iu.modificarEstadoOferta(ofertaAceptada.getId(), "entregando", user.getUsuario());
                        if (modificado) {
                            btnCambiarEstado.setText("CAMBIAR ESTADO:\n FINALIZAR PEDIDO");
                            ofertaAceptada.setEstado("entregando");

                            Intent notification = new Intent(PedidosPorDomiciliario.this, NotificationService.class);
                            notification.putExtra("title", "Su oferta está en camino");
                            notification.putExtra("content", ofertaAceptada.getCliente() + " su oferta ya se encuentra en camino...");
                            startService(notification);

                            Toast.makeText(PedidosPorDomiciliario.this, "Se modifica estado del pedido", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case "entregando":
                        boolean modificadoEstado = iu.modificarEstadoOferta(ofertaAceptada.getId(), "completada", user.getUsuario());
                        if (modificadoEstado) {
                            Intent notification = new Intent(PedidosPorDomiciliario.this, NotificationService.class);
                            notification.putExtra("title", "Oferta finalizada");
                            notification.putExtra("content", ofertaAceptada.getCliente() + ", gracias por su oferta, que la disfrute!");
                            startService(notification);
                            finish();

                            Toast.makeText(PedidosPorDomiciliario.this, "Se modifica estado del pedido", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            });

            btnVerMapa.setOnClickListener(click -> {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("origin", ofertaAceptada.getUbicacion())
                        .appendQueryParameter("destination", oferta.getUbicacion());
                String url = builder.build().toString();

                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse(url));
                startActivity(intentMapa);
            });
        }
    }
}