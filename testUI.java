
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class testUI {

	private JButton btnNewButton;
	private JRadioButton rdbtnDark;
	private JRadioButton rdbtnLight;
	private JFrame frame;
	private JComboBox<String> comboBox;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testUI window = new testUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Preferences pref = Preferences.userNodeForPackage(this.getClass());

		ArrayList<String> file = new ArrayList<String>();
		comboBox = new JComboBox<String>();
		comboBox.setBounds(121, 53, 172, 21);
		String s = "test.db";
		btnNewButton = new JButton(s.substring(0, s.length() - 3));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, LocalDate.parse("2020-04-04"));
			}
		});
		btnNewButton.setBounds(131, 202, 103, 32);
		File di = new File(".");
		String tempfilelist[] = di.list();
		for (int i = 0; i < tempfilelist.length; i++) {
			if (tempfilelist[i].endsWith(".db")) {
				file.add(tempfilelist[i].substring(0, tempfilelist[i].length() - 3));
			}
		}

		rdbtnLight = new JRadioButton("Light");
		rdbtnLight.setBounds(81, 120, 66, 21);

		rdbtnDark = new JRadioButton("Dark");
		rdbtnDark.setBounds(186, 120, 68, 21);
		rdbtnDark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnLight.isSelected() || !rdbtnDark.isSelected()) {
					rdbtnLight.setSelected(false);
					rdbtnDark.setSelected(true);
				}
				darkTheme();
				pref.putInt("THEME", 1);
			}

		});

		rdbtnLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDark.isSelected() || !rdbtnLight.isSelected()) {
					rdbtnDark.setSelected(false);
					rdbtnLight.setSelected(true);
				}
				lightTheme();
				pref.putInt("THEME", 0);
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (rdbtnLight.isSelected()) {
					frame.getContentPane().setBackground(Color.WHITE);
				} else {
					frame.getContentPane().setBackground(Color.DARK_GRAY);
				}

			}
		});
		if (pref.getInt("THEME", 0) == 1) {
			darkTheme();
			rdbtnDark.setSelected(true);
		} else {
			lightTheme();
			rdbtnLight.setSelected(true);
		}
		frame.getContentPane().setLayout(null);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Delete");
		mntmNewMenuItem.setIcon(new ImageIcon(testUI.class.getResource("/resorces/delete_16.png")));
		popupMenu.add(mntmNewMenuItem);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(rdbtnLight);
		frame.getContentPane().add(rdbtnDark);
		frame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textField.setText("5654");
		textField.setBounds(121, 160, 143, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	void darkTheme() {
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBorderPainted(true);
		rdbtnDark.setBackground(Color.DARK_GRAY);
		rdbtnLight.setBackground(Color.DARK_GRAY);
		rdbtnLight.setForeground(Color.WHITE);
		rdbtnDark.setForeground(Color.WHITE);
		comboBox.setBackground(Color.LIGHT_GRAY);
	}

	void lightTheme() {
		frame.getContentPane().setBackground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		rdbtnDark.setBackground(Color.WHITE);
		rdbtnLight.setBackground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorderPainted(true);
		rdbtnLight.setForeground(Color.BLACK);
		rdbtnDark.setForeground(Color.BLACK);
		comboBox.setBackground(Color.WHITE);
	}
}
