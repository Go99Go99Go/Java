package com.java.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.java.jdbc.MemberDao;
import com.java.jdbc.MemberDto;
import com.java.util.Util_UI;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.SystemColor;

public class MainPage extends JFrame {

	private JPanel pnlMain;
	private JTextField txtPw;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(300, 100, 800, 600);
		setLocationRelativeTo(null);
		pnlMain = new JPanel();
		pnlMain.setBackground(Color.WHITE);
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlMain);
		pnlMain.setLayout(null);

		txtPw = new JPasswordField();
		txtPw.setToolTipText("PW");
		txtPw.setBounds(200, 300, 300, 40);
		pnlMain.add(txtPw);
		txtPw.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setToolTipText("ID");
		txtEmail.setColumns(10);
		txtEmail.setBounds(200, 250, 300, 40);
		pnlMain.add(txtEmail);

		JLabel lblTitle = new JLabel("????????????????????????");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("??????", Font.BOLD, 20));
		lblTitle.setBounds(300, 17, 168, 30);
		pnlMain.add(lblTitle);

		JButton btnLogin = new JButton("Login"); // ????????? ??????
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setBounds(519, 251, 105, 89);
		pnlMain.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() { // ????????? ????????? ????????????
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText().trim();
				String pw = txtPw.getText().trim();			
				MemberDao dao = new MemberDao();
				int result = dao.login(email, pw); //???????????? ???????????? ????????? ???????????? ????????? ??????(1?????? ??????)		
				if (email.length() == 0 && pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "???????????? ??????????????? ??????????????????.", "Login", JOptionPane.ERROR_MESSAGE);
					txtEmail.setText("");
					txtPw.setText("");
				}else if (email.length() == 0) {
					JOptionPane.showMessageDialog(null, "???????????? ??????????????????.", "Login", JOptionPane.ERROR_MESSAGE);
					txtEmail.setText("");
				}else if (pw.length() == 0) {
					JOptionPane.showMessageDialog(null, "??????????????? ??????????????????.", "Login", JOptionPane.ERROR_MESSAGE);
					txtPw.setText("");
				}else if(result == 1) {	//?????????????????????
					dao.showName(email, pw); //????????????
					Util_UI.keyEmail = email; //??????????????? ???????????? ???????????? ?????? email??? ??????????????? ??????
					dispose();
					new MenuPage(); //?????????????????? ??????
				}
				else {
					JOptionPane.showMessageDialog(null, "??????????????? ??????????????? ???????????? ????????????.", "Login", JOptionPane.ERROR_MESSAGE);
					txtEmail.setText("");
					txtPw.setText("");
				}
			}
		});

		JButton btnFindPw = new JButton("PW??????"); // PW?????? ??????
		btnFindPw.setBackground(SystemColor.activeCaption);
		btnFindPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
					dispose();
					new FindPwPage();
			}
		});
		btnFindPw.setBounds(319, 396, 105, 27);
		pnlMain.add(btnFindPw);
		

		JButton btnSignUp = new JButton("????????????"); // ??????????????????
		btnSignUp.setBackground(SystemColor.activeCaption);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUpPage();
			}
		});
		btnSignUp.setBounds(319, 435, 105, 27);
		pnlMain.add(btnSignUp);
		
	}
}
