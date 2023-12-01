package puzzles.shorttruthtable.rules;

import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTable;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableBoard;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCell;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCellType;
import edu.rpi.legup.puzzle.shorttruthtable.rules.contradiction.ContradictionRuleBiconditional;
import edu.rpi.legup.save.InvalidFileFormatException;
import legup.MockGameBoardFacade;
import legup.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
public class BiconditionalContradictionRuleTest {
    private static final ContradictionRuleBiconditional RULE = new ContradictionRuleBiconditional();
    private static ShortTruthTable stt;

    @BeforeClass
    public static void setup(){
        MockGameBoardFacade.getInstance();
        stt = new ShortTruthTable();
    }

    @Test
    public void falseBiconditional() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/BiconditionalContradictionRule/falseBiconditional",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] types = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cellB = board.getCell(0,0);
        ShortTruthTableCell cellC = board.getCell(2,0);

        for(ShortTruthTableCellType i:types){
            for(ShortTruthTableCellType j:types){
                cellB.setData(i);
                cellC.setData(j);
                board.addModifiedData(cellB);
                board.addModifiedData(cellC);
                if(i.equals(j)){
                    Assert.assertNull(RULE.checkRule(transition));
                }
                else{
                    Assert.assertNotNull(RULE.checkRule(transition));
                }
            }
        }
    }

    @Test
    public void trueBiconditional() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/BiconditionalContradictionRule/trueBiconditional",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] types = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cellB = board.getCell(0,0);
        ShortTruthTableCell cellC = board.getCell(2,0);

        for(ShortTruthTableCellType i:types){
            for(ShortTruthTableCellType j:types){
                cellB.setData(i);
                cellC.setData(j);
                board.addModifiedData(cellB);
                board.addModifiedData(cellC);
                if(i.equals(j)){
                    Assert.assertNotNull(RULE.checkRule(transition));
                }
                else{
                    Assert.assertNull(RULE.checkRule(transition));
                }
            }
        }
    }
}
