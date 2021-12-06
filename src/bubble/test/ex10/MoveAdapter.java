package bubble.test.ex10;

// 어댑터 -> 걸러내는 역할
//추상클래스이기때문에 추상 메서드를 가질 수 있다.
public abstract class MoveAdapter implements Moveable {

	@Override
	public void down() {}

}
