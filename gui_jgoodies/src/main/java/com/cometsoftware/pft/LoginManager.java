package com.cometsoftware.pft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


import de.heckconsulting.tauchen.core.dto.UserDTO;
import de.heckconsulting.tauchen.core.service.UserService;

class LoginManager extends JDialog {
	
	private static final long serialVersionUID = -9121683805864281837L;
	private UserService service;
	
	public LoginManager(JFrame parent, boolean modal, UserService service ) {
        super(parent, modal);
        this.service = service;
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(290,190);
        setLocationRelativeTo(null);
        
        loginPanel = new JPanel();
        nameL = new JLabel();
        nameField = new JTextField();
        pwdL = new JLabel();
        passwordField = new JPasswordField();
        enterB = new JButton();
        exitB = new JButton();
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        nameL.setFont(new Font("Microsoft Sans Serif", 0, 14));
        nameL.setText("Login Name:");
        nameL.setPreferredSize(new Dimension(85, 20));
        loginPanel.add(nameL);
        nameField.setPreferredSize(new Dimension(100, 25));
        loginPanel.add(nameField);
        
        pwdL.setFont(new Font("Microsoft Sans Serif", 0, 14));
        pwdL.setText("Password:");
        pwdL.setPreferredSize(new Dimension(85, 20));
        loginPanel.add(pwdL);
        passwordField.setEchoChar('#');
        passwordField.setPreferredSize(new Dimension(100, 25));
        loginPanel.add(passwordField);
        
        enterB.setForeground(new Color(0, 153, 0));
        enterB.setText("Enter");
        enterB.setPreferredSize(new Dimension(70, 25));
        enterB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enterBActionPerformed(evt);
            }
        });
        loginPanel.add(enterB);
        getRootPane().setDefaultButton(enterB);
        
        exitB.setForeground(new Color(255, 0, 0));
        exitB.setText("Exit");
        exitB.setPreferredSize(new Dimension(70, 25));
        exitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitBActionPerformed(evt);
            }
        });
        loginPanel.add(exitB);
        
        getContentPane().add(loginPanel, BorderLayout.CENTER);
    }
    private void enterBActionPerformed(ActionEvent evt) {
        username = nameField.getText();
        String input = new String( passwordField.getPassword() );
        boolean isCorrect = false;
        try{
            isCorrect = isPasswordCorrect(username, input);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
        if ( isCorrect ) {
            setVisible(false);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocusInWindow();
        }
    }
    private void exitBActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
    private boolean isPasswordCorrect(String username, String password) throws SQLException{
    	user = service.validateLogin( username, password);
    	if ( user != null ) {
            return true;
    	} else {
    		return false;
    	}
    }
    
    public UserDTO getUser(){
        return user;
    }
    private JButton enterB, exitB;
    private JPanel loginPanel;
    private JTextField nameField;
    private JLabel nameL, pwdL;
    private JPasswordField passwordField;
    private String username;
    private UserDTO user;
}