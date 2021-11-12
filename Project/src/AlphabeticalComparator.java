import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class AlphabeticalComparator implements Comparator<Listing>{
    /**
     * Compares its two arguments for order.
     *
     * Returns a negative integer, zero, or a positive integer
     * as l1 is less than, equal to, or greater than l2 in terms
     * of the alphabetical order of their titles.
     *
     * Example: abc > Abc > ABC > Àbc > àbc > Äbc > äbc
     *
     * @param l1 the first Listing to compare
     * @param l2 the second Listing to compare
     * @return a negative integer, zero, or a positive integer
     *      as l1 is less than, equal to, or greater than l2
     */
    @Override
    public int compare (Listing l1, Listing l2) {
        String s1 = l1.getTitle();
        String s2 = l2.getTitle();

        // Get the Collator for Canadian English and set its strength to TERTIARY
        Collator caCollator = Collator.getInstance(Locale.CANADA);
        caCollator.setStrength(Collator.TERTIARY);
        return caCollator.compare(s1, s2) * -1;
    }
}
