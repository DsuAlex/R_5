public class Recitation5 {
    /*
     * You may add any private helper method you see necessary,
     * but you may NOT change the signatures of the methods below.
     */
    public static boolean lastItemReachable(int[] hopList){
        boolean isReached = false;
        if (hopList == null || hopList.length == 0) {isReached = false;
        }
               for (int i = 0; i < hopList.length;){
                   int currentIndex = hopList[i];

                   if(i + currentIndex >= hopList.length-1){
                       isReached = true;
                       break;
                       }
                   else if (currentIndex == 0) {
                       break;
                   }
                   i += currentIndex;
               }
        return isReached;
    }

    public static int[] slidingWindowMin(int[] numberList, int windowSize){
        if (numberList == null || numberList.length == 0 || windowSize <= 0) {
            return new int[0];
        }

        int n = numberList.length;
        int[] result = new int[n - windowSize + 1];
        int[] windowIndices = new int[windowSize];
        int start = 0, end = 0;

        for (int i = 0; i < n; i++) {
            start = removeOutOfWindow(windowIndices, start, end, i, windowSize);
            end = removeLargerElements(numberList, windowIndices, start, end, i);

            windowIndices[end++] = i;

            if (i >= windowSize - 1) {
                result[i - windowSize + 1] = numberList[windowIndices[start]];
            }
        }

        return result;
    }
    private static int removeOutOfWindow(int[] windowIndices, int start, int end, int currentIndex, int windowSize) {
        if (start < end && windowIndices[start] <= currentIndex - windowSize) {
            start++;
        }
        return start;
    }

    private static int removeLargerElements(int[] numberList, int[] windowIndices, int start, int end, int currentIndex) {
        while (start < end && numberList[windowIndices[end - 1]] > numberList[currentIndex]) {
            end--;
        }
        return end;
    }
}
