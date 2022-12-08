package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class MyServer extends Thread{

	private int socketPortNumber;
	private JTextArea jtaSalida;
	
	private InetAddress address;
	
	public MyServer(int socketPortNumber, JTextArea jtaSalida) throws UnknownHostException {
		super("Hilo Sevidor");
		this.socketPortNumber=socketPortNumber;
		this.jtaSalida=jtaSalida;
		this.address=InetAddress.getLocalHost();
	} // constructor
	
	public InetAddress obtenerIP() {
		return this.address;
	}
	
	public void run() {	
		try {
			ServerSocket serverSocket=new ServerSocket(this.socketPortNumber);
			do {
				this.jtaSalida.append("Servidor ejecutando \n");
				Socket socket=serverSocket.accept();
				this.jtaSalida.append("Cliente conectado \n");
				
				MyClient myClient=new MyClient(socket);	
				myClient.start();
				
			}while(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // run
	
} // fin clase
