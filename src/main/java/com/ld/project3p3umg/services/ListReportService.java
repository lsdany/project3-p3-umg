package com.ld.project3p3umg.services;

import com.ld.project3p3umg.dataStructure.list.List;
import com.ld.project3p3umg.domain.SearchResult;
import com.ld.project3p3umg.domain.Visit;
import com.ld.project3p3umg.parse.ListParse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Service @Slf4j
public class ListReportService {

    private List<Visit> visitList = new List<>();
    private final ListParse listParse;

    public ListReportService(ListParse listParse) {
        this.listParse = listParse;
    }

    public void addToList(SearchResult result){
        if(result != null && result.getResource() != null && result.getServer() != null){
            Visit visit = new Visit();
            visit.setDate(LocalDateTime.now());
            try {
                visit.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                log.error("Error obtaining ip ", e);
            }
            visit.setWebSite(result.getServer().getWebsite());
            visitList.add(visit);

            visitList = listParse.parseList(visitList);
        }
    }

    public java.util.List<Visit> getList() {
        return listParse.parse(visitList);
    }
}
