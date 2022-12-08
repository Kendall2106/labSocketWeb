package Domain;

import java.util.ArrayList;

public class Pelicula {

	private int codigo;
	private String nombre;
	private int duracion;
	private String sinopsis;
	private String idioma;
	private ArrayList<Categoria> categorias;
	private ArrayList<Actor> actores;

	public Pelicula(int codigo, String nombre, int duracion, String sinopsis, String idioma,
			ArrayList<Categoria> categorias, ArrayList<Actor> actores) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.sinopsis = sinopsis;
		this.idioma = idioma;
		this.categorias = categorias;
		this.actores = actores;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ArrayList<Actor> getActores() {
		return actores;
	}

	public void setActores(ArrayList<Actor> actores) {
		this.actores = actores;
	}

}// fin clase
