import java.util.*;
public class Multiply43{
    public static void main(String[] args) {
        System.out.println(multiply3("100","100"));
    }

        /**
    num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是[i+j, i+j+1]
    例: 123 * 45,  123的第1位 2 和45的第0位 4 乘积 08 存放在结果的第[1, 2]位中
      index:    0 1 2 3 4  
          
                    1 2 3
                *     4 5
                ---------
                      1 5
                    1 0
                  0 5
                ---------
                  0 6 1 5
                    1 2
                  0 8
                0 4
                ---------
                0 5 5 3 5
    这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中        
    **/ 

    public static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1=num1.length();
        int n2=num2.length();
        int[] res=new int[n1+n2];
        for (int i=n1-1;i>=0;i--) {
            for (int j=n2-1;j>=0;j--) {
                res[i+j+1]+=(num1.charAt(i)-48)*(num2.charAt(j)-48);
            }
        }
        //处理进位
        for(int i=res.length-1;i>=0;i--) {
            if(res[i]>=10){
                res[i-1]+=res[i]/10;
                res[i]%=10;
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<res.length;i++) {
            //前面最多只有一个0(除了两个数中有一个为0的时候)
            if (i==0 && res[i]==0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    //最终版
    public static String multiply3(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1=num1.length();
        int n2=num2.length();
        int[] res=new int[n1+n2];
        for (int i=n1-1;i>=0;i--) {
            for (int j=n2-1;j>=0;j--) {
                int sum=res[i+j+1]+(num1.charAt(i)-48)*(num2.charAt(j)-48);
                res[i+j+1]=sum%10;
                res[i+j]+=sum/10;
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<res.length;i++) {
            //前面最多只有一个0(除了两个数中有一个为0的时候)
            if (i==0 && res[i]==0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    //初版
    public static String multiply4(String num1, String num2) {
        // 1 2 3 
        // 4 5 6
        // 501 6
        int n1=num1.length();
        int n2=num2.length();
        //n1*n2 结果最长为 n1+n2
        int[] res=new int[n1+n2];
        for (int i=n1-1;i>=0;i--) {
            for (int j=n2-1;j>=0;j--) {
            //主要就是对这个i+j+1的理解
                res[i+j+1]+=(num1.charAt(i)-48)*(num2.charAt(j)-48);
            }
        }
        //处理进位
        for(int i=res.length-1;i>=0;i--) {
            if(res[i]>=10){
                res[i-1]+=res[i]/10;
                res[i]%=10;
            }
        }
        //去掉前面多余的0
        int index=0;
        while (index<res.length-1&&res[index]==0) { 
            index++;
        }
        StringBuilder sb=new StringBuilder();
        for (int i=index;i<res.length;i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    //update: 2020.4.16 在web上重新推了一遍
    //idx : 0 1 2
    //i :   4 5 6
    //j :   1 2 3
    //   ——————————
    //    1 3 6 8 (i+j+1)
    //    9 1 2
    //  4 5 6
    //  ——————————
    //0 1 2 3 4 5
    //0 5 6 0 8 8        
    public String multiply(String num1, String num2) {
        if(num1==null || num2==null) return num1;
        int N=num1.length()+num2.length();
        int[] res=new int[N];
        for(int i=0;i<num1.length();i++){
            for(int j=0;j<num2.length();j++){
                res[i+j+1]+=(num1.charAt(i)-48)*(num2.charAt(j)-48);
            }
        }
        //处理进位
        for(int i=res.length-1;i>0;i--){
            if(res[i]>=10){
                res[i-1]+=res[i]/10;
                res[i]%=10;
            }
        }
        //前两个为0一定是0
        //n*m位数 乘积应该是 (m+n-1 ~ m+n)位
        if(res[0]==0 && res[1]==0) return "0";
        //去除前导0
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<res.length;i++){
            if(res[i]==0 && i==0)continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public String multiply(String num1, String num2) {
        if(num1==null || num2==null) return num1;
        int n1=num1.length(),n2=num2.length();
        int[] res=new int[n1+n2];
        //如果想同时处理进位的话就必须倒推
        for(int i=n1-1;i>=0;i--){
            for(int j=n2-1;j>=0;j--){
                int sum=res[i+j+1]+(num1.charAt(i)-48)*(num2.charAt(j)-48);
                res[i+j+1]=sum%10;
                //res[i+j]会超过10,但是由于我们是倒推的,所以这个会在下一轮进行处理,否则就无法处理了
                res[i+j]+=sum/10; 
            }
        }   
        //前两个为0一定是0
        //n*m位数 乘积应该是 (m+n-1 ~ m+n)位
        if(res[0]==0 && res[1]==0) return "0";
        //去除前导0
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<res.length;i++){
            if(res[i]==0 && i==0)continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
