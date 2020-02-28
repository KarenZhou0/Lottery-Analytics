import java.util.HashSet;

public class ConsecutiveArray {
    public static int consecutive(int[] a) {
        // Put all the values into a HashSet
        HashSet<Integer> values = new HashSet();
        for (int i : a) {
            values.add(i);
        }
        
        int maxLength = 0;
        for (int i : values) {
            if (values.contains(i - 1)) continue;
            int length = 0;
            while (values.contains(i++)) length++;
            maxLength = Math.max(maxLength, length);
        }
        
        return maxLength;
    }
    
    // Sample test cases
    public static void main(String[] args) {
        assert consecutive(new int[]{1,2,3,4,5}) == 5 :
            "Single sequence, sorted order";
        assert consecutive(new int[]{5,4,3,2,1}) == 5 :
            "Single sequence, unsorted order";
        assert consecutive(new int[]{1,2,4,5,6}) == 3 :
            "Multiple sequences, sorted order";
        assert consecutive(new int[]{2,4,1,6,5}) == 3 :
            "Multiple sequences, unsorted order";
        System.out.println("Passed all test cases");
    }
}