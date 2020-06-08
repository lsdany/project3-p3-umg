package com.ld.project3p3umg.dataStructure.hash;

import com.ld.project3p3umg.domain.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luisdany
 */
@Slf4j
public class HashTable {

    private final int size;
    private Resource value;
    private List<Resource>[] table;

    public HashTable(int size){
        this.size = size;
        table = new List[size];
    }

    public void add(final Resource value){
        this.value = value;
        int index = hashCode();
        log.info("index: {}", index);
        List<Resource> list;
        if(table[index] == null){
            list = new ArrayList<>();
        }else{
            list = table[index];
        }
        list.add(value);
        table[index] = list;
    }

    public Resource search(final String name){
        List<Resource> list = getListByIndex(name);
        if(list != null){
            return list.stream()
                    .filter(v -> v.getName().equalsIgnoreCase(name))
                    .findFirst().orElse(null);
        }
        return null;
    }

    public List<Resource> searchContent(final String content){
        List<Resource> resourceList = new ArrayList<>();
        for(List<Resource> l : table){
            if(l != null){
                Resource r = l.stream().filter(s -> s.getContent().contains(content)).findFirst().orElse(null);
                if(r!=null){
                    resourceList.add(r);
                }
            }
        }
        return resourceList;
    }


    public void delete(final String name){
        Resource resource = search(name);
        List<Resource> list = getListByIndex(name);
        list.remove(resource);
    }

    private List<Resource> getListByIndex(String name){
        int index = convertToInt(name) % size;
        log.info("index obtained: {}", index);
        return table[index];
    }


    @Override
    public int hashCode() {
        return convertToInt(value.getName()) % size;
    }

    private int convertToInt(String word){
        char[] characters = word.toCharArray();
        int sum = 0;
        for (char c : characters) {
            sum += c;
        }
        log.info("sum {}", sum);
        return sum;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "size=" + size +
                ", value=" + value +
                ", table=" + Arrays.toString(table) +
                '}';
    }
}
