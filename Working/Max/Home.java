//package GameClasses;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


class Home extends JFrame implements ActionListener
{
	private String humanPlayer[] = {"1", "2", "3", "4"};
	private String CPUplayer[] = {"3", "2", "1", "0"};
	private String[] themes = {"Default", "Fire", "Ice", "Earth", "Rainbow"};
	public static Color plOneColor = new Color(30, 144, 255), plTwoColor = new Color(0, 255, 0), plThreeColor = new Color(255, 255, 0), plFourColor = new Color(169, 169, 169), backgroundColor = new Color(255, 255, 255), GameboardColor;
	 
	private JPanel panelTop_Home, panelBottomMain_Home, panelNewGame_Home, panelSettings_Home, panelCpuPlayers_Home;
	private JLabel lblTitle_Home, lblSettings, lblChoosePlayers, lblDifficulty, lblHumanPlayer, lblCPUplayer;
	private JButton btnNewGame_Home, btnResume_Home, btnSettings_Home, btnGoBack_NewGame, 
		btnGoBack_Settings, btnStartGame_ChoosePlayers;
	private JCheckBox chckbxHints, chckbxCpuPlayer1, chckbxCpuPlayer2, chckbxCpuPlayer3, 
		chckbxEasy, chckbxMedium, chckbxHard;
	private JComboBox comboBoxTheme, comboBoxPlayer, comboBoxCPUplayer;
	private ButtonGroup difficlutyGrp;
	
