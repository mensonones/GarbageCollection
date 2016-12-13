package com.br.gc.pds.net;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.StringDescription;
import org.springframework.stereotype.Service;

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
	private MensagemFactory mensagemFactory;

	// Metodo Responsavel por enviar mensagem de buscar todas a lixeiras
	// cadastradas para servidor
	public List<Lixeira> buscarLixeira() {
		mensagemFactory = new MensagemFactory();
		mensagem = mensagemFactory.empacotar("Lixeira", "getLixeiras", new ArrayList<String>());
	
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

	// Metodo Responsavel por solicitar calculo da rota de coleta de lixo
	// A rota é criada apartir de lixeiras que estão cheias e que possuem
	// StatusColetas como LIVRE
	public List<Rota> calcularRota(List<String> pontosLixeira,List<LixeiraEntity> lixeiras) {
		mensagemFactory = new MensagemFactory();
		
		List<String> pontosAlterarStatusColeta = new ArrayList<String>();
		
		

		for (LixeiraEntity ponto : lixeiras) {
			pontosAlterarStatusColeta.add(String.valueOf(ponto.getId()));
		}

		pontosLixeira.add(ConstantesPontosRota.LIXAO);
		
		mensagem = mensagemFactory.empacotar("Rota", "calcularRota", pontosLixeira);

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

	// Metodo Responsavvel por solicitar calculo da distancia entre duas
	// localizacoes
	public Double calcularDistancia(String origem, String destino) throws InvalidProtocolBufferException {
		mensagemFactory = new MensagemFactory();
		List<String> pontos = new ArrayList<>();

		pontos.add(origem);
		pontos.add(destino);

		mensagem = mensagemFactory.empacotar("Distancia", "calcularDistancia", pontos);

		Mensagem resposta = Mensagem.parseFrom(doOperations(mensagem));

		String distancia = new String(resposta.getArgumentos(0).toString(Charset.forName("utf-8")));
		return Double.valueOf(distancia);
	}

	// Metodo Responsavel por informar para o servidor quais lixeiras devem
	// mudar o estado de coleta
	public void alterarStatusColeta(String status, List<String> arguments) throws InvalidProtocolBufferException {
		mensagemFactory = new MensagemFactory();

		List<String> pontosAlterarStatus = new ArrayList<>();

		pontosAlterarStatus.add(status);

		for (String l : arguments) {
			pontosAlterarStatus.add(l);
		}

		mensagem = mensagemFactory.empacotar("Lixeira", "atualizarStatusColeta", pontosAlterarStatus);
		Mensagem resposta = Mensagem.parseFrom(doOperations(mensagem));
	}

	// Metodo Responsavel por enviar mensagem para o servidor e receber resposta
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