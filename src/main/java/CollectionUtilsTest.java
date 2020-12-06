package main.java;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

class CollectionUtilsTest {

    @Test
    void filter() {
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(4);
        arr.add(6);
        arr.add(2);
        arr.add(8);
        boolean a=CollectionUtils.filter(i->i%2==0,arr);
        Assert.assertEquals(false,a);
    }

    @Test
    void transformInNewCollection() {
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(4);
        arr.add(6);
        arr.add(2);
        arr.add(8);
        ArrayList<Float> sarr=new ArrayList<>();
        sarr.addAll(CollectionUtils.transformInNewCollection(arr,i->Float.parseFloat(String.valueOf(i))));
        System.out.println(Arrays.toString(sarr.toArray()));
    }

    @Test
    void transform() {
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(4);
        arr.add(6);
        arr.add(2);
        arr.add(8);
        ArrayList<String> sarr=new ArrayList<>();
        sarr.addAll(CollectionUtils.transform(arr,sarr,i-> String.valueOf(i)));
        System.out.println(Arrays.toString(sarr.toArray()));

    }

    @Test
    void forAllDo() {
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(4);
        arr.add(6);
        arr.add(2);
        arr.add(8);
        ArrayList<Integer> sarr=new ArrayList<>();
        sarr.addAll(CollectionUtils.forAllDo(arr,i->i+2));
        System.out.println(Arrays.toString(sarr.toArray()));


    }

    @Test
    void unmodifiableCollection() {

        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(4);
        arr.add(6);
        arr.add(2);
        arr.add(8);
        Collection<Integer> collection= CollectionUtils.unmodifiableCollection(arr);
        System.out.println(collection.size());
    }
}