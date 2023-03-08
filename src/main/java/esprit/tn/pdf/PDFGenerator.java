package esprit.tn.pdf;


import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import esprit.tn.Dto.AnnonceDto;
import esprit.tn.Entites.Announcement;
import lombok.Setter;

@Setter
public class PDFGenerator {
    private List<AnnonceDto> annonceDtos;
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        // Create the Object of Document
        Document document = new Document(PageSize.A4);
        // get the document and write the response to output stream
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // Add Font
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Create Object of Paragraph
        Paragraph paragraph = new Paragraph("Devis", fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        // Add to the document
        document.add(paragraph);
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 7, 7, 7, 7, 7, 7, 7});
        table.setSpacingBefore(7);
        // Create Table Header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(7);
        // Add Font
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Type Annonce", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix d'annonce", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Discount", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categorie d'annonce", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Type de meuble", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix de meuble", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantité des meubles", font));
        table.addCell(cell);



        for (AnnonceDto annonceDto : annonceDtos) {
            table.addCell(String.valueOf(annonceDto.getTypeA()));
            table.addCell(String.valueOf(annonceDto.getPriceA()));
            table.addCell(String.valueOf(annonceDto.getDiscount()));
            table.addCell(String.valueOf(annonceDto.getCategory()));
            table.addCell(String.valueOf(annonceDto.getType()));
            table.addCell(String.valueOf(annonceDto.getPriceS()));
            table.addCell(String.valueOf(annonceDto.getQuantite()));

        }
        // Add table to document
        document.add(table);
        document.close();
    }

}
