package bubble.test.ex12;

public interface Moveable {

	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {};
}
