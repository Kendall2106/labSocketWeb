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

import Domain.Actor;
import Domain.Categoria;
import Domain.Pelicula;
import Utility.Ruta;

public class PeliculaData {

	private Document document;
	private Element root;

	public PeliculaData() throws JDOMException, IOException {
		File archivo = new File(Ruta.PELICULAS);
		if (archivo.exists()) {
			SAXBuilder saxBuilder = new SAXBuilder();
			saxBuilder.setIgnoringElementContentWhitespace(true);
			this.document = saxBuilder.build(Ruta.PELICULAS);
			this.root = this.document.getRootElement();
		} else {
			this.root = new Element("Peliculas");
			this.document = new Document(this.root);
			guardarXML();
		}
	}

	private void guardarXML() throws FileNotFoundException, IOException {
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.output(this.document, new PrintWriter(Ruta.PELICULAS));
	}

	public boolean registrarPelicula(Pelicula pelicula) throws FileNotFoundException, IOException {
		Element ePelicula = new Element("Pelicula");
		ePelicula.setAttribute("codigo", "" + pelicula.getCodigo());

		Element eNombre = new Element("nombre");
		eNombre.addContent(pelicula.getNombre());

		Element eDuracion = new Element("duracion");
		eDuracion.addContent("" + pelicula.getDuracion());

		Element eSinopsis = new Element("sinopsis");
		eSinopsis.addContent(pelicula.getSinopsis());

		Element eIdioma = new Element("idioma");
		eIdioma.addContent(pelicula.getIdioma());

		Element eCategorias = new Element("Categorias");
		for (int i = 0; i < pelicula.getCategorias().size(); i++) {
			Element eCategoriaTemp = new Element("Categoria");
			eCategoriaTemp.setAttribute("nombre", "" + pelicula.getCategorias().get(i).getNombre());
			eCategorias.addContent(eCategoriaTemp);
		} // for

		Element eActores = new Element("Actores");
		for (int i = 0; i < pelicula.getActores().size(); i++) {
			Element eActorTemp = new Element("Actor");
			eActorTemp.setAttribute("nombre", "" + pelicula.getActores().get(i).getNombre());
			eActores.addContent(eActorTemp);
		} // for

		ePelicula.addContent(eNombre);
		ePelicula.addContent(eDuracion);
		ePelicula.addContent(eSinopsis);
		ePelicula.addContent(eIdioma);
		ePelicula.addContent(eCategorias);
		ePelicula.addContent(eActores);

		this.root.addContent(ePelicula);
		this.guardarXML();

		return true;
	}

