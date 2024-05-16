import view.BackgroundPanel;
import view.ConversionGUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        JFrame frame = new JFrame("Conversor de Moedas");
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/java/assets/Background.png");

        frame.setContentPane(backgroundPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(new ConversionGUI(), BorderLayout.CENTER);

        frame.setVisible(true);

    }
}
