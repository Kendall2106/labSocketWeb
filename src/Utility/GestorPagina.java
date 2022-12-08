package Utility;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.jdom.JDOMException;

import Business.ActorBusiness;
import Business.CategoriaBusiness;
import Business.PeliculaBusiness;
import Domain.Actor;
import Domain.Categoria;
import Domain.Pelicula;

public class GestorPagina {

	private PrintStream send;

	public GestorPagina(PrintStream send) {
		this.send = send;
	}

	//carga la pagina de las peliculas
	public void enviarRegistroPeliculas() {
		this.send.println("HTTP/1.0 200 OK");
		this.send.println("Content-Type: text/html; charset=utf-8");
		this.send.println("Server: MyServer");
		// marca el final de los headers de la response
		this.send.println("");

		this.send.println("<!DOCTYPE html>");
		this.send.println("<html lang=\"es\">");
		this.send.println("<head>");
		this.send.println("<!-- mi comentario -->");
		this.send.println("<meta charset=\"utf-8\">");
		this.send.println("	<title>Ejemplo HTML para el curso Programacion II</title>");
		this.send.println(
				"<meta name=\"description\" content=\"Estes es un ejemplo de una pagina web para el curso de Programacion II\">");
		this.send.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilo.css\">");
		this.send.println("</head>");

		this.send.println("	<body>");

		this.send.println("<header>");
		this.send.println("<h1>Visualisacion de Peliculas</h1>");
		this.send.println("<nav>");
		this.send.println("<ul>");
		this.send.println("<li><a href=\"index.html\">Inicio</a></li>");
		this.send.println("<li><a href=\"agregarCategoria.html\">Agregar Categoria</a></li>");
		this.send.println("<li><a href=\"agregarActor.html\">Agregar Actor</a></li>");
		this.send.println("<li><a href=\"agregarPelicula.html\">Agregar Pelicula</a></li>");
		this.send.println("<li><a href=\"listarPelicula.html\">Gestionar Pelicula</a></li>");
		this.send.println("</ul>");
		this.send.println("</nav>");
		this.send.println("</header>");

		this.send.println("<section id=\"contenido\">");
		this.send.println("<section id=\"principal\">");
		this.send.println("<form name =\"form1\" method =\"post\" action=\"registrarPeliculas\">");
		this.send.println("<legend>Registar Pelicula</legend>");
		this.send.println("<div>");
		this.send.println("<label for=\"codigo\">Codigo:</label>");
		this.send.println("<input type=\"number\" id=\"codigo\" name=\"codigo\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<label for=\"nombre\">Nombre:</label>");
		this.send.println("<input type=\"text\" id=\"nombre\" name=\"nombre\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<label for=\"duracion\">Duracion en horas:</label>");
		this.send.println("<input type=\"number\" id=\"duracion\" name=\"duracion\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<label for=\"sinopsis\">Sinopsis:</label>");
		this.send.println("<textarea id=\"sinopsis\" name =\"sinopsis\" cols=\"30\" rows=\"5\" required></textarea>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<label for=\"idioma\">Idioma:</label>");
		this.send.println("<input type=\"text\" id=\"idioma\" name=\"idioma\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<label for=\"categorias\">Categorias:</label>");
		this.send.println("<select  name=\"categorias\" multiple>");

