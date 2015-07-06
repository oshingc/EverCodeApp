package info.androidhive.slidingmenu.model;

public class Nivel {
	
	private Integer idNivel;
	private String descripcion;
	
	public Nivel(){
		
	}
	
	public Nivel(Integer idNivel){
		this.idNivel = idNivel;
	}

	public Integer getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
