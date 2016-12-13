package com.br.gc.pds.factory;

import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.model.Lixeiras.Lixeira;

public class LixeiraFactory {
	private LixeiraEntity lixeiraEntity;
	public LixeiraEntity factoryLixeira(Lixeira lixeiraProtocol) {
		lixeiraEntity = new LixeiraEntity();

		lixeiraEntity.setId(Long.valueOf(lixeiraProtocol.getId()));
		lixeiraEntity.setLocalizacao(lixeiraProtocol.getLocalizacao());
		lixeiraEntity.setPeso(lixeiraProtocol.getPeso());
		lixeiraEntity.setStatusCapacidade(lixeiraProtocol.getStatusCapacidade());
		lixeiraEntity.setStatusColeta(lixeiraProtocol.getStatusColeta());
		return lixeiraEntity;
	}
}
