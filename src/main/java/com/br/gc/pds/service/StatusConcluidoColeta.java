package com.br.gc.pds.service;

import java.util.ArrayList;
import java.util.List;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.net.Proxy;
import com.br.gc.pds.util.RelatorioColeta;
import com.br.gc.pds.util.StatusCaminhaoColeta;
import com.br.gc.pds.util.ValorStatusColeta;
import com.google.protobuf.InvalidProtocolBufferException;

public class StatusConcluidoColeta implements ModificadorStatusColeta{
	private RelatorioColeta relatorio;
	
	@Override
	public void atulaizarStatusColeta(Proxy proxy,ColetaEntity coleta,ColetaService coletaService) throws InvalidProtocolBufferException {
		relatorio = new RelatorioColeta();
		coleta.getCaminhao().setStatusCaminhaColeta(StatusCaminhaoColeta.LIVRE);
		coleta.setStatusColeta(ValorStatusColeta.COMPLETO);
		
		List<String> pontosAlterarStatus = new ArrayList<>();
		
		for (LixeiraEntity l : coleta.getLixeiras()) {
			pontosAlterarStatus.add(String.valueOf(l.getId()));
		}
		
		relatorio.gerarRelatorioRota(coletaService);
		coletaService.salvar(coleta);
		proxy.alterarStatusColeta("1", pontosAlterarStatus);
	}

}
