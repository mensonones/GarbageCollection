package com.br.gc.pds.service;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.net.Proxy;
import com.google.protobuf.InvalidProtocolBufferException;

public interface ModificadorStatusColeta {
	public ColetaEntity atulaizarStatusColeta(Proxy proxy,ColetaEntity coleta) throws InvalidProtocolBufferException;
}
