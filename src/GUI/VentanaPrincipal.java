package GUI;

import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import Server.MyServer;

public class VentanaPrincipal extends JFrame {

	private JTextArea jtaSalida;
	private MyServer myServer;

	public VentanaPrincipal() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Ejemplo Servidor");
		this.jtaSalida = new JTextArea();
		this.add(this.jtaSalida);
		iniciarServidor();
	} // constructor

	private void iniciarServidor() {
		try {
			this.myServer = new MyServer(5025, this.jtaSalida);
			this.myServer.start();
			this.jtaSalida.append(this.myServer.obtenerIP().toString() + "\n");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	} // iniciarServidor

} // fin clase
