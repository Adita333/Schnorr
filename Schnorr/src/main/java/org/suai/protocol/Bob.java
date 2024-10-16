package org.suai.protocol;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;

import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Bob extends JFrame {
    public Alice client;
    public Schnorr SCHNORR;
    
    public JTextArea textArea_p;
    public JTextArea textArea_q;
    public JTextArea textArea_g;
    public JTextArea textArea_y;
    
    public JTextArea X_TextArea;
    public JTextField random_E_TextField;
    public JTextArea S_TextArea;

    public Bob() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.dispose();
            }
        });
        
        setTitle("Bob (servidor)");
        setSize(434,638);
        setLocation(700, 50);
        getContentPane().setLayout(null);
        
        JLabel lblPublicKeys = new JLabel("Claves Públicas:");
        lblPublicKeys.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPublicKeys.setBounds(12, 4, 110, 16);
        getContentPane().add(lblPublicKeys);
        
        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBounds(12, 28, 392, 257);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblP = new JLabel("p =");
        lblP.setBounds(12, 12, 56, 16);
        panel.add(lblP);
        
        JScrollPane scrollPane_p = new JScrollPane();
        scrollPane_p.setBounds(37, 8, 344, 57);
        panel.add(scrollPane_p);
        scrollPane_p.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        textArea_p = new JTextArea();
        textArea_p.setLocation(46, 0);
        scrollPane_p.setViewportView(textArea_p);
        textArea_p.setEditable(false);
        textArea_p.setLineWrap(true);
        
        JLabel lblQ = new JLabel("q =");
        lblQ.setBounds(12, 78, 27, 16);
        panel.add(lblQ);
        
        textArea_q = new JTextArea();
        textArea_q.setEditable(false);
        textArea_q.setLineWrap(true);
        
        JScrollPane scrollPane_q = new JScrollPane(textArea_q);
        scrollPane_q.setBounds(37, 73, 344, 41);
        panel.add(scrollPane_q);
        scrollPane_q.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        JLabel lblG = new JLabel("g =");
        lblG.setBounds(12, 127, 56, 16);
        panel.add(lblG);
        
        JScrollPane scrollPane_g = new JScrollPane();
        scrollPane_g.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_g.setBounds(37, 122, 344, 57);
        panel.add(scrollPane_g);
        
        textArea_g = new JTextArea();
        textArea_g.setLineWrap(true);
        textArea_g.setEditable(false);
        scrollPane_g.setViewportView(textArea_g);
        
        JScrollPane scrollPane_y = new JScrollPane();
        scrollPane_y.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_y.setBounds(37, 187, 344, 57);
        panel.add(scrollPane_y);
        
        textArea_y = new JTextArea();
        textArea_y.setEditable(false);
        textArea_y.setLineWrap(true);
        scrollPane_y.setViewportView(textArea_y);
        
        JLabel lblY = new JLabel("y =");
        lblY.setBounds(12, 192, 56, 16);
        panel.add(lblY);
        
        JLabel label = new JLabel("Protocolo de verificación de autenticidad:");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(12, 298, 310, 16);
        getContentPane().add(label);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.setBounds(12, 324, 392, 256);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(37, 13, 344, 61);
        panel_1.add(scrollPane);
        
        X_TextArea = new JTextArea();
        X_TextArea.setForeground(Color.BLUE);
        X_TextArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        X_TextArea.setEditable(false);
        X_TextArea.setLineWrap(true);
        scrollPane.setViewportView(X_TextArea);
        
        JLabel lblX = new JLabel("x =");
        lblX.setBounds(12, 13, 56, 16);
        panel_1.add(lblX);
        
        JButton random_E_Button = new JButton("Random E:");
        random_E_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                random_E_TextField.setText(BigInteger.probablePrime(72, new Random()).toString());
            }
        });
        random_E_Button.setBounds(37, 98, 103, 25);
        panel_1.add(random_E_Button);
        
        random_E_TextField = new JTextField();
        random_E_TextField.setForeground(new Color(0, 128, 128));
        random_E_TextField.setBounds(152, 99, 229, 22);
        panel_1.add(random_E_TextField);
        random_E_TextField.setColumns(10);
        
        JLabel label_1 = new JLabel("3. Respuesta de Bob:");
        label_1.setBounds(12, 80, 122, 16);
        panel_1.add(label_1);
        
        JButton send_E_Button = new JButton("<< Enviar E a Alice");
        send_E_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                client.random_E_TextField.setText(random_E_TextField.getText());
                
            }
        });
        send_E_Button.setBounds(151, 129, 229, 25);
        panel_1.add(send_E_Button);
        
        JLabel lblNewLabel = new JLabel("s =");
        lblNewLabel.setBounds(12, 168, 56, 16);
        panel_1.add(lblNewLabel);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_1.setBounds(37, 165, 344, 44);
        panel_1.add(scrollPane_1);
        
        S_TextArea = new JTextArea();
        S_TextArea.setForeground(Color.RED);
        S_TextArea.setEditable(false);
        S_TextArea.setLineWrap(true);
        S_TextArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        scrollPane_1.setViewportView(S_TextArea);

        JButton checkOutButton = new JButton("5. Verificar autenticidad");
        
        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String X = X_TextArea.getText();
                String S = S_TextArea.getText();
                String E = random_E_TextField.getText();
                
                if (X.isEmpty() || S.isEmpty() || E.isEmpty()) {
                    String str = "¡Datos incompletos para la verificación! \nPor favor, complete todos los campos.";
                    JOptionPane.showMessageDialog(null, str, "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    BigInteger x = new BigInteger(X);
                    BigInteger s = new BigInteger(S);
                    BigInteger e = new BigInteger(E);
                    SCHNORR.checkOut(x, s, e, true);
                }
            }
        });
        checkOutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        checkOutButton.setForeground(Color.RED);
        checkOutButton.setBounds(37, 218, 343, 25);
        panel_1.add(checkOutButton);
    }
}
