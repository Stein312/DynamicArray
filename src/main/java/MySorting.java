package main.java;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MySorting<E> {
    public static <E> List<E> bubbleSort(List<E> list){
        int i=0;
        boolean again=false;
        E[] arr= (E[]) list.toArray();
        do{
            if(i>=arr.length-1){
                i=0;
                if(!again){
                    break;
                }
                again=false;
            }
            Comparable<? super E> k=(Comparable<? super E>)arr[i];
            int cmp=k.compareTo(arr[i+1]);
            if(cmp>0){
                E last=arr[i+1];
                arr[i+1]=arr[i];
                arr[i]=last;
                again=true;
            }
            i++;
        }while(true);
        return Arrays.asList(arr);
    }
    public static <E> List<E> insertionSort(List<E> list){
        E[] arr= (E[]) list.toArray();
        for (int left = 0; left < arr.length; left++) {
            E value = arr[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                Comparable<? super E> k=(Comparable<? super E>)value;
                int cmp=k.compareTo(arr[i]);
                if (cmp < 0) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = value;
        }
        return Arrays.asList(arr);
    }
    public static <E> List<E> quickSort(List<E> list){
        E[] arr= (E[]) list.toArray();
        quickSort(arr,0,arr.length-1);
        return Arrays.asList(arr);
    }
    private static <E> void quickSort(E[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        E pivot = source[(leftMarker + rightMarker) / 2];
        do {
            Comparable<? super E> k=(Comparable<? super E>)pivot;
            int cmp=k.compareTo(source[leftMarker]);
            while (cmp>0) {
                leftMarker++;
                cmp=k.compareTo(source[leftMarker]);
            }
            cmp=k.compareTo(source[rightMarker]);
            while (cmp<0) {
                rightMarker--;
                cmp=k.compareTo(source[rightMarker]);
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    E tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }
    public static <E> List<E> mergeSort(List<E> list){
        Object[] arr=new Object[list.size()];

        for(int i=0;i<list.size();i++) arr[i] = new Object[]{list.get(i)};
        return (List<E>)Arrays.asList(mergeSort(arr));
    }
    private static <E> Object[] mergeSort(Object[] arr){

        if(arr.length>1){
            int size;
            int j=2;
            if(arr.length%2!=0){
                if(arr.length==3) size= arr.length/2;
                else {
                size=arr.length/2+1;
                }
            }
            else size= arr.length/2;
            Object[] arrNew=new Object[size];
            for(int i=0;i<= arr.length-j;i+=2){
                arrNew[i/2]=merge((Object[]) arr[i],(Object[])arr[i+1]);
            }
            if(arr.length==3) arrNew[0]=merge((Object[])arrNew[0],(Object[])arr[2]);
            if(arrNew[arrNew.length-1]==null){
                    arrNew[arrNew.length-1]=arr[arr.length-1];
            }
            return mergeSort(arrNew);
        }
        return (Object[]) arr[0];
    }
    /*private static <E>  Object[] merge(Object[] arr1,Object[] arr2){
        Object[] arrNew=Arrays.copyOf(arr1,arr1.length+arr2.length);
        System.arraycopy(arr2,0,arrNew,arr1.length,arr2.length);
        quickSort(arrNew,0,arrNew.length-1);
        return arrNew;
    }*/
    private static <E>  Object[] merge(E[] arr1,E[] arr2){
        Object[] arrNew=new Object[arr1.length+arr2.length];
        int index1=0;
        int index2=0;
        for(int i=0;i<arrNew.length-1;i++){
            Comparable<? super E> k=(Comparable<? super E>) arr1[index1];
            int cmp=k.compareTo(arr2[index2]);
            if(cmp<0){
                arrNew[i]=arr1[index1];
                index1++;
                if(index1>=arr1.length){
                    i++;
                    for(;i<arrNew.length;i++){
                        arrNew[i]=arr2[index2];
                        index2++;
                    }
                    break;
                }
            }
            else if(cmp>0){
                arrNew[i]=arr2[index2];
                index2++;
                if(index2>=arr2.length){
                    i++;
                    for(;i< arrNew.length;i++){
                        arrNew[i]=arr1[index1];
                        index1++;
                    }
                    break;
                }
            }
            else{
                arrNew[i]=arr1[index1];
                index1++;
                if(index1>=arr1.length){
                    i++;
                    for(;i<arrNew.length;i++){
                        arrNew[i]=arr2[index2];
                        index2++;
                    }
                    break;
                }
            }
        }

        return arrNew;
    }
}
