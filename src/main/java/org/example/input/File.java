package org.example.input;


import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.example.models.Board;
import org.example.models.LeaderBoard;
import org.example.models.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Intereact with file.
 */

public class File {
    /**
     * Write map out to a file.
     */


    public boolean write(String path, Board b, Player p) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.newDocument();

            Element root = document.createElement("Game");
            document.appendChild(root);

            Element player = document.createElement("Player");
            root.appendChild(player);

            Element name = document.createElement("Name");
            name.appendChild(document.createTextNode(p.getName()));
            player.appendChild(name);

            Element color = document.createElement("Color");
            color.appendChild(document.createTextNode(String.valueOf(p.getColor())));
            player.appendChild(color);

            Element symbol = document.createElement("Symbol");
            symbol.appendChild(document.createTextNode(p.getSymbol()));
            player.appendChild(symbol);


            Element board = document.createElement("Board");
            root.appendChild(board);

            Element dimensions = document.createElement("Dimensions");
            Element height = document.createElement("Height");
            height.appendChild(document.createTextNode(String.valueOf(b.getH())));
            Element width = document.createElement("Width");
            width.appendChild(document.createTextNode(String.valueOf(b.getW())));

            dimensions.appendChild(height);
            dimensions.appendChild(width);

            board.appendChild(dimensions);

            for (int i = 0; i < b.getH(); i++) {
                Element row = document.createElement("Row");
                for (int j = 0; j < b.getW(); j++) {
                    Element cell = document.createElement("Cell");
                    cell.appendChild(document.createTextNode(String.valueOf(b.geti(i, j))));
                    row.appendChild(cell);
                }
                board.appendChild(row);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }


        return false;
    }


    public Object[] read(String filename) {

        try {
            java.io.File xmlFile = new java.io.File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Read Player
            Element playerElement = (Element) document.getElementsByTagName("Player").item(0);
            String name = playerElement.getElementsByTagName("Name").item(0).getTextContent();
            int color = Integer.parseInt(playerElement.getElementsByTagName("Color").item(0).getTextContent());
            String symbol = playerElement.getElementsByTagName("Symbol").item(0).getTextContent();
            Player player = new Player(1, name, color, symbol);

            // Read Board
            Element boardElement = (Element) document.getElementsByTagName("Board").item(0);
            int height = Integer.parseInt(boardElement.getElementsByTagName("Height").item(0).getTextContent());
            int width = Integer.parseInt(boardElement.getElementsByTagName("Width").item(0).getTextContent());
            Board board = new Board(height, width);

            NodeList rows = boardElement.getElementsByTagName("Row");
            for (int i = 0; i < rows.getLength(); i++) {
                Element row = (Element) rows.item(i);
                NodeList cells = row.getElementsByTagName("Cell");
                for (int j = 0; j < cells.getLength(); j++) {
                    int value = Integer.parseInt(cells.item(j).getTextContent());
                    board.seti(i, j, value);
                }
            }
            return new Object[]{board, player};

        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }


    public void filewrite(Board b, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(String.valueOf(b.getH()) + " " + String.valueOf(b.getW()) + "\n");
            for (int i = 0; i < b.getH(); i++) {
                for (int j = 0; j < b.getW(); j++) {
                    myWriter.write(String.valueOf(b.geti(i, j)) + " ");
                }
                myWriter.write("\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");

        }

    }

    public LeaderBoard readLeaderBoard(String filename) {
        try {
            java.io.File xmlFile = new java.io.File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Read Player
            Element playerElement = (Element) document.getElementsByTagName("Player").item(0);
            Element Number = (Element) document.getElementsByTagName("Number").item(0);

            String[] names = new String[Integer.parseInt(Number.getTextContent())];
            int[] scores = new int[(Integer.parseInt(Number.getTextContent()))];
            for (int i = 0; i < Integer.parseInt(Number.getTextContent()); i++) {
                String name = playerElement.getElementsByTagName("Name").item(i).getTextContent();
                String score = playerElement.getElementsByTagName("Name").item(i).getTextContent();
                names[i] = name;
                scores[i] = Integer.parseInt(score);
            }

            LeaderBoard lb = new LeaderBoard(names, scores);


            return lb;

        } catch (Exception e) {
            return null;
        }
    }

    public void writeLeaderBoard(String filename, String name) {

        LeaderBoard lb = readLeaderBoard(filename);

        try {

            for (int i = 0; i < lb.getSize(); i++) {
                if (lb.getNames()[i].equals(name)) {
                    lb.getScores()[i]++;
                } else {
                    lb.add(name);
                }
            }


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.newDocument();

            Element root = document.createElement("LeaderBoard");
            document.appendChild(root);

            Element number = document.createElement("Number");
            number.appendChild(document.createTextNode(String.valueOf(lb.getSize())));
            root.appendChild(number);

            Element player = document.createElement("Player");
            root.appendChild(player);

            for (int i = 0; i < lb.getNames().length; i++) {
                Element name1 = document.createElement("Name");
                name1.appendChild(document.createTextNode(lb.getNames()[i]));
                player.appendChild(name1);

                Element score = document.createElement("Score");
                score.appendChild(document.createTextNode(String.valueOf(lb.getScores()[i])));
                player.appendChild(score);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(filename);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }


    }
}