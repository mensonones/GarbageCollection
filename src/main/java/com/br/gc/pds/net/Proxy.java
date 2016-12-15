package com.br.gc.pds.net;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.gc.pds.factory.Factory;
import com.br.gc.pds.factory.MensagemFactory;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.model.Lixeiras.ListaLixeira;
import com.br.gc.pds.model.Lixeiras.Lixeira;
import com.br.gc.pds.model.Message.Mensagem;
import com.br.gc.pds.model.Rotas.ListaRota;
import com.br.gc.pds.model.Rotas.Rota;
import com.br.gc.pds.util.ConstantesPontosRota;
import com.google.protobuf.InvalidProtocolBufferException;

@Service
public class Proxy {
	private GarbageCollectorCliente cliente;
	private Mensagem mensagem;
	private Factory factory;

	public List<Lixeira> buscarLixeira() {
		factory = new MensagemFactory("Lixeira", "getLixeiras", new ArrayList<String>());
		factory.factory();
		mensagem = (Mensagem) factory.getFactory();
		
		try {
			Mensagem resposta = Mensagem.parseFrom(doOperations(mensagem));
			List<Lixeira> listaLixeira = ListaLixeira.parseFrom(resposta.getArgumentos(0).toByteArray())
					.getLixeiraList();
			return listaLixeira;
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Rota> calcularRota(List<String> pontosLixeira, List<LixeiraEntity> lixeiras) {
		List<String> pontosAlterarStatusColeta = new ArrayList<String>();

		for (LixeiraEntity ponto : lixeiras) {
			pontosAlterarStatusColeta.add(String.valueOf(ponto.getId()));
		}

		pontosLixeira.add(ConstantesPontosRota.LIXAO);
		factory = new MensagemFactory("Rota", "calcularRota", pontosLixeira);
		factory.factory();
		mensagem = (Mensagem) factory.getFactory();
		
		try {
			Mensagem resposta = Mensagem.parseFrom(doOperations(mensagem));
			List<Rota> listaRota = ListaRota.parseFrom(resposta.getArgumentos(0)).getRotaList();
			alterarStatusColeta("0", pontosAlterarStatusColeta);
			return listaRota;
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Double calcularDistancia(String origem, String destino) throws InvalidProtocolBufferException {
		List<String> pontos = new ArrayList<>();

		pontos.add(origem);
		pontos.add(destino);
		
		factory = new MensagemFactory("Distancia", "calcularDistancia", pontos);
		factory.factory();
		mensagem = (Mensagem) factory.getFactory();
		
		Mensagem resposta = Mensagem.parseFrom(doOperations(mensagem));

		String distancia = new String(resposta.getArgumentos(0).toString(Charset.forName("utf-8")));
		return Double.valueOf(distancia);
	}

	public void alterarStatusColeta(String status, List<String> arguments) throws InvalidProtocolBufferException {
		List<String> pontosAlterarStatus = new ArrayList<>();

		pontosAlterarStatus.add(status);

		for (String l : arguments) {
			pontosAlterarStatus.add(l);
		}
		factory = new MensagemFactory("Lixeira", "atualizarStatusColeta", pontosAlterarStatus);
		factory.factory();
		mensagem = (Mensagem) factory.getFactory();
		
		doOperations(mensagem);
	}

	private byte[] doOperations(Mensagem mensagem) {
		try {

			this.cliente = new TCPClienteBuilder().serverHost("127.0.0.1").serverPort(2068).build();
			cliente.sendRequest(mensagem);
			return cliente.getResponse();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}