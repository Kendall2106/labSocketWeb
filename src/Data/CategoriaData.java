package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import Domain.Categoria;
import Utility.Ruta;

public class CategoriaData {

	private Document document;
	private Element root;

	public CategoriaData() throws FileNotFoundException, IOException, JDOMException {
		File archivo = new File(Ruta.CATEGORIAS);
		if (archivo.exists()) {
			SAXBuilder saxBuilder = new SAXBuilder();
			saxBuilder.setIgnoringElementContentWhitespace(true);
			this.document = saxBuilder.build(Ruta.CATEGORIAS);
			this.root = this.document.getRootElement();
		} else {
			this.root = new Element("Categorias");
			this.document = new Document(this.root);
			guardarXML();
		}
	} // constructor

	private void guardarXML() throws FileNotFoundException, IOException {
		XMLOutputter xmOutputter = new XMLOutputter();
		xmOutputter.output(this.document, new PrintWriter(Ruta.CATEGORIAS));
	} // guardarXML

	public boolean registrarCategoria(Categoria categoria) throws FileNotFoundException, IOException {

		Element eCategoria = new Element("Categoria");
		eCategoria.setAttribute("nombre", "" + categoria.getNombre());

		Element eDescripcion = new Element("descripcion");
		eDescripcion.addContent(categoria.getDescripcion());

		eCategoria.addContent(eDescripcion);

		this.root.addContent(eCategoria);
		this.guardarXML();

		return true;
	} // registrarCarrera

	public ArrayList<Categoria> obtenerCategorias() throws JDOMException, IOException {
		ArrayList<Categoria> categorias = new ArrayList<>();
		List elementList = this.root.getChildren();

		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;

			Categoria categoria = new Categoria(elementoActual.getAttributeValue("nombre"),
					elementoActual.getChild("descripcion").getValue());

			categorias.add(categoria);
		} // for
		return categorias;
	} // obtenerCarreras

	public Categoria obtenerCategoriaNombre(String nombre) {
		List elementList = this.root.getChildren();

		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			if (elementoActual.getAttributeValue("nombre").equalsIgnoreCase(nombre)) {
				Categoria categoria = new Categoria(elementoActual.getAttributeValue("nombre"),
						elementoActual.getChild("descripcion").getValue());
				return categoria;
			}
			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove
		} // for
		return null;
	}

}
