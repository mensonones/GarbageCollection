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
		double limiteCarga = caminhao.getCapacidadeTotal();
		coleta = new ColetaEntity();
		for (Lixeira lixeiraProtocol : lixeirasProtocol) {
			if (lixeiraProtocol.getStatusCapacidade().equals(Lixeira.StatusCapacidade.CHEIA)
					&& lixeiraProtocol.getStatusColeta().equals(Lixeira.StatusColeta.LIVRE)
					&& limiteCarga - lixeiraProtocol.getPeso() >= 0) {
				
				LixeiraEntity lixeira = lixeiraFactory.factoryLixeira(lixeiraProtocol);
				limiteCarga = limiteCarga - lixeiraProtocol.getPeso();
				coleta.getLixeiras().add(lixeira);
			}
		}

		return coleta;
	}
}
