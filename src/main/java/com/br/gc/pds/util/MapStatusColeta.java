package com.br.gc.pds.util;

import com.br.gc.pds.service.StatusCanceladoColeta;
import com.br.gc.pds.service.StatusConcluidoColeta;

public class MapStatusColeta {

	public Object getInstance(String status) {

		switch (status) {
		case "completo":
			return new StatusConcluidoColeta();
		case "cancelado":
			return new StatusCanceladoColeta();
		default:
			return null;
		}

	}
}
