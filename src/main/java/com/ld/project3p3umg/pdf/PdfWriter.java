package com.ld.project3p3umg.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.ld.project3p3umg.domain.Visit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author luisdany
 */
@Component @Slf4j
public class PdfWriter {

    public void writeFile(List<Visit> taskList){

        try{
            Document document = new Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream("project_report.pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
//            Chunk courseName = new Chunk(course, font);

//            document.add(courseName);

            PdfPTable table = new PdfPTable(4);

            addTableHeader(table);
            for(Visit v : taskList){
                int count = Collections.frequency(taskList, v);
                v.setCount(count);
            }

            Set<Visit> visitSet = new HashSet<>(taskList);

            for(Visit v : visitSet){
                table.addCell(v.getWebSite());
                table.addCell(v.getIp());
                table.addCell(v.getDate().toString());
                table.addCell(""+v.getCount());
            }


            document.add(table);
            document.close();
        }catch (IOException | DocumentException e){
            log.error("Error writing file",e);
        }

    }


    private void addTableHeader(PdfPTable table) {
        Stream.of("Web Site", "Ip", "Date", "#")
                .forEach(column -> {
                    PdfPCell studentHeader = new PdfPCell();
                    studentHeader.setBackgroundColor(BaseColor.GREEN);
                    studentHeader.setBorderWidth(1);
                    studentHeader.setPhrase(new Phrase(column));
                    table.addCell(studentHeader);
                });
    }

    private void addRows(PdfPTable table, Set<Visit> list) {
        list.forEach(t -> table.addCell(t.getWebSite()));
        list.forEach(t -> table.addCell(t.getIp()));
        list.forEach(t -> table.addCell(t.getDate().toString()));
        list.forEach(t -> table.addCell(""+t.getCount()));
    }

}
