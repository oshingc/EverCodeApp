package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.database.DataBaseManager;
import info.androidhive.slidingmenu.database.DbHelper;
import info.androidhive.slidingmenu.model.Alternativa;
import info.androidhive.slidingmenu.model.Nivel;
import info.androidhive.slidingmenu.model.Pregunta;
import info.androidhive.slidingmenu.model.TipoPregunta;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class ExercisesFragment extends Fragment {

	private List<Pregunta> preguntas = new ArrayList<Pregunta>();
	private Integer idPreguntaActual = 0;
	private Pregunta preguntaActual;
	private Alternativa alternativa = null;
	private Handler progressBarHandler = new Handler();
	private Integer opcion;
	private String opcionRespuesta="";
	private Boolean esIncorrecta = null;
	private Integer puntaje = 0;

	public ExercisesFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_exercises,
				container, false);

		return rootView;

	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

		// LLENAR PREGUNTAS CON LA BD

		DbHelper helper = new DbHelper(getView().getContext());
		SQLiteDatabase db = helper.getWritableDatabase();

		DataBaseManager manager = new DataBaseManager(getView().getContext());

		preguntas = manager.getPreguntas(new Nivel(1), new TipoPregunta(1));
		
		nivel1();
		//nivel2();
		
		

	}
	
	private void nivel1(){
		
		RelativeLayout rl1 = (RelativeLayout) getView().findViewById(R.id.exercises_image);
		rl1.setVisibility(View.INVISIBLE);
		
		TextView t = (TextView) getView().findViewById(R.id.p1);
		t.setVisibility(View.INVISIBLE);
		
		ProgressBar pb = (ProgressBar) getView().findViewById(R.id.progressBar1);
		pb.setVisibility(View.INVISIBLE);
		
		TableLayout tt = (TableLayout)getView().findViewById(R.id.tabla);
		tt.setVisibility(View.INVISIBLE);
		
		Button n1 = (Button) getView().findViewById(R.id.n1);
		n1.setOnClickListener(new View.OnClickListener() {// Se
			@Override
			public void onClick(View v) {
				
				RelativeLayout rl1 = (RelativeLayout) getView().findViewById(R.id.exercises_niveles);
				rl1.setVisibility(View.INVISIBLE);
				
				RelativeLayout rl2 = (RelativeLayout) getView().findViewById(R.id.exercises_image);
				rl2.setVisibility(View.VISIBLE);
				
				TextView t = (TextView) getView().findViewById(R.id.p1);
				t.setVisibility(View.VISIBLE);
				
				ProgressBar pb = (ProgressBar) getView().findViewById(R.id.progressBar1);
				pb.setVisibility(View.VISIBLE);
				
				TableLayout tt = (TableLayout)getView().findViewById(R.id.tabla);
				tt.setVisibility(View.VISIBLE);
				
				botonContinuarPuntaje();

				llenarPreguntas();
				progressBar();
				botonCalificar();

				evaluarA1();
				evaluarA2();
				evaluarA3();
				evaluarA4();
			}
		});
		
	}
	
	private void nivel2(){
		Button n2 = (Button) getView().findViewById(R.id.n2);
		n2.setOnClickListener(new View.OnClickListener() {// Se
			@Override
			public void onClick(View v) {
				botonContinuarPuntaje();

				llenarPreguntas();
				progressBar();
				botonCalificar();

				evaluarA1();
				evaluarA2();
				evaluarA3();
				evaluarA4();
			}
		});
	}
	
	private void botonContinuarPuntaje(){
		Button bb = (Button) getView().findViewById(R.id.btnCon);
		bb.setVisibility(View.INVISIBLE);
	}

	private void progressBar() {// 3441

		ProgressBar progressBar = (ProgressBar) getView().findViewById(
				R.id.progressBar1);
		progressBar.setProgress(0);
	}

	private void progressBarIncremento() {// 3441

		ProgressBar progressBar = (ProgressBar) getView().findViewById(
				R.id.progressBar1);
		progressBar.setProgress(progressBar.getProgress() + 10);
	}

	private void botonCalificar() {// 3441

		Log.d("myTag", "calif");

		Button btnCalificar = (Button) getView()
				.findViewById(R.id.btnCalificar);
		btnCalificar.setVisibility(View.VISIBLE);
		btnCalificar.setClickable(false);

		btnCalificar.setBackgroundColor(Color.rgb(190, 195, 196));

		btnCalificar.setOnClickListener(new View.OnClickListener() {// Se
					@Override
					public void onClick(View v) {

						Log.d("myTag",
								(String) getView().getContext().getText(
										R.id.btnCalificar));

						Button btn = (Button) getView().findViewById(
								R.id.btnCalificar);
						Log.d("elboton", (String) btn.getText());

						if (alternativa != null
								&& btn.getText().equals("Calificar")) {
							


							if (alternativa.getEsRespuesta() == null) {
								Log.d("myTag", "es null");
							}
							if (alternativa.getEsRespuesta() != null
									&& alternativa.getEsRespuesta().equals(
											Boolean.TRUE)) {
								puntaje +=1;
								alternativa = null;

								esIncorrecta = Boolean.FALSE;
								
								WebView webView = (WebView) getView().findViewById(R.id.webView);
								   webView.getSettings().setJavaScriptEnabled(true);
								   webView.setVisibility(View.VISIBLE);
								   String url = "file:///android_asset/mensaje_success.html";
								   webView.loadUrl(url);
								
								bloquearAlternativas();
								// agregamos puntos
								//

								Button b2 = (Button) getView().findViewById(
										R.id.btnCalificar);
								//b2.setVisibility(View.GONE);
								b2.setVisibility(View.VISIBLE);
								b2.setText("Continuar");
								b2.setBackgroundColor(Color.rgb(19, 164, 145));// turquesa
								
								
	//								manejo de thread
								
								new Thread(new Runnable() {
									  public void run() {
						 
										  // your computer is too fast, sleep 1 second
										  try {
											Thread.sleep(1000);
										  } catch (InterruptedException e) {
											e.printStackTrace();
										  }
						 
										  // Update the progress bar
										  progressBarHandler.post(new Runnable() {
											public void run() {
											  progressBarIncremento();
											}
										  });
						 
									  }
								       }).start();
								//manejo de thread
								

								
								
								//progressBarIncremento();

							} else {
								//Rpta incorrecta
								Button b2 = (Button) getView().findViewById(
										R.id.btnCalificar);
								//b2.setVisibility(View.GONE);
								b2.setVisibility(View.VISIBLE);
								b2.setText("Continuar");
								b2.setBackgroundColor(Color.rgb(19, 164, 145));// turquesa
								
								respuestaIncorrecta();
								esIncorrecta = Boolean.TRUE;
								
	//								manejo de thread
								
								new Thread(new Runnable() {
									  public void run() {
						 
										  // your computer is too fast, sleep 1 second
										  try {
											Thread.sleep(1000);
										  } catch (InterruptedException e) {
											e.printStackTrace();
										  }
						 
										  // Update the progress bar
										  progressBarHandler.post(new Runnable() {
											public void run() {
											  progressBarIncremento();
											}
										  });
						 
									  }
								       }).start();
								//manejo de thread
							}
						}// fin de if continuar
						else {

							if (btn.getText().equals("Continuar")) {
								
							
								
								
								if(esIncorrecta!=null && esIncorrecta.equals(Boolean.TRUE)){
									
									alternativasGrises();
									marcarRespuestaCorrecta();
									esIncorrecta = null;
								}
								else{

									if (preguntas.size() > idPreguntaActual + 1) {
										idPreguntaActual++;
										Button b = (Button) getView().findViewById(
												R.id.btnCalificar);
										
										WebView webView = (WebView) getView().findViewById(R.id.webView);
										webView.setVisibility(View.INVISIBLE);
	
										b.setClickable(false);
										b.setBackgroundColor(Color.rgb(190, 195,
												196));// grisclaro
										b.setText("Calificar");
	
										llenarPreguntas();
									} else {
										vistaPuntaje();
										// fin de formulario
										// sleep 2 seconds, so that you can see the 100%
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
					 
										// close the progress bar dialog
	//									ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progressBar1);
	//									progressBar.dismiss();
									}
								}

							}
						}

					}
				});
	}
	
	private void vistaPuntaje(){
		
		WebView webView = (WebView) getView().findViewById(R.id.webView);
		webView.setVisibility(View.INVISIBLE);
		
		RelativeLayout rl1 = (RelativeLayout) getView().findViewById(R.id.exercises_image);
		rl1.setVisibility(View.INVISIBLE);
		
		TableLayout tl = (TableLayout) getView().findViewById(R.id.tabla);
		tl.setVisibility(View.INVISIBLE);
		
		Button bb = (Button) getView().findViewById(R.id.btnCon);
		bb.setVisibility(View.VISIBLE);

		bb.setOnClickListener(new View.OnClickListener() {// Se
					@Override
					public void onClick(View v) {
						//regresar a la vista inicial
					}
		});
				
		
		final WebView webViewP = (WebView) getView().findViewById(R.id.puntaje);
		webViewP.getSettings().setJavaScriptEnabled(true);
		 //webView.loadUrl("javascript:myinfo('"+opcionRespuesta+"')");
		if(puntaje>=5){
			 String url = "file:///android_asset/puntaje_sup.html";
			 webViewP.loadUrl(url);
			 webViewP.setWebViewClient(new WebViewClient(){
				    public void onPageFinished(WebView view, String url){   
				        webViewP.loadUrl("javascript:puntaje('"+puntaje+"')");
				    }           
				});
		}else{
			 String url = "file:///android_asset/puntaje_inf.html";
			 webViewP.loadUrl(url);
			 webViewP.setWebViewClient(new WebViewClient(){
				    public void onPageFinished(WebView view, String url){   
				        webViewP.loadUrl("javascript:puntaje('"+puntaje+"')");
				    }           
				});
		}
		
	}
	
	
	private void marcarRespuestaCorrecta(){
		
		if(preguntaActual.getA1().getEsRespuesta().equals(Boolean.TRUE)){
			Button b1 = (Button)getView().findViewById(R.id.a1);
			b1.setBackgroundColor(Color.rgb(4, 119, 175));//azul
		}
		if(preguntaActual.getA2().getEsRespuesta().equals(Boolean.TRUE)){
			Button b2 = (Button)getView().findViewById(R.id.a2);
			b2.setBackgroundColor(Color.rgb(4, 119, 175));//azul
		}
		if(preguntaActual.getA3().getEsRespuesta().equals(Boolean.TRUE)){
			Button b3 = (Button)getView().findViewById(R.id.a3);
			b3.setBackgroundColor(Color.rgb(4, 119, 175));//azul
		}
		if(preguntaActual.getA4().getEsRespuesta().equals(Boolean.TRUE)){
			Button b4 = (Button)getView().findViewById(R.id.a4);
			b4.setBackgroundColor(Color.rgb(4, 119, 175));//azul
		}
		final WebView webView = (WebView) getView().findViewById(R.id.webView);
		 webView.getSettings().setJavaScriptEnabled(true);
		 //webView.loadUrl("javascript:myinfo('"+opcionRespuesta+"')");
		 String url = "file:///android_asset/mensaje_info.html";
		 webView.loadUrl(url);
		 webView.setWebViewClient(new WebViewClient(){
			    public void onPageFinished(WebView view, String url){   
			        webView.loadUrl("javascript:myinfo('"+opcionRespuesta+"')");
			    }           
			});
		
		
	}
	
	private void alternativasGrises(){
		
		Button b = (Button) getView().findViewById(R.id.a1);
		b.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

		Button b1 = (Button) getView().findViewById(R.id.a2);
		b1.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro
		

		Button b2 = (Button) getView().findViewById(R.id.a3);
		b2.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

		Button b3 = (Button) getView().findViewById(R.id.a4);
		b3.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

		
	}
	
	private void respuestaIncorrecta(){
		if(opcion==1){
			Button b1 = (Button)getView().findViewById(R.id.a1);
			b1.setBackgroundColor(Color.RED);
		}
		if(opcion==2){
			Button b2 = (Button)getView().findViewById(R.id.a2);
			b2.setBackgroundColor(Color.RED);
		}
		if(opcion==3){
			Button b3 = (Button)getView().findViewById(R.id.a3);
			b3.setBackgroundColor(Color.RED);
		}
		if(opcion==4){
			Button b4 = (Button)getView().findViewById(R.id.a4);
			b4.setBackgroundColor(Color.RED);
		}
		bloquearAlternativas();
		
		WebView webView = (WebView) getView().findViewById(R.id.webView);
		webView.setVisibility(View.VISIBLE);
		webView.getSettings().setJavaScriptEnabled(true);
		String url = "file:///android_asset/mensaje_error.html";
		webView.loadUrl(url);
	}

	//

	@SuppressLint("ResourceAsColor")
	private void evaluarA1() {

		Button a1 = (Button) getView().findViewById(R.id.a1);
		a1.setOnClickListener(new View.OnClickListener() {// Se
			@Override
			public void onClick(View v) {
				// Log.d("myTag", "click a");
				alternativa = preguntaActual.getA1();

				Button b = (Button) getView().findViewById(R.id.a1);
				b.setBackgroundColor(Color.rgb(4, 119, 175));// azul

				Button b1 = (Button) getView().findViewById(R.id.a2);
				b1.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b2 = (Button) getView().findViewById(R.id.a3);
				b2.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b3 = (Button) getView().findViewById(R.id.a4);
				b3.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro
				//
				Button btnCalificar = (Button) getView().findViewById(
						R.id.btnCalificar);
				btnCalificar.setVisibility(View.VISIBLE);
				btnCalificar.setClickable(true);
				btnCalificar.setBackgroundColor(Color.rgb(19, 164, 145));// turquesa
				
				opcion = 1;

			}
		});
	}

	@SuppressLint("ResourceAsColor")
	private void evaluarA2() {

		Button a2 = (Button) getView().findViewById(R.id.a2);
		a2.setOnClickListener(new View.OnClickListener() {// Se
			@Override
			public void onClick(View v) {

				alternativa = preguntaActual.getA2();

				Button b = (Button) getView().findViewById(R.id.a1);
				b.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b1 = (Button) getView().findViewById(R.id.a2);
				b1.setBackgroundColor(Color.rgb(4, 119, 175));//azul
				

				Button b2 = (Button) getView().findViewById(R.id.a3);
				b2.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b3 = (Button) getView().findViewById(R.id.a4);
				b3.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				// Button btnCalificarClaro = (Button)
				// getView().findViewById(R.id.btnCalificarClaro);
				// btnCalificarClaro.setVisibility(View.INVISIBLE);

				Button btnCalificar = (Button) getView().findViewById(
						R.id.btnCalificar);
				btnCalificar.setVisibility(View.VISIBLE);
				btnCalificar.setClickable(true);
				btnCalificar.setBackgroundColor(Color.rgb(19, 164, 145));//turquesa

				opcion = 2;
			}
		});
	}

	@SuppressLint("ResourceAsColor")
	private void evaluarA3() {

		Button a3 = (Button) getView().findViewById(R.id.a3);
		a3.setOnClickListener(new View.OnClickListener() {// Se
			@Override
			public void onClick(View v) {

				alternativa = preguntaActual.getA3();

				Button b = (Button) getView().findViewById(R.id.a1);
				b.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b1 = (Button) getView().findViewById(R.id.a2);
				b1.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b2 = (Button) getView().findViewById(R.id.a3);
				b2.setBackgroundColor(Color.rgb(4, 119, 175));//azul

				Button b3 = (Button) getView().findViewById(R.id.a4);
				b3.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				// Button btnCalificarClaro = (Button)
				// getView().findViewById(R.id.btnCalificarClaro);
				// btnCalificarClaro.setVisibility(View.INVISIBLE);

				Button btnCalificar = (Button) getView().findViewById(
						R.id.btnCalificar);
				btnCalificar.setVisibility(View.VISIBLE);
				btnCalificar.setClickable(true);
				btnCalificar.setBackgroundColor(Color.rgb(19, 164, 145));//turquesa

				opcion = 3;
			}
		});
	}

	@SuppressLint("ResourceAsColor")
	private void evaluarA4() {

		Button a4 = (Button) getView().findViewById(R.id.a4);
		a4.setOnClickListener(new View.OnClickListener() {// Se
			@Override
			public void onClick(View v) {

				alternativa = preguntaActual.getA4();

				Button b = (Button) getView().findViewById(R.id.a1);
				b.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b1 = (Button) getView().findViewById(R.id.a2);
				b1.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b2 = (Button) getView().findViewById(R.id.a3);
				b2.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro

				Button b3 = (Button) getView().findViewById(R.id.a4);
				b3.setBackgroundColor(Color.rgb(4, 119, 175));//azul

				Button btnCalificar = (Button) getView().findViewById(
						R.id.btnCalificar);
				btnCalificar.setVisibility(View.VISIBLE);
				btnCalificar.setClickable(true);
				btnCalificar.setBackgroundColor(Color.rgb(19, 164, 145));//turquesa

				opcion = 4;
			}
		});
	}
	
	
	private void bloquearAlternativas() {
		if (preguntas != null) {

			Button b1 = (Button) getActivity().findViewById(R.id.a1);
			b1.setClickable(false);

			Button b2 = (Button) getActivity().findViewById(R.id.a2);
			b2.setClickable(false);

			Button b3 = (Button) getActivity().findViewById(R.id.a3);
			b3.setClickable(false);

			Button b4 = (Button) getActivity().findViewById(R.id.a4);
			b4.setClickable(false);
		} else {
			
		}
	}
	
	private void hallarRespuesta(){
		if(preguntaActual.getA1().getEsRespuesta().equals(Boolean.TRUE)){
			opcionRespuesta = "A";
		}
		if(preguntaActual.getA2().getEsRespuesta().equals(Boolean.TRUE)){
			opcionRespuesta = "B";
		} 
		if(preguntaActual.getA3().getEsRespuesta().equals(Boolean.TRUE)){
			opcionRespuesta = "C";
		} 
		if(preguntaActual.getA4().getEsRespuesta().equals(Boolean.TRUE)){
			opcionRespuesta = "D";
		} 
	}

	public void llenarPreguntas() {
		if (preguntas != null) {
			Log.d("myTag", idPreguntaActual + "");
			preguntaActual = preguntas.get(idPreguntaActual);
			
			hallarRespuesta();

			TextView texto = (TextView) getActivity().findViewById(R.id.p1);
			texto.setText(preguntaActual.getEnunciado());

			Button b1 = (Button) getActivity().findViewById(R.id.a1);
			b1.setText(preguntaActual.getA1().getAlternativa());
			b1.setBackgroundColor(Color.rgb(117, 133, 135));//gris oscuro
			b1.setClickable(true);

			Button b2 = (Button) getActivity().findViewById(R.id.a2);
			b2.setText(preguntaActual.getA2().getAlternativa());
			b2.setBackgroundColor(Color.rgb(117, 133, 135));
			b2.setClickable(true);

			Button b3 = (Button) getActivity().findViewById(R.id.a3);
			b3.setText(preguntaActual.getA3().getAlternativa());
			b3.setBackgroundColor(Color.rgb(117, 133, 135));
			b3.setClickable(true);

			Button b4 = (Button) getActivity().findViewById(R.id.a4);
			b4.setText(preguntaActual.getA4().getAlternativa());
			b4.setBackgroundColor(Color.rgb(117, 133, 135));
			b4.setClickable(true);
		} else {
			Log.d("myTag", "no hay Preguntas");
		}
	}

	// Setters y Getters

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

}
