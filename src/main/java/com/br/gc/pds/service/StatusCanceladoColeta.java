package com.br.gc.pds.service;

import java.util.ArrayList;
import java.util.List;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.net.Proxy;
import com.br.gc.pds.util.StatusCaminhaoColeta;
import com.br.gc.pds.util.ValorStatusColeta;
import com.google.protobuf.InvalidProtocolBufferException;

public class StatusCanceladoColeta implements ModificadorStatusColeta{

	@Override
	public void atulaizarStatusColeta(Proxy proxy, ColetaEntity coleta,ColetaService coletaService) throws InvalidProtocolBufferException {
		coleta.getCaminhao().setStatusCaminhaColeta(StatusCaminhaoColeta.LIVRE);
		coleta.setStatusColeta(ValorStatusColeta.CANCELADO);
		
		List<String> pontosAlterarStatus = new ArrayList<>();
		
		for (LixeiraEntity l : coleta.getLixeiras()) {
			pontosAlterarStatus.add(String.valueOf(l.getId()));
		}

		proxy.alterarStatusColeta("1", pontosAlterarStatus);
		coletaService.salvar(coleta);
	}

}
