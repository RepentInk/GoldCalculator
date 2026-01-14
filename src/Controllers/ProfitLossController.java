package Controllers;

import Helpers.HelperFunctions;
import Models.ProfitLoss;
import Repository.ProfitLossRepository;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class ProfitLossController {

    ProfitLossRepository profitLossRepository = new ProfitLossRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateTable(JTable table, String startDate, String endDate) {
        if (endDate.equals("")) {
            endDate = helper.returnDate();
        }

        List<ProfitLoss> profitLosses;
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            profitLosses = profitLossRepository.findProfitLossDates(startDate, endDate);
        } else {
            profitLosses = profitLossRepository.list(endDate);
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (ProfitLoss profitLoss : profitLosses) {
            object = new Object[]{
                profitLoss.getId(),
                profitLoss.getStart_date(),
                profitLoss.getEnd_date(),
                profitLoss.getTotal_budget(),
                profitLoss.getBudget_left(),
                profitLoss.getTotal_expenses(),
                profitLoss.getAmount_from_gold(),
                profitLoss.getProfit_loss(),
                profitLoss.getCreated_time(),
                profitLoss.getCreated_date()
            };

            defaultTableModel.addRow(object);
        }
    }

    public void saveUpdate(
            String profitLossId,
            String startDate,
            String endDate,
            String totalBudget,
            String budgetLeft,
            String totalExpenses,
            String totalAmount,
            String profitLossValue
    ) {

        int profit_id = profitLossId.isEmpty() ? 0 : Integer.parseInt(profitLossId);
        double total_budget = totalBudget.isEmpty() ? 0 : helper.parseAmountWithComma(totalBudget);
        double budget_left = budgetLeft.isEmpty() ? 0 : helper.parseAmountWithComma(budgetLeft);
        double total_expenses = totalExpenses.isEmpty() ? 0 : helper.parseAmountWithComma(totalExpenses);
        double amount_from_gold = totalAmount.isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount);
        double profit_loss = profitLossValue.isEmpty() ? 0 : helper.parseAmountWithComma(profitLossValue);

        String createdTime = helper.returnTime();
        String createdDate = helper.returnDate();
        String rawDate = helper.returnDate();

        if (profit_id > 0) {

            ProfitLoss profitLoss = new ProfitLoss(
                    profit_id,
                    total_budget,
                    budget_left,
                    total_expenses,
                    amount_from_gold,
                    profit_loss,
                    startDate,
                    endDate,
                    createdTime,
                    createdDate,
                    rawDate
            );

            profitLossRepository.update(profitLoss, profit_id);
        } else {

            ProfitLoss profitLoss = new ProfitLoss(
                    total_budget,
                    budget_left,
                    total_expenses,
                    amount_from_gold,
                    profit_loss,
                    startDate,
                    endDate,
                    createdTime,
                    createdDate,
                    rawDate
            );

            profitLossRepository.save(profitLoss);
        }
    }

}
