package com.br.gc.pds.factory;

import java.util.List;

import com.br.gc.pds.net.Proxy;
import com.br.gc.pds.util.Distancia;
import com.google.protobuf.InvalidProtocolBufferException;

public class GeradorRota {

	public List<String> gerarRota(List<String> lixeiras) throws InvalidProtocolBufferException {
		Proxy proxy = new Proxy();

		for (int i = 0; i < lixeiras.size() - 1; i++) {
			Distancia distanciaComp = null;
			for (int j = i + 1; j < lixeiras.size(); j++) {
				Distancia distancia = new Distancia();
				distancia.setLixeira_origem(lixeiras.get(i));
				distancia.setLixeira_destino(lixeiras.get(j));
				distancia.setDistancia(
						proxy.calcularDistancia(lixeiras.get(i), lixeiras.get(j)));

				if (distanciaComp == null) {
					distanciaComp = distancia;
				} else {
					if (distancia.getDistancia() < distanciaComp.getDistancia()) {

						String lixeiraAux = distanciaComp.getLixeira_destino();
						lixeiras.remove(i + 1);
						lixeiras.add(i + 1, distancia.getLixeira_destino());
						lixeiras.remove(j);
						lixeiras.add(j, lixeiraAux);

						distanciaComp = distancia;
					}
				}
			}
		}
		return lixeiras;

	}
}
