package bubble.test.ex11;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//메인스레드는 바쁘다 ->키보드 이벤트를 처리하기 바쁘다.
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

	//이미지를 불러오기위해 버퍼를 달았따.
	private BufferedImage image;
	//콤포지션 (결합)
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		
		while(true) {
			//생상확인
			Color leftColor = new Color(image.getRGB(player.getX() - 10,player.getY()+25 ));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 +15,player.getY()+25 ));
			int bottomColor = image.getRGB(player.getX() +10 ,player.getY()+50 +5) 
					+ image.getRGB(player.getX() +50 -10,player.getY()+50 +5) ;
			//캐릭토 오른쪽과 왼쪽끝 하단에 좌표를 확인
			
			
			//바닥 충돌확인
			if(bottomColor != -2 ) {
//				System.out.println("바텀 컬러 : "+bottomColor);
//				System.out.println("바닥충돌함");
				player.setDown(false);
			}else {//오른쪽왼쪽 이동중에는 down상태가 유지되지 않는다
				//-2 일때는 내 바닥생깔이 하얀색이라는 것
				if(!player.isUp() && !player.isDown()) {
					
					player.down();
				}
				
				
			}
			
			//외벽 충돌확인
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
//				System.out.println("왼쪽 벽에 충돌함");
				player.setLeftWallCrash(true);//충돌했으니 멈추도록상태변경
				
				player.setLeft(false);
			}else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
//				System.out.println("오른쪽 벽에 충돌함");
				player.setRightWallCrash(true);
				player.setRight(false);
			}else {
				//위상태를 벗어나면 다시 움직일 수 있도록 해준다
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
