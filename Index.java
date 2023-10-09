import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class Index {
	public static JLabel lblFileName;
	private JFrame frmMarksEvaluation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frmMarksEvaluation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DisplayAll.sortEntries=new ArrayList<String>();
		DisplayAll.sortEntries.add("Roll No");
		DisplayAll.sortEntries.add("Name");
		DisplayAll.sortEntries.add("Total");
		JLabel lblDate = new JLabel("");
		lblDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Date now = new Date();
				lblDate.setToolTipText(now.toString());
			}
		});
		myarray.setArrayList();
		frmMarksEvaluation = new JFrame();
		frmMarksEvaluation
		.setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/resorces/tick_16.png")));
		frmMarksEvaluation.setTitle("Marks Evaluation");
		frmMarksEvaluation.setBounds(100, 100, 673, 458);
		frmMarksEvaluation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Date now = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E dd-MM-yyyy");
		lblDate.setText(ft.format(now));
		JButton btnAddStudent = new JButton("Add Student      ");
		btnAddStudent.setIcon(new ImageIcon(Index.class.getResource("/resorces/user-add-icon-64.png")));
		btnAddStudent.setFocusable(false);
		btnAddStudent.setMargin(new Insets(2, 2, 2, 2));
		btnAddStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddStudent.setSelectedIcon(null);
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.setBackground(SystemColor.controlHighlight);
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent.runMyProject();

			}
		});
		btnAddStudent.setFont(new Font("Lato", Font.PLAIN, 18));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);

		menuBar.setMargin(new Insets(5, 0, 5, 0));
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JMenu mnMainMMenu = new JMenu("Menu");
		mnMainMMenu.setSelectedIcon(null);
		mnMainMMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnMainMMenu);

		JMenu mnDeleteStudent = new JMenu("Delete a Student");
		mnDeleteStudent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnDeleteStudent.setPreferredSize(new Dimension(135, 20));
		mnDeleteStudent.setIcon(null);
		mnMainMMenu.add(mnDeleteStudent);

		JMenuItem mntmSearchByRoll = new JMenuItem("Rollno");
		mntmSearchByRoll.setIcon(null);
		mntmSearchByRoll.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmSearchByRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int roll = 0;
				boolean correct = true;
				String rol;
				// loop required so that we can take correct input(Numeric Value only)
				while (correct) {
					try {
						do {
							rol = JOptionPane.showInputDialog(null, "Enter the Rollno", "Delete",
									JOptionPane.PLAIN_MESSAGE);
						} while (rol.length() == 0);

						roll = Integer.parseInt(rol);
						if (roll < 1)
							JOptionPane.showMessageDialog(null, "RollNo should be greater than 0");
						else {
							correct = false;
							int index = myarray.searchByRoll(roll);
							if (index != -1) {
								myarray.delete(index);
								JOptionPane.showMessageDialog(null, "Record Deleted Sucessfully");
							} else
								JOptionPane.showMessageDialog(null, "RollNo Not Found !!!", "Error",
										JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please Enter A Numeric Number", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						correct = false;
					}
				}

			}
		});

		JMenuItem mntmSearchByName = new JMenuItem("Name");
		mntmSearchByName.setIcon(null);
		mntmSearchByName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmSearchByName.setPreferredSize(new Dimension(74, 20));
		mntmSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n;
				try {
					do {
						n = JOptionPane.showInputDialog(null, "Enter Name", "Delete", JOptionPane.PLAIN_MESSAGE);
					} while (n.length() == 0);
					int index = myarray.searchByName(n);
					if (index != -1) {
						myarray.delete(index);
						JOptionPane.showMessageDialog(null, "Record Deleted Sucessfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "Name Not Found!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception NullPoint) {

				}
			}
		});

		mnDeleteStudent.add(mntmSearchByName);
		mnDeleteStudent.add(mntmSearchByRoll);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(null);
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.setSelectedIcon(null);
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.initSettings();
			}
		});
		mntmSettings.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnMainMMenu.add(mntmSettings);

		mnMainMMenu.add(mntmExit);

		JButton btnSearch = new JButton("Search Student");
		btnSearch.setIcon(new ImageIcon(Index.class.getResource("/resorces/search-64.png")));
		btnSearch.setFocusable(false);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String	name="";
				try {
					do {
						name = JOptionPane.showInputDialog(null, "Enter Name or Rollno", "Search Student",JOptionPane.QUESTION_MESSAGE);
					} while (name.length() == 0 );
					int	index = myarray.searchByRoll(Integer.parseInt(name));
					if (index != -1)
						StudentFound.initfound(index);
					else
						JOptionPane.showMessageDialog(null, "Student Not Found!!!", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException n) {
					int	index = myarray.searchByName(name);
					if (index != -1)
						StudentFound.initfound(index);
					else
						JOptionPane.showMessageDialog(null, "Student Not Found!!!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (NullPointerException e1) {

				}
			}
		});
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setFont(new Font("Lato", Font.PLAIN, 18));

		JButton btnDelay = new JButton("Show Delays ");
		btnDelay.setIcon(new ImageIcon(Index.class.getResource("/resorces/deadline-64.png")));
		btnDelay.setFocusable(false);
		btnDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delay.initdelay();
			}
		});
		btnDelay.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDelay.setHideActionText(true);
		btnDelay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelay.setBackground(SystemColor.controlHighlight);
		btnDelay.setFont(new Font("Lato", Font.PLAIN, 18));

		JButton btnDisplay = new JButton("View Students");
		btnDisplay.setIcon(new ImageIcon(Index.class.getResource("/resorces/table-64.png")));
		btnDisplay.setFocusable(false);
		btnDisplay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayAll.init();
			}
		});
		btnDisplay.setBackground(SystemColor.controlHighlight);
		btnDisplay.setPreferredSize(new Dimension(101, 21));
		btnDisplay.setMinimumSize(new Dimension(101, 21));
		btnDisplay.setMaximumSize(new Dimension(101, 21));
		btnDisplay.setFont(new Font("Lato", Font.PLAIN, 18));

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GroupLayout groupLayout = new GroupLayout(frmMarksEvaluation.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(58)
						.addComponent(btnAddStudent, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE).addGap(72)
						.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE).addGap(77))
				.addGroup(groupLayout.createSequentialGroup().addGap(58)
						.addComponent(btnDelay, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE).addGap(72)
						.addComponent(btnDisplay, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE).addGap(77))
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
				.addComponent(panelBottom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE));
		groupLayout
		.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(63)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddStudent, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGap(73)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDelay, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(btnDisplay, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGap(45).addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));

		lblFileName = new JLabel("");
		lblFileName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFileName.setToolTipText("File Name = " + myarray.getFileName() + ".db");
			}
		});

		lblFileName.setText("File : " + myarray.getFileName());
		lblFileName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		;
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFileName, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
					.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBottom.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblFileName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
		);
		panelBottom.setLayout(gl_panelBottom);
		frmMarksEvaluation.getContentPane().setLayout(groupLayout);

	}

}
