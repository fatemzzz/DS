package org.example;
import java.util.*;

public class MedianHandler {
    private List<Long> maxHeap;
    private List<Long> minHeap;
    private long maxLength;
    private long minLength;
    private long minSum;
    private long maxSum;
    private long med;
    private List<Long> maxIndex;
    private List<Long> minIndex;
    private List<Long> revMaxIndex;
    private List<Long> revMinIndex;
    private long n;

    public MedianHandler(long n) {
        this.n = n;
        maxLength = 0;
        minLength = 0;
        maxSum = 0;
        minSum = 0;
        med = 0;
        maxHeap = new ArrayList<>();
        minHeap = new ArrayList<>();
        maxIndex = new ArrayList<>();
        minIndex = new ArrayList<>();
        revMaxIndex = new ArrayList<>();
        revMinIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maxIndex.add(-1L);
            minIndex.add(-1L);
            revMaxIndex.add(-1L);
            revMinIndex.add(-1L);
        }
    }

    public void addNum(long x, long arrIndex) {
        if (maxLength > minLength) {
            if (x < med) {
                long indexTobeRemoved = revMaxIndex.get(0);
                addToMin(maxHeap.get(0), indexTobeRemoved);
                removeFromMax(0, indexTobeRemoved);
                addToMax(x, arrIndex);
            } else
                addToMin(x, arrIndex);
        } else if (maxLength < minLength) {
            if (x > med) {
                long indexTobeRemoved = revMinIndex.get(0);
                addToMax(minHeap.get(0), indexTobeRemoved);
                removeFromMin(0, indexTobeRemoved);
                addToMin(x, arrIndex);
            } else
                addToMax(x, arrIndex);
        } else {
            if (x <= med)
                addToMax(x, arrIndex);
            else
                addToMin(x, arrIndex);
        }
        if (maxLength > minLength)
            med = maxHeap.get(0);
        else
            med = minHeap.get(0);
    }

    public long getMedian() {
        return med;
    }

    public void removeNum(long x, long arrIndex) {
        if (maxIndex.get((int) arrIndex) != -1)
            removeFromMax(maxIndex.get((int) arrIndex), arrIndex);
        else if (minIndex.get((int) arrIndex) != -1)
            removeFromMin(minIndex.get((int) arrIndex), arrIndex);

        if (maxLength > minLength)
            med = maxHeap.get(0);
        else
            med = minHeap.get(0);
    }
    public void addToMax(long x, long arrIndex) {
        maxSum += x;
        maxHeap.add(x);
        maxIndex.set((int) arrIndex, maxLength);
        revMaxIndex.set((int) maxLength, arrIndex);
        long i = maxLength;
        while (i > 0 && maxHeap.get((int) ((i - 1) / 2)) < maxHeap.get((int) i)) {
            long temp = maxHeap.get((int) i);
            maxHeap.set((int) i, maxHeap.get((int) ((i - 1) / 2)));
            maxHeap.set((int) ((i - 1) / 2), temp);
            maxIndex.set((int) (long) revMaxIndex.get((int) i), (i - 1) / 2);
            maxIndex.set((int) (long) revMaxIndex.get((int) ((i - 1) / 2)), i);
            temp = revMaxIndex.get((int) i);
            revMaxIndex.set((int) i, revMaxIndex.get((int) ((i - 1) / 2)));
            revMaxIndex.set((int) ((i - 1) / 2), temp);
            i = (i - 1) / 2;
        }
        maxLength++;
    }

    public long calcDiff() {
        long ans = ((minSum - (med * minLength)) + ((med * maxLength) - maxSum));
        return ans;
    }

    public void addToMin(long x, long arrIndex) {
        minSum += x;
        minHeap.add(x);
        minIndex.set((int) arrIndex, minLength);
        revMinIndex.set((int) minLength, arrIndex);
        long i = minLength;
        while (i > 0 && minHeap.get((int) ((i - 1) / 2)) > minHeap.get((int) i)) {
            long temp = minHeap.get((int) i);
            minHeap.set((int) i, minHeap.get((int) ((i - 1) / 2)));
            minHeap.set((int) ((i - 1) / 2), temp);
            minIndex.set((int) (long) revMinIndex.get((int) i), (i - 1) / 2);
            minIndex.set((int) (long) revMinIndex.get((int) ((i - 1) / 2)), i);
            temp = revMinIndex.get((int) i);
            revMinIndex.set((int) i, revMinIndex.get((int) ((i - 1) / 2)));
            revMinIndex.set((int) ((i - 1) / 2), temp);
            i = (i - 1) / 2;
        }
        minLength++;
    }
    public void removeFromMax(long index, long arrIndex) {
        maxSum -= maxHeap.get((int) index);
        maxIndex.set((int) arrIndex, -1L);
        revMaxIndex.set((int) index, -1L);
        while (true) {
            if (2 * index + 1 > maxLength - 1)
                break;
            if (2 * index + 2 <= maxLength - 1 && maxHeap.get((int) (2 * index + 1)) >= maxHeap.get((int) (2 * index + 2))) {
                maxHeap.set((int) index, maxHeap.get((int) (2 * index + 1)));
                maxIndex.set(revMaxIndex.get((int) (2 * index + 1)).intValue(), index);
                revMaxIndex.set((int) index, revMaxIndex.get((int) (2 * index + 1)));
                index = 2 * index + 1;
            } else {
                if (2 * index + 2 > maxLength - 1) {
                    maxHeap.set((int) index, maxHeap.get((int) (2 * index + 1)));
                    maxIndex.set(revMaxIndex.get((int) (2 * index + 1)).intValue(), index);
                    revMaxIndex.set((int) index, revMaxIndex.get((int) (2 * index + 1)));
                    index = 2 * index + 1;
                } else {
                    maxHeap.set((int) index, maxHeap.get((int) (2 * index + 2)));
                    maxIndex.set(revMaxIndex.get((int) (2 * index + 2)).intValue(), index);
                    revMaxIndex.set((int) index, revMaxIndex.get((int) (2 * index + 2)));
                    index = 2 * index + 2;
                }
            }
        }
        maxHeap.set((int) index, maxHeap.get((int) (maxLength - 1)));
        if (maxLength - 1 != index)
            maxIndex.set(revMaxIndex.get((int) (maxLength - 1)).intValue(), index);
        revMaxIndex.set((int) index, revMaxIndex.get((int) (maxLength - 1)));
        revMaxIndex.set((int) (maxLength - 1), -1L);
        maxHeap.remove((int) maxLength - 1);
        maxLength--;
        if (index != maxLength - 1) {
            long i = index;
            while (i > 0 && maxHeap.get((int) ((i - 1) / 2)) < maxHeap.get((int) i)) {
                long temp = maxHeap.get((int) i);
                maxHeap.set((int) i, maxHeap.get((int) ((i - 1) / 2)));
                maxHeap.set((int) ((i - 1) / 2), temp);
                maxIndex.set(revMaxIndex.get((int) i).intValue(), (i - 1) / 2);
                maxIndex.set(revMaxIndex.get((int) ((i - 1) / 2)).intValue(), i);
                temp = revMaxIndex.get((int) i);
                revMaxIndex.set((int) i, revMaxIndex.get((int) ((i - 1) / 2)));
                revMaxIndex.set((int) ((i - 1) / 2), temp);
                i = (i - 1) / 2;
            }
        }
    }
    public void removeFromMin(long index, long arrIndex) {
        minSum -= minHeap.get((int) index);
        minIndex.set((int) arrIndex, -1L);
        revMinIndex.set((int) index, -1L);
        while (true) {
            if (2 * index + 1 > minLength - 1)
                break;
            if (2 * index + 2 <= minLength - 1 && minHeap.get((int) (2 * index + 1)) <= minHeap.get((int) (2 * index + 2))) {
                minHeap.set((int) index, minHeap.get((int) (2 * index + 1)));
                minIndex.set(revMinIndex.get((int) (2 * index + 1)).intValue(), index);
                revMinIndex.set((int) index, revMinIndex.get((int) (2 * index + 1)));
                index = 2 * index + 1;
            } else {
                if (2 * index + 2 > minLength - 1) {
                    minHeap.set((int) index, minHeap.get((int) (2 * index + 1)));
                    minIndex.set(revMinIndex.get((int) (2 * index + 1)).intValue(), index);
                    revMinIndex.set((int) index, revMinIndex.get((int) (2 * index + 1)));
                    index = 2 * index + 1;
                } else {
                    minHeap.set((int) index, minHeap.get((int) (2 * index + 2)));
                    minIndex.set(revMinIndex.get((int) (2 * index + 2)).intValue(), index);
                    revMinIndex.set((int) index, revMinIndex.get((int) (2 * index + 2)));
                    index = 2 * index + 2;
                }
            }
        }
        minHeap.set((int) index, minHeap.get((int) minLength - 1));
        if (minLength - 1 != index)
            minIndex.set(revMinIndex.get((int) minLength - 1).intValue(), index);
        revMinIndex.set((int) index, (long) revMinIndex.get((int) minLength - 1).intValue());
        revMinIndex.set((int) minLength - 1, -1L);
        minHeap.remove((int) minLength - 1);
        minLength--;
        if (index != minLength - 1) {
            long i = index;
            while (i > 0 && minHeap.get((int) (i - 1) / 2) > minHeap.get((int) i)) {
                long temp = minHeap.get((int) i);
                minHeap.set((int) i, minHeap.get((int) (i - 1) / 2));
                minHeap.set((int) (i - 1) / 2, temp);
                minIndex.set(revMinIndex.get((int) i).intValue(), (i - 1) / 2);
                minIndex.set(revMinIndex.get((int) (i - 1) / 2).intValue(), i);
                temp = revMinIndex.get((int) i);
                revMinIndex.set((int) i, revMinIndex.get((int) (i - 1) / 2));
                revMinIndex.set((int) (i - 1) / 2, temp);
                i = (i - 1) / 2;
            }
        }
    }
}