		try {
			CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
			ArrayList<Categoria> categorias = categoriaBusiness.obtenerCategorias();
			for (int i = 0; i < categorias.size(); i++) {
				this.send.println("<option value=" + categorias.get(i).getNombre() + " selected>"
						+ categorias.get(i).getNombre() + "</option>");
			}
		} catch (IOException | JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.send.println("</select>");
		this.send.println("</div>");

		this.send.println("<div>");
		this.send.println("<label for=\"actores\">Actores o Actrices:</label>");
		this.send.println("<select  name=\"actores\" multiple>");

		try {
			ActorBusiness actorBusiness = new ActorBusiness();
			ArrayList<Actor> actores = actorBusiness.obtenerActores();
			for (int i = 0; i < actores.size(); i++) {
				this.send.println("<option value=" + actores.get(i).getNombre() + " selected>"
						+ actores.get(i).getNombre() + "</option>");
			}
		} catch (IOException | JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.send.println("</select>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<input type=\"submit\"  value=\"Registrar\">");
		this.send.println("</div>");
		this.send.println("</form>");
		this.send.println("</body>");
		this.send.println("</html>");
	} // fin

	//carga la pagina que enlista las peliculas
	public void listarPeliculas() {
		this.send.println("HTTP/1.0 200 OK");
		this.send.println("Content-Type: text/html; charset=utf-8");
		this.send.println("Server: MyServer");
		// marca el final de los headers de la response
		this.send.println("");
		// Envía el HTML

		this.send.println("<!DOCTYPE html>");
		this.send.println("<html lang=\"es\">");
		this.send.println("<head>");
		this.send.println("<meta charset=\"utf-8\">");
		this.send.println("<title>Ejemplo HTML para el curso Programacion II</title>");
		this.send.println(
				"<meta name=\"description\" content=\"Estes es un ejemplo de una pagina web para el curso de Programacion II\">");
		this.send.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilo.css\">");
		this.send.println("</head>");
		this.send.println("<body>");
		this.send.println("<header>");
		this.send.println("<h1>Visualisacion de Peliculas</h1>");
		this.send.println("<nav>");
		this.send.println("<ul>");
		this.send.println("<li><a href=\"index.html\">Inicio</a></li>");
		this.send.println("<li><a href=\"agregarCategoria.html\">Agregar Categoria</a></li>");
		this.send.println("<li><a href=\"agregarActor.html\">Agregar Actor</a></li>");
		this.send.println("<li><a href=\"agregarPelicula.html\">Agregar Pelicula</a></li>");
		this.send.println("<li><a href=\"listarPelicula.html\">Gestionar Pelicula</a></li>");
		this.send.println("</ul>");
		this.send.println("</nav>");
		this.send.println("</header>");
		this.send.println("<section id=\"contenido\">");
		this.send.println("<section id=\"principal\">");
		this.send.println("<article>");
		this.send.println("<legend>Gestionar Peliculas</legend>");

		// se crea una tabla dinamica
		this.send.println("<table>");
		this.send.println("<THEAD>");
		this.send.println("<tr>\r\n" + "<th>Codigo</th>\r\n" + "<th>Nombre</th>\r\n" + "<th>Duracion</th>\r\n"
				+ "<th>Sinopsis</th>\r\n" + "<th>Idioma</th>\r\n" + "<th>Categorias</th>\r\n" + "<th>Actores</th>\r\n"
				+ "</tr>");
		this.send.println("</THEAD>");
		this.send.println("<tbody>");
		try {
			PeliculaBusiness peliculaBusiness = new PeliculaBusiness();
			ArrayList<Pelicula> peliculas = peliculaBusiness.obtenerPeliculas();
			for (int i = 0; i < peliculas.size(); i++) {
				this.send.println("<tr>\r\n" + "<td>" + peliculas.get(i).getCodigo() + "</td>\r\n" + "<td>"
						+ peliculas.get(i).getNombre() + "</td>\r\n" + "<td>" + peliculas.get(i).getDuracion()
						+ "</td>\r\n" + "<td>" + peliculas.get(i).getSinopsis() + "</td>\r\n" + "<td>"
						+ peliculas.get(i).getIdioma() + "</td>\r\n" + "<td>" + peliculas.get(i).getCategorias()
						+ "</td>\r\n" + "<td>" + peliculas.get(i).getActores() + "</td>\r\n" + "</tr>");
			} // for
		} catch (IOException | JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.send.println("</tbody>");
		this.send.println("</tr></table>");
		this.send.println("</article>");
		this.send.println("<form name =\"form2\" method =\"post\" action=\"borrarPelicula\">");
		this.send.println("<div>");
		this.send.println("<label for=\"borrar\">Borrar por codigo:</label>");
		this.send.println("<input type=\"number\" id=\"borrar\" name=\"borrar\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<input type=\"submit\"  value=\"Borrar\">");
		this.send.println("</div>");
		this.send.println("</form>");

		this.send.println("<form name =\"form3\" method =\"post\" action=\"actualizarPelicula\">");
		this.send.println("<div>");
		this.send.println("<label for=\"actualizar\">Actualizar por codigo:</label>");
		this.send.println("</div>");
		this.send.println("<select  name=\"codigos\">");
		try {
			PeliculaBusiness peliculaBusiness = new PeliculaBusiness();
			ArrayList<Pelicula> peliculas = peliculaBusiness.obtenerPeliculas();
			for (int i = 0; i < peliculas.size(); i++) {
				this.send.println("<option value=" + peliculas.get(i).getCodigo() + " >" + peliculas.get(i).getCodigo()
						+ "</option>");
			}
		} catch (IOException | JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.send.println("</select>");
		this.send.println("<div>");
		this.send.println("<label for=\"actualizarTipo\">Escoja la categoria:</label>");
		this.send.println("</div>");
		this.send.println("<select  name=\"tipoDato\">");
		this.send.println("<option value=\"nombre\" selected>Nombre</option>");
		this.send.println("<option value=\"duracion\" >Duracion</option>");
		this.send.println("<option value=\"sinopsis\" >Sinopsis</option>");
		this.send.println("<option value=\"idioma\" >Idioma</option>");
		this.send.println("</select>");
		this.send.println("<div>");
		this.send.println("<label for=\"actualizarDato\">Digite el nuevo dato:</label>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<input type=\"text\" id=\"actualizar\" name=\"actualizar\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<input type=\"submit\"  value=\"Actualizar\">");
		this.send.println("</div>");
		this.send.println("</form>");

		this.send.println("<form name =\"form4\" method =\"post\" action=\"buscarPelicula\">");
		this.send.println("<div>");
		this.send.println("<label for=\"buscar\">Buscar por nombre o Categoria:</label>");
		this.send.println("<input type=\"text\" id=\"buscar\" name=\"buscar\" required/>");
		this.send.println("</div>");
		this.send.println("<div>");
		this.send.println("<input type=\"submit\"  value=\"Buscar\">");
		this.send.println("</div>");
		this.send.println("</form>");

		this.send.println("</body>");
		this.send.println("</html>");
	}//fin

	//carga la pagina de la busqueda de peliculas
	public void buscarPeliculas(ArrayList<Pelicula> peliculas) {
		this.send.println("HTTP/1.0 200 OK");
		this.send.println("Content-Type: text/html; charset=utf-8");
		this.send.println("Server: MyServer");
		// marca el final de los headers de la response
		this.send.println("");
		// Envía el HTML

		this.send.println("<!DOCTYPE html>");
		this.send.println("<html lang=\"es\">");
		this.send.println("<head>");
		this.send.println("<meta charset=\"utf-8\">");
		this.send.println("<title>Ejemplo HTML para el curso Programacion II</title>");
		this.send.println(
				"<meta name=\"description\" content=\"Estes es un ejemplo de una pagina web para el curso de Programacion II\">");
		this.send.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilo.css\">");
		this.send.println("</head>");
		this.send.println("<body>");
		this.send.println("<header>");
		this.send.println("<h1>Visualisacion de Peliculas</h1>");
		this.send.println("<nav>");
		this.send.println("<ul>");
		this.send.println("<li><a href=\"index.html\">Inicio</a></li>");
		this.send.println("<li><a href=\"agregarCategoria.html\">Agregar Categoria</a></li>");
		this.send.println("<li><a href=\"agregarActor.html\">Agregar Actor</a></li>");
		this.send.println("<li><a href=\"agregarPelicula.html\">Agregar Pelicula</a></li>");
		this.send.println("<li><a href=\"listarPelicula.html\">Gestionar Pelicula</a></li>");
		this.send.println("</ul>");
		this.send.println("</nav>");
		this.send.println("</header>");
		this.send.println("<section id=\"contenido\">");
		this.send.println("<section id=\"principal\">");
		this.send.println("<article>");
		this.send.println("<legend>Gestionar Peliculas</legend>");

		// se crea una tabla dinamica
		this.send.println("<table>");
		this.send.println("<THEAD>");
		this.send.println("<tr>\r\n" + "<th>Codigo</th>\r\n" + "<th>Nombre</th>\r\n" + "<th>Duracion</th>\r\n"
				+ "<th>Sinopsis</th>\r\n" + "<th>Idioma</th>\r\n" + "<th>Categorias</th>\r\n" + "<th>Actores</th>\r\n"
				+ "</tr>");
		this.send.println("</THEAD>");
		this.send.println("<tbody>");
		for (int i = 0; i < peliculas.size(); i++) {
			this.send.println("<tr>\r\n" + "<td>" + peliculas.get(i).getCodigo() + "</td>\r\n" + "<td>"
					+ peliculas.get(i).getNombre() + "</td>\r\n" + "<td>" + peliculas.get(i).getDuracion() + "</td>\r\n"
					+ "<td>" + peliculas.get(i).getSinopsis() + "</td>\r\n" + "<td>" + peliculas.get(i).getIdioma()
					+ "</td>\r\n" + "<td>" + peliculas.get(i).getCategorias() + "</td>\r\n" + "<td>"
					+ peliculas.get(i).getActores() + "</td>\r\n" + "</tr>");
		}
		this.send.println("</tbody>");
		this.send.println("</tr></table>");
		this.send.println("</body>");
		this.send.println("</html>");
	}//fin

} // fin clase
