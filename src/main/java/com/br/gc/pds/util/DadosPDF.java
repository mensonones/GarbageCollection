package com.br.gc.pds.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.model.Rotas.Passo;
import com.br.gc.pds.model.Rotas.Rota;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class DadosPDF {
	String nomePDF;
	Document document = new Document();
	Paragraph titulo, enderecoOrigem,enderecoDestino,trajeto, distancia, caminhao;
	Font fonte;

	public void gerarRelatorioRota(ColetaEntity coleta,List<Rota> rotas) {
		TratadorInstrucao tratador = new TratadorInstrucao();
		try {
			nomePDF = "Coleta - "+coleta.getIdColeta();
			String endereco = "C:/Users/Carlos/Desktop/";
			PdfWriter.getInstance(document, new FileOutputStream(endereco + nomePDF + ".PDF"));
			document.open();
			
			fonte = new Font(FontFamily.COURIER, 16, Font.BOLD);
			titulo = new Paragraph("GARBAGE COLLECTION", fonte);
			titulo.setAlignment(Element.ALIGN_CENTER);			
			document.add(titulo);
			
			Image figura = Image.getInstance("C:/Users/Carlos/Desktop/arte.jpg");
			figura.setAlignment(Element.ALIGN_CENTER);
			figura.setSpacingAfter(10);
			document.add(figura);
			caminhao = new Paragraph("Caminhao: "+coleta.getCaminhao().getPlaca());
			caminhao.setSpacingAfter(10);
			document.add(caminhao);
			for(Rota rota : rotas){
				enderecoOrigem = new Paragraph("Origem: "+ rota.getEnderecoOrigem());
				enderecoOrigem.setSpacingAfter(10);
				document.add(enderecoOrigem);
				
				enderecoDestino = new Paragraph("Destino: " + rota.getEnderecoDestino());
				enderecoDestino.setSpacingAfter(10);
				document.add(enderecoDestino);
				
				for(Passo passo : rota.getPassoList()){
					trajeto = new Paragraph("Passo:\n"+tratador.tratarIntruncao(passo.getInstrucao()));
					trajeto.setSpacingAfter(10);
					document.add(trajeto);
					
					distancia = new Paragraph("Distância: "+passo.getDistancia());
					distancia.setSpacingAfter(10);
					document.add(distancia);
				}
			}			
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}
	
/*public void exibirDados(ColetaEntity coleta,List<Rota> rota) {
		
		try {
			nomePDF = "Coleta "+coleta.getId();
			String endereco = "C:/Users/Carlos/Desktop/";
			PdfWriter.getInstance(document, new FileOutputStream(endereco + nomePDF + ".PDF"));
			document.open();
			
			fonte = new Font(FontFamily.COURIER, 16, Font.BOLD);
			campo1 = new Paragraph("GARBAGE COLLECTION", fonte);
			campo1.setAlignment(Element.ALIGN_CENTER);			
			document.add(campo1);
			
			Image figura = Image.getInstance("C:/Users/Carlos/Desktop/arte.jpg");
			figura.setAlignment(Element.ALIGN_CENTER);
			figura.setSpacingAfter(10);
			document.add(figura);
			
			campo2 = new Paragraph("Serviço : " + "Brunagem");
			campo2.setSpacingAfter(10);
			document.add(campo2);
			
			campo3 = new Paragraph("Quantidade de lixeiras : " + "4");
			campo3.setSpacingAfter(10);
			document.add(campo3);
			
			campo4 = new Paragraph("Caminhão : " + "Carro pipa");
			campo4.setSpacingAfter(10);
			document.add(campo4);
			
			campo5 = new Paragraph("Quantidade de rota : " + "10");
			campo5.setSpacingAfter(10);
			document.add(campo5);
			
			campo6 = new Paragraph("Menor rota : " + "Rua Epitácio Pessoa");
			campo6.setSpacingAfter(10);
			document.add(campo6);
			
			campo7 = new Paragraph("Peso da rota escolhida : " + "200km");
			campo7.setSpacingAfter(10);
			document.add(campo7);
						
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}*/
}	