import java.util.*;

abstract class SelfBounded<T extends SelfBounded<T>>{
	
	public abstract T abs(T t);

	public T returnAbs(T t){
		System.out.println("inside abstact abs");
		return abs(t);
	}

}

class Concrete extends SelfBounded<Concrete>{

	public Concrete abs(Concrete d){
		System.out.println("inside abs of d");
		return d;
	}

}

public class SelfBound{

	public static void main(String[] args) {
		Concrete c = new Concrete();
		c.returnAbs(c).abs(c);
	}

}