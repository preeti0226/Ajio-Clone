import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * SimpleAjioProducts.java
 * Beginner-friendly Swing app: products grid + footer + slogans.
 *
 * Save this file as SimpleAjioProducts.java (UTF-8)
 * Optional image files (place in same folder):
 * tshirt.jpg, pants.jpg, frock.jpg, kurta.jpg, maxi.jpg
 *
 * Compile:
 * javac SimpleAjioProducts.java
 * Run:
 * java SimpleAjioProducts
 */
public class SimpleAjioProducts {
    private static int cartCount = 0;
    private static JLabel cartLabel;

    public static void main(String[] args) {
        // Ensure UI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Ajio - Products");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);

        // Top bar (title + cart)
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        JLabel title = new JLabel("AJIO - Simple Store");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        topBar.add(title, BorderLayout.WEST);

        cartLabel = new JLabel("Cart: 0");
        cartLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topBar.add(cartLabel, BorderLayout.EAST);

        // Products panel (grid)
        JPanel productsPanel = new JPanel(new GridLayout(0, 3, 12, 12));
        productsPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        productsPanel.setBackground(Color.WHITE);

        // Add product cards
        productsPanel.add(createProductCard("tshirt.jpg", "T-Shirt", "₹499"));
        productsPanel.add(createProductCard("pants.jpg", "Pants", "₹899"));
        productsPanel.add(createProductCard("frock.jpg", "Frock", "₹799"));
        productsPanel.add(createProductCard("kurta.jpg", "Kurta", "₹699"));
        productsPanel.add(createProductCard("maxi.jpg", "Maxi Dress", "₹1199"));
        productsPanel.add(createPlaceholderCard());

        // Scroll pane for products
        JScrollPane scrollProducts = new JScrollPane(productsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Footer panel
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        footer.setBackground(new Color(245, 245, 245));
        JLabel footerText = new JLabel(
                "<html><center>Thanks for shopping with AJIO!<br>© All copyrights to M.Saiviswitha</center></html>");
        footerText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        footerText.setHorizontalAlignment(SwingConstants.CENTER);
        footer.add(footerText, BorderLayout.CENTER);

        // Slogans panel (vertical, scrollable if needed)
        JPanel slogansInner = new JPanel();
        slogansInner.setLayout(new BoxLayout(slogansInner, BoxLayout.Y_AXIS));
        slogansInner.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        slogansInner.setBackground(new Color(255, 248, 220)); // light cream

        JLabel sloganTitle = new JLabel("AJIO Marketing Slogans");
        sloganTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        sloganTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        slogansInner.add(sloganTitle);
        slogansInner.add(Box.createVerticalStrut(8));

        String[] slogans = {
                "🔥 Fashion & Style",
                "AJIO – Fashion That Speaks Before You Do.",
                "Style That Fits Your Vibe.",
                "Wear Your Confidence, Wear AJIO.",
                "💃 Women’s Fashion",
                "Because Every Woman Deserves To Shine.",
                "Slay In Style. Every Day.",
                "Elegance Never Goes Out Of Fashion.",
                "Be Bold. Be Beautiful. Be You."
        };

        for (String s : slogans) {
            JLabel lbl = new JLabel(s);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            slogansInner.add(lbl);
            slogansInner.add(Box.createVerticalStrut(6));
        }

        JScrollPane slogansScroll = new JScrollPane(slogansInner,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        slogansScroll.setPreferredSize(new Dimension(300, 180));

        // Combine footer + slogans into bottom container
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.add(footer, BorderLayout.NORTH);
        bottomContainer.add(slogansScroll, BorderLayout.CENTER);

        // Layout main frame
        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(scrollProducts, BorderLayout.CENTER);
        frame.add(bottomContainer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Create a product card with image, name, price, and Add button
    private static JPanel createProductCard(String imageFile, String productName, String price) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        card.setBackground(Color.WHITE);

        // Image
        JLabel imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = loadImageIcon(imageFile, 220, 220);
        imgLabel.setIcon(icon);
        card.add(imgLabel, BorderLayout.CENTER);

        // Bottom info + button
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(Color.WHITE);

        JLabel nameLbl = new JLabel(productName);
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLbl.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel priceLbl = new JLabel(price);
        priceLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
        priceLbl.setForeground(new Color(0, 128, 64));

        JButton addBtn = new JButton("Add to Cart");
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartCount++;
                cartLabel.setText("Cart: " + cartCount);
                // small feedback
                JOptionPane.showMessageDialog(card, productName + " added to cart!");
            }
        });

        bottom.add(Box.createVerticalStrut(6));
        bottom.add(nameLbl);
        bottom.add(Box.createVerticalStrut(4));
        bottom.add(priceLbl);
        bottom.add(Box.createVerticalStrut(8));
        bottom.add(addBtn);
        bottom.add(Box.createVerticalStrut(6));

        card.add(bottom, BorderLayout.SOUTH);

        return card;
    }

    // Placeholder card for layout balance
    private static JPanel createPlaceholderCard() {
        JPanel p = new JPanel();
        p.setBackground(new Color(245, 245, 245));
        p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.add(new JLabel("More products coming soon ..... 🥳🥳🥳🥳🥳🎉🎉🎉🎊🎊🎊🎊"));

        return p;
    }

    // Load image or create a placeholder image icon
    private static ImageIcon loadImageIcon(String fileName, int w, int h) {
        File f = new File(fileName);
        if (f.exists() && f.isFile()) {
            ImageIcon orig = new ImageIcon(fileName);
            Image img = orig.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            // placeholder image
            BufferedImage ph = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = ph.createGraphics();
            g.setColor(new Color(230, 230, 230));
            g.fillRect(0, 0, w, h);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("SansSerif", Font.PLAIN, 18));
            FontMetrics fm = g.getFontMetrics();
            String text = "No Image";
            int tx = (w - fm.stringWidth(text)) / 2;
            int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, tx, ty);
            g.dispose();
            return new ImageIcon(ph);
        }
    }
}
