package main.java;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils<T> {

 public static <T> boolean filter(Predicate<? super T> predicate, Collection<T> collection){
     Collection<T> result=new ArrayList<>();
     for(T a:collection){
         if(predicate.test(a)){
            result.add(a);
         }
     }
     boolean res=!(collection.size()==result.size());
     collection=result;
     return res;
 }
 public static  <R,T> Collection<R> transformInNewCollection(Collection<T> collection,Function<? super T,? extends R> function){
     Collection<R> result=new ArrayList<>();
     for(T t:collection){
         result.add(function.apply(t));
 }
     return result;
}
    public static <T,R> Collection<R> transform(Collection<T> collection,Collection<R> res,
                                                     Function<? super T,? extends R> function){
        res=new ArrayList<>();
        for(T t:collection){
            res.add(function.apply(t));
        }
        return res;

    }
    public static <T> Collection<T> forAllDo(Iterable<T> collection, Function<? super T,? extends T> closure){
        Collection<T> result=new ArrayList<>();
        for(T t:collection){
            result.add(closure.apply(t));
        }
        return result;
    }

    public static <T> Collection<T> unmodifiableCollection (Collection<T> collection){

        return Collections.unmodifiableCollection(collection);

    }
}
