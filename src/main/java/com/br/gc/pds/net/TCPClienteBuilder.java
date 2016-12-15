package com.br.gc.pds.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClienteBuilder {
	private GarbageCollectorCliente cliente;
	
	public TCPClienteBuilder(){
		this.cliente = new GarbageCollectorCliente();
	}
	
	public TCPClienteBuilder serverHost(String serverHost){
		this.cliente.setServerHost(serverHost);
		return this;
	}
	
	public TCPClienteBuilder serverPort(int serverPort){
		this.cliente.setServerPort(serverPort);
		return this;
	}
	
	public GarbageCollectorCliente build() throws UnknownHostException, IOException{
		Socket socket = new Socket(
				this.cliente.getServerHost(),
				this.cliente.getServerPort());
		
		cliente.setSocket(socket);
		
		return this.cliente;
	}
}
