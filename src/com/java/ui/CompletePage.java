package com.java.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.java.jdbc.MovieDao;
import com.java.jdbc.MovieDto;
import com.java.jdbc.RSV_MovieDao;
import com.java.jdbc.RSV_MovieDto;
import com.java.jdbc.RankDao;
import com.java.util.Util_Calendar;
import com.java.util.Util_UI;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.DateModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.SystemColor;

public class CompletePage extends JFrame {

	private JPanel contentPane;
	private JTextField txtMovieName;
	private JTextField txtDate;
	private JTextField txtLocation;
	private JTextField txtPeople;
	private JTextField txtSeat;
	private JTextField txtPrice;
	private JTextField txtNow;
	private JButton btnCancle;
	private JTextField txtHall;
	private JTextField txtTime;
	private JTextField txtRunTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompletePage frame = new CompletePage();
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
	public CompletePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("????????????");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("??????", Font.BOLD, 20));
		lblTitle.setBounds(300, 17, 160, 30);
		contentPane.add(lblTitle);

		JButton btnLogOut = new JButton("????????????");
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "???????????? ???????????????????", "Logout", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new MainPage();
					dispose();
				}
			}
		});
		btnLogOut.setBounds(638, 54, 97, 23);
		contentPane.add(btnLogOut);

		JLabel lblUserName = new JLabel("?????????");
		lblUserName.setText(Util_UI.name);
		lblUserName.setFont(new Font("??????", Font.BOLD, 15));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(648, 29, 57, 15);
		contentPane.add(lblUserName);

		JLabel lbltext1 = new JLabel("???");
		lbltext1.setHorizontalAlignment(SwingConstants.CENTER);
		lbltext1.setFont(new Font("??????", Font.BOLD, 15));
		lbltext1.setBounds(696, 29, 57, 15);
		contentPane.add(lbltext1);

		btnCancle = new JButton("????????????");
		btnCancle.setBackground(SystemColor.activeCaption);
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ------?????????------
				Util_UI.cntAdult = 0; // ????????? ????????? ???
				Util_UI.cntChild = 0; // ????????? ???????????? ???
				Util_UI.price = 0; // ??? ????????? ???????????? ????????????
				Util_UI.headCount = 0; // ????????? ????????? ???
				Util_UI.cntSeat = 0; // ????????? ????????? ???
				Util_UI.srtLocation = ""; // ????????? ??????
				Util_UI.srtMovie = ""; // ????????? ??????
				Util_UI.srtRunTime = ""; // ????????? ????????? ????????????
				Util_UI.srtDate = ""; // ????????? ??????
				Util_UI.srtHall = ""; // ????????? ?????? ???
				Util_UI.srtTime = ""; // ????????? ??????
				Util_UI.srtSeat = ""; // ????????? ????????????
				Util_UI.now = ""; // ????????? ????????? ?????? ?????? ?????? ???????????? ????????? ????????????

				dispose();
				new MenuPage();
			}
		});
		btnCancle.setFont(new Font("??????", Font.BOLD, 15));
		btnCancle.setBounds(243, 452, 100, 70);
		contentPane.add(btnCancle);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(-138, 97, 739, 345);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lbl3 = new JLabel("?????? ??????:");
		panel.add(lbl3);
		lbl3.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl3.setFont(new Font("??????", Font.BOLD, 15));

		txtLocation = new JTextField();
		txtLocation.setText(Util_UI.srtLocation); // ????????? ?????? ??????
		txtLocation.setBackground(new Color(255, 255, 255));
		txtLocation.setEditable(false);
		panel.add(txtLocation);
		txtLocation.setColumns(10);

		JLabel lbl2 = new JLabel("??????:");
		panel.add(lbl2);
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl2.setFont(new Font("??????", Font.BOLD, 15));

		txtDate = new JTextField();
		txtDate.setText(Util_UI.srtDate); // ????????? ?????? ??????
		txtDate.setBackground(new Color(255, 255, 255));
		txtDate.setEditable(false);
		panel.add(txtDate);
		txtDate.setColumns(10);
		txtDate.setText(Util_Calendar.getDate);

		JLabel lbl1 = new JLabel("?????? ??????:");
		panel.add(lbl1);
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl1.setFont(new Font("??????", Font.BOLD, 15));

		txtMovieName = new JTextField();
		txtMovieName.setText(Util_UI.srtMovie); // ????????? ?????? ??????
		txtMovieName.setBackground(new Color(255, 255, 255));
		txtMovieName.setEditable(false);
		panel.add(txtMovieName);
		txtMovieName.setColumns(10);

		JLabel lbl2_1 = new JLabel("?????? ??????:");
		lbl2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl2_1.setFont(new Font("??????", Font.BOLD, 15));
		panel.add(lbl2_1);

		txtTime = new JTextField();
		txtTime.setText(Util_UI.srtTime); // ????????? ????????????
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBackground(Color.WHITE);
		panel.add(txtTime);

		JLabel lbl2_1_1 = new JLabel("?????? ??????:");
		lbl2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl2_1_1.setFont(new Font("??????", Font.BOLD, 15));
		panel.add(lbl2_1_1);

		txtRunTime = new JTextField();
		txtRunTime.setText(Util_UI.srtRunTime); // ????????? ????????? ???????????? ??????
		txtRunTime.setEditable(false);
		txtRunTime.setColumns(10);
		txtRunTime.setBackground(Color.WHITE);
		panel.add(txtRunTime);

		JLabel lblNewLabel_1_2 = new JLabel("?????? ??????:");
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("??????", Font.BOLD, 15));

		txtPeople = new JTextField();
		txtPeople.setBackground(new Color(255, 255, 255));
		txtPeople.setEditable(false);
		panel.add(txtPeople);
		txtPeople.setColumns(10);
		txtPeople.setText("?????? " + Util_UI.cntAdult + "???" + " ????????? " + Util_UI.cntChild + "???");

		JLabel lbl1_1 = new JLabel("?????? ???:");
		lbl1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl1_1.setFont(new Font("??????", Font.BOLD, 15));
		panel.add(lbl1_1);

		txtHall = new JTextField();
		txtHall.setText(Util_UI.srtHall); // ????????? ????????? ??????
		txtHall.setEditable(false);
		txtHall.setColumns(10);
		txtHall.setBackground(Color.WHITE);
		panel.add(txtHall);

		JLabel lblNewLabel_1_1_2 = new JLabel("??????:");
		panel.add(lblNewLabel_1_1_2);
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setFont(new Font("??????", Font.BOLD, 15));

		txtSeat = new JTextField();
		txtSeat.setBackground(new Color(255, 255, 255));
		txtSeat.setEditable(false);
		panel.add(txtSeat);
		txtSeat.setColumns(10);
		Util_UI ui = new Util_UI();
		txtSeat.setText(Util_UI.srtSeat);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("????????? ??????:");
		panel.add(lblNewLabel_1_1_1_1_1);
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("??????", Font.BOLD, 15));

		txtNow = new JTextField();
		txtNow.setBackground(new Color(255, 255, 255));
		txtNow.setEditable(false);
		panel.add(txtNow);
		txtNow.setColumns(10);
		txtNow.setText(Util_Calendar.todayDate); // ???????????? ?????? ????????? ?????????

		JLabel lblNewLabel_1_1_1_1 = new JLabel("?????? ??????:");
		panel.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("??????", Font.BOLD, 15));

		txtPrice = new JTextField();
		txtPrice.setBackground(new Color(255, 255, 255));
		txtPrice.setEditable(false);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		txtPrice.setText(Integer.toString((Util_UI.price)) + "???");

		JButton btnOk = new JButton("????????????");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//????????? ?????????????????? ??????
				String email = Util_UI.keyEmail;
				String movie_location = txtLocation.getText().trim();
				String movie_date = txtDate.getText().trim();
				String movie_name = txtMovieName.getText().trim();
				String movie_time = txtTime.getText().trim();
				String movie_runtime = txtRunTime.getText().trim();
				String movie_people = txtPeople.getText().trim();
				String movie_hall = txtHall.getText().trim();
				String movie_seat = txtSeat.getText().trim();
				String movie_now = txtNow.getText().trim();
				String movie_price = txtPrice.getText().trim();

				RSV_MovieDto dto = new RSV_MovieDto(email, movie_location, movie_date, movie_name, movie_time,
						movie_runtime, movie_people, movie_hall, movie_seat, movie_now, movie_price);
				dto.setMovie_name(email);
				dto.setMovie_location(movie_location);
				dto.setMovie_date(movie_date);
				dto.setMovie_name(movie_name);
				dto.setMovie_time(movie_time);
				dto.setMovie_runtime(movie_runtime);
				dto.setMovie_people(movie_people);
				dto.setMovie_hall(movie_hall);
				dto.setMovie_seat(movie_seat);
				dto.setMovie_now(movie_now);
				dto.setMovie_price(movie_price);

				RankDao rdao = new RankDao();
				int a = rdao.updateRank(Util_UI.price, Util_UI.headCount, movie_name); // ????????? ????????? ???????????? ????????????????????????
				if (a == 1) {
					System.out.println("???????????? ??????????????????");
				} else {
					System.out.println("???????????? ??????????????????");
				}

				RSV_MovieDao dao = new RSV_MovieDao();
				int result = dao.addRSV_Movie(dto); //???????????? ???????????? ????????? ??????
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "?????? ???????????????.");
					// ?????????
					Util_UI.cntAdult = 0; // ????????? ????????? ???
					Util_UI.cntChild = 0; // ????????? ???????????? ???
					Util_UI.price = 0; // ??? ????????? ???????????? ????????????
					Util_UI.headCount = 0; // ????????? ????????? ???
					Util_UI.cntSeat = 0; // ????????? ????????? ???
					Util_UI.srtLocation = ""; // ????????? ??????
					Util_UI.srtMovie = ""; // ????????? ??????
					Util_UI.srtRunTime = ""; // ????????? ????????? ????????????
					Util_UI.srtDate = ""; // ????????? ??????
					Util_UI.srtHall = ""; // ????????? ?????? ???
					Util_UI.srtTime = ""; // ????????? ??????
					Util_UI.srtSeat = ""; // ????????? ????????????
					Util_UI.now = ""; // ????????? ????????? ?????? ?????? ?????? ???????????? ????????? ????????????
					dispose();
					new MenuPage(); // ?????????????????? ??????
				} else {
					JOptionPane.showMessageDialog(null, "???????????? ??????????????????.");
				}
			}
		});
		btnOk.setBackground(SystemColor.activeCaption);
		btnOk.setFont(new Font("??????", Font.BOLD, 15));
		btnOk.setBounds(397, 452, 100, 70);
		contentPane.add(btnOk);

	}
}
