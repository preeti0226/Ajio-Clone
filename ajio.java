import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AjioClone {

    static JFrame frame;
    static JPanel mainPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AjioClone().createUI());
    }

    public void createUI() {
        frame = new JFrame("AJIO Clone - Java Frontend");
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25, 25, 70));
        header.setPreferredSize(new Dimension(1000, 70));

        JLabel logo = new JLabel("AJIO");
        logo.setFont(new Font("Arial", Font.BOLD, 34));
        logo.setForeground(Color.WHITE);
        logo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        header.add(logo, BorderLayout.WEST);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        nav.setBackground(new Color(25, 25, 70));

        String[] cats = { "MEN", "WOMEN", "KIDS", "HOME & KITCHEN" };
        for (String c : cats) {
            JButton btn = new JButton(c);
            btn.setBackground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(e -> showCategory(c));
            nav.add(btn);
        }
        header.add(nav, BorderLayout.CENTER);

        frame.add(header, BorderLayout.NORTH);

        // ===== MAIN CONTENT =====
        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        showHomePage(); // show home by default

        // ===== FOOTER =====
        JPanel footer = new JPanel();
        footer.setBackground(new Color(50, 50, 70));
        footer.setPreferredSize(new Dimension(1000, 50));
        JLabel foot = new JLabel("© 2025 AJIO Clone | Developed by Harshitha,Viswitha,Sravanthi");
        foot.setForeground(Color.WHITE);
        footer.add(foot);
        frame.add(footer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Show home page
    public void showHomePage() {
        mainPanel.removeAll();

        JPanel banner = new JPanel();
        banner.setBackground(new Color(230, 230, 255));
        banner.setPreferredSize(new Dimension(1000, 150));
        JLabel text = new JLabel("🎉 FESTIVE SALE – Up to 70% OFF on Top Brands!");
        text.setFont(new Font("SansSerif", Font.BOLD, 26));
        text.setForeground(new Color(60, 40, 100));
        banner.add(text);

        mainPanel.add(banner, BorderLayout.NORTH);

        JPanel welcome = new JPanel(new BorderLayout());
        JLabel msg = new JLabel("Explore Latest Collections from AJIO", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 22));
        msg.setForeground(new Color(80, 80, 120));
        welcome.add(msg, BorderLayout.CENTER);

        mainPanel.add(welcome, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Show category page
    public void showCategory(String category) {
        mainPanel.removeAll();

        // Subcategory bar
        JPanel subcatPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        subcatPanel.setBackground(new Color(245, 245, 255));
        subcatPanel.setPreferredSize(new Dimension(1000, 60));

        String[] men = { "T-Shirts", "Jeans", "Shoes", "Watches" };
        String[] women = { "Dresses", "Handbags", "Heels", "Jewellery" };
        String[] kids = { "Tops", "Shorts", "Toys", "Footwear" };
        String[] home = { "Bedding", "Decor", "Kitchen", "Storage" };

        String[] subcats = switch (category) {
            case "MEN" -> men;
            case "WOMEN" -> women;
            case "KIDS" -> kids;
            default -> home;
        };

        for (String s : subcats) {
            JButton b = new JButton(s);
            b.setBackground(new Color(255, 255, 255));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.addActionListener(e -> showProducts(category, s));
            subcatPanel.add(b);
        }

        mainPanel.add(subcatPanel, BorderLayout.NORTH);

        // Default product list
        showProducts(category, subcats[0]);
    }

    // Show products of category
    public void showProducts(String category, String subcategory) {
        JPanel productsContainer = new JPanel();
        productsContainer.setLayout(new GridLayout(2, 3, 20, 20));
        productsContainer.setBackground(Color.WHITE);
        productsContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 1; i <= 6; i++) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(250, 250, 250));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel title = new JLabel(subcategory + " " + i, SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel emoji = new JLabel("🛍", SwingConstants.CENTER);
            emoji.setFont(new Font("Arial", Font.PLAIN, 36));

            JLabel price = new JLabel("₹" + (999 + i * 150), SwingConstants.CENTER);
            price.setFont(new Font("Arial", Font.PLAIN, 14));

            JButton add = new JButton("Add to Cart");
            add.setBackground(new Color(25, 118, 210));
            add.setForeground(Color.WHITE);
            add.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JPanel bottom = new JPanel(new GridLayout(2, 1));
            bottom.add(price);
            bottom.add(add);

            card.add(title, BorderLayout.NORTH);
            card.add(emoji, BorderLayout.CENTER);
            card.add(bottom, BorderLayout.SOUTH);

            productsContainer.add(card);
        }

        JScrollPane scroll = new JScrollPane(productsContainer);
        scroll.setBorder(null);

        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
