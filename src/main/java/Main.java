import io.github.cdimascio.dotenv.Dotenv;
import view.BackgroundPanel;
import view.ConversionGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Dotenv dotenv = Dotenv.load();
        String API_KEY = dotenv.get("API_KEY");
        JFrame frame = new JFrame("Conversor de Moedas");

        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/java/assets/Background.png");
        frame.setContentPane(backgroundPanel);

        ConversionGUI conversionGUI = new ConversionGUI(API_KEY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(conversionGUI, BorderLayout.CENTER);

        frame.setVisible(true);


    }
}
