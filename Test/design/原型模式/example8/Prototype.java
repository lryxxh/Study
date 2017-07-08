package 原型模式.example8;

public interface Prototype {
	public Prototype clone();

	public String getName();

	public void setName(String name);
}
