package com.br.gc.pds.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.br.gc.pds.model.ColetaEntity;
import com.br.gc.pds.service.ColetaService;
import com.itextpdf.text.Anchor;
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

public class RelatorioColeta {
	String nomePDF;
	Document document = new Document();
	PdfPCell garbage, relatorio,gerado;
	Paragraph campo4, campo5, campo6;
	Font fonte, fonte2;
	Date data = new Date();
	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	
	public void gerarRelatorioRota(ColetaService coletaService) {
		List<ColetaEntity> coletas = coletaService.listar();
		try {
			nomePDF = "RelatorioColeta";
			PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Carlos/git/GarbageCollection/src/main/resources/relatorios/coletas/" + nomePDF + ".PDF"));
			document.open();
			
			fonte = new Font(FontFamily.COURIER, 18, Font.BOLD);
			fonte2 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			
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
			table3.addCell("ID");
			table3.addCell("DATA/HORA");
			table3.addCell("ROTA");
			document.add(table3);
			PdfPTable table4 = new PdfPTable(3);
			table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			for(ColetaEntity coleta : coletas){
				if(coleta.getStatusColeta().equals(ValorStatusColeta.COMPLETO)){
					table4.addCell(String.valueOf(coleta.getIdColeta()));
					table4.addCell(coleta.getDataColeta());
					Anchor link = new Anchor("Visualizar Rota");
					link.setReference("Coleta-"+coleta.getIdColeta()+".PDF");
					table4.addCell(link);
				}
			}
				
			document.add(table4);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}
}
