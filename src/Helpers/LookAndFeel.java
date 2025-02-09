package Helpers;

/**
 *
 * @author nyark
 */
public class LookAndFeel {

    public static String frameSkins() {
        switch (ShopData.getGetSkinType()) {
            case 2:
                return "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
            case 1:
                return "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
            default:
                return "com.jtattoo.plaf.texture.TextureLookAndFeel";
        }
    }

}
