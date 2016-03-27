package com.zenit.bryan.miscontactos;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.zenit.bryan.miscontactos.util.ContactListAdapter;
import com.zenit.bryan.miscontactos.util.Contacto;
import com.zenit.bryan.miscontactos.util.TextChangedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Button btnAgregar;
    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private Button btnAgregar;
    //private List<Contacto> Contactos = new ArrayList<Contacto>(); //se sustituye por ArrayAdapter
    private ArrayAdapter<Contacto> adapter; //Contiene infraestructura de notificaciónes, es mejor que ArrayList
    private ListView contactsListView;
    private ImageView imgViewContacto;
    private int request_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarIU();
        inicializarListaContactos();
        inicializarTabs();
    }


    private void inicializarListaContactos() {
        adapter = new ContactListAdapter(this, new ArrayList<Contacto>());
        contactsListView.setAdapter(adapter);
    }
    private void inicializarIU() {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        contactsListView = (ListView) findViewById(R.id.listView);
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //super.onTextChanged(s, start, before, count);
                btnAgregar = (Button) findViewById(R.id.btnAgregar);
                btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
    }

    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Lista");
        tabHost.addTab(spec);
    }

    public void onClick(View view) {
        agregarContacto(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtDireccion.getText().toString()
        );
        String msg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        btnAgregar.setEnabled(false);
        limpiarCampos();

    }



    private void agregarContacto(String nombre, String telefono, String email, String direccion) {
        Contacto nuevo = new Contacto(nombre, telefono, email, direccion);
        adapter.add(nuevo);
    }

    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtNombre.requestFocus();
    }

    public void onImgClick(View view) {
        Intent intent = null;
        //Verificar la versión de la plataforma
        if(Build.VERSION.SDK_INT < 19){
            //versión JellyBean 4.3 e inferiores
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }else{
            //Kitkat 4.4 y superiores
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(intent, request_code);
    }
}
