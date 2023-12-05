package puzzles.shorttruthtable.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTable;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableBoard;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCell;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCellType;
import edu.rpi.legup.puzzle.shorttruthtable.rules.caserule.CaseRuleOr;
import edu.rpi.legup.save.InvalidFileFormatException;
import legup.MockGameBoardFacade;
import legup.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
public class OrCaseRuleTest {
    private static final CaseRuleOr RULE = new CaseRuleOr();
    private static ShortTruthTable stt;

    @BeforeClass
    public static void setup(){
        MockGameBoardFacade.getInstance();
        stt = new ShortTruthTable();
    }

    @Test
    public void CaseRuleTest() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/OrCaseRule/caseRuleTest",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cell = board.getCell(2,0);
        ArrayList<Board> cases = RULE.getCases(board,cell);

        Assert.assertEquals(cases.size(),2);

        ShortTruthTableBoard board1 = (ShortTruthTableBoard) cases.get(0);
        ShortTruthTableBoard board2 = (ShortTruthTableBoard) cases.get(1);
        Assert.assertEquals(board1.getHeight(),board2.getHeight());
        Assert.assertEquals(board1.getWidth(),board2.getWidth());

        ShortTruthTableCellType cellType1 = board1.getCell(0,0).getType();
        ShortTruthTableCellType cellType2 = board2.getCell(3,0).getType();

        Assert.assertTrue(cellType1.equals(cellType2));

        for(int i=0; i<board1.getHeight(); i++){
            for(int k=0; k<board2.getWidth(); k++){
                Point point = new Point(k,i);
                if(point.equals(board1.getCell(k,i).getLocation())){
                    continue;
                }
                Assert.assertTrue(board1.getCell(k,i).equals(board2.getCell(k,i)));
            }
        }
    }
}
