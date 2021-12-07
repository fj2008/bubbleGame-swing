package bubble.test.ex13;

public interface Moveable {

	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};
	default public void attack() {};
	//attack이 default인이유는 버블이 어택을 하는것이 아니기때문이다.
}
