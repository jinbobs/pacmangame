import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
//20195096 최진성.
 class pacman {
	
	private static Random random;
	private static int pacmanH, pacmanW, enemyH, enemyW, numOfDot, where, start;
	private static int fieldMin=0, fieldMax=13;
	private static Icon temp1, temp2, temp3, temp4, temp;
	
	private static final int FRAME_WIDTH = 690;
	private static final int FRAME_HEIGHT = 650;
	
	static Timer t;

	public pacman(){
	final JFrame frame = new JFrame();//게임 프레임
	final JFrame frame1 = new JFrame();//게임에 실패했을때 생성되는 프레임
	//게임을 만들면서 필요했던 이미지들.
	final ImageIcon successIcon = new ImageIcon("gameoverImage.png");
	final ImageIcon gameoverIcon = new ImageIcon("gameover.png");
	final ImageIcon smallDot = new ImageIcon("smallDot.png");
	final ImageIcon rebutton = new ImageIcon("rebutton.png");
	final ImageIcon gcbtn = new ImageIcon("gameclearbtn.png");
	
	final ImageIcon wall = new ImageIcon("wall.png");
	final ImageIcon enemy = new ImageIcon("enemy.png");
	final ImageIcon enemy1 = new ImageIcon("enemy1.png");
	final ImageIcon enemy2 = new ImageIcon("enemy2.png");
	final ImageIcon enemy3 = new ImageIcon("enemy3.png");
	
	final ImageIcon pacman0 = new ImageIcon("pacman.png");
	final ImageIcon pacman1 = new ImageIcon("pacman1.png");
	final ImageIcon pacman2 = new ImageIcon("pacman2.png");
	final ImageIcon pacman3 = new ImageIcon("pacman3.png");
	final ImageIcon pacman4 = new ImageIcon("pacman4.png");
	final ImageIcon pacman5 = new ImageIcon("pacman5.png");
	final ImageIcon empty = new ImageIcon("empty.png");
	final JButton  gameclearbtn =new JButton(gcbtn);
	final JButton  rebtn =new JButton(rebutton);
	final JLabel gover=new JLabel();
	final JLabel end=new JLabel();
	
	end.setIcon(successIcon);
	gover.setIcon(gameoverIcon);
	gover.setBounds(0,0,690,650);
	rebtn.setBounds(225,455,240,50);
	rebtn.setBorderPainted(false);//버튼 테두리 설정
	rebtn.setFocusPainted(false);//포커스 표시
	rebtn.setContentAreaFilled(false);//버튼영역 배경표시 설정

	rebtn.addActionListener(new ActionListener() {//게임을 다시 시작하기 위한 액션리스너.
		@Override
		public void actionPerformed(ActionEvent e) {
			frame1.dispose();
			pacman c=new pacman();
					}
	});
	
	
	gameclearbtn.addActionListener(new ActionListener() {//게임을 클리어하면 시스템이 종료되게하는 액션리스너.

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	});
	
	Random random = new Random();
	

	
	pacmanH=12;  pacmanW=7;  enemyH=7;  enemyW=7;  numOfDot=79;  start=3;  temp=empty;
	//여기서 스타트를 2이상의 값으로 해주지 않는다면, 적이 갇힐수도 있기 때문에 무조권 2이상의 값으로 설정해줘야함.
	
	final JLabel[][] f = new JLabel[14][14];//라벨 배열을 생성하여 이 라벨에 이미지를 집어넣음.
	
	for (int i=0; i<14; i++) {
		for(int j=0; j<14; j++) {
			f[i][j] = new JLabel();
		}
	}
	

	
	
	class TListener implements ActionListener {   //timer클래스 ActionListener(적의 움직임을 timer클래스에서 나타낸다.시간에 따라 랜덤값으로 적이 움직임.)
		public void actionPerformed(ActionEvent event)
		{
			if(start<=0) where = 1+random.nextInt(4);//1~4까지의 값을 랜덤으로 줌.
			else { 
				where = 1; start--; //만약 스타트의 값이 1이라고 했을때 이문장에서 0이되어 한번 올라갔다가 공간에 갇힐수도 있게됌.
			}
			switch(where) {
			case 1:
				if(!(f[enemyH-1][enemyW].getIcon()).equals(wall)) {//현재위치보다  위에방향의 아이콘이 벽과 같지않다면 if문 실행.
					temp1=f[enemyH-1][enemyW].getIcon();//temp1에 현재위치보다 아랫방향의 아이콘을 복사함.(즉temp1은 점이있는 아이콘이됌)
					f[enemyH-1][enemyW].setIcon(enemy2);//그위치를 적이미지로 설정함.(즉,적을 아래로 이동시킴을 의미)
					f[enemyH][enemyW].setIcon(temp);//그리고나서 적이 전에 있던 자리를 temp로 설정함.
					temp=temp1;//temp1의 위치의 아이콘을 temp에 복사.(temp를 점이 있는 아이콘으로 만들어줌으로서, 적은 코인을 먹지않고 움직이는 상태가 됌)
					enemyH--;//적의 높이를 높여서 적이 f[enemyH-1][enemyW] 로 이동한다.
				}//temp1으로 복사하는 이유는 적은 점을 먹으면 안돼기 때문에, 적이 지나온 자리에 다시 점을 생성시켜주기 위해서. 
				break;
			case 2:
				if(!(f[enemyH+1][enemyW].getIcon()).equals(wall)) {//where이 2일경우는 아래로 내려감
					temp2=f[enemyH+1][enemyW].getIcon();
					f[enemyH+1][enemyW].setIcon(enemy3);
					f[enemyH][enemyW].setIcon(temp);
					temp=temp2;
					enemyH++;
				}
				break;
			case 3:
				if(!(f[enemyH][enemyW-1].getIcon()).equals(wall)) {//where이 3일경우는 왼쪽으로 이동.
					temp3=f[enemyH][enemyW-1].getIcon();
					f[enemyH][enemyW-1].setIcon(enemy1);
					f[enemyH][enemyW].setIcon(temp);
					temp=temp3;
					enemyW--;
				}
				break;
			case 4:
				if(!(f[enemyH][enemyW+1].getIcon()).equals(wall)) {//where이 4일경우는 오른쪽으로 이동.
					temp4=f[enemyH][enemyW+1].getIcon();
					f[enemyH][enemyW+1].setIcon(enemy);
					f[enemyH][enemyW].setIcon(temp);
					temp=temp4;
					enemyW++;
				}
				break;
			}
			if(enemyH==pacmanH && enemyW==pacmanW) {//팩맨과 적의 위치가 같다면 frame1 ui를 불러서 게임오버화면을 띄우게 함.
				f[enemyH][enemyW].setIcon(enemy);
				t.stop();//타이머 스탑을 시켜주지 않으면 프레임을 종료시켰더라도 타이머는 유지되기 때문에 필수로 사용하여야 게임을 다시 시작할수 있음.
				frame.dispose();
				frame1.add(rebtn);
				frame1.add(gover);
				frame1.setTitle("PacmanGame");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				
			}
			
			if(enemyH==5 && enemyW==7) { //라벨의 값에서 적의 위치가 5,7에 위치하면 그자리에 벽을 생성하여 공간에 빠지는 것을 막아줌.
				f[6][7].setIcon(wall); 
				}//벽을 만들어서 그자리에 못가게함.
			
	
		}
	}
	class KListener extends KeyAdapter{  //키어댑터를 상속받아 인터페이스를 구현했을때보다 더 쉽게 원하는 메소드만 불러올수있음.
		public void keyPressed(KeyEvent e) {
			if(numOfDot<1) {//점의 남은 개수가 없어지면 성공 프레임을 만들어줌.
				frame.dispose();//프레임종료
				frame1.add(gameclearbtn);
				gameclearbtn.setBounds(150,400,377,59);
				gameclearbtn.setBorderPainted(false);
				frame1.add(end);
				frame1.setTitle("PacmanGame");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
				
				
			}
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP:
				if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot) || (f[pacmanH-1][pacmanW].getIcon()).equals(empty)) {
					if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH-1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH-1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH-1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH-1][pacmanW].setIcon(pacman3);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanH--;
				}
				if((f[pacmanH-1][pacmanW].getIcon()).equals(enemy)||f[pacmanH-1][pacmanW].getIcon().equals(enemy1)||f[pacmanH-1][pacmanW].getIcon().equals(enemy2)||f[pacmanH-1][pacmanW].getIcon().equals(enemy3)) {
					f[enemyH][enemyW].setIcon(enemy);
					t.stop();					
					frame.dispose();					
					frame1.add(rebtn);
					frame1.add(gover);
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
				}
				break;
			case KeyEvent.VK_DOWN:
				if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot) || (f[pacmanH+1][pacmanW].getIcon()).equals(empty)) {
					if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH+1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH+1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH+1][pacmanW].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH+1][pacmanW].setIcon(pacman2);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanH++;
				}
				if((f[pacmanH+1][pacmanW].getIcon()).equals(enemy)||f[pacmanH+1][pacmanW].getIcon().equals(enemy1)||f[pacmanH+1][pacmanW].getIcon().equals(enemy2)||f[pacmanH+1][pacmanW].getIcon().equals(enemy3)){
					f[enemyH][enemyW].setIcon(enemy);
					frame.dispose();
					t.stop();
					frame1.add(rebtn);
					frame1.add(gover);
					
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
				
				}
				break;
			case KeyEvent.VK_LEFT:
				if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot) || (f[pacmanH][pacmanW-1].getIcon()).equals(empty)) {
					if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH][pacmanW-1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW-1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW-1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH][pacmanW-1].setIcon(pacman4);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanW--;
				}
				if((f[pacmanH][pacmanW-1].getIcon()).equals(enemy)||f[pacmanH][pacmanW-1].getIcon().equals(enemy1)||f[pacmanH][pacmanW-1].getIcon().equals(enemy2)||f[pacmanH][pacmanW-1].getIcon().equals(enemy3)){
					f[enemyH][enemyW].setIcon(enemy);
					frame.dispose();
					t.stop();
					frame1.add(rebtn);
					frame1.add(gover);
					
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
				}
				
				break;
			case KeyEvent.VK_RIGHT:
				if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot) || (f[pacmanH][pacmanW+1].getIcon()).equals(empty)) {
					if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman3)) {
						numOfDot--;
						}
					else if(f[pacmanH][pacmanW+1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman2)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW+1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman4)) {
						numOfDot--;
					}
					else if(f[pacmanH][pacmanW+1].getIcon().equals(smallDot) && (f[pacmanH][pacmanW].getIcon()).equals(pacman5)) {
						numOfDot--;
					}
					f[pacmanH][pacmanW+1].setIcon(pacman5);
					f[pacmanH][pacmanW].setIcon(empty);
					pacmanW++;
				}
				if((f[pacmanH][pacmanW+1].getIcon()).equals(enemy)||f[pacmanH][pacmanW+1].getIcon().equals(enemy1)||f[pacmanH][pacmanW+1].getIcon().equals(enemy2)||f[pacmanH][pacmanW+1].getIcon().equals(enemy3)) {
					f[enemyH][enemyW].setIcon(enemy);
					frame.dispose();
					t.stop();
					frame1.add(rebtn);
					frame1.add(gover);
					
					frame1.setTitle("PacmanGame");
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
					
					
					
				}
				break;
			}
			
		}
	}
	KListener listener = new KListener();
	TListener tListener = new TListener();
	
	
	
	t = new Timer(150, tListener);//0.15초에 한번씩 랜덤값에 의한 적의 움직임 설정.
	t.start();

	
	
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(14,14));
	
	frame.requestFocus();
	frame.addKeyListener(new KListener());
	
	
	for(int i=0; i<14; i++) {
		for(int j=0; j<14; j++) {
			f[i][j].setIcon(wall);
			f[i][j].addKeyListener(listener);
			panel.add(f[i][j]);
		}
	}
	
	f[1][1].setIcon(smallDot);	f[2][1].setIcon(smallDot);	f[3][1].setIcon(smallDot);	f[4][1].setIcon(smallDot);	f[5][1].setIcon(smallDot);
	f[5][2].setIcon(smallDot);	f[5][3].setIcon(smallDot);	f[1][3].setIcon(smallDot);	f[2][3].setIcon(smallDot);	f[3][3].setIcon(smallDot);
	f[4][3].setIcon(smallDot);	f[1][4].setIcon(smallDot);	f[1][5].setIcon(smallDot);	f[1][6].setIcon(smallDot);	f[1][7].setIcon(smallDot);
	f[1][8].setIcon(smallDot);	f[1][9].setIcon(smallDot);	f[1][10].setIcon(smallDot);	f[1][11].setIcon(smallDot);	f[1][12].setIcon(smallDot);
	f[2][9].setIcon(smallDot);	f[2][12].setIcon(smallDot);	f[3][12].setIcon(smallDot);	f[4][12].setIcon(smallDot);	f[5][12].setIcon(smallDot);
	f[3][4].setIcon(smallDot);	f[3][5].setIcon(smallDot);	f[3][10].setIcon(smallDot);	f[3][11].setIcon(smallDot);
	f[4][5].setIcon(smallDot);	f[4][6].setIcon(smallDot);	f[4][7].setIcon(smallDot);	f[4][8].setIcon(smallDot);	f[4][9].setIcon(smallDot);
	f[4][10].setIcon(smallDot);	f[2][7].setIcon(smallDot);	f[3][7].setIcon(smallDot);	f[4][10].setIcon(smallDot);	f[4][11].setIcon(smallDot);
	f[5][11].setIcon(smallDot);	f[6][11].setIcon(smallDot);	f[7][11].setIcon(smallDot);	f[7][12].setIcon(smallDot);	f[8][12].setIcon(smallDot);
	f[9][12].setIcon(smallDot);	f[10][12].setIcon(smallDot);f[11][12].setIcon(smallDot);f[5][7].setIcon(empty);		f[6][2].setIcon(smallDot);
	f[7][1].setIcon(smallDot);	f[9][11].setIcon(smallDot);	f[11][1].setIcon(smallDot);	f[11][3].setIcon(smallDot);	f[11][11].setIcon(smallDot);
	f[7][2].setIcon(smallDot);	f[7][3].setIcon(smallDot);	f[8][1].setIcon(smallDot);	f[9][1].setIcon(smallDot);	f[9][2].setIcon(smallDot);
	f[9][3].setIcon(smallDot);	f[9][4].setIcon(smallDot);	f[9][5].setIcon(smallDot);	f[9][6].setIcon(smallDot);	f[12][1].setIcon(smallDot);
	f[12][2].setIcon(smallDot);	f[12][3].setIcon(smallDot);	f[12][4].setIcon(smallDot);	f[12][5].setIcon(smallDot);	f[12][9].setIcon(smallDot);
	f[12][10].setIcon(smallDot);f[12][11].setIcon(smallDot); f[10][5].setIcon(smallDot); f[11][5].setIcon(smallDot); f[10][6].setIcon(smallDot);
	f[10][7].setIcon(smallDot);	f[10][8].setIcon(smallDot); f[10][9].setIcon(smallDot); f[11][7].setIcon(smallDot); f[12][7].setIcon(pacman3);
	f[9][8].setIcon(smallDot); f[9][9].setIcon(smallDot); f[11][9].setIcon(smallDot); f[6][5].setIcon(empty);f[6][6].setIcon(empty);
	f[6][7].setIcon(empty); f[6][8].setIcon(empty);f[6][9].setIcon(empty); f[7][5].setIcon(empty);f[7][6].setIcon(empty);
	f[7][7].setIcon(enemy); f[7][8].setIcon(empty);f[7][9].setIcon(empty);
	
	frame.add(panel);
	frame.setTitle("PacmanGame");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	
}


	static class startUI extends JFrame{//처음 시작 ui
		startUI(){
			setTitle("PacmanGame");
			
			final ImageIcon str = new ImageIcon("startButton2.png");
			final ImageIcon help = new ImageIcon("helpbutton1.png");
			final ImageIcon strback = new ImageIcon("startImage1.png");
			JPanel startPanel=new JPanel(null);
			JButton strbtn =new JButton(str);
			JButton helpbtn =new JButton(help);
			JLabel a=new JLabel();
			strbtn.setBorderPainted(false);
			helpbtn.setBorderPainted(false);
			helpbtn.setFocusPainted(false);
			strbtn.setFocusPainted(false);
			startPanel.add(helpbtn);
			a.setIcon(strback);
			startPanel.add(a);
			a.setBounds(0,0,440,777);
			startPanel.add(strbtn);
			helpbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new helpUI();
					dispose();
					
					
					
				}
			});
			strbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					new pacman();
					dispose();
					
				}
			});
			helpbtn.setBounds(230,230,200,33);
			strbtn.setBounds(230,170,200,31);
			add(startPanel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setSize(449,785);
			
			
			
		}
	}
}

