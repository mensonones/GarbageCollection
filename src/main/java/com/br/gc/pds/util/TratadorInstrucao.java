package com.br.gc.pds.util;

public class TratadorInstrucao {
	public String tratarIntruncao(String instrucao){
		instrucao = instrucao.replaceAll("<b>","");
		instrucao = instrucao.replaceAll("</b>","");
		instrucao = instrucao.replaceAll("<div","");
		instrucao = instrucao.replaceAll(":0.9em","");
		instrucao = instrucao.replaceAll("style=","");
		instrucao = instrucao.replaceAll("font-size","");
		instrucao = instrucao.replaceAll("</div>","");
		instrucao = instrucao.replaceAll(">", "");
		return instrucao;
	}
}
