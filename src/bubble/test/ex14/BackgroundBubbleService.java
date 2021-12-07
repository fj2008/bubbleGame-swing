package bubble.test.ex14;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class BackgroundBubbleService {

	//이미지를 불러오기위해 버퍼를 달았따.
	private BufferedImage image;
	//콤포지션 (결합)
	private Bubble bubble;
	
	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean leftWall() {
		Color leftColor = new Color(image.getRGB(bubble.getX() - 10,bubble.getY()+25 ));
		//외벽 충돌확인
		//왼쪽 벽충돌
		if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bubble.getX() + 50 +15,bubble.getY()+25 ));
		//오른쪽 벽 충돌
		if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean topWall() {
		Color topColor = new Color(image.getRGB(bubble.getX() +25, bubble.getY() - 10));
		if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return true;
		}
		
		return false;
	}
	
}
