package view;

import model.Conversion;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ConversionGUI extends JLabel {

    public ConversionGUI(String API_KEY) throws IOException {

        final String[] CURRENCIES = {"BRL", "USD", "EUR", "JPY", "GBP", "CHF", "CAD", "AUD", "NZD", "CNY", "ARS"};

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel fromLabel = new JLabel("De");
        fromLabel.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        fromLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fromLabel, gbc);

        JComboBox<String> fromCurrency = new JComboBox<>(CURRENCIES);
        fromCurrency.setPreferredSize(new Dimension(150, fromCurrency.getPreferredSize().height));
        fromCurrency.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        fromCurrency.setMaximumRowCount(5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromCurrency, gbc);

        JLabel toLabel = new JLabel("Para");
        toLabel.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        toLabel.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(toLabel, gbc);

        JComboBox<String> toCurrency = new JComboBox<>(CURRENCIES);
        toCurrency.setPreferredSize(new Dimension(150, toCurrency.getPreferredSize().height));
        toCurrency.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        toCurrency.setMaximumRowCount(5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(toCurrency, gbc);

        JLabel amountLabel = new JLabel("Valor em BRL:");
        amountLabel.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        amountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(amountLabel, gbc);

        JTextField amountField = new JTextField();
        amountField.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        amountField.setBorder(new EmptyBorder(0, 5, 0 , 0));
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
        resultLabel.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setVisible(false);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(resultLabel, gbc);

        JTextField resultField = new JTextField();
        resultField.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        resultField.setBorder(new EmptyBorder(0, 5, 0, 0));
        resultField.setBackground(Color.WHITE);
        resultField.setFocusable(false);
        resultField.setEditable(false);
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
        previousResultsLabel.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        previousResultsLabel.setForeground(Color.WHITE);
        previousResultsLabel.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(previousResultsLabel, gbc);

        JComboBox<String> resultsComboBox = new JComboBox<>();
        resultsComboBox.setPreferredSize(new Dimension(300, resultsComboBox.getPreferredSize().height));
        resultsComboBox.setFont(new Font("JetBrainsMono-Bold", Font.BOLD, 14));
        resultsComboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
        resultsComboBox.setMaximumRowCount(4);
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

        convertButton.addActionListener(e -> {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            String amount = amountField.getText();

            if (from != null && to != null && !amount.isEmpty()) {
                try {
                    double amountValue = Double.parseDouble(amount);
                    String url_str = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                            API_KEY, from, to, amountValue);

                    String result = Conversion.getConversionResult(url_str);
                    String formattedResult = Conversion.formatResult(result);
                    String resultText = amount + " " + from + " em " + to + " = " + formattedResult;
                    resultField.setText(formattedResult);
                    resultField.setVisible(true);
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

