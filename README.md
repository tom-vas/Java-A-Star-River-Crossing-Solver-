# 🌉 River Crossing Puzzle Solver with A* Search

This project solves the classic **River Crossing Puzzle**, where a family must cross a river using a boat under specific constraints. The solution is developed in **Java** using the **A\*** search algorithm to find the optimal path.

## 🎯 Goal

To find the **optimal (minimum total time) sequence of moves** for all family members to cross from the right (initial) side to the left (final) side, ensuring the total time is within a given limit.

## ⚙️ Program Architecture

The program consists of the following key classes:

* **`FamilyMember`**: Represents a family member, storing their **ID** and the **time** required for them to cross the river.
* **`State`**: Represents a state in the search space. It tracks which members are on the left/right bank and the location of the **lamp**. It implements the **heuristic function** and the `Comparable` interface for state sorting.
* **`SpaceSearcher`**: Implements the **A\*** algorithm using a sorted list (Priority Queue implementation) and a closed set (`closedSet`) to manage the search space.
* **`Main`**: The main execution class. It handles user input (number of members, crossing times, time limit) and prints the step-by-step optimal solution.

## 💡 Heuristic Function (h)

The heuristic function is designed to be **consistent** and **admissible**, ensuring the A\* algorithm finds the optimal solution.

| Lamp Side | Heuristic Logic |
| :--- | :--- |
| **Right** | $h = \text{MAX}(\text{Time of members on the Right})$ |
| **Left** | $h = \text{MAX}(\text{Time of members on the Right}) + \text{MIN}(\text{Time of members on the Left})$ |

The evaluation score $f(n)$ is calculated as: $f(n) = g(n) + h(n)$, where $g(n)$ is the actual cost (time) to reach the current state.

## 🚀 Execution Example

The program prompts the user for input upon execution.

### Example Input: 5 Members, Limit 30 minutes

| Member (ID) | Time (min) |
| :--------: | :----------: |
| m1         | 1            |
| m2         | 3            |
| m3         | 6            |
| m4         | 8            |
| m5         | 12           |

**Time Limit:** 30 minutes

**Resulting Optimal Path (29 minutes):**

| Step | From | To | People Moved | Move Time | Total Time |
| :--: | :--: | :---: | :---: | :------------: | :--------------: |
| 1    | Right| Left | m1 (1), m2 (3) | 3 mins | 3 mins |
| 2    | Left | Right | m1 (1) | 1 min | 4 mins |
| 3    | Right| Left | m1 (1), m3 (6) | 6 mins | 10 mins |
| 4    | Left | Right | m1 (1) | 1 min | 11 mins |
| 5    | Right| Left | m4 (8), m5 (12) | 12 mins | 23 mins |
| 6    | Left | Right | m2 (3) | 3 mins | 26 mins |
| 7    | Right| Left | m1 (1), m2 (3) | 3 mins | **29 mins** |
