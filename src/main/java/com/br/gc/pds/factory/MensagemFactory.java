package com.br.gc.pds.factory;

import java.nio.charset.Charset;
import java.util.List;

import com.br.gc.pds.model.Message.Mensagem;
import com.google.protobuf.ByteString;

public class MensagemFactory {
	
	
	public Mensagem empacotar(String objectReference, String methodId, List<String> arguments) {
		
		Mensagem.Builder builder = Mensagem.newBuilder();
		builder.setTipo(0);
		builder.setId(1);
		builder.setObjeto(objectReference);
		builder.setMetodo(methodId);
		for (String arg : arguments) {
			builder.addArgumentos(ByteString.copyFrom(arg, Charset.forName("utf-8")));
		}

		return builder.build();
	}
}
