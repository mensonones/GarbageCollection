package com.br.gc.pds.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioRota {
	String nomePDF;
	Document document = new Document();
	PdfPCell garbage, relatorio,gerado;
	Paragraph campo4, campo5, campo6;
	Font fonte, fonte2, fonte3;
	Date data = new Date();
	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	

	public void gerarRelatorioRota(ColetaEntity coleta , List<Rota> rotas) {
		TratadorInstrucao tratador = new TratadorInstrucao();
		try {
			nomePDF = "Coleta-"+coleta.getIdColeta();
			PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Carlos/git/GarbageCollection/src/main/resources/relatorios/coletas/" + nomePDF + ".PDF"));
			document.open();
			
			fonte = new Font(FontFamily.COURIER, 18, Font.BOLD);
			fonte2 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			fonte3 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			
			garbage = new PdfPCell(new Phrase("GARBAGE COLLECTOR ", fonte));
			garbage.setHorizontalAlignment(Element.ALIGN_CENTER);
			garbage.setBorder(Rectangle.NO_BORDER);
			relatorio = new PdfPCell(new Phrase("RELATÓRIO DE COLETAS", fonte2));
			relatorio.setHorizontalAlignment(Element.ALIGN_CENTER);
			relatorio.setBorder(Rectangle.NO_BORDER);
			gerado = new PdfPCell(new Phrase("Gerado em: " + formatador.format(data), fonte2));
			gerado.setHorizontalAlignment(Element.ALIGN_CENTER);
			gerado.setBorder(Rectangle.NO_BORDER);
			
			Image figura = Image.getInstance("C:/Users/Carlos/git/GarbageCollection/src/main/resources/static/imagem/arte.jpg");
			figura.setBorder(Rectangle.NO_BORDER);
			figura.scaleAbsolute(100f, 100f);
			
			PdfPTable table = new PdfPTable(2);
			PdfPTable table2 = new PdfPTable(1);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			table2.addCell(garbage);
			table2.addCell(relatorio);
			table2.addCell(gerado);
			PdfPCell figuraCelula = new PdfPCell(figura, false);
			table.addCell(figuraCelula).setBorder(Rectangle.NO_BORDER);
			table.addCell(table2);
			table.setSpacingAfter(20f);
			document.add(table);
			
			PdfPTable table3 = new PdfPTable(3);
			table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell("CAMINHÃO");
			table3.addCell("DATA/HORA");
			table3.addCell("QTD.LIXEIRAS");
			table3.addCell(coleta.getCaminhao().getPlaca());
			table3.addCell(coleta.getDataColeta());
			table3.addCell(String.valueOf(coleta.getLixeiras().size()));
			document.add(table3);
			
			for(Rota rota : rotas){
				Paragraph origem = new Paragraph("Origem: "+rota.getEnderecoOrigem(), fonte3);
				origem.setSpacingBefore(12);
				
				Paragraph destino = new Paragraph("Destino: "+rota.getEnderecoDestino(), fonte3);
				destino.setSpacingAfter(12);
				
				document.add(origem);
				document.add(destino);
				
				for(Passo passo : rota.getPassoList()){
					Paragraph trajeto = new Paragraph(tratador.tratarIntruncao(passo.getInstrucao()));
					document.add(trajeto);
					Paragraph distancia = new Paragraph("Distância: "+passo.getDistancia());
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
}	