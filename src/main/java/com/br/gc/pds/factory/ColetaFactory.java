package com.br.gc.pds.factory;

import java.util.List;

import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.model.Lixeiras.Lixeira;

public class ColetaFactory {

	private LixeiraFactory lixeiraFactory;
	private ColetaEntity coleta;

	public ColetaEntity factoryLixeirasColeta(List<Lixeira> lixeirasProtocol, Caminhao caminhao) {
		lixeiraFactory = new LixeiraFactory();
		coleta = new ColetaEntity();
		for (Lixeira lixeiraProtocol : lixeirasProtocol) {
			if (lixeiraProtocol.getStatusCapacidade().equals(Lixeira.StatusCapacidade.CHEIA)
					&& lixeiraProtocol.getStatusColeta().equals(Lixeira.StatusColeta.LIVRE)
					&& caminhao.getCapacidadeTotal() - lixeiraProtocol.getPeso() <= caminhao.getCapacidadeTotal()) {
		
				LixeiraEntity lixeira = lixeiraFactory.factoryLixeira(lixeiraProtocol);
				caminhao.setCapacidadeTotal(caminhao.getCapacidadeTotal() - lixeiraProtocol.getPeso());
				coleta.getLixeiras().add(lixeira);
			}
		}

		return coleta;
	}
}
