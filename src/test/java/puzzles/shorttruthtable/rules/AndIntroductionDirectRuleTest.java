package puzzles.shorttruthtable.rules;

import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTable;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableBoard;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCell;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCellType;
import edu.rpi.legup.puzzle.shorttruthtable.rules.basic.introduction.DirectRuleAndIntroduction;
import edu.rpi.legup.save.InvalidFileFormatException;
import legup.MockGameBoardFacade;
import legup.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AndIntroductionDirectRuleTest {
    private static final DirectRuleAndIntroduction RULE = new DirectRuleAndIntroduction();
    private static ShortTruthTable stt;

    @BeforeClass
    public static void setup(){
        MockGameBoardFacade.getInstance();
        stt = new ShortTruthTable();
    }

    @Test
    public void FalseBC() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/AndIntroductionDirectRule/FalseBC",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] cellTypes = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE,ShortTruthTableCellType.UNKNOWN};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cell = board.getCell(1,0);

        for(ShortTruthTableCellType cellType:cellTypes){
            if(cellType==ShortTruthTableCellType.FALSE) {
                continue;
            }
            if(cellType!=ShortTruthTableCellType.UNKNOWN){
                cell.setData(cellType);
                board.addModifiedData(cell);
            }

            Assert.assertNotNull(RULE.checkRule(transition));

        }
    }

    @Test
    public void TrueBC() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/AndIntroductionDirectRule/TrueBC",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] cellTypes = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE,ShortTruthTableCellType.UNKNOWN};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cell = board.getCell(1,0);

        for(ShortTruthTableCellType cellType:cellTypes){
            if(cellType==ShortTruthTableCellType.TRUE) {
                continue;
            }
            if(cellType!=ShortTruthTableCellType.UNKNOWN){
                cell.setData(cellType);
                board.addModifiedData(cell);
            }

            Assert.assertNotNull(RULE.checkRule(transition));

        }
    }

    @Test
    public void TrueBFalseC() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/AndIntroductionDirectRule/TrueBFalseC",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] cellTypes = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE,ShortTruthTableCellType.UNKNOWN};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cell = board.getCell(1,0);

        for(ShortTruthTableCellType cellType:cellTypes){
            if(cellType==ShortTruthTableCellType.TRUE){
                continue;
            }

            if(cellType!=ShortTruthTableCellType.UNKNOWN){
                cell.setData(cellType);
                board.addModifiedData(cell);
            }

            Assert.assertNull(RULE.checkRule(transition));

        }
    }

    @Test
    public void FalseBTrueC() throws InvalidFileFormatException{
        TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/AndIntroductionDirectRule/FalseBTrueC",stt);
        TreeNode rootNode = stt.getTree().getRootNode();
        TreeTransition transition = rootNode.getChildren().get(0);
        transition.setRule(RULE);

        ShortTruthTableCellType[] cellTypes = {ShortTruthTableCellType.TRUE,ShortTruthTableCellType.FALSE,ShortTruthTableCellType.UNKNOWN};
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cell = board.getCell(1,0);

        for(ShortTruthTableCellType cellType:cellTypes){
            if(cellType==ShortTruthTableCellType.TRUE){
                continue;
            }

            if(cellType!=ShortTruthTableCellType.UNKNOWN){
                cell.setData(cellType);
                board.addModifiedData(cell);
            }

            Assert.assertNull(RULE.checkRule(transition));

        }
    }
}
