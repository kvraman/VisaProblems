package street;

/**
 * 
 */

/**
 * @author venkat.kalyan
 *
 */
public class MeetingPoint {

	/**
	 * 
	 */
	public MeetingPoint() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int houses[] = { 17, 28, 34, 50, 91, 109, 121, 145, 172, 189, 211 };
		int restaurants[] = { 18, 33, 55, 67, 89, 144, 170 };

		computeMinDistanceUsingBruteForceMethod(restaurants, houses);
		computeMinDistance(restaurants, houses);
	}

	/**
	 * @param restaurants
	 * @param houses
	 */
	private static void computeMinDistance(int[] restaurants, int[] houses) {
		// First compute the distance of all houses from the first restaurant
		// This will take O(m) time
		// Further, keep track of how many houses are on the left side of the
		// restaurant and how many on the right in two variables
		int distance = 0;
		int left = 0, right = 0;
		int totalIterations = 0;

		for (int i = 0; i < houses.length; i++) {
			int vecDistance = houses[i] - restaurants[0];
			if (vecDistance < 0)
				left++;
			else
				right++;
			distance += Math.abs(vecDistance);
			totalIterations++;
		}

		int minDistance = distance;
		int closestRestaurant = 0;
		System.out.println(distance);
		System.out.println("Left: " + left + "  " + " Right: " + right);

		// Now, get the next restaurant and the distance between the two restaurants
		// That should be positive
		for (int j = 1; j < restaurants.length; j++) {
			// Now, since we have moved to the right from the first
			// restaurant, some houses would have moved to the left side
			// from the right side
			// For these houses alone, compute how much the distance changed by
			// by computing the difference between the distance to the next restaurant
			// to the previous restaurant

			// For all houses on the left of the previous restaurant
			// the distance will increase by distance form next restaurant to previous
			// restaurant
			// multiplied by the number of restaurants to the left
			// Similarly, the distance will decrease for houses originally on the right
			// Compute the additional distance due to this
			int additionalDistance = 0;
			int nMoved = 0;
			totalIterations++;
			while ((right > 0) && houses[left] <= restaurants[j]) {
				additionalDistance += (((restaurants[j] - houses[left]) - (houses[left] - restaurants[j - 1])));
				left++;
				right--;
				nMoved++;
				totalIterations++;
			}

			// So, now distance of all houses from the scond restaurant is simply
			// the total distance to the original restaurant
			// plus the additional distance
			additionalDistance += ((left - nMoved) - right) * (restaurants[j] - restaurants[j - 1]);
			distance += additionalDistance;
			if (minDistance > distance) {
				minDistance = distance;
				closestRestaurant = j;
			}
			System.out.println(distance);
			System.out.println("Left: " + left + "  " + " Right: " + right);
		}

		System.out.println("Closest Restaurant is " + closestRestaurant + " at distance " + minDistance);
		System.out.println("Total Iterations " + totalIterations);
	}

	/**
	 * @param restaurants
	 * @param houses
	 * 
	 */
	private static void computeMinDistanceUsingBruteForceMethod(int[] restaurants, int[] houses) {
		// Brute force
		int minDistance = Integer.MAX_VALUE;
		int closestRestaurant = -1;
		int totalIterations = 0;
		for (int i = 0; i < restaurants.length; i++) {
			int distance = 0;
			for (int j = 0; j < houses.length; j++) {
				distance += Math.abs(houses[j] - restaurants[i]);
				totalIterations++;
			}
			System.out.println(distance);

			if (distance < minDistance) {
				minDistance = distance;
				closestRestaurant = i;
			}
		}

		System.out.println("Closest Restaurant is " + closestRestaurant + " at distance " + minDistance);
		System.out.println("Total Iterations " + totalIterations);
	}

}