	private int intHuman, intCPU, difficulty;
	private boolean hints;
	
	
	public Home() 
	{
		setTitle("Blokus");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//.......... Top Panel ..........
		panelTop_Home = new JPanel();
		panelTop_Home.setBackground(plOneColor);
		panelTop_Home.setBounds(0, 0, 700, 110);
		getContentPane().add(panelTop_Home);
		panelTop_Home.setLayout(null);
		
		lblTitle_Home = new JLabel("Blokus");
		lblTitle_Home.setFont(new Font("courier", Font.BOLD, 50));
		lblTitle_Home.setBounds(251, 27, 210, 70);
		panelTop_Home.add(lblTitle_Home);
		//.......... Top Panel ..........
		
		
		//.......... Bottom Panels ..........		
		panelBottomMain_Home = new JPanel();
		panelBottomMain_Home.setBackground(UIManager.getColor("TabbedPane.highlight"));
		panelBottomMain_Home.setBounds(0, 110, 700, 262);
		getContentPane().add(panelBottomMain_Home);
		panelBottomMain_Home.setLayout(null);
		
		panelNewGame_Home = new JPanel();
		panelNewGame_Home.setBackground(UIManager.getColor("TabbedPane.highlight"));
		panelNewGame_Home.setBounds(0, 110, 700, 262);
		getContentPane().add(panelNewGame_Home);
		panelNewGame_Home.setLayout(null);
		
		panelSettings_Home = new JPanel();
		panelSettings_Home.setBackground(UIManager.getColor("TabbedPane.highlight"));
		panelSettings_Home.setBounds(0, 110, 700, 262);
		getContentPane().add(panelSettings_Home);
		panelSettings_Home.setLayout(null);
		
		panelCpuPlayers_Home = new JPanel();
		panelCpuPlayers_Home.setBackground(UIManager.getColor("TabbedPane.highlight"));
		panelCpuPlayers_Home.setBounds(0, 110, 700, 262);
		getContentPane().add(panelCpuPlayers_Home);
		panelCpuPlayers_Home.setLayout(null);
		//.......... Bottom Panels ..........		

		
		//.......... Main Menu ..........
		btnNewGame_Home = new JButton("New Game");
		btnNewGame_Home.setFont(new Font("courier", Font.PLAIN, 23));
		btnNewGame_Home.setBackground(plTwoColor);
		btnNewGame_Home.setBounds(240, 31, 220, 45);
		btnNewGame_Home.addActionListener(this);
		panelBottomMain_Home.add(btnNewGame_Home);
		
		btnResume_Home = new JButton("Resume");
		btnResume_Home.setFont(new Font("courier", Font.PLAIN, 23));
		btnResume_Home.setBackground(plThreeColor);
		btnResume_Home.setBounds(240, 107, 220, 45);
		btnResume_Home.addActionListener(this);
		panelBottomMain_Home.add(btnResume_Home);
		
		btnSettings_Home = new JButton("Settings");
		btnSettings_Home.setFont(new Font("courier", Font.PLAIN, 23));
		btnSettings_Home.setBackground(plFourColor);
		btnSettings_Home.setBounds(240, 183, 220, 45);
		btnSettings_Home.addActionListener(this);
		panelBottomMain_Home.add(btnSettings_Home);
		//.......... Main Menu ..........
		
		
		//.......... Choose Player(s) [New Game button] ..........
		lblChoosePlayers = new JLabel("Choose Player(s)");
		lblChoosePlayers.setFont(new Font("courier", Font.BOLD, 20));
		lblChoosePlayers.setBounds(250, 15, 250, 35);
		panelNewGame_Home.add(lblChoosePlayers);
		
		lblDifficulty = new JLabel("Difficulty"); 
		lblDifficulty.setFont(new Font("courier", Font.BOLD, 20));
		lblDifficulty.setBounds(290, 100, 120, 35);
		panelNewGame_Home.add(lblDifficulty);
		
		lblHumanPlayer = new JLabel("Human"); 
		lblHumanPlayer.setFont(new Font("courier", Font.BOLD, 20));
		lblHumanPlayer.setBounds(100, 55, 115, 35);
		panelNewGame_Home.add(lblHumanPlayer);
		
		lblCPUplayer = new JLabel("CPU"); 
		lblCPUplayer.setFont(new Font("courier", Font.BOLD, 20));
		lblCPUplayer.setBounds(410, 55, 115, 35);
		panelNewGame_Home.add(lblCPUplayer);
		
		comboBoxPlayer = new JComboBox(humanPlayer);
		comboBoxPlayer.setBackground(Color.LIGHT_GRAY);
		comboBoxPlayer.setFont(new Font("courier", Font.PLAIN, 20));
		comboBoxPlayer.setBounds(200, 55, 115, 35);
		comboBoxPlayer.addActionListener(this);
		panelNewGame_Home.add(comboBoxPlayer);
		
		comboBoxCPUplayer = new JComboBox(CPUplayer);
		comboBoxCPUplayer.setBackground(Color.LIGHT_GRAY);
		comboBoxCPUplayer.setFont(new Font("courier", Font.PLAIN, 20));
		comboBoxCPUplayer.setBounds(480, 55, 115, 35);
		comboBoxCPUplayer.addActionListener(this);
		panelNewGame_Home.add(comboBoxCPUplayer);
		
		hints = true;
		chckbxEasy = new JCheckBox("Easy");
		chckbxEasy.setBackground(plTwoColor);
		chckbxEasy.setFont(new Font("courier", Font.PLAIN, 20));
		chckbxEasy.setBounds(88, 140, 115, 35);
		chckbxEasy.setSelected(hints);
		chckbxEasy.addActionListener(this);
		panelNewGame_Home.add(chckbxEasy);
		
	    chckbxMedium = new JCheckBox("Medium");
		chckbxMedium.setBackground(plThreeColor);
		chckbxMedium.setFont(new Font("courier", Font.PLAIN, 20));
		chckbxMedium.setBounds(291, 140, 115, 35);
		chckbxMedium.addActionListener(this);
		panelNewGame_Home.add(chckbxMedium);
		
		chckbxHard = new JCheckBox("Hard");
		chckbxHard.setBackground(plFourColor);
		chckbxHard.setFont(new Font("courier", Font.PLAIN, 20));
		chckbxHard.setBounds(494, 140, 115, 35);
		chckbxHard.addActionListener(this);
		panelNewGame_Home.add(chckbxHard);
		
		btnStartGame_ChoosePlayers = new JButton("Start Game");
		btnStartGame_ChoosePlayers.setBackground(Color.GREEN);
		btnStartGame_ChoosePlayers.setFont(new Font("courier", Font.PLAIN, 17));
		btnStartGame_ChoosePlayers.setBounds(525, 210, 150, 35);
		btnStartGame_ChoosePlayers.addActionListener(this);
		panelNewGame_Home.add(btnStartGame_ChoosePlayers);
		
		difficlutyGrp = new ButtonGroup();
		difficlutyGrp.add(chckbxEasy);
		difficlutyGrp.add(chckbxMedium);
		difficlutyGrp.add(chckbxHard);
		//.......... Choose Player(s) [New Game button] ..........
		
		
		//.......... Settings ..........	
		lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("courier", Font.BOLD, 30));
		lblSettings.setBounds(285, 30, 150, 35);
		panelSettings_Home.add(lblSettings);
		
