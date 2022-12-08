package Business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;

import Data.CategoriaData;
import Domain.Categoria;

public class CategoriaBusiness {

	private CategoriaData categoriaData;
	
	public CategoriaBusiness() throws FileNotFoundException, IOException, JDOMException {
		this.categoriaData= new CategoriaData();
	}
	
	public boolean registrarCategoria(Categoria categoria) throws FileNotFoundException, IOException {
		return this.categoriaData.registrarCategoria(categoria);
	}
	
	public ArrayList<Categoria> obtenerCategorias() throws JDOMException, IOException{
		return this.categoriaData.obtenerCategorias();
	}
	
	public Categoria obtenerCategoriaNombre(String nombre){
		return this.categoriaData.obtenerCategoriaNombre(nombre);
	}
}//fin clase