	public ArrayList<Pelicula> obtenerPeliculas() throws JDOMException, IOException {
		ArrayList<Pelicula> peliculas = new ArrayList<>();
		List elementList = this.root.getChildren();
		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			List elementListCategorias = elementoActual.getChild("Categorias").getChildren();
			ArrayList<Categoria> categorias = new ArrayList<>();
			CategoriaData categoriaData = new CategoriaData();
			for (Object object : elementListCategorias) {
				Element elementocategoriaActual = (Element) object;
				categorias
						.add(categoriaData.obtenerCategoriaNombre(elementocategoriaActual.getAttributeValue("nombre")));
			} // for
			List elementListActores = elementoActual.getChild("Actores").getChildren();
			ArrayList<Actor> actores = new ArrayList<>();
			ActorData actorData = new ActorData();
			for (Object object : elementListActores) {
				Element elementoActorActual = (Element) object;
				actores.add(actorData.obtenerActorNombre(elementoActorActual.getAttributeValue("nombre")));
			} // for

			Pelicula pelicula = new Pelicula(Integer.parseInt(elementoActual.getAttributeValue("codigo")),
					elementoActual.getChildText("nombre"), Integer.parseInt(elementoActual.getChildText("duracion")),
					elementoActual.getChildText("sinopsis"), elementoActual.getChildText("idioma"), categorias,
					actores);

			peliculas.add(pelicula);
			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove
		} // for
		return peliculas;
	}

	public void eliminarPelicula(String codigo) throws FileNotFoundException, IOException {
		List elementList = this.root.getChildren();
		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			if (elementoActual.getAttributeValue("codigo").equals(codigo)) {
				this.root.removeContent(elementoActual);
				this.guardarXML();
				break;
			}
			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove

		} // for

	}// actualizarJugadorNombre

	public void actualizarPelicula(int codigo, String tipoDato, String datoNuevo)
			throws FileNotFoundException, IOException {
		List elementList = this.root.getChildren();
		int valor = 0;
		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			if (elementoActual.getAttributeValue("codigo").equals(codigo + "")) {
				elementoActual.getChild(tipoDato).setText(datoNuevo);
				this.guardarXML();
			}
			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove
		} // for
	}// actualizarJugadorNombre

	public Pelicula buscarPeliculaCodigo(int codigo) throws FileNotFoundException, IOException, JDOMException {
		List elementList = this.root.getChildren();

		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			if (elementoActual.getAttributeValue("codigo").equals(codigo + "")) {
				List elementListCategorias = elementoActual.getChild("Categorias").getChildren();
				ArrayList<Categoria> categorias = new ArrayList<>();
				CategoriaData categoriaData = new CategoriaData();
				for (Object object : elementListCategorias) {
					Element elementocategoriaActual = (Element) object;
					categorias.add(
							categoriaData.obtenerCategoriaNombre(elementocategoriaActual.getAttributeValue("nombre")));
				} // for
				List elementListActores = elementoActual.getChild("Actores").getChildren();
				ArrayList<Actor> actores = new ArrayList<>();
				ActorData actorData = new ActorData();
				for (Object object : elementListActores) {
					Element elementoActorActual = (Element) object;
					actores.add(actorData.obtenerActorNombre(elementoActorActual.getAttributeValue("nombre")));
				} // for

				Pelicula pelicula = new Pelicula(Integer.parseInt(elementoActual.getAttributeValue("codigo")),
						elementoActual.getChild("nombre").getValue(),
						Integer.parseInt(elementoActual.getChild("duracion").getValue()),
						elementoActual.getChild("sinopsis").getValue(), elementoActual.getChild("idioma").getValue(),
						categorias, actores);

				return pelicula;
			}
			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove
		} // for
		return null;
	}

	public ArrayList<Pelicula> buscarPelicula(String dato) throws FileNotFoundException, IOException, JDOMException {
		List elementList = this.root.getChildren();
		ArrayList<Pelicula> peliculas = new ArrayList<>();
		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			List elementListCategorias2 = elementoActual.getChild("Categorias").getChildren();

			for (Object object2 : elementListCategorias2) {
				Element elementocategoriaActual2 = (Element) object2;

				if (elementoActual.getChildText("nombre").indexOf(dato) != -1 && dato.length() > 0
						|| elementocategoriaActual2.getAttributeValue("nombre").equals(dato)) {

					List elementListCategorias = elementoActual.getChild("Categorias").getChildren();
					ArrayList<Categoria> categorias = new ArrayList<>();
					CategoriaData categoriaData = new CategoriaData();
					for (Object object : elementListCategorias) {
						Element elementocategoriaActual = (Element) object;
						categorias.add(categoriaData
								.obtenerCategoriaNombre(elementocategoriaActual.getAttributeValue("nombre")));
					} // for
					List elementListActores = elementoActual.getChild("Actores").getChildren();
					ArrayList<Actor> actores = new ArrayList<>();
					ActorData actorData = new ActorData();
					for (Object object : elementListActores) {
						Element elementoActorActual = (Element) object;
						actores.add(actorData.obtenerActorNombre(elementoActorActual.getAttributeValue("nombre")));
					} // for
					Pelicula pelicula = new Pelicula(Integer.parseInt(elementoActual.getAttributeValue("codigo")),
							elementoActual.getChild("nombre").getValue(),
							Integer.parseInt(elementoActual.getChild("duracion").getValue()),
							elementoActual.getChild("sinopsis").getValue(),
							elementoActual.getChild("idioma").getValue(), categorias, actores);

					peliculas.add(pelicula);
					break;
				}
			}

			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove
		} // for
		return peliculas;
	}
}