		chckbxHints = new JCheckBox("    Hints");
		chckbxHints.setBackground(new Color(192, 192, 192));
		chckbxHints.setSelected(true);
		chckbxHints.setFont(new Font("courier", Font.PLAIN, 25));
		chckbxHints.setBounds(240, 90, 220, 45);
		chckbxHints.addActionListener(this);
		panelSettings_Home.add(chckbxHints);
		
		comboBoxTheme = new JComboBox(themes);
		comboBoxTheme.setBackground(new Color(192, 192, 192));
		comboBoxTheme.setFont(new Font("courier", Font.PLAIN, 21));
		comboBoxTheme.setBounds(240, 170, 220, 45);
		comboBoxTheme.addActionListener(this);
		panelSettings_Home.add(comboBoxTheme);
		//.......... Settings ..........
		
		
		//.......... Go Back buttons ..........
		btnGoBack_NewGame = new JButton("Go Back");
		btnGoBack_NewGame.setBackground(Color.YELLOW);
		btnGoBack_NewGame.setFont(new Font("courier", Font.PLAIN, 17));
		btnGoBack_NewGame.setBounds(25, 210, 115, 35);
		btnGoBack_NewGame.addActionListener(this);
		panelNewGame_Home.add(btnGoBack_NewGame);
		
