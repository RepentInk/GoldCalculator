package Main;

import Helpers.HelperFunctions;
import Screen.BuyGoldScreen;
import Screen.CustomerScreen;
import Screen.DailyBudgetScreen;
import Screen.DashboardScreen;
import Screen.PaymentsScreen;
import Screen.PricingScreen;
import Screen.ShopDetailScreen;
import Screen.UserScreen;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author nyark
 */
public class Dashboard extends javax.swing.JFrame {

    JLabel[] menuItems;
    JPanel[] sidebarPanel;
    JLabel selectedLabel;
    JPanel selectedPanel;

    HelperFunctions helper = new HelperFunctions();

    public Dashboard() {
        initComponents();
        setIconImage(helper.setIcon("/Images/doubleUU.png"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLabelArray();
        this.addActionMenuItems();
        this.setSidebarPanel();

        this.showPanel(new DashboardScreen());
    }

    private void setLabelArray() {
        this.menuItems = new JLabel[]{
            lbl_DailyBudget,
            lbl_BuyGold,
            lbl_Customers,
            lbl_Payments,
            lbl_Pricing,
            lbl_ShopDetails,
            lbl_Users
        };
    }

    private void setSidebarPanel() {
        this.sidebarPanel = new JPanel[]{
            DailyBudget,
            BuyGold,
            Customers,
            Payments,
            Pricing,
            ShopDetails,
            Users
        };
    }

    private void addActionMenuItems() {
        Component[] components = scrollPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;

                JLabel label = (JLabel) panel.getComponent(0);
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        switchPanel(label);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetPanelLabelColors(label);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (!label.equals(selectedLabel)) {
                            label.setForeground(Color.WHITE);
                            panel.setBackground(new Color(153, 153, 153));
                        }
                    }
                });
            }
        }
    }

    private void setPanelLabelColors(JLabel label, JPanel panel) {
        for (JLabel menuItem : menuItems) {
            if (!menuItem.equals(this.selectedLabel)) {
                menuItem.setForeground(Color.WHITE);
            }
        }

        for (JPanel sidebar : sidebarPanel) {
            if (!sidebar.equals(this.selectedPanel)) {
                sidebar.setBackground(new Color(153, 153, 153));
            }
        }

        label.setForeground(new Color(0, 0, 102));
        panel.setBackground(Color.LIGHT_GRAY);
    }

    private void setSelection(JLabel label, JPanel panel) {
        this.selectedLabel = label;
        this.selectedPanel = panel;

        this.setPanelLabelColors(label, panel);
    }

    private void showPanel(JPanel panel) {
        childrenContainerPanel.setLayout(new java.awt.BorderLayout(0, 0));
        childrenContainerPanel.removeAll();
        childrenContainerPanel.add(panel);
        childrenContainerPanel.revalidate();
    }

    private void resetPanelLabelColors(JLabel label) {
        switch (label.getText().trim()) {
            case "Daily Budget":
                this.setPanelLabelColors(lbl_DailyBudget, DailyBudget);
                break;
            case "Buy Gold":
                this.setPanelLabelColors(lbl_BuyGold, BuyGold);
                break;
            case "Customers":
                this.setPanelLabelColors(lbl_Customers, Customers);
                break;
            case "Payments":
                this.setPanelLabelColors(lbl_Payments, Payments);
                break;
            case "Pricing":
                this.setPanelLabelColors(lbl_Pricing, Pricing);
                break;
            case "Shop Details":
                this.setPanelLabelColors(lbl_ShopDetails, ShopDetails);
                break;
            case "Users":
                this.setPanelLabelColors(lbl_Users, Users);
                break;
            default:
        }
    }

    private void switchPanel(JLabel label) {
        switch (label.getText().trim()) {
            case "Daily Budget":
                this.setSelection(lbl_DailyBudget, DailyBudget);
                this.showPanel(new DailyBudgetScreen());
                break;
            case "Buy Gold":
                this.setSelection(lbl_BuyGold, BuyGold);
                this.showPanel(new BuyGoldScreen());
                break;
            case "Customers":
                this.setSelection(lbl_Customers, Customers);
                this.showPanel(new CustomerScreen());
                break;
            case "Payments":
                this.setSelection(lbl_Payments, Payments);
                this.showPanel(new PaymentsScreen());
                break;
            case "Pricing":
                this.setSelection(lbl_Pricing, Pricing);
                this.showPanel(new PricingScreen());
                break;
            case "Shop Details":
                this.setSelection(lbl_ShopDetails, ShopDetails);
                this.showPanel(new ShopDetailScreen());
                break;
            case "Users":
                this.setSelection(lbl_Users, Users);
                this.showPanel(new UserScreen());
                break;
            case "Logout":
                logout();
                break;
            default:

        }
    }

    public void logout() {
        int ask = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "LOGOUT USER", JOptionPane.YES_NO_OPTION);
        if (ask == 0) {
            AdminLogin adminLogin = new AdminLogin();
            this.dispose();
            adminLogin.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarMainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbl_dashboard = new javax.swing.JLabel();
        sidebarScroll = new javax.swing.JScrollPane();
        scrollPanel = new javax.swing.JPanel();
        DailyBudget = new javax.swing.JPanel();
        lbl_DailyBudget = new javax.swing.JLabel();
        BuyGold = new javax.swing.JPanel();
        lbl_BuyGold = new javax.swing.JLabel();
        Pricing = new javax.swing.JPanel();
        lbl_Pricing = new javax.swing.JLabel();
        Payments = new javax.swing.JPanel();
        lbl_Payments = new javax.swing.JLabel();
        Users = new javax.swing.JPanel();
        lbl_Users = new javax.swing.JLabel();
        Logout = new javax.swing.JPanel();
        lbl_Logout = new javax.swing.JLabel();
        Customers = new javax.swing.JPanel();
        lbl_Customers = new javax.swing.JLabel();
        ShopDetails = new javax.swing.JPanel();
        lbl_ShopDetails = new javax.swing.JLabel();
        mainContainerPanel = new javax.swing.JScrollPane();
        childrenContainerPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnFile = new javax.swing.JMenu();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MAIN DASHBORD");

        sidebarMainPanel.setBackground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        lbl_dashboard.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbl_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        lbl_dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_dashboard.setText("Dashboard");
        lbl_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dashboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        sidebarScroll.setBackground(new java.awt.Color(153, 153, 153));
        sidebarScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sidebarScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scrollPanel.setBackground(new java.awt.Color(153, 153, 153));

        DailyBudget.setBackground(new java.awt.Color(153, 153, 153));
        DailyBudget.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_DailyBudget.setBackground(new java.awt.Color(153, 153, 153));
        lbl_DailyBudget.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_DailyBudget.setForeground(new java.awt.Color(255, 255, 255));
        lbl_DailyBudget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/machine_type.png"))); // NOI18N
        lbl_DailyBudget.setText("Daily Budget");
        lbl_DailyBudget.setToolTipText("Click to view Categories");

        javax.swing.GroupLayout DailyBudgetLayout = new javax.swing.GroupLayout(DailyBudget);
        DailyBudget.setLayout(DailyBudgetLayout);
        DailyBudgetLayout.setHorizontalGroup(
            DailyBudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_DailyBudget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DailyBudgetLayout.setVerticalGroup(
            DailyBudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DailyBudgetLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_DailyBudget, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        BuyGold.setBackground(new java.awt.Color(153, 153, 153));
        BuyGold.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_BuyGold.setBackground(new java.awt.Color(153, 153, 153));
        lbl_BuyGold.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_BuyGold.setForeground(new java.awt.Color(255, 255, 255));
        lbl_BuyGold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/machine.png"))); // NOI18N
        lbl_BuyGold.setText("Buy Gold");
        lbl_BuyGold.setToolTipText("Click to view Items");

        javax.swing.GroupLayout BuyGoldLayout = new javax.swing.GroupLayout(BuyGold);
        BuyGold.setLayout(BuyGoldLayout);
        BuyGoldLayout.setHorizontalGroup(
            BuyGoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_BuyGold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BuyGoldLayout.setVerticalGroup(
            BuyGoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuyGoldLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_BuyGold, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        Pricing.setBackground(new java.awt.Color(153, 153, 153));
        Pricing.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Pricing.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Pricing.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Pricing.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Pricing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/renting.png"))); // NOI18N
        lbl_Pricing.setText("Pricing");
        lbl_Pricing.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout PricingLayout = new javax.swing.GroupLayout(Pricing);
        Pricing.setLayout(PricingLayout);
        PricingLayout.setHorizontalGroup(
            PricingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PricingLayout.createSequentialGroup()
                .addComponent(lbl_Pricing, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PricingLayout.setVerticalGroup(
            PricingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PricingLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_Pricing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        Payments.setBackground(new java.awt.Color(153, 153, 153));
        Payments.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Payments.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Payments.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Payments.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Payments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/payment.png"))); // NOI18N
        lbl_Payments.setText("Payments");
        lbl_Payments.setToolTipText("Click to view Orders");

        javax.swing.GroupLayout PaymentsLayout = new javax.swing.GroupLayout(Payments);
        Payments.setLayout(PaymentsLayout);
        PaymentsLayout.setHorizontalGroup(
            PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentsLayout.createSequentialGroup()
                .addComponent(lbl_Payments, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PaymentsLayout.setVerticalGroup(
            PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Payments, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Users.setBackground(new java.awt.Color(153, 153, 153));
        Users.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Users.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Users.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Users.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Users.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/users.png"))); // NOI18N
        lbl_Users.setText("Users");
        lbl_Users.setToolTipText("Click to view Arrears");

        javax.swing.GroupLayout UsersLayout = new javax.swing.GroupLayout(Users);
        Users.setLayout(UsersLayout);
        UsersLayout.setHorizontalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLayout.createSequentialGroup()
                .addComponent(lbl_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        UsersLayout.setVerticalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Logout.setBackground(new java.awt.Color(153, 153, 153));
        Logout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Logout.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Logout.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Logout.setForeground(new java.awt.Color(204, 0, 0));
        lbl_Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        lbl_Logout.setText("Logout");
        lbl_Logout.setToolTipText("Click to Logout");

        javax.swing.GroupLayout LogoutLayout = new javax.swing.GroupLayout(Logout);
        Logout.setLayout(LogoutLayout);
        LogoutLayout.setHorizontalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LogoutLayout.setVerticalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        Customers.setBackground(new java.awt.Color(153, 153, 153));
        Customers.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Customers.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Customers.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Customers.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/customer.png"))); // NOI18N
        lbl_Customers.setText("Customers");
        lbl_Customers.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout CustomersLayout = new javax.swing.GroupLayout(Customers);
        Customers.setLayout(CustomersLayout);
        CustomersLayout.setHorizontalGroup(
            CustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Customers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CustomersLayout.setVerticalGroup(
            CustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomersLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_Customers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        ShopDetails.setBackground(new java.awt.Color(153, 153, 153));
        ShopDetails.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_ShopDetails.setBackground(new java.awt.Color(153, 153, 153));
        lbl_ShopDetails.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_ShopDetails.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ShopDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/operator.png"))); // NOI18N
        lbl_ShopDetails.setText("Shop Details");
        lbl_ShopDetails.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout ShopDetailsLayout = new javax.swing.GroupLayout(ShopDetails);
        ShopDetails.setLayout(ShopDetailsLayout);
        ShopDetailsLayout.setHorizontalGroup(
            ShopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShopDetailsLayout.createSequentialGroup()
                .addComponent(lbl_ShopDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ShopDetailsLayout.setVerticalGroup(
            ShopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShopDetailsLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_ShopDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout scrollPanelLayout = new javax.swing.GroupLayout(scrollPanel);
        scrollPanel.setLayout(scrollPanelLayout);
        scrollPanelLayout.setHorizontalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DailyBudget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BuyGold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Customers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ShopDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Payments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Pricing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        scrollPanelLayout.setVerticalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPanelLayout.createSequentialGroup()
                .addComponent(DailyBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BuyGold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Customers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Payments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Pricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ShopDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Users, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(482, Short.MAX_VALUE))
        );

        sidebarScroll.setViewportView(scrollPanel);

        javax.swing.GroupLayout sidebarMainPanelLayout = new javax.swing.GroupLayout(sidebarMainPanel);
        sidebarMainPanel.setLayout(sidebarMainPanelLayout);
        sidebarMainPanelLayout.setHorizontalGroup(
            sidebarMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sidebarScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        sidebarMainPanelLayout.setVerticalGroup(
            sidebarMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarMainPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidebarScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout childrenContainerPanelLayout = new javax.swing.GroupLayout(childrenContainerPanel);
        childrenContainerPanel.setLayout(childrenContainerPanelLayout);
        childrenContainerPanelLayout.setHorizontalGroup(
            childrenContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 888, Short.MAX_VALUE)
        );
        childrenContainerPanelLayout.setVerticalGroup(
            childrenContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 972, Short.MAX_VALUE)
        );

        mainContainerPanel.setViewportView(childrenContainerPanel);

        jMenuBar1.setBackground(new java.awt.Color(153, 153, 153));

        mnFile.setText("File");
        mnFile.add(jSeparator35);
        mnFile.add(jSeparator2);
        mnFile.add(jSeparator36);

        jMenuBar1.add(mnFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainContainerPanel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(sidebarMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dashboardMouseClicked
        this.showPanel(new DashboardScreen());
    }//GEN-LAST:event_lbl_dashboardMouseClicked

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BuyGold;
    private javax.swing.JPanel Customers;
    private javax.swing.JPanel DailyBudget;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel Payments;
    private javax.swing.JPanel Pricing;
    private javax.swing.JPanel ShopDetails;
    private javax.swing.JPanel Users;
    private javax.swing.JPanel childrenContainerPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator36;
    private javax.swing.JLabel lbl_BuyGold;
    private javax.swing.JLabel lbl_Customers;
    private javax.swing.JLabel lbl_DailyBudget;
    private javax.swing.JLabel lbl_Logout;
    private javax.swing.JLabel lbl_Payments;
    private javax.swing.JLabel lbl_Pricing;
    private javax.swing.JLabel lbl_ShopDetails;
    private javax.swing.JLabel lbl_Users;
    private javax.swing.JLabel lbl_dashboard;
    private javax.swing.JScrollPane mainContainerPanel;
    private javax.swing.JMenu mnFile;
    private javax.swing.JPanel scrollPanel;
    private javax.swing.JPanel sidebarMainPanel;
    private javax.swing.JScrollPane sidebarScroll;
    // End of variables declaration//GEN-END:variables
}
