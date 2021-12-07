package bubble.test.ex13;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// 의존성 콤포지션
	private BubbleFrame mContext;
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	// 위치상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태
	private int state; // 0(물방울), 1(적을 가둔 물방울)

	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물장울
	private ImageIcon bomb; // 물방울이 터진상태

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
	}

	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		backgroundBubbleService = new BackgroundBubbleService(this);

	}

	private void initSetting() {

		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();
		setIcon(bubble);
		setSize(50, 50);
		state = 0;

	}

	@Override
	public void left() {
		left = true;

		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);

			// 체크하면서 벽에 닿으면 for문 정지
			if (backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);

			if (backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);
			if (backgroundBubbleService.topWall()) {
				up = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clearBubble();// 천장에 버블이 도착하고 나서 3초 후에 메모리에서 소멸
	}

	// 이메서드를 호출할 메서드가 up()밖에없다면 private로 해주는게 낫다.
	// 메서드를 만들때는 동사를 앞으로 해주는게 나중에 .을사용해서 찾을때 편하다.
	public void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this);// buubleFrame의 bubble이 메모리에서 소멸한다.
			// 하지만 메모리에서 사라졌지만 화면에이미지는 그대로 남아있다.
			// 그래서 repaint시켜서 전체를 다시 그린다.
			// 이때 메모리에서 없는것은 안그리기때문에 그림도 다시 사라진다.

			mContext.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
