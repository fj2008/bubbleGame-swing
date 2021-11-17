package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

//1. jframe을 상속하면 윈도우 창이 된다
//2. 윈도우 창은 내부에 패널을 하나 가지고 있다. (jpennel)

public class BubbleFrame extends JFrame {
	
	public BubbleFrame() {
		setSize(1000,640);
		getContentPane().setLayout(null);
		setVisible(true);
	}
	

	public static void main(String[] args) {
	
		new BubbleFrame();

	}

}
