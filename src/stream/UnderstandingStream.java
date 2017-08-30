package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class UnderstandingStream {

	public static void main(String [] args){
		
		//functionalInterfaces();
		streamImpl();
		
	}
	
	//suppose we want to iterate over a list of integers and 
	//find out sum of all the integers greater than 10
	
	static int sumIterator(List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		int sum = 0;
		while (it.hasNext()) {
			int num = it.next();
			if (num > 10) {
				sum += num;
			}
		}
		return sum;
	}
	
	//external iteration vs internal iteration & lazy-seeking
	//sequential and parllel, flitering
	//functional interfaces & lambda expressions
	
	static int sumStream(List<Integer> list) {
		return list.stream().filter(i -> i > 10).mapToInt(i -> i).sum();
	}
	
	//doesnt store data but produces piplined data & consumable only
	//IntStream,LongStream and DoubleStream
	
	//Function and BiFunction --> Function<T, R> --> map/maptoInt/reduce/toArray
	//Predicate and BiPredicate --> filter/anyMatch/allMatch/noneMatch
	//Consumer and BiConsumer --> peek/forEach/forEachOrdered
	//Supplier --> generate/collect
	//java.util.Optinal --> terminal stream operations
	//java.util.Spliterator
	
	static void functionalInterfaces() {
		
		ToIntFunction<String> i1  = (x)-> Integer.parseInt(x);
	    System.out.println(i1.applyAsInt("2"));
		ToIntBiFunction<String,String> i2  = (x,y)-> Integer.parseInt(x) +Integer.parseInt(x);
	    System.out.println(i2.applyAsInt("2","3"));
	    
	    BiPredicate<Integer, Integer> bi = (x, y) -> x > y;
	    System.out.println(bi.test(2, 3));
	    
	    Map<Integer,String> map = new HashMap<>();
	    map.put(1, "A");
	    map.put(2, "B");
	    map.put(3, "C");
	    BiConsumer<Integer,String> biConsumer = (key,value) -> 
	       		System.out.println("Key:"+ key+" Value:"+ value);
		map.forEach(biConsumer);
	       
	    Supplier<String> i  = ()-> "Java8";
	    System.out.println(i.get());
	       
	   	Optional emptyOptional = Optional.empty();
	   	//System.out.println(emptyOptional.get() );
	   	Optional nonEmptyOptional = Optional.of( "Java8" );
	   	System.out.println(nonEmptyOptional.get() );      //ifPresent & isPresent
	   	Optional nullableOptional = Optional.ofNullable( null );
	   	//System.out.println(nullableOptional.get() );
	 
	}
	
	static void streamImpl(){
		
		Stream<Integer> stream = Stream.of(new Integer[]{1,2,3,4}); 
		//Stream<Integer> stream1 = Stream.of(new int[]{1,2,3,4}); 
		
		List<Integer> myList = new ArrayList<>();
		for(int i=0; i<100; i++) myList.add(i);
		Stream<Integer> sequentialStream = myList.stream();   //sequential stream
		//sequentialStream.forEach(p -> System.out.print(p + "="));
		Stream<Integer> parallelStream = myList.parallelStream();   //parallel stream
		//parallelStream.forEach(p -> System.out.print(p + "="));
		Stream<String> stream1 = Stream.generate(() -> {return "abc";});
		//stream1.forEach(p -> System.out.print(p + "="));
		Stream<String> stream2 = Stream.iterate("abc", (i) -> i);
		//stream2.forEach(p -> System.out.print(p + "="));
		LongStream is = Arrays.stream(new long[]{1,2,3,4});
		is.forEach(p -> System.out.print(p + "="));
		IntStream is2 = "abc".chars();
		is2.forEach(p -> System.out.print(p + "="));
	}
	
}
