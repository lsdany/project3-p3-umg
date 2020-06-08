package com.ld.project3p3umg.parse;

import com.ld.project3p3umg.dataStructure.list.List;
import com.ld.project3p3umg.dataStructure.list.Node;
import com.ld.project3p3umg.domain.Visit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author luisdany
 */
@Slf4j @Component
public class ListParse {

    public java.util.List<Visit> parse(List<Visit> visits){
        java.util.List<Visit> temp = new ArrayList<>();
        if(visits!= null){
            Node<Visit> visit = visits.getStart();
            while(visit != null){
                Visit v = visit.getData();
                temp.add(v);
                visit = visit.getLeft();
            }
        }
        return temp;
    }

    public List<Visit> parseList(List<Visit> visits){
        java.util.List<Visit> temp = new ArrayList<>();
        if(visits!= null){
            Node<Visit> visit = visits.getStart();
            while(visit != null){
                Visit v = visit.getData();
                temp.add(v);
                visit = visit.getLeft();
            }
        }

        temp = temp.stream().sorted(Comparator.comparing(Visit::getWebSite)).collect(Collectors.toList());

        List<Visit> orderVisits = new List<>();
        for(Visit v : temp){
            orderVisits.add(v);
        }

        return orderVisits;
    }

}
