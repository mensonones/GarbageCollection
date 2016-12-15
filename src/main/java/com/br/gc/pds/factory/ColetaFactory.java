package com.br.gc.pds.factory;

import java.util.List;

import com.br.gc.pds.model.Caminhao;
import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.LixeiraEntity;
import com.br.gc.pds.model.Lixeiras.Lixeira;

public class ColetaFactory implements Factory {

	private LixeiraFactory lixeiraFactory;
	private ColetaEntity coleta;
	private List<Lixeira> lixeirasProtocol;
	private Caminhao caminhao;
	
	public ColetaFactory(List<Lixeira> lixeirasProtocol,Caminhao caminhao) {
		this.lixeirasProtocol = lixeirasProtocol;
		this.caminhao = caminhao;
	}
	
	@Override
	public void factory() {
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
	}

	@Override
	public Object getFactory() {
		return coleta;
	}
}
