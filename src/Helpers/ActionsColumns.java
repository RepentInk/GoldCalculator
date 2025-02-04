package Helpers;

import static Helpers.ModelType.Renting;

/**
 *
 * @author nyark
 */
public class ActionsColumns {

    public static int[] tableActionColumn(ModelType modelType) {
        int[] column = {};

        switch (modelType) {
            case Users:
                column = new int[]{5, 6};
                break;
            case MachineTypes:
                column = new int[]{4, 5};
                break;
            case Machines:
                column = new int[]{9, 10};
                break;
            case Customers:
                column = new int[]{6, 7};
                break;
            case Operators:
                column = new int[]{5, 6};
                break;
            case Renting:
                column = new int[]{12, 13, 14, 15, 16};
                break;
            case Payments:
                column = new int[]{9, 10};
                break;
            case OperatorRenting:
                column = new int[]{4, 5};
                break;
            case NonWorkHours:
                column = new int[]{5, 6};
                break;
            default:
                throw new AssertionError();
        }

        return column;
    }

    public static int[] tableColumnSummation(ModelType modelType) {
        int[] column = {};

        switch (modelType) {
            case Renting:
                column = new int[]{4, 5, 6};
                break;
            case Payments:
                column = new int[]{4, 5, 6};
                break;
            default:
                throw new AssertionError();
        }

        return column;
    }
}
