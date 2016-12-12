package com.br.gc.pds;

import java.io.FileOutputStream;
import java.io.IOException;
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
	Paragraph campo1, campo2,campo3,campo4, campo5, campo6, campo7;
	Font fonte;

	public void exibirDados() {
		try {
			nomePDF = "Bruno";
			PdfWriter.getInstance(document, new FileOutputStream("/home/leonardo/Downloads/" + nomePDF + ".PDF"));
			document.open();
			
			fonte = new Font(FontFamily.COURIER, 16, Font.BOLD);
			campo1 = new Paragraph("GARBAGE COLLECTION", fonte);
			campo1.setAlignment(Element.ALIGN_CENTER);			
			document.add(campo1);
			
			Image figura = Image.getInstance("/home/leonardo/Downloads/imageGB.png");
			figura.setAlignment(Element.ALIGN_CENTER);
			figura.setSpacingAfter(10);
			document.add(figura);
			
			campo2 = new Paragraph("Serviço : " + " Reciclagem");
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
	}
}	