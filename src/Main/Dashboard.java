package Main;

import Helpers.HelperFunctions;
import Helpers.LookAndFeel;
import Models.Expenses;
import Screen.BuyGoldScreen;
import Screen.CreditScreen;
import Screen.CustomerScreen;
import Screen.DailyBudgetScreen;
import Screen.DashboardScreen;
import Screen.ExpensesScreen;
import Screen.ExpensesTypeScreen;
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
        setIconImage(helper.setIcon(helper.iconImagePath()));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLabelArray();
        this.addActionMenuItems();
        this.setSidebarPanel();

        this.showPanel(new DashboardScreen());
    }

    private void setLabelArray() {
        this.menuItems = new JLabel[]{
            lbl_BuyGold,
            lbl_Payments,
            lbl_Credit,
            lbl_Expenses,
            lbl_DailyBudget,
            lbl_Customers,
            lbl_ExpensesType,
            lbl_Users,
            lbl_Pricing,
            lbl_ShopDetails
        };
    }

    private void setSidebarPanel() {
        this.sidebarPanel = new JPanel[]{
            BuyGold,
            Payments,
            Credit,
            Expenses,
            DailyBudget,
            Customers,
            ExpensesType,
            Users,
            Pricing,
            ShopDetails
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
            case "Buy Gold":
                this.setPanelLabelColors(lbl_BuyGold, BuyGold);
                break;
            case "Payments":
                this.setPanelLabelColors(lbl_Payments, Payments);
                break;
            case "Credit":
                this.setPanelLabelColors(lbl_Credit, Credit);
                break;
            case "Expenses":
                this.setPanelLabelColors(lbl_Expenses, Expenses);
                break;
            case "Daily Budget":
                this.setPanelLabelColors(lbl_DailyBudget, DailyBudget);
                break;
            case "Customers":
                this.setPanelLabelColors(lbl_Customers, Customers);
                break;
            case "Expenses Type":
                this.setPanelLabelColors(lbl_ExpensesType, ExpensesType);
                break;
            case "Users":
                this.setPanelLabelColors(lbl_Users, Users);
                break;
            case "Pricing":
                this.setPanelLabelColors(lbl_Pricing, Pricing);
                break;
            case "Shop Details":
                this.setPanelLabelColors(lbl_ShopDetails, ShopDetails);
                break;
            default:
        }
    }

    private void switchPanel(JLabel label) {
        switch (label.getText().trim()) {
            case "Buy Gold":
                this.setSelection(lbl_BuyGold, BuyGold);
                this.showPanel(new BuyGoldScreen());
                break;
            case "Payments":
                this.setSelection(lbl_Payments, Payments);
                this.showPanel(new PaymentsScreen());
                break;
            case "Credit":
                this.setSelection(lbl_Credit, Credit);
                this.showPanel(new CreditScreen());
                break;
            case "Expenses":
                this.setSelection(lbl_Expenses, Expenses);
                this.showPanel(new ExpensesScreen());
                break;
            case "Daily Budget":
                this.setSelection(lbl_DailyBudget, DailyBudget);
                this.showPanel(new DailyBudgetScreen());
                break;
            case "Customers":
                this.setSelection(lbl_Customers, Customers);
                this.showPanel(new CustomerScreen());
                break;
            case "Expenses Type":
                this.setSelection(lbl_ExpensesType, ExpensesType);
                this.showPanel(new ExpensesTypeScreen());
                break;
            case "Users":
                this.setSelection(lbl_Users, Users);
                this.showPanel(new UserScreen());
                break;
            case "Pricing":
                this.setSelection(lbl_Pricing, Pricing);
                this.showPanel(new PricingScreen());
                break;
            case "Shop Details":
                this.setSelection(lbl_ShopDetails, ShopDetails);
                this.showPanel(new ShopDetailScreen());
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
        Credit = new javax.swing.JPanel();
        lbl_Credit = new javax.swing.JLabel();
        Users = new javax.swing.JPanel();
        lbl_Users = new javax.swing.JLabel();
        Logout = new javax.swing.JPanel();
        lbl_Logout = new javax.swing.JLabel();
        Customers = new javax.swing.JPanel();
        lbl_Customers = new javax.swing.JLabel();
        ShopDetails = new javax.swing.JPanel();
        lbl_ShopDetails = new javax.swing.JLabel();
        Payments = new javax.swing.JPanel();
        lbl_Payments = new javax.swing.JLabel();
        Expenses = new javax.swing.JPanel();
        lbl_Expenses = new javax.swing.JLabel();
        ExpensesType = new javax.swing.JPanel();
        lbl_ExpensesType = new javax.swing.JLabel();
        mainContainerPanel = new javax.swing.JScrollPane();
        childrenContainerPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GOLDINK");

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
        lbl_DailyBudget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/budget.png"))); // NOI18N
        lbl_DailyBudget.setText("Daily Budget");
        lbl_DailyBudget.setToolTipText("Click to view Categories");

        javax.swing.GroupLayout DailyBudgetLayout = new javax.swing.GroupLayout(DailyBudget);
        DailyBudget.setLayout(DailyBudgetLayout);
        DailyBudgetLayout.setHorizontalGroup(
            DailyBudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DailyBudgetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_DailyBudget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        lbl_BuyGold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/goldbars.png"))); // NOI18N
        lbl_BuyGold.setText("Buy Gold");
        lbl_BuyGold.setToolTipText("Click to view Items");

        javax.swing.GroupLayout BuyGoldLayout = new javax.swing.GroupLayout(BuyGold);
        BuyGold.setLayout(BuyGoldLayout);
        BuyGoldLayout.setHorizontalGroup(
            BuyGoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuyGoldLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_BuyGold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        BuyGoldLayout.setVerticalGroup(
            BuyGoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuyGoldLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lbl_BuyGold, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        Pricing.setBackground(new java.awt.Color(153, 153, 153));
        Pricing.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Pricing.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Pricing.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Pricing.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Pricing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pricing.png"))); // NOI18N
        lbl_Pricing.setText("Pricing");
        lbl_Pricing.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout PricingLayout = new javax.swing.GroupLayout(Pricing);
        Pricing.setLayout(PricingLayout);
        PricingLayout.setHorizontalGroup(
            PricingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PricingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Pricing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PricingLayout.setVerticalGroup(
            PricingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PricingLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_Pricing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        Credit.setBackground(new java.awt.Color(153, 153, 153));
        Credit.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Credit.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Credit.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Credit.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Credit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/credit.png"))); // NOI18N
        lbl_Credit.setText("Credit");
        lbl_Credit.setToolTipText("Click to view Orders");

        javax.swing.GroupLayout CreditLayout = new javax.swing.GroupLayout(Credit);
        Credit.setLayout(CreditLayout);
        CreditLayout.setHorizontalGroup(
            CreditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CreditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Credit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        CreditLayout.setVerticalGroup(
            CreditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreditLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lbl_Credit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Customers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        CustomersLayout.setVerticalGroup(
            CustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Customers, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        ShopDetails.setBackground(new java.awt.Color(153, 153, 153));
        ShopDetails.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_ShopDetails.setBackground(new java.awt.Color(153, 153, 153));
        lbl_ShopDetails.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_ShopDetails.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ShopDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/shop.png"))); // NOI18N
        lbl_ShopDetails.setText("Shop Details");
        lbl_ShopDetails.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout ShopDetailsLayout = new javax.swing.GroupLayout(ShopDetails);
        ShopDetails.setLayout(ShopDetailsLayout);
        ShopDetailsLayout.setHorizontalGroup(
            ShopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShopDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ShopDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ShopDetailsLayout.setVerticalGroup(
            ShopDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShopDetailsLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_ShopDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap()
                .addComponent(lbl_Payments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PaymentsLayout.setVerticalGroup(
            PaymentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaymentsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lbl_Payments, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        Expenses.setBackground(new java.awt.Color(153, 153, 153));
        Expenses.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_Expenses.setBackground(new java.awt.Color(153, 153, 153));
        lbl_Expenses.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_Expenses.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Expenses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/expenses.png"))); // NOI18N
        lbl_Expenses.setText("Expenses");
        lbl_Expenses.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout ExpensesLayout = new javax.swing.GroupLayout(Expenses);
        Expenses.setLayout(ExpensesLayout);
        ExpensesLayout.setHorizontalGroup(
            ExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExpensesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Expenses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ExpensesLayout.setVerticalGroup(
            ExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Expenses, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        ExpensesType.setBackground(new java.awt.Color(153, 153, 153));
        ExpensesType.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_ExpensesType.setBackground(new java.awt.Color(153, 153, 153));
        lbl_ExpensesType.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_ExpensesType.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ExpensesType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/expenses_type.png"))); // NOI18N
        lbl_ExpensesType.setText("Expenses Type");
        lbl_ExpensesType.setToolTipText("Click to view Sales");

        javax.swing.GroupLayout ExpensesTypeLayout = new javax.swing.GroupLayout(ExpensesType);
        ExpensesType.setLayout(ExpensesTypeLayout);
        ExpensesTypeLayout.setHorizontalGroup(
            ExpensesTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExpensesTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ExpensesType, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        ExpensesTypeLayout.setVerticalGroup(
            ExpensesTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExpensesTypeLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_ExpensesType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout scrollPanelLayout = new javax.swing.GroupLayout(scrollPanel);
        scrollPanel.setLayout(scrollPanelLayout);
        scrollPanelLayout.setHorizontalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPanelLayout.createSequentialGroup()
                .addGroup(scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Logout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ShopDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Users, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExpensesType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Customers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DailyBudget, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Expenses, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Credit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Payments, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BuyGold, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Pricing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        scrollPanelLayout.setVerticalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPanelLayout.createSequentialGroup()
                .addComponent(BuyGold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Payments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Credit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Expenses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(DailyBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Customers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ExpensesType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Users, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Pricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ShopDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
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
            .addComponent(mainContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
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
        UIManager.setLookAndFeel(LookAndFeel.frameSkins());
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BuyGold;
    private javax.swing.JPanel Credit;
    private javax.swing.JPanel Customers;
    private javax.swing.JPanel DailyBudget;
    private javax.swing.JPanel Expenses;
    private javax.swing.JPanel ExpensesType;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel Payments;
    private javax.swing.JPanel Pricing;
    private javax.swing.JPanel ShopDetails;
    private javax.swing.JPanel Users;
    private javax.swing.JPanel childrenContainerPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_BuyGold;
    private javax.swing.JLabel lbl_Credit;
    private javax.swing.JLabel lbl_Customers;
    private javax.swing.JLabel lbl_DailyBudget;
    private javax.swing.JLabel lbl_Expenses;
    private javax.swing.JLabel lbl_ExpensesType;
    private javax.swing.JLabel lbl_Logout;
    private javax.swing.JLabel lbl_Payments;
    private javax.swing.JLabel lbl_Pricing;
    private javax.swing.JLabel lbl_ShopDetails;
    private javax.swing.JLabel lbl_Users;
    private javax.swing.JLabel lbl_dashboard;
    private javax.swing.JScrollPane mainContainerPanel;
    private javax.swing.JPanel scrollPanel;
    private javax.swing.JPanel sidebarMainPanel;
    private javax.swing.JScrollPane sidebarScroll;
    // End of variables declaration//GEN-END:variables
}
