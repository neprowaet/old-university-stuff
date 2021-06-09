package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Main{

    public static void main(String[] args) {
        List<Integer> whole = new ArrayList<Integer>();
        for (int i = 1; i <= 30; i++) whole.add(i);
        List<Integer> part = whole.subList(2,28);

        System.out.println(subsequence(whole, part));
    }


    public static <E> int subsequence(List<E> whole, List<E> part)
    {
        if(whole.size() < part.size()) return -1;

        long hw=0, hp=0;

        long h = 1;
        for (int k = 1; k <= part.size()-1; k++) {
            h <<= 8;
            h %= q;
        }

        Iterator<E> wit = whole.iterator(), pit = part.iterator();
        Iterator<E> wit2 = whole.iterator();

        for (int i=0;i<=whole.size()-part.size();i++)
        {
            if(i==0) {
                for (int n = 0; n < part.size(); n++) {
                    hp = ((hp << 8) + (pit.next().hashCode() & 255)) % q;
                    hw = ((hw << 8) + (wit.next().hashCode() & 255)) % q;
                }
            }
            else hw = newhash(hw, wit2.next(), wit.next(), h);

            if(hw == hp) if(listsEqual(whole.subList(i, i + part.size()), part)) return i;
        }

        return -1;
    }

    public static final int q = 31;

    public static <E> long newhash (long h, E a, E b, long nh){
        long r = 0;
        r = (h - nh * (a.hashCode() & 255)) % q;
        if (r < 0) r += q;
        r = ((r << 8) + (b.hashCode() & 255)) % q;

        return r;
    }

    public static <E> boolean listsEqual (List<E> a, List<E> b){
        if(a.size() < b.size()) return false;
        boolean r = true;

        for(int i = 0; i<a.size();i++)
            if(!a.get(i).equals(b.get(i))){
                r = false; break;
            }

        return r;
    }



}
