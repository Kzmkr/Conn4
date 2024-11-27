package org.example.ui;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.example.models.HighScore;

/**
 * The HighscorePrinter class provides methods to render high scores in a formatted table.
 */
public class HighscorePrinter {
    AsciiTable at = new AsciiTable();
    String[] header = {"Name", "Win", "Lose", "Ratio"};

    /**
     * Renders the high scores in a formatted ASCII table.
     *
     * @param hs the HighScore object containing the high scores
     * @return a string representation of the formatted high scores table
     */
    public String render(HighScore hs) {
        at.addRule();
        at.addRow(header);

        at.addRule();
        String name;
        int win;
        int lose;
        float ratio;
        for (int i = 0; i < hs.getSize(); i++) {
            name = hs.getNameAt(i);
            win = hs.getWinScoreAt(i);
            lose = hs.getLoseScoreAt(i);
            ratio = (float) win / (win + lose);
            at.addRow(name, win, lose, String.format("%.2f", ratio));
            at.addRule();
        }
        CWC_LongestLine cwc = new CWC_LongestLine();

        at.getRenderer().setCWC(cwc);
        at.setPaddingLeft(2);
        at.setPaddingRight(2);
        at.setTextAlignment(TextAlignment.CENTER);

        return at.render();
    }
}