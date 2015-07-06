package info.androidhive.slidingmenu.model;

public class TipoPregunta {

	private Integer idTipoPregunta;
	private String descripcion;
	
	public TipoPregunta(){
	}
	
	public TipoPregunta(Integer idTipoPregunta){
		this.idTipoPregunta = idTipoPregunta;
	}
	
	public Integer getIdTipoPregunta() {
		return idTipoPregunta;
	}
	public void setIdTipoPregunta(Integer idTipoPregunta) {
		this.idTipoPregunta = idTipoPregunta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
