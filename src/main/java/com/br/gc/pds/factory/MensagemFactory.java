package com.br.gc.pds.factory;

import java.nio.charset.Charset;
import java.util.List;

import com.br.gc.pds.model.Message.Mensagem;
import com.google.protobuf.ByteString;

public class MensagemFactory implements Factory {
	private String objectReference;
	private String methodId; 
	private List<String> arguments;
	private Mensagem.Builder builder;
	
	public MensagemFactory(String objectReference,String methodId,List<String> arguments) {
		this.objectReference = objectReference;
		this.methodId = methodId;
		this.arguments = arguments;
	}
	
	@Override
	public void factory() {
		builder = Mensagem.newBuilder();
		builder.setTipo(0);
		builder.setId(1);
		builder.setObjeto(objectReference);
		builder.setMetodo(methodId);
		for (String arg : arguments) {
			builder.addArgumentos(ByteString.copyFrom(arg, Charset.forName("utf-8")));
		}		
	}

	@Override
	public Object getFactory() {
		return builder.build();
	}
}
