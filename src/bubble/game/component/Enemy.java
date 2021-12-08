package bubble.game.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Moveable;
import bubble.game.service.BackgroundEnemyService;
import bubble.game.state.Enemyway;
import lombok.Getter;
import lombok.Setter;

//class Player -> new 가능한 애들 게임에 존재할 수 있다. (추상메서드를 가질 수 없다)
@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

	private BubbleFrame mContext;

	// 위치상태
	private int x;
	private int y;

	// 플래이어의 방향 상태
	private Enemyway enemyWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private int state; // 0 = 살아있는 상태, 1= 물방울에 갇힌 상태
	
	// 적군 속도
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	private ImageIcon enemyR, enemyL;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
		right();
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");

	}

	private void initSetting() {

		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false;
		down = false;
		state =0;
		
		enemyWay = Enemyway.RIGHT;// 플레이어의 방향새팅

		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void initBackgroundEnemyService() {
		new Thread(new BackgroundEnemyService(this)).start();
	}

	@Override
	public void left() {
		enemyWay = Enemyway.LEFT;
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(enemyL);
				x = x - SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void right() {
		enemyWay = Enemyway.RIGHT;
		right = true;
		new Thread(() -> {
			while (right) {
				setIcon(enemyR);
				x = x + SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			up = false;
			down();
		}).start();
	}

	@Override
	public void down() {

		down = true;
		new Thread(() -> {
			while (down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}

}
