package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.container.LoginContainer;
import info.androidhive.slidingmenu.database.DataBaseManager;
import info.androidhive.slidingmenu.database.DbHelper;
import info.androidhive.slidingmenu.model.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	LoginContainer root;

	private DataBaseManager manager;
	private Cursor cursor;
	private SimpleCursorAdapter adapter;
	private Integer indexView = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DbHelper helper = new DbHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();

		// this.root = (LoginContainer)
		// this.getLayoutInflater().inflate(R.layout.activity_login, null);
		try {
			// this.setContentView(root);
			this.setContentView(R.layout.activity_login);
			
			final Button botonRedirectIniciarSesion = (Button) findViewById(R.id.btn_iniciar_sesion);// Al
			botonRedirectIniciarSesion.setVisibility(View.INVISIBLE);

			final Button botonCrearUsuario = (Button) findViewById(R.id.account);//Al dar clic en el boton de id login
            botonCrearUsuario.setVisibility(View.INVISIBLE);
			

			/**
			 * L O G I N
			 * **/
			final Button botonLogin = (Button) findViewById(R.id.login);// Al
																		// dar
																		// clic
																		// en el
																		// boton
																		// de id
																		// login
			botonLogin.setOnClickListener(new View.OnClickListener() {// Se
																		// realiza
																		// lo
																		// siguiente
						@Override
						public void onClick(View v) {

							String usuarioText = ((EditText) findViewById(R.id.userInput))
									.getText().toString();
							String passText = ((EditText) findViewById(R.id.passInput))
									.getText().toString();
							if (usuarioText != null && passText != null && !usuarioText.isEmpty() && !passText.isEmpty()) {

								manager = new DataBaseManager(
										getApplicationContext());

								Usuario user = manager.getUsuario(usuarioText,
										passText);

								if (user.getUserId() != null) {
									
									usuarioText = null;
									passText = null;

									Intent mainActivity = new Intent(
											LoginActivity.this,
											MainActivity.class);
									startActivity(mainActivity);

								} else {
									Toast.makeText(getApplicationContext(),
											"Usuario Incorrecto",
											Toast.LENGTH_SHORT).show();

								}

								/*
								 * if (usuarioText.equals("admin") &&
								 * passText.equals("admin")) { Intent menuView =
								 * new Intent(LoginActivity.this,
								 * SampleActivity.class);
								 * startActivity(menuView); } else {
								 * Toast.makeText(getApplicationContext(),
								 * "Usuario Incorrecto",
								 * Toast.LENGTH_SHORT).show();
								 * 
								 * }
								 */
							}
						}
					});
			
			
			
			
			/**
			 * A C C O U N T
			 * **/
			
			
			 botonCrearUsuario.setOnClickListener(new View.OnClickListener() {//Se realiza lo siguiente
                @Override
                public void onClick(View v) {
                	
                	try{
                    String usuarioText = ((EditText) findViewById(R.id.userInput)).getText().toString();
                    String passText = ((EditText) findViewById(R.id.passInput)).getText().toString();
                    if (usuarioText != null && passText != null && !usuarioText.isEmpty() && !passText.isEmpty()) {

                    	Log.d("myTag", "before db");
                        //Creacion de usuario
                        DataBaseManager manager = new DataBaseManager(LoginActivity.this);
                        manager.addUsuario(usuarioText, passText);

                        //Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();

                        /*Intent menuView = new Intent(CreateUserActivity.this, SampleActivity.class);
                        startActivity(menuView);*/
                        
                        Intent mainActivity = new Intent(
								LoginActivity.this,
								MainActivity.class);
						startActivity(mainActivity);

                    }else{
                        Toast.makeText(getApplicationContext(), "Ingrese todos los campos", Toast.LENGTH_SHORT).show();

                    }
                	}catch(Exception e){
                		e.printStackTrace();
                	}
                }
            });
            

			/*
			 * final Button botonRedirectCrearUsuario = (Button)
			 * findViewById(R.id.crear);//Al dar clic en el boton de id login
			 * botonRedirectCrearUsuario.setOnClickListener(new
			 * View.OnClickListener() {//Se realiza lo siguiente
			 * 
			 * @Override public void onClick(View v) {
			 * 
			 * Intent createUserActivity = new Intent(LoginActivity.this,
			 * CreateUserActivity.class); startActivity(createUserActivity);
			 * 
			 * } });
			 */

			/**
			 * Redirección a creación de usuario
			 * **/
//			final TextView botonRedirectCrearUsuarioUrl = (TextView) findViewById(R.id.url_create_account);// Al
//																											// dar
//																											// clic
//																											// en
//																											// el
//																											// boton
//																											// de
//																											// id
//																											// login
//			botonRedirectCrearUsuarioUrl
//					.setOnClickListener(new View.OnClickListener() {// Se
//																	// realiza
//																	// lo
//																	// siguiente
//						@Override
//						public void onClick(View v) {
//
//							Intent createUserActivity = new Intent(
//									LoginActivity.this,
//									CreateUserActivity.class);
//							startActivity(createUserActivity);
//
//						}
//					});
			
			// login
			
			//here
			
			final Button botonRedirectCrearUsuario = (Button) findViewById(R.id.btn_create_user);
			
			botonRedirectCrearUsuario.setOnClickListener(new View.OnClickListener() {// Se
				// realiza
				// lo
				// siguiente
				@Override
				public void onClick(View v) {
					
					((EditText) findViewById(R.id.userInput)).setText("");
					((EditText) findViewById(R.id.passInput)).setText("");
					
					TextView urlOlvidastePass = (TextView) findViewById(R.id.olvidaste_pass);
					urlOlvidastePass.setVisibility(View.INVISIBLE);
				
					botonRedirectCrearUsuario.setVisibility(View.INVISIBLE);
					botonRedirectIniciarSesion.setVisibility(View.VISIBLE);
					

					botonLogin.setVisibility(View.INVISIBLE);
					botonCrearUsuario.setVisibility(View.VISIBLE);
				
				}
			});
			
			
			botonRedirectIniciarSesion.setOnClickListener(new View.OnClickListener() {// Se
				// realiza
				// lo
				// siguiente
				@Override
				public void onClick(View v) {
					
					((EditText) findViewById(R.id.userInput)).setText("");
					((EditText) findViewById(R.id.passInput)).setText("");
					TextView urlOlvidastePass = (TextView) findViewById(R.id.olvidaste_pass);
					urlOlvidastePass.setVisibility(View.VISIBLE);

					botonRedirectCrearUsuario.setVisibility(View.VISIBLE);
					botonRedirectIniciarSesion.setVisibility(View.INVISIBLE);
					
					botonLogin.setVisibility(View.VISIBLE);
					botonCrearUsuario.setVisibility(View.INVISIBLE);

				}
				});

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public String cambioView(){
		indexView *= -1;
		if(indexView.equals(new Integer(-1))){
			//crear cuenta
			return "s_create_account";
		}
		else{
			if(indexView.equals(new Integer(1))){
				return "s_iniciar_sesion";
			}
		}
		return "";
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.sample, menu);
		return true;
	}

	/*
	 * public void toggleMenu(View v){ this.root.toggleMenu(); }
	 */

}