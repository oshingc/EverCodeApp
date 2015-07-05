package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.model.Alternativa;
import info.androidhive.slidingmenu.model.Pregunta;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExercisesFragment extends Fragment {
	
	private List<Pregunta> preguntas = new ArrayList<Pregunta>();
	private Integer idPreguntaActual = 0;
	private Pregunta preguntaActual;

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
	        
	        Pregunta p1 = new Pregunta(1,"¿Qué es un Algoritmo?",
	        		new Alternativa("A. Un conjunto de pasos ordenados a seguir que cumplen un fin u objetivo.",
	        				Boolean.TRUE),
	        		new Alternativa("B. Es una estructura de control oshina.",
	        				Boolean.FALSE),
	        		new Alternativa("C. Es un dispositivo de red.",
	        				Boolean.FALSE),
	        		new Alternativa("D. Es un operación matemática definida por John Napier.",
	        				Boolean.FALSE),"Nivel 1");
	        
	        Pregunta p2 = new Pregunta(2,"¿Qué es pseudocódigo?",
	        		new Alternativa("A. Es un lenguaje de programación a nivel máquina.",
	        				Boolean.FALSE),
	        		new Alternativa("B. Es un lenguaje de especificación de algoritmos.",
	        				Boolean.TRUE),
	        		new Alternativa("C. Es una línea de código.",
	        				Boolean.FALSE),
	        		new Alternativa("D. Es una sentencia de código.",
	        				Boolean.FALSE),"Nivel 1");
	        
	        preguntas.add(p1);
	        preguntas.add(p2);
	        
	        llenarPreguntas();
	 
	        evaluarA1();
			evaluarA2();
			evaluarA3();
			evaluarA4();

	    }
	
	private void evaluarA1(){
		
		Button a1 = (Button) getView().findViewById(R.id.a1);
		a1.setOnClickListener(new View.OnClickListener() {// Se
					@Override
					public void onClick(View v) {
						Log.d("myTag", "click a");
						if(preguntaActual.getA1().getEsRespuesta().equals(Boolean.TRUE)){
							Toast.makeText(getView().getContext(),
									"Respuesta Correcta",
									Toast.LENGTH_SHORT).show();
							if(preguntas.size()>idPreguntaActual+1){
								idPreguntaActual++;
								llenarPreguntas();
							}else{
								//fin de formulario
							}
							
						}else{
							Toast.makeText(getView().getContext(),
									"Respuesta InCorrecta",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	
	private void evaluarA2(){
		
		Button a2 = (Button)  getView().findViewById(R.id.a2);
		a2.setOnClickListener(new View.OnClickListener() {// Se
					@Override
					public void onClick(View v) {
						Log.d("myTag", "click b");
						if(preguntaActual.getA2().getEsRespuesta().equals(Boolean.TRUE)){
							Toast.makeText(getView().getContext(),
									"Respuesta Correcta",
									Toast.LENGTH_SHORT).show();
							if(preguntas.size()>idPreguntaActual+1){
								idPreguntaActual++;
								llenarPreguntas();
							}else{
								//fin de formulario
							}
						}else{
							Toast.makeText(getView().getContext(),
									"Respuesta InCorrecta",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	
	private void evaluarA3(){
		
		Button a3 = (Button)  getView().findViewById(R.id.a3);
		a3.setOnClickListener(new View.OnClickListener() {// Se
					@Override
					public void onClick(View v) {
						Log.d("myTag", "click c");
						if(preguntaActual.getA3().getEsRespuesta().equals(Boolean.TRUE)){
							Toast.makeText(getView().getContext(),
									"Respuesta Correcta",
									Toast.LENGTH_SHORT).show();
							if(preguntas.size()>idPreguntaActual+1){
								idPreguntaActual++;
								llenarPreguntas();
							}else{
								//fin de formulario
							}
						}else{
							Toast.makeText(getView().getContext(),
									"Respuesta InCorrecta",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	
	private void evaluarA4(){
		
		Button a4 = (Button)  getView().findViewById(R.id.a4);
		a4.setOnClickListener(new View.OnClickListener() {// Se
					@Override
					public void onClick(View v) {
						Log.d("myTag", "click d");
						if(preguntaActual.getA4().getEsRespuesta().equals(Boolean.TRUE)){
							Toast.makeText(getView().getContext(),
									"Respuesta Correcta",
									Toast.LENGTH_SHORT).show();
							if(preguntas.size()>idPreguntaActual+1){
								idPreguntaActual++;
								llenarPreguntas();
							}else{
								//fin de formulario
							}
						}else{
							Toast.makeText(getView().getContext(),
									"Respuesta InCorrecta",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	public void llenarPreguntas(){
		preguntaActual = preguntas.get(idPreguntaActual);
		
		TextView texto = (TextView) getActivity().findViewById(R.id.p1);
		texto.setText(preguntaActual.getEnunciado());
		
		Button b1 = (Button) getActivity().findViewById(R.id.a1);
		b1.setText(preguntaActual.getA1().getAlternativa());

		Button b2 = (Button) getActivity().findViewById(R.id.a2);
		b2.setText(preguntaActual.getA2().getAlternativa());
		
		Button b3 = (Button) getActivity().findViewById(R.id.a3);
		b3.setText(preguntaActual.getA3().getAlternativa());
		
		Button b4 = (Button) getActivity().findViewById(R.id.a4);
		b4.setText(preguntaActual.getA4().getAlternativa());
	}

	//Setters y Getters
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
	
	 
	
}