		btnGoBack_Settings = new JButton("Go Back");
		btnGoBack_Settings.setBackground(Color.YELLOW);
		btnGoBack_Settings.setFont(new Font("courier", Font.PLAIN, 17));
		btnGoBack_Settings.setBounds(25, 210, 115, 35);
		btnGoBack_Settings.addActionListener(this);
		panelSettings_Home.add(btnGoBack_Settings);
		//.......... Go Back buttons ..........

		
		panelNewGame_Home.setVisible(false);
		panelSettings_Home.setVisible(false);
		panelBottomMain_Home.setVisible(true);
		setResizable(false);
		setVisible(true);
	}
	
	
	//......updates the colors on the main windows
	public void updateColors()
	{
		panelTop_Home.setBackground(plOneColor);
		btnNewGame_Home.setBackground(plTwoColor);
		btnResume_Home.setBackground(plThreeColor);
		btnSettings_Home.setBackground(plFourColor);
		chckbxEasy.setBackground(plTwoColor);
		chckbxMedium.setBackground(plThreeColor);
		chckbxHard.setBackground(plFourColor);
			
	}
	public static Color getPlayerColor(int player)
	{
		if (player == 0)
		{
				return plOneColor;
		}
		else if (player == 1)
		{
			return plTwoColor;
		}
		else if (player == 2)
		{
			return plThreeColor;
		}
		else if  (player == 3)
		{
			return plFourColor;
		}
		else {
			return Color.WHITE;
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e) 
    {
		if (chckbxHints.isSelected() == false) { hints = false; }

		if (e.getSource().equals(btnResume_Home)) 
        {
			setVisible(false);
			Game gridWindow = new Game(0, hints, difficulty);
        }
	
		
        if (e.getSource().equals(btnNewGame_Home)) 
        {
        	panelNewGame_Home.setVisible(true);
        	panelSettings_Home.setVisible(false);
        	panelBottomMain_Home.setVisible(false);
        }
        else if (e.getSource().equals(btnSettings_Home)) 
        {
        	panelSettings_Home.setVisible(true);
        	panelBottomMain_Home.setVisible(false);
        	panelNewGame_Home.setVisible(false);      	
		}
        else if (e.getSource().equals(btnGoBack_NewGame) || e.getSource().equals(btnGoBack_Settings)) 
        {
        	panelNewGame_Home.setVisible(false);
        	panelSettings_Home.setVisible(false);
        	panelBottomMain_Home.setVisible(true);
		}
        else if (e.getSource().equals(btnStartGame_ChoosePlayers)) 
        {
        	if (chckbxEasy.isSelected() || chckbxMedium.isSelected() || chckbxHard.isSelected())
        	{
        		Object ob1 = comboBoxPlayer.getSelectedItem();
        		Object ob2 = comboBoxCPUplayer.getSelectedItem();
        		intHuman = Integer.valueOf((String) ob1);
        		intCPU = Integer.valueOf((String) ob2);
        		
        		
        		if (intHuman + intCPU >= 2 && intHuman + intCPU <= 4) 
        		{
        			if (chckbxEasy.isSelected() ) {
        				difficulty = 1; 
        				setVisible(false);
        			}
        			else if (chckbxMedium.isSelected()) {
        				difficulty = 2;
        				setVisible(false);
        			}

        			else if (chckbxHard.isSelected()) {
        				difficulty = 3;
        				setVisible(false);
        			}
        			Game gridWindow = new Game(1, hints, difficulty);
        		 }else {
        			JOptionPane.showMessageDialog(this, "Players cannot be more than 4");
        		}
			}
		}
        else if (e.getSource().equals(comboBoxTheme))
        {
        	String selectedTheme = comboBoxTheme.getSelectedItem().toString();
        			
        	if (selectedTheme == "Default")
        	{
        		plOneColor = new Color(30, 144, 255);
        		plTwoColor = new Color(0, 255, 0);
        		plThreeColor = new Color(255, 255, 0);
        		plFourColor = new Color(169, 169, 169);
        		updateColors();
        	}
        	else if (selectedTheme == "Fire")
        	{
        		plOneColor = new Color(255, 0, 0);
        		plTwoColor = new Color(235, 102, 0);
        		plThreeColor = new Color(235, 184, 0);
        		plFourColor = new Color(231, 235, 0);
        		updateColors();
        	}
        		
        	else if (selectedTheme == "Ice")
        	{
        		plOneColor = new Color(0, 0, 240);
        		plTwoColor = new Color(0, 204, 255);
        		plThreeColor = new Color(0, 247, 255);
        		plFourColor = new Color(194, 61, 255);
        		updateColors();
        		
        	}	
        	else if (selectedTheme == "Earth")
        	{
        		plOneColor = new Color(255, 111, 0);
        		plTwoColor = new Color(140, 98, 34);
        		plThreeColor = new Color(55, 255, 0);
        		plFourColor = new Color(100, 100, 100);
        		updateColors();
        		
        	}
        	else if (selectedTheme == "Rainbow")
        	{
        		plOneColor = new Color(255, 0, 0);
        		plTwoColor = new Color(47, 255, 0);
        		plThreeColor = new Color(0, 200, 255);
        		plFourColor = new Color(171, 0, 157);
        		updateColors();
        		
        	}
        }
    }
}