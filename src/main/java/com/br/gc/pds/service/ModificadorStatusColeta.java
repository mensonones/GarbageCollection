package com.br.gc.pds.service;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.net.Proxy;
import com.google.protobuf.InvalidProtocolBufferException;

public interface ModificadorStatusColeta {
	public void atulaizarStatusColeta(Proxy proxy,ColetaEntity coleta,ColetaService coletaService) throws InvalidProtocolBufferException;
}
