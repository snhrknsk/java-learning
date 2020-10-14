package ch02.ex09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListCombine {

	public static <T> List<T> getCombinedList(Stream<ArrayList<T>> listStream){
		List<T> resultList = listStream.reduce(new ArrayList<T>(), (list, val)->{
			list.addAll(val);
			return list;
		},(list,val)->{
			list.addAll(val);
            return list;
		});
		return resultList;
	}

}
