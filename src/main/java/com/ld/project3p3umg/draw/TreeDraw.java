package com.ld.project3p3umg.draw;

import com.ld.project3p3umg.dataStructure.avl.Node;
import com.ld.project3p3umg.domain.Server;
import com.ld.project3p3umg.services.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author luisdany
 */
@Component
@Slf4j
public class TreeDraw {

    private final ServerService serverService;

    private final String imageName = "/home/luisdany/p3/tree";
    private final String fileName = "/home/luisdany/p3/tree.dot";

    public TreeDraw(ServerService serverService) {
        this.serverService = serverService;
    }

    public String draw() throws IOException {
        throughTree(serverService.getTree().getRoot());
        writeFile();
        String name = generateImage();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            log.error("Error sleeping ",e);
        }
        sbTree = new StringBuilder();
        return imageAsBase64(name);
    }

    StringBuilder sbTree = new StringBuilder();

    public void throughTree(Node<Server> node) {
        if (node != null) {
            if (node.getFather() == null) {
                if(node.getLeft() == null && node.getRight() == null){
                    sbTree.append("\"");
                    sbTree.append(node.getData().getName());
                    sbTree.append("\"");
                    sbTree.append(";\n");
                }

                if (node.getLeft() != null) {
                    //isn't root node
                    sbTree.append("\"");
                    sbTree.append(node.getData().getName());
                    sbTree.append("\"");
                    sbTree.append(" ").append("->").append(" ").append("\"");
                    sbTree.append(node.getLeft().getData().getName()).append("\"");
                    sbTree.append(";\n");
                    throughTree(node.getLeft());
                }
                if (node.getRight() != null) {
                    sbTree.append("\"");
                    sbTree.append(node.getData().getName());
                    sbTree.append("\"");
                    sbTree.append(" ").append("->").append(" ").append("\"");
                    sbTree.append(node.getRight().getData().getName()).append("\"");
                    sbTree.append(";\n");
                    throughTree(node.getRight());
                }
            } else {
                if (node.getLeft() != null) {
                    //isn't root node
                    sbTree.append("\"");
                    sbTree.append(node.getData().getName());
                    sbTree.append("\"");
                    sbTree.append(" ").append("->").append(" ").append("\"");
                    sbTree.append(node.getLeft().getData().getName()).append("\"");
                    sbTree.append(";\n");
                    throughTree(node.getLeft());
                }
                if (node.getRight() != null) {
                    sbTree.append("\"");
                    sbTree.append(node.getData().getName());
                    sbTree.append("\"");
                    sbTree.append(" ").append("->").append(" ").append("\"");
                    sbTree.append(node.getRight().getData().getName()).append("\"");
                    sbTree.append(";\n");
                    throughTree(node.getRight());
                }
            }
        }
        log.info("throughTree finished -> {}", sbTree);
    }

    private void writeFile() throws IOException {
        String tree = "digraph graph_name {\n" +
                "  graph [\n" +
                "    charset = \"UTF-8\";\n" +
                "    label = \"Servers\",\n" +
                "    labelloc = \"t\",\n" +
                "    labeljust = \"c\",\n" +
                "    fontcolor = black,\n" +
                "    fontsize = 18,\n" +
                "    style = \"filled\",\n" +
                "    rankdir = TB,\n" +
                "    splines = spline,\n" +
                "    ranksep = 1.0,\n" +
                "    nodesep = 0.9\n" +
                "  ];\n" +
                "\n" +
                "  node [\n" +
                "    shape = circle\n" +
                "    colorscheme = \"rdylgn11\"\n" +
                "    style = \"solid,filled\",\n" +
                "    fontsize = 7,\n" +
                "    fontcolor = black,\n" +
                "    fontname = \"Migu 1M\",\n" +
                "    color = \"#EEEEEE\",\n" +
                "    fillcolor = \"#EEEEEE\",\n" +
                "    fixedsize = true,\n" +
                "    height = 1,\n" +
                "    width = 1\n" +
                "  ];\n" +
                "\n" +
                "  edge [\n" +
                "    style = solid,\n" +
                "    fontsize = 14,\n" +
                "    fontcolor = black,\n" +
                "    fontname = \"Migu 1M\",\n" +
                "    color = \"#31CEF0\",\n" +
                "    labelfloat = true,\n" +
                "    labeldistance = 2.5,\n" +
                "    labelangle = 70\n" +
                "  ];\n";

        tree += "\n" + sbTree.toString();

        tree += "\n}\n";

        log.info(tree);

        Path path = Paths.get(fileName);
        byte[] strToBytes = tree.getBytes();

        Files.write(path, strToBytes);
    }

    private String generateImage() {
        try {
            log.info("Generating image begins");
            ProcessBuilder processBuilder;
            String name = imageName + System.currentTimeMillis() + ".png";
            processBuilder = new ProcessBuilder("dot", "-Tpng", "-o", name, fileName);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();
            log.info("Generating image ends");
            return name;
        } catch (Exception e) {
            log.error("error generating image ",e);
        }
        return "";
    }

    private String imageAsBase64(String name){
        log.info("image as base 64");
        try{
            Path path = Paths.get(name);
            byte[] image = Files.readAllBytes(path);
            return Base64.encodeBase64String(image);
        }catch (IOException e){
            log.error("Error reading image ",e);
        }
        return "";

    }


}
