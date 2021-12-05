package bubble.test.ex05;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player();
		add(player);

	}

	private void initSetting() {
		setSize(1000, 640);

		setLocationRelativeTo(null);// jframe 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x버튼으로 창을 끌때 JVM도 같이 종료하기
	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {// 리스너를 만들었다.
			//키보드 클릭이벤트핸들러
			@Override
			public void keyPressed(KeyEvent e) {

				System.out.println(e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(!player.isLeft()) {//이벤트핸들러가 한번만 실행되도록
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight()) {//이벤트핸들러가 한번만 실행되도록
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if(!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;

				}

			}
			
			//키보드에서 손을 땔때 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}

		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}
}
