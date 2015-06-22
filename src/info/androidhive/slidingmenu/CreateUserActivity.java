package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.database.DataBaseManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by oshin on 17/05/15.
 */
public class CreateUserActivity extends Activity {

    //private IUsuario iUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // this.root = (LoginContainer) this.getLayoutInflater().inflate(R.layout.activity_login, null);
        try {
            //this.setContentView(root);
            this.setContentView(R.layout.activity_create_user);



            final Button botonCrearUsuario = (Button) findViewById(R.id.cu_registrar);//Al dar clic en el boton de id login
            botonCrearUsuario.setOnClickListener(new View.OnClickListener() {//Se realiza lo siguiente
                @Override
                public void onClick(View v) {
                    String usuarioText = ((EditText) findViewById(R.id.cu_userInput)).getText().toString();
                    String passText = ((EditText) findViewById(R.id.cu_passInput)).getText().toString();
                    if (usuarioText != null && passText != null && !usuarioText.isEmpty() && !passText.isEmpty()) {

                        //Creacion de usuario
                        DataBaseManager manager = new DataBaseManager(CreateUserActivity.this);
                        manager.addUsuario(usuarioText, passText);

                        Toast.makeText(getApplicationContext(), "Creación de user", Toast.LENGTH_SHORT).show();

                        /*Intent menuView = new Intent(CreateUserActivity.this, SampleActivity.class);
                        startActivity(menuView);*/
                        
                        Intent mainActivity = new Intent(
								CreateUserActivity.this,
								MainActivity.class);
						startActivity(mainActivity);

                    }else{
                        Toast.makeText(getApplicationContext(), "Ingrese todos los campos", Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }catch(Exception e){
            e.printStackTrace();

        }
    }





//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.sample, menu);
//        return true;
//    }

}
