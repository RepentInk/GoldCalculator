package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Models.BudgetAddition;
import Repository.BudgetAdditionRepository;
import Repository.BudgetRepository;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class BudgetAdditionController {

    HelperFunctions helper = new HelperFunctions();
    BudgetAdditionRepository budgetAdditionRepository = new BudgetAdditionRepository();
    BudgetRepository budgetRepository = new BudgetRepository();

    public void populateTable(JTable table, int budget_id) {
        List<BudgetAddition> budgetAdditions = budgetAdditionRepository.listBudgetAddUp(budget_id);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (BudgetAddition budgetAddition : budgetAdditions) {
            object = new Object[]{
                budgetAddition.getId(),
                budgetAddition.getSource(),
                helper.priceToString(budgetAddition.getAmount()),
                budgetAddition.getUser(),
                budgetAddition.getCreated_time(),
                budgetAddition.getCreated_date()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel budgetAdditionID,
            int budgetID,
            JTextField sourceName,
            JTextField totalAmount,
            JTable table
    ) {

        int budget_addition_id = budgetAdditionID.getText().isEmpty() ? 0 : Integer.parseInt(budgetAdditionID.getText());
        String source = sourceName.getText();
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        String created_date = helper.returnDate();
        String created_time = helper.returnTime();

        BudgetAddition budgetAddition = new BudgetAddition(
                budgetID,
                source,
                total_amount,
                created_date,
                created_time, Authuser.getId()
        );

        int last_inserted_id = budgetAdditionRepository.save(budgetAddition);

        budgetRepository.updateDailyBudget(budgetID, total_amount);

        this.populateAfterSaving(table, last_inserted_id);
    }

    private void populateAfterSaving(JTable table, int budget_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        BudgetAddition budgetAddition = budgetAdditionRepository.find(budget_id);
        object = new Object[]{
            budgetAddition.getId(),
            budgetAddition.getSource(),
            helper.priceToString(budgetAddition.getAmount()),
            budgetAddition.getUser(),
            budgetAddition.getCreated_time(),
            budgetAddition.getCreated_date()
        };

        tmodel.insertRow(0, object);
    }

}
