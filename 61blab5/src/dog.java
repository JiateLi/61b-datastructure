
public class dog {
	int heigh;
	int age;
	String name;
	
	void bark(){
		System.out.print("come on!");
	}
	public static void main(String[] args){
		dog d1 = new dog();
		((smalldog)d1).bark();
		}
}

class smalldog extends dog{
	int kuan;
	
	void bark(){
		System.out.print("wawawawa!");
	}
}

