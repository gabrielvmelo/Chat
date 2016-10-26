package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import client.Client;
import server.Server;
import streams.Writer;

import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.SystemColor;

public class GUIFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField ipField;
	private JTextField portField;
	private JTextField textField;
	private String ipAdress;
	private String userName;
	private int port;
	private Thread threadServer;
	private Thread threadClient;
	private Client client;
	private Server server;
	private Writer write;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	//private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame frame = new GUIFrame();
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
	public GUIFrame() {
		setTitle("Chat 1.6");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 443);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*textArea = new JTextPane();
		textArea.setBounds(10, 136, 456, 195);
		contentPane.add(textArea);*/

		nameField = new JTextField();
		nameField.setBackground(SystemColor.activeCaptionBorder);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(10, 13, 190, 26);
		contentPane.add(nameField);
		nameField.setColumns(10);

		ipField = new JTextField();
		ipField.setBackground(SystemColor.activeCaptionBorder);
		ipField.setHorizontalAlignment(SwingConstants.CENTER);
		ipField.setBounds(12, 45, 190, 28);
		contentPane.add(ipField);
		ipField.setColumns(10);

		portField = new JTextField();
		portField.setBackground(SystemColor.activeCaptionBorder);
		portField.setHorizontalAlignment(SwingConstants.CENTER);
		portField.setBounds(12, 84, 86, 28);
		contentPane.add(portField);
		portField.setColumns(10);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(SystemColor.menu);
		lblUserName.setBounds(210, 14, 96, 14);
		contentPane.add(lblUserName);

		JLabel lblIp = new JLabel("IP");
		lblIp.setForeground(SystemColor.text);
		lblIp.setBounds(212, 55, 55, 16);
		contentPane.add(lblIp);

		JLabel lblIp_1 = new JLabel("Port");
		lblIp_1.setForeground(SystemColor.text);
		lblIp_1.setBounds(100, 86, 55, 16);
		contentPane.add(lblIp_1);

		textField = new JTextField();
		textField.setBackground(SystemColor.controlShadow);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int pressBtn = arg0.getKeyCode();
				if(pressBtn == KeyEvent.VK_ENTER){
					try{
						System.out.println("ok");
						if(client != null){
							client.getWrite().sendMessage();
						}
						else if (server != null){
							server.getWrite().sendMessage();
						}
						textField.setText("");
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});
		textField.setBounds(10, 342, 456, 52);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnStart = new JButton("Start 1");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ipAdress = ipField.getText();
					port = Integer.parseInt(portField.getText());
					userName = nameField.getText();
					System.out.println(userName);
					server = new Server(port, textArea, textField, userName);
					threadServer = new Thread(server);
					threadServer.start();

				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnStart.setBounds(351, 11, 90, 28);
		contentPane.add(btnStart);

		JButton btnStop = new JButton("Start 2");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ipAdress = ipField.getText();
					port = Integer.parseInt(portField.getText());
					userName = nameField.getText();
					System.out.println(userName);
					client = new Client(ipAdress, port, textArea, textField, userName);
					threadClient = new Thread(client);
					threadClient.start();
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnStop.setBounds(351, 42, 90, 28);
		contentPane.add(btnStop);
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 144, 439, 186);
				contentPane.add(scrollPane);
		
				textArea = new JTextArea();
				textArea.setBackground(SystemColor.scrollbar);
				textArea.setWrapStyleWord(true);
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);


	}
}
