package main.java;

public interface Matrix<V> {
    V get(int i, int j);
    void set(int i, int j, Integer value);
}
