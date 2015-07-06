package info.androidhive.slidingmenu.model;

public class Pregunta {

	private Integer idPregunta;
	private String enunciado;
	private TipoPregunta tipoPregunta;
	private Alternativa a1;
	private Alternativa a2;
	private Alternativa a3;
	private Alternativa a4;
	private Nivel nivel;

	public Pregunta() {
	}

	public Pregunta(Integer idPregunta, String enunciado, Alternativa a1,
			Alternativa a2, Alternativa a3, Alternativa a4, String nivel) {
		this.idPregunta = idPregunta;
		this.enunciado = enunciado;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.nivel = new Nivel();
		this.nivel.setDescripcion(nivel);
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Alternativa getA1() {
		return a1;
	}

	public void setA1(Alternativa a1) {
		this.a1 = a1;
	}

	public Alternativa getA2() {
		return a2;
	}

	public void setA2(Alternativa a2) {
		this.a2 = a2;
	}

	public Alternativa getA3() {
		return a3;
	}

	public void setA3(Alternativa a3) {
		this.a3 = a3;
	}

	public Alternativa getA4() {
		return a4;
	}

	public void setA4(Alternativa a4) {
		this.a4 = a4;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public TipoPregunta getTipoPregunta() {
		return tipoPregunta;
	}

	public void setTipoPregunta(TipoPregunta tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}

}
