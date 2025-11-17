public class FamilyMember {
	
	protected int id;
	protected int time;

	public FamilyMember(int id, int time) {
		this.id = id;
		this.time = time;
	}

	public FamilyMember() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}