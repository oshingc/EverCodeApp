package info.androidhive.slidingmenu.model;

public class Alternativa {
	
	private Integer idAlternativa;
	private String alternativa;
	private Boolean esRespuesta;
	
	public Alternativa(){
		
	}
	
	public Alternativa(String alternativa, Boolean esRespuesta){
		this.alternativa = alternativa;
		this.esRespuesta = esRespuesta;
	}
	
	public Alternativa(Integer idAlternativa, String alternativa, Boolean esRespuesta){
		this.idAlternativa = idAlternativa;
		this.alternativa = alternativa;
		this.esRespuesta = esRespuesta;
	}
	
	public Integer getIdAlternativa() {
		return idAlternativa;
	}
	public void setIdAlternativa(Integer idAlternativa) {
		this.idAlternativa = idAlternativa;
	}
	public String getAlternativa() {
		return alternativa;
	}
	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}
	public Boolean getEsRespuesta() {
		return esRespuesta;
	}
	public void setEsRespuesta(Boolean esRespuesta) {
		this.esRespuesta = esRespuesta;
	}

}
