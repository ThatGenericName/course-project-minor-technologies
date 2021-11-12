import java.util.Comparator;

public class SalaryComparator implements Comparator<Listing>{
    /**
     * Compares its two arguments for order.
     *
     * Returns a negative integer, zero, or a positive integer
     * as l1 is less than, equal to, or greater than l2 in terms
     * of their salaries.
     *
     * @param l1 the first Listing to compare
     * @param l2 the second Listing to compare
     * @return a negative integer, zero, or a positive integer
     *      as l1 is less than, equal to, or greater than l2
     */
    @Override
    public int compare (Listing l1, Listing l2) {
        int p1 = l1.getPay();
        int p2 = l2.getPay();

        return p1 - p2;
    }
}