import java.util.*;
public class Merge56{
    public static void main(String[] args) {
        Merge56 m=new Merge56();
        int[][] nums=new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] nums2=new int[][]{{1,4},{4,6},{5,10}};
        int[][] nums3=new int[][]{{1,4},{2,3}};
        printArray(m.merge(nums3));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals ==null || intervals.length<=0) {
            return new int[][]{};
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        LinkedList<int[]> list=new LinkedList<>();
        for (int i=1;i<intervals.length;i++) {
            if (intervals[i][0]<=intervals[i-1][1]) {
                if (intervals[i][1]>intervals[i-1][1]) {
                    intervals[i][0]=intervals[i-1][0];   
                }else{
                    intervals[i][0]=intervals[i-1][0];
                    intervals[i][1]=intervals[i-1][1];
                }
            }else{
                list.add(intervals[i-1]);
            }
        }
        list.add(intervals[intervals.length-1]);
/*        int[][] res=new int[list.size()][2];
        for (int i=0;i<list.size();i++) {
            res[i][0]=list.get(i)[0];
            res[i][1]=list.get(i)[1];
        }*/
        return list.toArray(new int[0][0]); //题解哪里学到一招
    }

    //偶然看到,简化下代码
    public int[][] merge(int[][] intervals) {
        if (intervals ==null || intervals.length<=0) {
            return new int[][]{};
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        LinkedList<int[]> list=new LinkedList<>();
        for (int i=1;i<intervals.length;i++) {
            if (intervals[i][0]<=intervals[i-1][1]) {
                intervals[i][0]=intervals[i-1][0];
                intervals[i][1]=Math.max(intervals[i-1][1],intervals[i][1]);
            }else{
                list.add(intervals[i-1]);
            }
        }
        list.add(intervals[intervals.length-1]);
        return list.toArray(new int[0][0]);
    }

    //update: 2020.4.16
    public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length<=0) return intervals;
        Arrays.sort(intervals,(a,b)->a[0]!=b[0]?a[0]-b[0]:a[1]-b[1]);
        List<int[]> res=new ArrayList<>();
        res.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] pre=res.get(res.size()-1);
            if(intervals[i][0]<=pre[1]){
                if(intervals[i][1]>=pre[1]){
                    pre[1]=intervals[i][1];
                }
            }else{
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[0][0]);
    }

    public static void printArray(int[][] arr){
        for (int i=0;i<arr.length;i++) {
            System.out.println(arr[i][0]+","+arr[i][1]);
        }
    }
}