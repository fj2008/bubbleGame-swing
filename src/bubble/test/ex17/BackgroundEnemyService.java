package bubble.test.ex17;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

//메인스레드는 바쁘다 ->키보드 이벤트를 처리하기 바쁘다.
//백그라운드에서 계속 관찰
public class BackgroundEnemyService implements Runnable{

	//이미지를 불러오기위해 버퍼를 달았따.
	private BufferedImage image;
	//콤포지션 (결합)
	private Enemy enemy;
	
	
	// 알아야할 대상, 플레이어, 버블
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(enemy.getState() == 0) {
		
		// 2. 벽 충돌 체크
	
			//생상확인
			Color leftColor = new Color(image.getRGB(enemy.getX() - 10,enemy.getY()+25 ));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 50 +15,enemy.getY()+25 ));
			int bottomColor = image.getRGB(enemy.getX() +10 ,enemy.getY()+50 +5) 
					+ image.getRGB(enemy.getX() +50 -10,enemy.getY()+50 +5) ;
			//캐릭토 오른쪽과 왼쪽끝 하단에 좌표를 확인
			
			
			//바닥 충돌확인
			if(bottomColor != -2 ) {
//				System.out.println("바텀 컬러 : "+bottomColor);
//				System.out.println("바닥충돌함");
				enemy.setDown(false);
			}else {//오른쪽왼쪽 이동중에는 down상태가 유지되지 않는다
				//-2 일때는 내 바닥생깔이 하얀색이라는 것
				if(!enemy.isUp() && !enemy.isDown()) {
					
					enemy.down();
				}
				
			}
			
			//외벽 충돌확인
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				
				enemy.setLeft(false);
				if(!enemy.isRight()) {
					enemy.right();
				}
				
			}else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				enemy.setRight(false);
				if(!enemy.isLeft()) {
					enemy.left();
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
