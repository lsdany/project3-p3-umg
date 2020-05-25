package com.ld.project3p3umg.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ld.project3p3umg.domain.ResourcesJson;
import com.ld.project3p3umg.domain.ServerJson;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class JsonParse {

    private String fileContent;
    private JsonFile jsonFile;


    public void parseContent(String path){
        this.fileContent = jsonFile.readFile(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ServerJson[] serverJsons = mapper.readValue(fileContent, ServerJson[].class);
//            return getUsers(userJsons);
        } catch (JsonProcessingException e) {
            log.error("error parsing json file ", e);
        }
//        return null;
    }


//    public CustomList<User> parseContentFromIS(InputStream inputStream){
//        this.fileContent = fileJsonReader.readFromStream(inputStream);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            UserJson[] userJsons = mapper.readValue(fileContent, UserJson[].class);
//            return getUsers(userJsons);
//        } catch (JsonProcessingException e) {
//            //TODO manage exception
//            e.printStackTrace();
//        }
//        return null;
//    }


    private void getUsers(ServerJson[] serverJsons){
//        CustomList<User> userList = new SimpleList<>();
        for(ServerJson s : serverJsons){

            ResourcesJson[] resourcesJsons = s.getResources();
//            CustomList<Course> courseList = new DoubleList<>();
            int idCounter = 0;
            for(ResourcesJson r : resourcesJsons){

//                Course course = Course.builder().id(idCounter++).name(c.getName()).students(studentList).build();
//                courseList.add(course);

            }
//            User user = User.builder().name(u.getName()).password(u.getPassword()).user(u.getUser()).courses(courseList).build();
//            userList.add(user);
        }
//        return userList;
    }

}
