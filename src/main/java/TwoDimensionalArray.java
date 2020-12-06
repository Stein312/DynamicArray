package main.java;

public class TwoDimensionalArray<V> implements Matrix<V> {
    transient V[][] arr;
    int size1;
    int size2;
    public TwoDimensionalArray(int size1,int size2) {
        this.size1=size1;
        this.size2=size2;
        arr= (V[][]) new Object[size1][];


    }


    @Override
    public V get(int i, int j) {
        return (V)arr[i][j];
    }

    @Override
    public void set(int i, int j, Integer value) {
        if(arr[i]==null){
            arr[i]= (V[]) new Object[size2];
        }
        arr[i][j]=(V)value;
    }
}
