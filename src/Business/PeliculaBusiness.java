package Business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;

import Data.PeliculaData;
import Domain.Pelicula;

public class PeliculaBusiness {
	private PeliculaData peliculaData;

	public PeliculaBusiness() throws JDOMException, IOException {
		this.peliculaData = new PeliculaData();
	}

	public boolean registrarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		return peliculaData.registrarPelicula(pelicula);
	}

	public ArrayList<Pelicula> obtenerPeliculas() throws JDOMException, IOException {
		return peliculaData.obtenerPeliculas();
	}

	public void eliminarPelicula(String codigo) throws FileNotFoundException, IOException {
		this.peliculaData.eliminarPelicula(codigo);
	}

	public void actualizarPelicula(int codigo, String tipoDato, String datoNuevo)
			throws FileNotFoundException, IOException {
		this.peliculaData.actualizarPelicula(codigo, tipoDato, datoNuevo);
	}

	public Pelicula buscarPeliculaCodigo(int codigo) throws FileNotFoundException, IOException, JDOMException {
		return this.peliculaData.buscarPeliculaCodigo(codigo);
	}

	public ArrayList<Pelicula> buscarPelicula(String dato) throws FileNotFoundException, IOException, JDOMException {
		return this.peliculaData.buscarPelicula(dato);
	}
}// fin clase
