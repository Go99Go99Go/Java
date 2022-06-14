package com.java.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.java.jdbc.DBConnection;
import com.java.util.Util_Calendar;
import com.java.util.Util_UI;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.DateModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectMoviePage extends JFrame {

	static String driver = "org.mariadb.jdbc.Driver"; // jdbc드라이버
	static String url = "jdbc:mariadb://Localhost:3306/movie"; // jdbc:db이름://Localhost3306
	static String uid = "root";
	static String pwd = "1234";

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;

	private DefaultTableModel tbModel;
	private JPanel contentPane;
	private JTable table;

	public void dbClose() { // DB닫기
		try {
			if (rs != null) // ResultSet이 비어있지 않으면 ResultSet 닫기
				rs.close();
			if (stmt != null) // statement가 비어있지 않으면 statement 닫기
				stmt.close();
			if (pstmt != null)// preparetatement가 비어있지 않으면 preparetatement 닫기
				pstmt.close();
			if (con != null) // db 커넥션이 비어있지 않으면 닫기
				con.close();
		} catch (Exception e) {
			System.out.println(e + "=> DB닫기 실패");
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectMoviePage frame = new SelectMoviePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void selectAll() {
		con = DBConnection.getConnection();
		try {
			// 선택한 지역에서 상영중인 영화를 보여주는 쿼리문
			pstmt = con.prepareStatement(
					"SELECT DISTINCT movie_name, movie_runtime from movie_tb   where movie_location = '"
							+ Util_UI.srtLocation + "' ORDER BY movie_name asc");
			tbModel.setRowCount(0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String[] row = new String[2];
				for (int i = 0; i < row.length; i++) {
					row[i] = rs.getString(i + 1);
				}
				tbModel.addRow(row);
			}
		} catch (Exception e) {

		} finally {
			dbClose();
		}
	}

	/**
	 * Create the frame.
	 */
	public SelectMoviePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
		lblTitle.setBounds(300, 17, 160, 30);
		contentPane.add(lblTitle);

		JButton btnLogOut = new JButton("로그아웃");
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Logout", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new MainPage();
					dispose();
				}
			}
		});
		btnLogOut.setBounds(638, 54, 97, 23);
		contentPane.add(btnLogOut);

		JLabel lblUserName = new JLabel("홍길동");
		lblUserName.setText(Util_UI.name);
		lblUserName.setFont(new Font("굴림", Font.BOLD, 15));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(648, 29, 57, 15);
		contentPane.add(lblUserName);

		JLabel lbltext1 = new JLabel("님");
		lbltext1.setHorizontalAlignment(SwingConstants.CENTER);
		lbltext1.setFont(new Font("굴림", Font.BOLD, 15));
		lbltext1.setBounds(696, 29, 57, 15);
		contentPane.add(lbltext1);

		JButton btnPre = new JButton("이전");
		btnPre.setBackground(SystemColor.activeCaption);
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util_UI.srtMovie = ""; // 선택한 영화
				dispose();
				new SelectLocationPage();
			}
		});
		btnPre.setFont(new Font("굴림", Font.BOLD, 20));
		btnPre.setBounds(44, 430, 100, 70);
		contentPane.add(btnPre);

		Util_UI ui = new Util_UI();

		JLabel lblAdult_5 = new JLabel("영화 선택");
		lblAdult_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdult_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblAdult_5.setBounds(285, 29, 192, 68);
		contentPane.add(lblAdult_5);

		JButton btnNext = new JButton("다음");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 다음버튼을 누르면
				Util_UI.row = table.getSelectedRow(); // 선택한 row값 받아옴
				Util_UI.srtMovie = (String) table.getValueAt(Util_UI.row, 0); // 선택한 영화를 전역변수에 저장
				Util_UI.srtRunTime = (String) table.getValueAt(Util_UI.row, 1);// 선택한 영화의 상영길이를 전역변수에 저장
				// System.out.println("선택한 영화:"+Util_UI.srtMovie);
				// System.out.println("선택한 영화의 상영길이:"+Util_UI.srtRunTime);
				dispose();
				new SelectDatePage(); // 영화 선택 페이지로 넘어감

			}
		});
		btnNext.setFont(new Font("굴림", Font.BOLD, 20));
		btnNext.setBackground(SystemColor.activeCaption);
		btnNext.setBounds(331, 430, 100, 70);
		contentPane.add(btnNext);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == 1) {
					Util_UI.row = table.getSelectedRow(); // 행
					Util_UI.col = table.getSelectedColumn(); // 열
				}
			}

		});
		scrollPane.setBounds(200, 200, 350, 150);
		contentPane.add(scrollPane);

		tbModel = (new DefaultTableModel(new Object[][] {}, new String[] { "CGV " + Util_UI.srtLocation, "상영 길이" }));
		table = new JTable(tbModel);
		scrollPane.setViewportView(table);
		table.setFont(new Font("굴림", Font.BOLD, 20));
		table.setRowHeight(25);
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 셀 중앙정렬을 위한 코드
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}

		table.getColumn("CGV " + Util_UI.srtLocation).setPreferredWidth(300); // 셀 넓이조절

		selectAll();

	}
}
