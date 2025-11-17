import java.util.*;

public class SpaceSearcher {

	private ArrayList<State> states;
	private HashSet<State> closedSet;

	public SpaceSearcher() {
		this.states = null;
		this.closedSet = null;
	}

	public State A_StarClosedSet(State initial_state) {
		
		this.states = new ArrayList<State>();
		this.closedSet = new HashSet<State>();
		this.states.add(initial_state);

		while (this.states.size() > 0) {

			State current_state = this.states.remove(0);

			if (current_state.isFinal()) {
				return current_state;
			}

			if (!closedSet.contains(current_state)) {

				this.closedSet.add(current_state);
				this.states.addAll(current_state.getChildren());
				Collections.sort(this.states);
			}
		}

		return null;
	}

}