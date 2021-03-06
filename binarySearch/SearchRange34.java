public class SearchRange34{
    public static void main(String[] args) {
        SearchRange34 s= new SearchRange34();
        int []a={1};
        int []res=new int[2];
        res=s.searchRange(a,2);
        System.out.println(res[0]+","+res[1]);
    }


    //两次二分
    public int[] searchRange(int[] nums, int target) {
        if(nums.length<=0){
            return new int[]{-1,-1};
        }
        return new int[]{left(nums,target,0,nums.length-1),right(nums,target,0,nums.length-1)};
    }

    //5,7,7,8,8,8,8,10,10
    public int left(int []nums,int target,int lo,int hi){
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            System.out.println("lo: "+nums[lo]+"mid: "+nums[mid] +"hi: "+nums[hi]);
            if(nums[mid]<target){
                lo=mid+1;
            }else if(nums[mid]>target){
                hi=mid-1;
            }else if(mid>0){ //nums[mid]=target
                if(nums[mid-1]!=target){
                    return mid;
                }else{
                    //控制向左找
                    hi=mid-1;
                }
            }else{
                return mid; //0
            }
        }
        return -1;
    }

    public int right(int []nums,int target,int lo,int hi){
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]<target){
                lo=mid+1;
            }else if(nums[mid]>target){
                hi=mid-1;
            }else if(mid<nums.length-1){
                if(nums[mid+1]!=target){
                    return mid;
                }else{
                    //控制向右找
                    lo=mid+1;
                }
            }else{
                return mid; //nums.length
            }
        }
        return -1;
    }

    //两次二分
    public int[] searchRange(int[] nums, int target) {
        if(nums.length<=0){
            return new int[]{-1,-1};
        }
        return new int[]{left(nums,target,0,nums.length-1),right(nums,target,0,nums.length-1)};
    }

    //找大于等于target的第一个元素,小于肯定不符合
    public int left(int []nums,int target,int lo,int hi){
        while(lo<hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]<target){ //排除小于target的,剩下【lo,hi】都是大于等于的
                lo=mid+1;
            }else{
                hi=mid;
            }
        }
        return nums[hi]==target?hi:-1;
    }

    //找小于等于target的最后一个元素,大于肯定不符合
    public int right(int []nums,int target,int lo,int hi){
        while(lo<hi){
            int mid=lo+(hi-lo)/2+1;
            if(nums[mid]>target){ //排除大于target,剩下[lo,hi]都是小于等于的
                hi=mid-1;
            }else{
                lo=mid;
            }
        }
        return nums[hi]==target?hi:-1;
    }
}