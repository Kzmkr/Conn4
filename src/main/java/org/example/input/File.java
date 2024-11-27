package org.example.input;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.example.models.Board;
import org.example.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The File class provides methods to write and read game data to and from an XML file.
 */
public class File {

    private static final Logger log = LoggerFactory.getLogger(File.class);

    /**
     * Writes the game data to an XML file.
     *
     * @param path the path to the XML file
     * @param b the Board object containing the game board data
     * @param p the Player object containing the player data
     * @return true if the data was successfully written, false otherwise
     */
    public boolean write(String path, Board b, Player p) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Create root element
            Element root = document.createElement("Game");
            document.appendChild(root);

            // Create player element
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

            // Create board element
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

            // Add board cells
            for (int i = 0; i < b.getH(); i++) {
                Element row = document.createElement("Row");
                for (int j = 0; j < b.getW(); j++) {
                    Element cell = document.createElement("Cell");
                    cell.appendChild(document.createTextNode(String.valueOf(b.getI(i, j))));
                    row.appendChild(cell);
                }
                board.appendChild(row);
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);
            return true;

        } catch (ParserConfigurationException | TransformerException e) {
            return false;
        }
    }

    /**
     * Reads the game data from an XML file.
     *
     * @param filename the path to the XML file
     * @return an array containing the Board and Player objects, or null if an error occurs
     */
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
                    board.setI(i, j, value);
                }
            }
            return new Object[]{board, player};

        } catch (Exception e) {
            log.error("e: ", e);
            return null;
        }
    }
}