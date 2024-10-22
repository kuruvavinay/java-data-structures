public class Practice {

        public int lengthOfLongestSubstring(String s) {
            int[] array = new int[128];
            int start = 0;
            int max = 0;

            // Initialize the array to -1 for all ASCII characters
            for (int i = 0; i < 128; i++) {
                array[i] = -1;
            }

            // Iterate through the string
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                // If the character has been seen and is within the current window
                if (array[ch] >= start) {
                    // Move the start of the window to one position after the last occurrence
                    start = array[ch] + 1;
                }

                // Update the last seen index of the character
                array[ch] = i;

                // Update the maximum length of the substring found
                max = Math.max(max, i - start + 1);
            }

            return max;
        }
}
