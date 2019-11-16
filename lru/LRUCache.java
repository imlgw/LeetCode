import java.util.*;
public class LRUCache {

    class Node{
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key,int value){
            this.value=value;
            this.key=key;
        }
    }

    HashMap<Integer,Node> map=new HashMap<>();

    Node head=null;

    Node tail=null;

    int capacity=0;

    public LRUCache(int capacity) {
        this.capacity=capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node=map.get(key);
            //移动到链表头
            move2Head(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node newHead=new Node(key,value);
        if (map.containsKey(key)) {
            Node node=map.get(key);
            node.value=value;
            //移动到链表头
            move2Head(node);
            return;
        }
        if (map.size()==capacity) {
            map.remove(tail.key);
            removeNode(tail);
        }
        move2Head(newHead);
        map.put(key,newHead);
    }

    public void removeNode(Node node){
        if (node.key==tail.key) {
            tail=tail.pre;
            return;
        }
        if (node.pre==null || node.next==null) {
            return;
        }
        node.pre.next=node.next;
        node.next.pre=node.pre;
    }

    public void move2Head(Node newHead){
        if (map.size()==0) {
            head=tail=newHead;
            return;
        }
        if (newHead.key==head.key) {
            return;
        }
        removeNode(newHead);
        newHead.next=head;
        newHead.pre=null;
        head.pre=newHead;
        head=newHead;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */