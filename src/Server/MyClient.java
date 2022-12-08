package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

import org.jdom.Element;
import org.jdom.JDOMException;

import Business.ActorBusiness;
import Business.CategoriaBusiness;
import Business.PeliculaBusiness;
import Domain.Actor;
import Domain.Categoria;
import Domain.Pelicula;
import Utility.GestorPagina;

public class MyClient extends Thread {

	private Socket socket;
	private PrintStream send;
	private BufferedReader receive;

	private BufferedOutputStream bufferedOutputStream;
	private String fileName;
	private GestorPagina gestorPagina;

	public MyClient(Socket socket) throws IOException {
		this.socket = socket;
		this.send = new PrintStream(socket.getOutputStream());
		this.receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.gestorPagina = new GestorPagina(this.send);
		this.bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

	} // constructor

	public void run() {
		try {
			String linea = receive.readLine();
			String request_method = linea;
			int postDataI = -1;
			while ((linea = receive.readLine()) != null && (linea.length() != 0)) {
				if (linea.indexOf("Content-Length:") > -1) {
					postDataI = new Integer(linea.substring(linea.indexOf("Content-Length:") + 16, linea.length()))
							.intValue();

				} // if
			} // while

			//recibe las respuestas por GET
			if (request_method.startsWith("GET")) {
				int i, f;
				i = request_method.indexOf("/");
				f = request_method.indexOf(" ", i);
				String consulta = request_method.substring(i + 1, f);
				if (consulta.equals("")) {
					enviarArchivo("pages/index.html");
				}
				if (consulta.contains("agregarPelicula.html")) { // se valida el link
					this.gestorPagina.enviarRegistroPeliculas();
					// enviarArchivo("pages/"+consulta);
				}
				if (consulta.contains("listarPelicula.html")) { // se valida el link
					this.gestorPagina.listarPeliculas();
					// enviarArchivo("pages/"+consulta);
				} else {
					enviarArchivo("pages/" + consulta);
				}
				//recibe las respuestas por POST
			} else if (request_method.startsWith("POST")) {
				String postData = "";
				if (postDataI > 0) {
					char[] charArray = new char[postDataI];
					receive.read(charArray, 0, postDataI);
					postData = new String(charArray);
					if (request_method.contains("registrarCategoria")) {
						String datos[] = postData.split("&");
						String datoNombre[] = datos[0].split("=");
						String datoDescripcion[] = datos[1].split("=");
						Categoria c = new Categoria(datoNombre[1], datoDescripcion[1]);
						try {
							CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
							categoriaBusiness.registrarCategoria(c);
						} catch (JDOMException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						enviarArchivo("pages/index.html");
					}//fin registrar Categoria
					
					if (request_method.contains("registrarActores")) {
						String datos[] = postData.split("&");
						String datoNombre[] = datos[0].split("=");
						String datoApellido[] = datos[1].split("=");
						String datoEdad[] = datos[2].split("=");
						Actor actor = new Actor(datoNombre[1], datoApellido[1], Integer.parseInt(datoEdad[1]));
						try {
							ActorBusiness actorBusiness = new ActorBusiness();
							actorBusiness.registrarActores(actor);
						} catch (JDOMException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						enviarArchivo("pages/index.html");
					}//fin registrar Actores
					
					if (request_method.contains("registrarPeliculas")) {
						ArrayList<String> categoriasText = new ArrayList<>();
						ArrayList<String> actoresText = new ArrayList<>();
						ArrayList<Categoria> categorias = new ArrayList<>();
						ArrayList<Actor> actores = new ArrayList<>();
						String datos[] = postData.split("&");
						String datoCodigo[] = datos[0].split("=");
						String datoNombre[] = datos[1].split("=");
						String datoDuracion[] = datos[2].split("=");
						String datoSinopsis[] = datos[3].split("=");
						String datoIdioma[] = datos[4].split("=");
						for (int i = 5; i < datos.length; i++) {
							if (datos[i].split("=")[0].equals("categorias")) {
								categoriasText.add(datos[i].split("=")[1].replace("%2B", "+"));
							} else {
								actoresText.add(datos[i].split("=")[1]);
							}
						}

						CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
						for (int i = 0; i < categoriasText.size(); i++) {
							categorias.add(categoriaBusiness.obtenerCategoriaNombre(categoriasText.get(i)));
						}

						ActorBusiness actorBusiness = new ActorBusiness();
						for (int i = 0; i < actoresText.size(); i++) {
							actores.add(actorBusiness.obtenerActorNombre(actoresText.get(i)));
						}

						Pelicula pelicula = new Pelicula(Integer.parseInt(datoCodigo[1]), datoNombre[1],
								Integer.parseInt(datoDuracion[1]), datoSinopsis[1].replace("+", " "), datoIdioma[1],
								categorias, actores);
						PeliculaBusiness peliculaBusiness = new PeliculaBusiness();
						if (peliculaBusiness.buscarPeliculaCodigo(Integer.parseInt(datoCodigo[1])) == null) {
							peliculaBusiness.registrarPelicula(pelicula);
							enviarArchivo("pages/index.html");
						} else {

						}

					}//registrar Peliculas
					
					if (request_method.contains("borrarPelicula")) {
						String datos[] = postData.split("&");
						String datoCodigo[] = datos[0].split("=");
						try {
							PeliculaBusiness peliculaBusiness = new PeliculaBusiness();
							peliculaBusiness.eliminarPelicula(datoCodigo[1]);
						} catch (JDOMException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						enviarArchivo("pages/index.html");
					}//borra Peliculas
					
					if (request_method.contains("actualizarPelicula")) {
						String datos[] = postData.split("&");
						String datoCodigo[] = datos[0].split("=");
						String datoTipo[] = datos[1].split("=");
						String datoNuevo[] = datos[2].split("=");
						try {
							PeliculaBusiness peliculaBusiness = new PeliculaBusiness();
							peliculaBusiness.actualizarPelicula(Integer.parseInt(datoCodigo[1]), datoTipo[1],
									datoNuevo[1]);

						} catch (JDOMException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						enviarArchivo("pages/index.html");
					}//actualiza Peliculas
					
					if (request_method.contains("buscarPelicula")) {
						String datos[] = postData.split("&");
						String datoCodigo[] = datos[0].split("=");
						PeliculaBusiness peliculaBusiness = new PeliculaBusiness();
						this.gestorPagina.buscarPeliculas(peliculaBusiness.buscarPelicula(datoCodigo[1]));
					}//busca Peliculas
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // run

	public void enviarArchivo(String nombreArchivo) {
		try {
			int b_leidos = 0;
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(nombreArchivo));
			byte[] buf = new byte[1024];
			int tam_bloque = 0;
			if (bufferedInputStream.available() >= 1024) {
				tam_bloque = 1024;
			} else {
				bufferedInputStream.available();
			}

			int tam_archivo = bufferedInputStream.available();

			String sb = "";
			sb = sb + "HTTP/1.0 200 ok\n";
			sb = sb + "Server: MyServer";
			sb = sb + "Content-Type: text/html \n";
			sb = sb + "Content-Length: " + tam_archivo + " \n";
			sb = sb + "\n";
			bufferedOutputStream.write(sb.getBytes());
			bufferedOutputStream.flush();
			while ((b_leidos = bufferedInputStream.read(buf, 0, buf.length)) != -1) {
				bufferedOutputStream.write(buf, 0, b_leidos);

			}
			bufferedOutputStream.flush();
			bufferedInputStream.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	} // enviarArchivo

} // fin clase
