import java.util.*;

public class State implements Comparable<State> {

	private ArrayList<FamilyMember> Right;
	private ArrayList<FamilyMember> Left;
	private String lamp_side;
	private int score = 5000;
	private State father = null;
	private int cost = 0;

	public State(ArrayList<FamilyMember> initial) {
		this.setRight(initial);
		this.Left = new ArrayList<FamilyMember>();
		this.setLampSide("right");
	}

	public State(ArrayList<FamilyMember> left, ArrayList<FamilyMember> right, String lamp, int cost) {
		this.setRight(right);
		this.setLeft(left);
		this.setLampSide(lamp);
		this.setCost(cost);
	}

	public State() {

	}

	public ArrayList<FamilyMember> getRight() {
		return Right;
	}

	public int getScore() {
		return score;

	}

	public ArrayList<FamilyMember> getLeft() {
		return Left;
	}

	public String getLamp() {
		return lamp_side;
	}

	public State getFather() {
		return father;
	}

	public int getCost() {
		return this.cost;
	}

	public void setRight(ArrayList<FamilyMember> right) {
		this.Right = new ArrayList<FamilyMember>(right);
	}

	public void setLeft(ArrayList<FamilyMember> left) {
		this.Left = new ArrayList<FamilyMember>(left);
	}

	public void setLampSide(String lamp_side) {
		this.lamp_side = lamp_side;
	}

	public void setFather(State father) {
		this.father = father;
	}

	private void setCost(int cost) {
		this.cost += cost;
	}

	private void setScore(int score) {
		this.score = score;
	}

	public boolean isFinal() {
		return Right.isEmpty();
	}

	private int heuristic() {

		int h_value = 0;
		int max = 0;

		if (lamp_side == "right") {

			for (int i = 0; i < Right.size(); i++) {
				if (Right.get(i).getTime() > max)
					max = Right.get(i).getTime();
			}
			return max;
		}

		
		else {
			int min = 1000000;
			for (int i = 0; i < Left.size(); i++) {
				if (Left.get(i).getTime() < min) {
					min = Left.get(i).getTime();
				}
			}
			if (this.getRight().isEmpty()) {
				h_value = min;
				return h_value;
			}
			for (int i = 0; i < Right.size(); i++) {
				if (Right.get(i).getTime() > max) {
					max = Right.get(i).getTime();
				}
			}
			if (max < min) {
				max = min;
			}
			return max + min;
		}
	}

	private void evaluate() {
		setScore(this.getCost() + this.heuristic());
	}

	
	public ArrayList<State> getChildren() {

		ArrayList<State> children = new ArrayList<State>();
		ArrayList<FamilyMember> temp = new ArrayList<FamilyMember>();
		State child;
		int maxt = 0;
		int t1 = 0;
		int t2 = 0;

		if (lamp_side == "right") {

			if (this.Left.isEmpty() && (this.Right.size() == 1)) {
				child = new State(this.Left, this.Right, "right", this.cost);
				child.setFather(this);
				child.setCost(this.Right.get(0).time);
				temp.add(this.Right.get(0));
				child.moveLeft(temp);

				if (child.getCost() <= Main.time_limit) {
					children.add(child);
				}
			}

			else {

				for (int i = 0; i < Right.size(); i++) {
					for (int j = i + 1; j < Right.size(); j++) {
						temp.clear();
						child = new State(this.Left, this.Right, "right", this.cost);
						child.setFather(this);
						temp.add(this.Right.get(i));
						temp.add(this.Right.get(j));
						t1 = this.Right.get(i).getTime();
						t2 = this.Right.get(j).getTime();
						maxt = Math.max(t1, t2);
						child.moveLeft(temp);
						child.setCost(maxt);
						child.evaluate();

						if (child.getCost() <= Main.time_limit) {
							children.add(child);
						}
					}
				}
			}
		}

		else if (lamp_side == "left") {

			for (int i = 0; i < this.Left.size(); i++) {
				child = new State(this.Left, this.Right, this.lamp_side, this.cost);
				FamilyMember member = this.Left.get(i);
				t1 = member.getTime();
				child.moveRight(member);
				child.setCost(t1);
				child.evaluate();
				child.setFather(this);

				if (child.getCost() <= Main.time_limit) {
					children.add(child);
				}
			}
		}
		return children;
	}

	private void moveLeft(ArrayList<FamilyMember> members) {
		for (int i = 0; i < members.size(); i++) {
			if (this.Right.remove(members.get(i))) {
				this.Left.add(members.get(i));
			}
		}
		this.setLampSide("left");
	}

	private void moveRight(FamilyMember member) {
		if (this.Left.remove(member)) {
			this.Right.add(member);
		}
		this.setLampSide("right");
	}

	@Override
	public int compareTo(State s) {
		return Double.compare(this.score, s.score);
	}

	public void show(String side) {

		if (side == "left") {
			System.out.print(" (lampa) ");
		}

		for (int j = 0; j < this.getLeft().size(); j++) {
			System.out.print("m" + this.getLeft().get(j).getId() + " ");
		}

		System.out.print("                                                                     ");

		if (side == "right") {
			System.out.print(" (lampa) ");
		}

		for (int j = 0; j < this.getRight().size(); j++) {
			System.out.print("m" + this.getRight().get(j).getId() + " ");
		}

		System.out.println();
		System.out.print("				" + "__________River___________" + "								\n\n");
	}

}
