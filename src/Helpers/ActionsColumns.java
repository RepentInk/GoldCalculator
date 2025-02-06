package Helpers;

/**
 *
 * @author nyark
 */
public class ActionsColumns {

    public static int[] tableActionColumn(ModelType modelType) {
        int[] column = {};

        switch (modelType) {
            case Users:
                column = new int[]{6, 7};
                break;
            case Budget:
                column = new int[]{11, 12};
                break;
            case BuyGold:
                column = new int[]{13, 14};
                break;
            case Customers:
                column = new int[]{6, 7};
                break;
            case Payments:
                column = new int[]{9, 10};
                break;
            default:
                throw new AssertionError();
        }

        return column;
    }

    public static int[] tableColumnSummation(ModelType modelType) {
        int[] column = {};

        switch (modelType) {
            case Payments:
                column = new int[]{4, 5, 6};
                break;
            default:
                throw new AssertionError();
        }

        return column;
    }
}
