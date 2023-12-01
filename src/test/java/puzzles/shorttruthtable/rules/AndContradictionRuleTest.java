package puzzles.shorttruthtable.rules;

import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTable;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableBoard;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCell;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCellType;
import edu.rpi.legup.puzzle.shorttruthtable.rules.contradiction.ContradictionRuleAnd;
import edu.rpi.legup.save.InvalidFileFormatException;
import legup.MockGameBoardFacade;
import legup.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AndContradictionRuleTest {
    private static final ContradictionRuleAnd RULE = new ContradictionRuleAnd();
    private static ShortTruthTable stt;

    @BeforeClass
    public static void setup(){
        MockGameBoardFacade.getInstance();
        stt = new ShortTruthTable();
    }

   @Test
   public void falseAnd() throws InvalidFileFormatException{
       TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/AndContradictionRule/falseAnd",stt);
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
               if(i.equals(ShortTruthTableCellType.TRUE) && j.equals(ShortTruthTableCellType.TRUE)){
                   Assert.assertNull(RULE.checkRule(transition));
               }
               else{
                   Assert.assertNotNull(RULE.checkRule(transition));
               }
           }
       }

   }

   @Test
   public void trueAnd() throws InvalidFileFormatException{
       TestUtilities.importTestBoard("puzzles/shorttruthtable/rules/AndContradictionRule/trueAnd",stt);
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
               if(i.equals(ShortTruthTableCellType.TRUE) && j.equals(ShortTruthTableCellType.TRUE)){
                   Assert.assertNotNull(RULE.checkRule(transition));
               }
               else{
                   Assert.assertNull(RULE.checkRule(transition));
               }
           }
       }
   }

}
