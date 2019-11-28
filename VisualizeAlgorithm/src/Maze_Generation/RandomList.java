package Maze_Generation;

import java.util.ArrayList;

public class RandomList<E> {
    private ArrayList<E> q;
    public RandomList(){
        q = new ArrayList<>();
    }
    public void add(E e){
        q.add(e);
    }
    public E remote(){
        if (q.size() == 0)
            throw new IllegalArgumentException("there's no element to remote");
        int randIndex = (int)(Math.random() * q.size());
        E ret = q.get(randIndex);

        q.set(randIndex,q.get(q.size()-1));
        q.remove(q.size()-1);


        return ret;
    }
    public int size(){
        return q.size();
    }
}
