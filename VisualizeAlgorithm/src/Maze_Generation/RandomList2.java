package Maze_Generation;

import java.util.ArrayList;
import java.util.LinkedList;

public class RandomList2<E> {
    private LinkedList<E> q;
    public RandomList2(){
        q = new LinkedList<>();
    }
    public void add(E e){
        if (Math.random() < 0.5)
            q.addLast(e);
        else
            q.addFirst(e);
    }
    public E remote(){
        if (q.size() == 0)
            throw new IllegalArgumentException("there's no element to remote");
        if (Math.random() < 0.5)
            return q.removeFirst();
        else
            return q.removeLast();

    }
    public int size(){
        return q.size();
    }
}
