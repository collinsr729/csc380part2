package csc380;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

public class myFrame extends JFrame {
	JTextPane text;
	JButton confirm, deny;
	boolean bool = true;
	boolean buttonPressed = false;
	JPanel panel;
	public myFrame() {
		super("Campus Delivery");

		confirm = new JButton("Ok");
		deny = new JButton("Cancel");

		text = new JTextPane();
		text.setEditable(true);
		text.setText("Just setting up...");
		setBounds(100, 100, 
				Math.max(text.getWidth(),confirm.getWidth()+deny.getWidth()),
				20+text.getHeight()+confirm.getHeight());
		panel = new JPanel();
		panel.setBounds(getBounds());
		panel.setLayout(new BorderLayout());
		panel.add(text,BorderLayout.PAGE_START);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bool = true;
				buttonPressed = true;
			}
		});
		panel.add(confirm, BorderLayout.WEST);
		deny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bool = false;
				buttonPressed = true;
			}
		});
		panel.add(deny, BorderLayout.EAST);
		add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void setText(String s) {
		text.setText(s);
	}
}
