package gui.application.form;

import gui.manager.TABSupplier;
import gui.manager.TABEmployee;
import gui.manager.TABOrder;
import gui.manager.TABCustomer;
import gui.manager.TABPersonalInformation;
import gui.manager.TABDamageItem;
import gui.manager.TABPurchaseOrder;
import gui.manager.TABReturnOrder;
import gui.manager.TABProduct;
import gui.manager.TABReport;
import gui.manager.TABStats;
import gui.manager.TABPromotion;
import gui.manager.TABUnit;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gui.application.Application;
import gui.application.form.other.FormDashboard;
import gui.application.form.other.FormInbox;
import gui.application.form.other.FormRead;
import gui.menu.Menu;
import gui.menu.MenuAction;
import javax.swing.JFrame;
import gui.staff.TABIndividualReport;
import gui.staff.TABPurchase;
import gui.staff.TABSell;
import gui.staff.damageItem.TabDamageItem;
import util.ViewPdfPanel;

/**
 *
 * @author Raven
 */
public class MainForm extends JLayeredPane {

    //1 quan ly, 2 nhan vien
    private int type;

    public MainForm(int type) {
        this.type = type;
        init();

    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu(type);
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("gui/icon/svg/" + icon, 0.8f));
    }

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (type == 1) {
                if (index == 0) {
                    Application.showForm(new TABStats());
                } else if (index == 1) {
                    Application.showForm(new TABReport());
                } else if (index == 2) {
                    Application.showForm(new TABProduct());
                } else if (index == 3) {
                    Application.showForm(new TABUnit());
                } else if (index == 4) {
                    Application.showForm(new TABEmployee());
                } else if (index == 5) {
                    Application.showForm(new TABCustomer());
                } else if (index == 6) {
                    Application.showForm(new TABSupplier());
                } else if (index == 7) {
                    Application.showForm(new TABPurchaseOrder());
                } else if (index == 8) {
                    Application.showForm(new TABReturnOrder());
                } else if (index == 9) {
                    Application.showForm(new TABDamageItem());
                } else if (index == 10) {
                    Application.showForm(new TABOrder());
                } else if (index == 11) {
                    Application.showForm(new TABPromotion());
                } else if (index == 12) {
                    Application.showForm(new TABPersonalInformation());
                } else if (index == 13) {
                    Application.showForm(new ViewPdfPanel(1));
                } else if ( index ==14 ){
                    Application.logout();
                }else {
                    action.cancel();
                }
            } else if (type == 2) {
                if (index == 20) {
                    Application.showForm(new TABSell());
                } else if (index == 21) {
                    Application.showForm(new TABProduct());
                } else if (index == 22) {
                    Application.showForm(new TABCustomer());
                } else if (index == 23) {
                    Application.showForm(new TABSupplier());
                } else if (index == 24) {
                    Application.showForm(new TABPurchase());
                } else if (index == 25) {
                    Application.showForm(new gui.staff.TABReturnOrder());
                } else if (index == 26) {
                    Application.showForm(new TabDamageItem());
                } else if (index == 27) {
                    Application.showForm(new TABIndividualReport());
                } else if (index == 28) {
                    Application.showForm(new TABPersonalInformation());
                } else if (index == 29) {
                    Application.showForm(new ViewPdfPanel(2));
                } else if (index == 30) {
                    Application.logout();
                } else {
                    action.cancel();
                }
            }

        });
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("gui/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) throws RemoteException {
        menu.setSelectedMenu(index, subIndex);
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