class helpUI extends JFrame{
	helpUI(){
		
		JPanel panel=new JPanel(null);
		add(panel);
		ImageIcon underImage = new ImageIcon("pacmans.png");
		ImageIcon explainImage = new ImageIcon("게임설명.png");
		ImageIcon btnImage = new ImageIcon("화살표버튼.png");
		ImageIcon btnImage2 = new ImageIcon("화살표버튼반대.png");
		JLabel gamerule=new JLabel();
		JLabel gamestart=new JLabel("게임시작");
		gamestart.setFont(new Font("ExtraBOLD",Font.CENTER_BASELINE,20));
		gamestart.setForeground(Color.yellow);
		gamestart.setBounds(285,560,100,70);
		panel.add(gamestart);
		JLabel backui=new JLabel("이전화면");
		backui.setFont(new Font("ExtraBOLD",Font.CENTER_BASELINE,20));
		backui.setForeground(Color.yellow);
		backui.setBounds(60,560,100,70);
		panel.add(backui);

		gamerule.setIcon(underImage);
		JLabel gameexplain=new JLabel();
		gameexplain.setIcon(explainImage);
		JButton startbtn=new JButton(btnImage);
		JButton startui=new JButton(btnImage2);
		
		
		startbtn.setBounds(50, 600, 100, 66);
		startui.setBounds(280, 600, 100, 66);
		gameexplain.setBounds(0,0,448,780);
		gamerule.setBounds(0,0,449,76);
		startbtn.setBorderPainted(false);
		startbtn.setContentAreaFilled(false);//버튼영역을 배경표시로 설정
		startui.setFocusPainted(false);
		startui.setBorderPainted(false);
		startui.setContentAreaFilled(false);//버튼영역을 배경표시로 설정
		startui.setFocusPainted(false);
		
		panel.add(startbtn);
		panel.add(startui);
		panel.add(gamerule);	
		panel.add(gameexplain);
		startui.addActionListener(new ActionListener(){//게임을 시작하는 버튼

			@Override
			public void actionPerformed(ActionEvent e) {
				new pacman();
				dispose();				
			}
			
		});
		startbtn.addActionListener(new ActionListener(){//처음화면으로 돌아가는 버튼

			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.startUI a=new pacman.startUI();
				dispose();
				
			}
			
		});
	
		
		setTitle("PacmanGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		setSize(449,785);
	
	}
	
}

public class pacmangame {
	public static void main(String[] args) {
		pacman.startUI a=new pacman.startUI();
	}
}
