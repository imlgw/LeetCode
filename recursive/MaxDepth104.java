public class MaxDepth104{

    public static void main(String[] args) {

    }

    //maxDepth(root)=1+max(maxDepth(root.left),maxDepth(root.right));
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int maxLeft=maxDepth(root.left);
        int maxRight=maxDepth(root.right);
        return (maxLeft>maxRight?maxLeft:maxRight)+1;
    }

    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int max=1;
        while(!queue.isEmpty()){
            int count=queue.size();
            while(count>0){
                TreeNode node=queue.poll();
                if (node.left!=null) {
                    queue.add(node.left);
                }
                if (node.right!=null) {
                    queue.add(node.right);
                }
                count--;
            }
            max++;
        }
        return max;
    }
}