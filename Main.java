import java.util.*;


public class Main {

	public static int time_limit;
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Input the number of family members:");

		int N = in.nextInt();

		ArrayList<FamilyMember> family = new ArrayList<FamilyMember>();

		for (int i = 0; i < N; i++) {
			System.out.println("Give the amount of minutes required to cross the river for the family member with id:"+ (i + 1));
			int mins = in.nextInt();
			FamilyMember m = new FamilyMember(i + 1, mins);
			family.add(m);
		}
		System.out.println("Give the total time limit in minutes: ");
		time_limit = in.nextInt();

		State initial_state = new State(family);

		SpaceSearcher space_searcher = new SpaceSearcher();
		State term = null;
		long start = System.currentTimeMillis();

		term = space_searcher.A_StarClosedSet(initial_state);
		long finish = System.currentTimeMillis();

		if (term == null) {
			System.out.println("\nSorry, a solution could not be found.");
		}

		else {

			State temp = term;

			ArrayList<State> path = new ArrayList<State>();
			path.add(term);

			while (temp.getFather() != null) {
				path.add(temp.getFather());
				temp = temp.getFather();
			}
		
			Collections.reverse(path);
			
		
			System.out.println("Finished in " + (path.size() - 1) + " steps.");
			int counter = 0;
			String side = "";

			for (State item : path) {

				if (item.getFather() == null) {
					System.out.println("\nInitial State");
					side = "right";
				} else {
					System.out.print("Step " + counter + ":");

					if (counter % 2 == 1) {
						side = "left";
						System.out.println("Moved from Right to Left\n");

						for (FamilyMember j : item.getLeft()) {

							if (!(item.getFather().getLeft().contains(j))) {
								System.out.println("Person " + j.getId() + " with time: " + j.getTime() + " minutes");
							}
						}
					}

					else {
						side = "right";
						System.out.println(" Moved from Left to Right\n");
						for (FamilyMember j : item.getRight()) {

							if (!(item.getFather().getRight().contains(j))) {
								System.out.println("Person " + j.getId() + " with time: " + j.getTime() + " minutes");
							}
						}
					}
				}
				counter++;
				System.out.println("\n");
				item.show(side);
				System.out.println("Time : " + item.getCost() + " mins");
				System.out.println("\n");
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("\n");
			}
			System.out.println("Minimum time for all members to cross the river: "+ term.getCost() + " minutes");
			System.out.println("River crossed by all members within the time limit (" + (time_limit) + ")");
		}
		System.out.println("Problem solved in : " + (double) (finish - start) / 1000 + " sec\n");
		in.close();
	}

}
