package com.br.gc.pds.factory;

import java.util.List;

import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.model.Lixeiras.Lixeira;
import com.br.gc.pds.service.LixeiraService;

public class ColetaFactory {
	
	private LixeiraFactory lixeiraFactory;
	
	public ColetaEntity factoryColeta(List<Lixeira> lixeirasProtocol,Caminhao caminhao){
		lixeiraFactory = new LixeiraFactory();
		if(lixeirasProtocol != null){
			ColetaEntity coleta = new ColetaEntity();
			for (Lixeira lixeiraProtocol : lixeirasProtocol) {
				if(lixeiraProtocol.getStatusCapacidade().equals(Lixeira.StatusCapacidade.CHEIA) && lixeiraProtocol.getStatusColeta().equals(Lixeira.StatusColeta.LIVRE)){
					LixeiraEntity lixeira = lixeiraFactory.factoryLixeira(lixeiraProtocol);
					coleta.getLixeiras().add(lixeira);
				}
			}
			//coleta.setCaminhao(caminhao);
			return coleta;
		}
		
		return null;
	}
}
