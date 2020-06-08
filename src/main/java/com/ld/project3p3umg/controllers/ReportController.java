package com.ld.project3p3umg.controllers;

import com.ld.project3p3umg.domain.Visit;
import com.ld.project3p3umg.pdf.PdfReader;
import com.ld.project3p3umg.pdf.PdfWriter;
import com.ld.project3p3umg.services.ListReportService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author luisdany
 */
@Controller
public class ReportController {

    private final ListReportService listReportService;
    private final PdfWriter pdfWriter;
    private final PdfReader pdfReader;

    public ReportController(ListReportService listReportService, PdfWriter pdfWriter, PdfReader pdfReader) {
        this.listReportService = listReportService;
        this.pdfWriter = pdfWriter;
        this.pdfReader = pdfReader;
    }


    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public FileSystemResource viewReport(Model model) {
        List<Visit> list = listReportService.getList();
        pdfWriter.writeFile(list);
        return new FileSystemResource(pdfReader.readFile());
    }

}
