package Business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;

import Data.ActorData;
import Domain.Actor;

public class ActorBusiness {

	private ActorData actorData;
	
	public ActorBusiness() throws FileNotFoundException, IOException, JDOMException {
		this.actorData= new ActorData();
	}
	
	public boolean registrarActores(Actor actor) throws FileNotFoundException, IOException {
		return actorData.registrarActores(actor);
	}
	
	public ArrayList<Actor> obtenerActores() throws JDOMException, IOException{
		return actorData.obtenerActores();
	}
	
	public Actor obtenerActorNombre(String nombre){
		return actorData.obtenerActorNombre(nombre);
	}
}//fin clase
