package com.cometsoftware.pft;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

import de.heckconsulting.tauchen.core.dto.UserDTO;
import de.heckconsulting.tauchen.core.service.UserService;

@Component
public class Startup {
	
    @Autowired
    private UserService userService;
    
    UserDTO user;
	
	private JPanel createContentPane() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel( "This is a test" + (user == null ? "User nicht vorhanden" : user.getId() ));
		panel.add( label );
		return panel;
	}
	
    private void createAndShowGUI() {
    	
    	JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        LoginManager lg = new LoginManager(frame,true, userService );
        lg.setVisible(true);
        frame.setVisible(true);
        user = lg.getUser();
        frame.setTitle( frame.getTitle() + "   '" + user.getUsername() + "'" );
        
        
        //Create and set up the content pane.
        frame.setContentPane(createContentPane());
 
        //Display the window.
        Dimension frameSize = new Dimension(400, 300);
        frame.setSize(frameSize);
        frame.setPreferredSize(frameSize);
        frame.setVisible(true);
    }
	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		final Startup p = context.getBean( Startup.class );
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				try {
					UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
				} catch (Exception e) {
					e.printStackTrace();
				}
        	    p.createAndShowGUI();
            }
        });
	}

}
