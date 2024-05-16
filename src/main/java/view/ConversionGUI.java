package view;

import io.github.cdimascio.dotenv.Dotenv;
import model.Conversion;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConversionGUI extends JLabel {

    public ConversionGUI() throws IOException, URISyntaxException {

        final Font BOLD_FONT = new Font("Font", Font.BOLD, 14);
        final String[] CURRENCIES = Conversion.getCurrencyCodes().toArray(new String[0]);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel fromLabel = new JLabel("De");
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setFont(BOLD_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fromLabel, gbc);

        JComboBox<String> fromCurrency = new JComboBox<>(CURRENCIES);
        fromCurrency.setPreferredSize(new Dimension(150, fromCurrency.getPreferredSize().height));
        fromCurrency.setMaximumRowCount(5);
        fromCurrency.setFont(BOLD_FONT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromCurrency, gbc);

        JLabel toLabel = new JLabel("Para");
        toLabel.setForeground(Color.WHITE);
        toLabel.setFont(BOLD_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(toLabel, gbc);

        JComboBox<String> toCurrency = new JComboBox<>(CURRENCIES);
        toCurrency.setPreferredSize(new Dimension(150, toCurrency.getPreferredSize().height));
        toCurrency.setMaximumRowCount(5);
        toCurrency.setFont(BOLD_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(toCurrency, gbc);

        JLabel amountLabel = new JLabel("Valor em BRL:");
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(BOLD_FONT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setBorder(new EmptyBorder(0, 5, 0 , 0));
        amountField.setFont(BOLD_FONT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(amountField, gbc);

        JButton convertButton = new JButton("Converter");
        convertButton.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 13));
        convertButton.setBackground(Color.decode("#142026"));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusable(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(convertButton, gbc);

        JLabel resultLabel = new JLabel("Valor em BRL:");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(BOLD_FONT);
        resultLabel.setVisible(false);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(resultLabel, gbc);

        JTextField resultField = new JTextField();
        resultField.setBorder(new EmptyBorder(0, 5, 0, 0));
        resultField.setBackground(Color.WHITE);
        resultField.setFocusable(false);
        resultField.setEditable(false);
        resultField.setFont(BOLD_FONT);
        resultField.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(resultField, gbc);

        JLabel emptyLabel = new JLabel();
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(emptyLabel, gbc);

        JLabel emptyLabel2 = new JLabel();
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(emptyLabel2, gbc);

        JLabel previousResultsLabel = new JLabel("Resultados Anteriores: ");
        previousResultsLabel.setForeground(Color.WHITE);
        previousResultsLabel.setFont(BOLD_FONT);
        previousResultsLabel.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(previousResultsLabel, gbc);

        JComboBox<String> resultsComboBox = new JComboBox<>();
        Map<String, String> resultDateMap = new HashMap<>();
        resultsComboBox.setPreferredSize(new Dimension(300, resultsComboBox.getPreferredSize().height));
        resultsComboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXX");
        resultsComboBox.setMaximumRowCount(4);
        resultsComboBox.setFont(BOLD_FONT);
        resultsComboBox.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(resultsComboBox, gbc);

        JLabel emptyLabel3 = new JLabel();
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(emptyLabel3, gbc);

        JLabel emptyLabel4 = new JLabel();
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(emptyLabel4, gbc);

        JLabel imageLabel = new JLabel();
        Image img = ImageIO.read(new File("src/main/java/assets/alura_challenges.png"));
        Image scaledImg = img.getScaledInstance(180, 40, Image.SCALE_SMOOTH);
        imageLabel.setBorder(new EmptyBorder(0, 65, 0, 0));
        imageLabel.setIcon(new ImageIcon(scaledImg));
        imageLabel.setOpaque(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(imageLabel, gbc);

        fromCurrency.addActionListener(e -> {
            String selectedCurrency = (String) fromCurrency.getSelectedItem();
            amountLabel.setText("Valor em " + selectedCurrency + ":");
        });

        toCurrency.addActionListener(e -> {
            String selectedCurrency = (String) toCurrency.getSelectedItem();
            resultLabel.setText("Valor em " + selectedCurrency + ":");
            resultField.setText("");
        });

        convertButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                convertButton.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                convertButton.setBackground(Color.decode("#142026"));
            }
        });

        resultsComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) resultsComboBox.getSelectedItem();
                if (selectedItem != null) {
                    String date = resultDateMap.get(selectedItem);
                    resultsComboBox.setToolTipText(date);
                }
            }
        });

        convertButton.addActionListener(e -> {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            String amount = amountField.getText().replace(",", ".");

            if (from != null && to != null && !amount.isEmpty()) {
                try {
                    Dotenv dotenv = Dotenv.load();
                    String API_KEY = dotenv.get("API_KEY");
                    double amountValue = Double.parseDouble(amount);
                    String url_str = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                            API_KEY, from, to, amountValue);
                    String result = Conversion.getConversionResult(url_str);

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String data = sdf.format(date);

                    String resultText = amount + " " + from + " em " + to + " = " + result;
                    resultField.setText(result);
                    resultField.setVisible(true);

                    resultDateMap.put(resultText, data);
                    resultsComboBox.addItem(resultText);
                    resultsComboBox.setSelectedItem(resultText);
                    resultsComboBox.setVisible(true);

                    resultLabel.setVisible(true);
                    previousResultsLabel.setVisible(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao buscar o resutado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

