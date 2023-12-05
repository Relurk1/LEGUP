package puzzles.shorttruthtable.rules;

import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTable;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableBoard;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCell;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCellType;
import edu.rpi.legup.puzzle.shorttruthtable.rules.contradiction.ContradictionRuleNot;
import edu.rpi.legup.save.InvalidFileFormatException;
import legup.MockGameBoardFacade;
import legup.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
public class NotContradictionRuleTest {
    private static final ContradictionRuleNot RULE = new ContradictionRuleNot();
    private static ShortTruthTable stt;

    @BeforeClass
    public static void setup(){
        MockGameBoardFacade.getInstance();
        stt = new ShortTruthTable();
    }

    @Test
    public void falseNot() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/NotContradictionRule/falseNot",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] types = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cellB = board.getCell(1,0);

        cellB.setData(ShortTruthTableCellType.FALSE);
        board.addModifiedData(cellB);
        Assert.assertNull(RULE.checkRule(transition));
        cellB.setData(ShortTruthTableCellType.TRUE);
        board.addModifiedData(cellB);
        Assert.assertNotNull(RULE.checkRule(transition));

    }

    @Test
    public void trueNot() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/NotContradictionRule/trueNot",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] types = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cellB = board.getCell(1,0);

        cellB.setData(ShortTruthTableCellType.TRUE);
        board.addModifiedData(cellB);
        Assert.assertNull(RULE.checkRule(transition));
        cellB.setData(ShortTruthTableCellType.FALSE);
        board.addModifiedData(cellB);
        Assert.assertNotNull(RULE.checkRule(transition));

    }
}
