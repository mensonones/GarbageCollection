package com.br.gc.pds.net;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

import com.br.gc.pds.model.Message.Mensagem;

public class GarbageCollectorCliente {
	private String serverHost;
	private int serverPort;
	private Socket socket;

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public static TCPClienteBuilder Builder() {
		return new TCPClienteBuilder();
	}

	public void sendRequest(Mensagem mensagem) {
		try {
			mensagem.writeTo(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getResponse() {
		try {
			byte[] resposta = IOUtils.toByteArray(socket.getInputStream());
			return resposta;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
