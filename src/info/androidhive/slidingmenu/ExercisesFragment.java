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
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ExercisesFragment extends Fragment {

	private List<Pregunta> preguntas = new ArrayList<Pregunta>();
	private Integer idPreguntaActual = 0;
	private Pregunta preguntaActual;
	private Alternativa alternativa = null;

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

		/*
		 * Pregunta p1 = new Pregunta(1,"¿Qué es un Algoritmo?", new
		 * Alternativa(
		 * "A. Un conjunto de pasos ordenados a seguir que cumplen un fin u objetivo."
		 * , Boolean.TRUE), new
		 * Alternativa("B. Es una estructura de control oshina.",
		 * Boolean.FALSE), new Alternativa("C. Es un dispositivo de red.",
		 * Boolean.FALSE), new
		 * Alternativa("D. Es un operación matemática definida por John Napier."
		 * , Boolean.FALSE),"Nivel 1");
		 * 
		 * Pregunta p2 = new Pregunta(2,"¿Qué es pseudocódigo?", new
		 * Alternativa("A. Es un lenguaje de programación a nivel máquina.",
		 * Boolean.FALSE), new
		 * Alternativa("B. Es un lenguaje de especificación de algoritmos.",
		 * Boolean.TRUE), new Alternativa("C. Es una línea de código.",
		 * Boolean.FALSE), new Alternativa("D. Es una sentencia de código.",
		 * Boolean.FALSE),"Nivel 1");
		 * 
		 * preguntas.add(p1); preguntas.add(p2);
		 */

		// LLENAR PREGUNTAS CON LA BD

		DbHelper helper = new DbHelper(getView().getContext());
		SQLiteDatabase db = helper.getWritableDatabase();

		DataBaseManager manager = new DataBaseManager(getView().getContext());

		preguntas = manager.getPreguntas(new Nivel(1), new TipoPregunta(1));

		llenarPreguntas();
		progressBar();
		botonCalificar();

		evaluarA1();
		evaluarA2();
		evaluarA3();
		evaluarA4();

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

	/*
	 * private void botonContinuar(){//3441
	 * 
	 * Button btnSgte = (Button) getView().findViewById(R.id.btnSgte);
	 * btnSgte.setVisibility(View.INVISIBLE);
	 * btnSgte.setBackgroundColor(Color.rgb(161, 183, 186));
	 * 
	 * 
	 * 
	 * btnSgte.setOnClickListener(new View.OnClickListener() {// Se
	 * 
	 * @Override public void onClick(View v) {
	 * 
	 * Log.d("myTAg","btnsgte");
	 * 
	 * if(preguntas.size()>idPreguntaActual+1){ idPreguntaActual++;
	 * 
	 * 
	 * alternativa = null;
	 * 
	 * llenarPreguntas();
	 * 
	 * 
	 * Button b = (Button) getView().findViewById(R.id.btnSgte);
	 * b.setVisibility(View.INVISIBLE);
	 * 
	 * Button b0 = (Button) getView().findViewById(R.id.a1);
	 * //b0.setBackgroundColor(Color.rgb(117, 133, 135));
	 * b0.setVisibility(View.VISIBLE);
	 * b0.getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
	 * 
	 * 
	 * Button b1 = (Button) getView().findViewById(R.id.a2);
	 * b1.setBackgroundColor(Color.rgb(117, 133, 135));
	 * b1.setVisibility(View.VISIBLE);
	 * 
	 * Button b2 = (Button) getView().findViewById(R.id.a3);
	 * b2.setBackgroundColor(Color.rgb(117, 133, 135));
	 * b2.setVisibility(View.VISIBLE);
	 * 
	 * Button b3 = (Button) getView().findViewById(R.id.a4);
	 * b3.setBackgroundColor(Color.rgb(117, 133, 135));
	 * b3.setVisibility(View.VISIBLE);
	 * 
	 * Button bt = (Button) getView().findViewById(R.id.btnCalificar);
	 * bt.setVisibility(View.VISIBLE); bt.setClickable(false);
	 * bt.setBackgroundColor(Color.rgb(161, 183, 186));
	 * 
	 * }else{ //fin de formulario }
	 * 
	 * } }); }
	 */

	// private void botonCalificarClaro(){//3441
	//
	// Button btnCalificarClaro = (Button)
	// getView().findViewById(R.id.btnCalificarClaro);
	// btnCalificarClaro.setVisibility(View.VISIBLE);
	// btnCalificarClaro.setClickable(false);
	//
	// }

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
								alternativa = null;
								Toast.makeText(getView().getContext(),
										"Respuesta Correcta",
										Toast.LENGTH_SHORT).show();
								
								bloquearAlternativas();
								// agregamos puntos
								//

								Button b2 = (Button) getView().findViewById(
										R.id.btnCalificar);
								b2.setVisibility(View.VISIBLE);
								b2.setText("Continuar");
								b2.setBackgroundColor(Color.rgb(19, 164, 145));// turquesa

								progressBarIncremento();

							} else {
								Toast.makeText(getView().getContext(),
										"Respuesta InCorrecta",
										Toast.LENGTH_SHORT).show();
							}
						}// fin de if continuar
						else {

							Log.d("myTag", "else");
							if (btn.getText().equals("Continuar")) {

								Log.d("myTag", "yep es continuar");
								Log.d("myTag", (String) getView().getContext()
										.getText(R.id.btnCalificar));

								if (preguntas.size() > idPreguntaActual + 1) {
									idPreguntaActual++;
									Button b = (Button) getView().findViewById(
											R.id.btnCalificar);

									b.setClickable(false);
									b.setBackgroundColor(Color.rgb(190, 195,
											196));// grisclaro
									b.setText("Calificar");

									llenarPreguntas();
								} else {
									// fin de formulario
								}

							}
						}

					}
				});
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
				// Button btnCalificarClaro = (Button)
				// getView().findViewById(R.id.btnCalificarClaro);
				// btnCalificarClaro.setVisibility(View.INVISIBLE);

				Button btnCalificar = (Button) getView().findViewById(
						R.id.btnCalificar);
				btnCalificar.setVisibility(View.VISIBLE);
				btnCalificar.setClickable(true);
				btnCalificar.setBackgroundColor(Color.rgb(19, 164, 145));// turquesa

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

				// Log.d("myTag", "click b");
				// if(preguntaActual.getA2().getEsRespuesta()==null){
				// Log.d("myTag","es null");
				// }
				//
				// if(preguntaActual.getA2().getEsRespuesta().equals(Boolean.TRUE)){
				// Toast.makeText(getView().getContext(),
				// "Respuesta Correcta",
				// Toast.LENGTH_SHORT).show();
				// Button b = (Button) getView().findViewById(R.id.btnSgte);
				// b.setVisibility(View.VISIBLE);
				//
				// progressBarIncremento();
				//
				// // if(preguntas.size()>idPreguntaActual+1){
				// // idPreguntaActual++;
				// // llenarPreguntas();
				// // }else{
				// // //fin de formulario
				// // }
				// }else{
				// Toast.makeText(getView().getContext(),
				// "Respuesta InCorrecta",
				// Toast.LENGTH_SHORT).show();
				// }
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

				// Log.d("myTag", "click c");
				// if(preguntaActual.getA3().getEsRespuesta()==null){
				// Log.d("myTag","es null");
				// }
				// if(preguntaActual.getA3().getEsRespuesta().equals(Boolean.TRUE)){
				// Toast.makeText(getView().getContext(),
				// "Respuesta Correcta",
				// Toast.LENGTH_SHORT).show();
				// Button b = (Button) getView().findViewById(R.id.btnSgte);
				// b.setVisibility(View.VISIBLE);
				//
				// progressBarIncremento();
				//
				// // if(preguntas.size()>idPreguntaActual+1){
				// // idPreguntaActual++;
				// // llenarPreguntas();
				// // }else{
				// // //fin de formulario
				// // }
				// }else{
				// Toast.makeText(getView().getContext(),
				// "Respuesta InCorrecta",
				// Toast.LENGTH_SHORT).show();
				// }
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

				// Button btnCalificarClaro = (Button)
				// getView().findViewById(R.id.btnCalificarClaro);
				// btnCalificarClaro.setVisibility(View.INVISIBLE);

				Button btnCalificar = (Button) getView().findViewById(
						R.id.btnCalificar);
				btnCalificar.setVisibility(View.VISIBLE);
				btnCalificar.setClickable(true);
				btnCalificar.setBackgroundColor(Color.rgb(19, 164, 145));//turquesa

				// Log.d("myTag", "click d");
				// if(preguntaActual.getA4().getEsRespuesta()==null){
				// Log.d("myTag","es null");
				// }
				// if(preguntaActual.getA4().getEsRespuesta().equals(Boolean.TRUE)){
				// Toast.makeText(getView().getContext(),
				// "Respuesta Correcta",
				// Toast.LENGTH_SHORT).show();
				// Button b = (Button) getView().findViewById(R.id.btnSgte);
				// b.setVisibility(View.VISIBLE);
				//
				// progressBarIncremento();
				//
				// // if(preguntas.size()>idPreguntaActual+1){
				// // idPreguntaActual++;
				// // llenarPreguntas();
				// // }else{
				// // //fin de formulario
				// // }
				// }else{
				// Toast.makeText(getView().getContext(),
				// "Respuesta InCorrecta",
				// Toast.LENGTH_SHORT).show();
				// }
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

	public void llenarPreguntas() {
		if (preguntas != null) {
			Log.d("myTag", idPreguntaActual + "");
			preguntaActual = preguntas.get(idPreguntaActual);

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
