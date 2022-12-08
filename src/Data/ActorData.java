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
import Utility.Ruta;

public class ActorData {

	private Document document;
	private Element root;

	public ActorData() throws FileNotFoundException, IOException, JDOMException {
		File archivo = new File(Ruta.ACTORES);
		if (archivo.exists()) {
			SAXBuilder saxBuilder = new SAXBuilder();
			saxBuilder.setIgnoringElementContentWhitespace(true);
			this.document = saxBuilder.build(Ruta.ACTORES);
			this.root = this.document.getRootElement();
		} else {
			this.root = new Element("Actores");
			this.document = new Document(this.root);
			guardarXML();
		}
	} // constructor

	private void guardarXML() throws FileNotFoundException, IOException {
		XMLOutputter xmOutputter = new XMLOutputter();
		xmOutputter.output(this.document, new PrintWriter(Ruta.ACTORES));
	} // guardarXML

	public boolean registrarActores(Actor actor) throws FileNotFoundException, IOException {

		Element eActor = new Element("Actor");
		eActor.setAttribute("nombre", "" + actor.getNombre());

		Element eApellido = new Element("apellido");
		eApellido.addContent(actor.getApellido());
		Element eEdad = new Element("edad");
		eEdad.addContent(actor.getEdad() + "");

		eActor.addContent(eApellido);
		eActor.addContent(eEdad);

		this.root.addContent(eActor);
		this.guardarXML();

		return true;
	} // registrarCarrera

	public ArrayList<Actor> obtenerActores() throws JDOMException, IOException {
		ArrayList<Actor> actores = new ArrayList<>();
		List elementList = this.root.getChildren();

		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;

			Actor actor = new Actor(elementoActual.getAttributeValue("nombre"),
					elementoActual.getChild("apellido").getValue(),
					Integer.parseInt(elementoActual.getChild("edad").getValue()));

			actores.add(actor);
		} // for
		return actores;
	} // obtenerCarreras

	public Actor obtenerActorNombre(String nombre) {
		List elementList = this.root.getChildren();

		for (Object objetoActual : elementList) {
			Element elementoActual = (Element) objetoActual;
			if (elementoActual.getAttributeValue("nombre").equals(nombre)) {
				Actor actor = new Actor(elementoActual.getAttributeValue("nombre"),
						elementoActual.getChild("apellido").getValue(),
						Integer.parseInt(elementoActual.getChild("edad").getValue()));
				return actor;
			}
			// actualizar seria setValue en vez de getVAlue y eliminar se usa remove
		} // for
		return null;
	}

} // fin clase
