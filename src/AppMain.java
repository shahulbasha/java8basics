import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppMain {

	public static void main(String[] args) {

		List<String> list=new ArrayList<>(Arrays.asList("a","b","c"));
		List<String> list1=new ArrayList<>(Arrays.asList("d","e","f","dog"));
		List<String> emptyList=new ArrayList<>();
		Consumer<String> c= System.out::println;
		Consumer<String> c1= emptyList::add;
		Consumer<Integer> c2=null;
		//c.accept("a");

		Stream<String> filter = list.stream().filter(s->s.equalsIgnoreCase("b"));
		Stream.of("d");
		Collections.sort(list);
		System.out.println("*****************");
		
		list1.stream()
			 .peek(System.out::println)
			 .filter(s->s.startsWith("d"))
			 .forEach(emptyList::add);
	
		System.out.println("*****************");
		emptyList.forEach(c);
		System.out.println("*****************");
		
		Stream<String> filter2 = list1.stream()
		 .filter(s->s.startsWith("a"));
		emptyList.forEach(c);
		
		System.out.println("*****************");
			emptyList.forEach(c);
		
			
	System.out.println("##############");
	double asDouble = emptyList.stream().mapToInt(s->s.length()).average().getAsDouble();
	emptyList.stream().map(s->s.length()).forEach(System.out::println);
	System.out.println(asDouble);
	
	try {
		FileReader file=new FileReader(new File("C:\\Users\\basha\\Desktop\\testTextFile.txt"));
		BufferedReader br=new BufferedReader(file);
		Stream<String> flatMap = br.lines().flatMap(line->Stream.of(line.split("\\s+")));
		br.lines().map(line->{
			String[] s=line.split(" ");
			return s;
		});
		flatMap.forEach(c);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	List<Integer> intList=Arrays.asList();
Optional<Integer> reduce = intList.stream()
			.reduce(Integer::sum);

		System.out.println(reduce);
		
		List<String> updatedStringList=Arrays.asList("Shahul","Hameed","Basha","M","H");
		updatedStringList.stream()
		.collect(Collectors.toList())
		.forEach(System.out::println);;

		Person person=new Person("Shahul",27,"M");
		Person person1=new Person("Hameed",25,"M");
		Person person2=new Person("Basha",24,"M");
		
		List<Person> personList=Arrays.asList(person,person1,person2);
		Map<Integer, List<Person>> collect = personList.stream()
				.filter(s->s.getAge()>20)
				.collect(Collectors.groupingBy(Person::getAge));
		
		collect.forEach((s1,s2)->{
			System.out.println(s1.intValue()+s2.toString());
		});
		
		Map<Integer, Long> collect2 = personList.stream()
		.filter(s->s.getAge()>20)
		.collect(Collectors.groupingBy(Person::getAge,Collectors.counting()));
		
		collect2.forEach((s1,s2)->{
			System.out.println(s1+" "+s2);
		});
		
		List<Person> personList1=new ArrayList<>();
		try {
			BufferedReader br1=new BufferedReader(new FileReader(new File("C:\\Users\\basha\\Desktop\\people.txt")));
			br1.lines().map(line->{
				String[] split = line.split(" ");
				Person p=new Person(split[0].trim(),Integer.parseInt(split[1]),split[2]);
				personList1.add(p);
				return p;
			}).forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stream<Person> stream = personList1.stream();
		Optional<Person> min = stream.filter(s->s.getAge()<10)
				.min(Comparator.comparing(Person::getAge));
		System.out.println(min.get());

		Map<Integer,Long> personMap=personList1.stream()
					.collect(Collectors.groupingBy(Person::getAge,Collectors.counting()));
		System.out.println(personMap);
		Map<Integer, List<Person>> collect3 = personList1.stream()
		.collect(Collectors.groupingBy(Person::getAge));
		System.out.println(collect3);
		
		Map<Integer, String> collect4 = personList1.stream()
		.collect(Collectors.groupingBy(Person::getAge,Collectors.mapping(Person::getName, Collectors.joining(" "))));

		
		Map<Integer, List<String>> collect5 = personList1.stream()
					.collect(Collectors.groupingBy(Person::getAge,Collectors.mapping(Person::getName, Collectors.toList())));
		
		Map<Integer, TreeSet<String>> collect6 = personList1.stream()
					.collect(Collectors.groupingBy(
							Person::getAge,
							Collectors.mapping(Person::getName, Collectors.toCollection(TreeSet::new))));
		
		System.out.println(collect6);
		
		LocalDate now=LocalDate.now();
		LocalDate nextSunday=now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(nextSunday);
		
		LocalTime time=LocalTime.now();
		LocalTime bedTime1=LocalTime.of(22, 0);
		LocalTime wakeUpTime=bedTime1.plusHours(8);
		System.out.println(time+" "+bedTime1+" "+wakeUpTime);
		
		Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		availableZoneIds.stream().filter(s->s.startsWith("Del")).forEach(c);
		
		ZonedDateTime currentMeeting=ZonedDateTime.of(LocalDate.now(), LocalTime.now(),ZoneId.of("Asia/Calcutta"));
		ZonedDateTime withZoneSameInstant = currentMeeting.withZoneSameInstant(ZoneId.of("US/Central"));
		
		System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(withZoneSameInstant));
		
		StringJoiner sj=new StringJoiner(",","{","}");
		sj.add(time.toString()).add(bedTime1.toString()).add(wakeUpTime.toString());
		System.out.println(sj.toString());
		List<String> arrayList=Arrays.asList("Shahul","Hameed","Basha","vv","fwewf","feferg");
		String join = String.join(" ", arrayList);
	//	System.out.println(join);
		Spliterator<String> spliterator = arrayList.stream().spliterator();
		System.out.println(spliterator.estimateSize());
//		spliterator.forEachRemaining(c);


		Spliterator<String> trySplit = spliterator.trySplit();
		if(trySplit!=null) {
			System.out.println("TrySplit");
			trySplit.forEachRemaining(c);
		}
		System.out.println("Remaining");
		spliterator.forEachRemaining(c);
		
		personList.forEach(System.out::println);
		System.out.println("*******************");
		personList.sort(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				
				return o1.getName().length()-o2.getName().length()==0?o2.getAge()-o1.getAge():o1.getName().compareTo(o2.getName());
			}
		});
		List<String> lists=new ArrayList<>();
		personList.forEach(System.out::println);
		System.out.println("$$$$$$$$$$$$$$$");
		personList.sort(Comparator.comparing(Person::getName).thenComparing(Person::getAge).reversed());
		personList.forEach(System.out::println);
		System.out.println("$$$$$$$$$$$$$$$");
		lists.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
		Comparator.nullsFirst(Comparator.naturalOrder());
		List<Person> subList = personList1.subList(0, 1);
		List<Person> subList2 = personList1.subList(1, personList1.size());
		
		//Map<Integer,List<Person>> mapNew=mapByAge(personList1);
		
		Map<Integer,Map<String,List<Person>>> biMap=new HashMap<>();
		
		personList1.forEach(personNew->
			biMap.computeIfAbsent(personNew.getAge(), HashMap::new)
				 .merge(personNew.getGender(), new ArrayList<>(Arrays.asList(personNew)), (l1,l2)->{
					 l1.addAll(l2);
					 return l1;
				 })
		);
		
/*		Map<Integer, List<Person>> map10=mapByAge(subList);
		Map<Integer, List<Person>> map11=mapByAge(subList2);*/
	//	map10.forEach((key,value)->System.out.println("Jey : "+key+" Value :"+value));
		System.out.println("------------------------");
	//	map11.forEach((key,value)->System.out.println("Jey : "+key+" Value :"+value));
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
		biMap.forEach((key,value)->System.out.println(key +" "+value));
		//map10.putAll(map11);
		//map10.merge(map11., value, remappingFunction)
/*		for (Map.Entry<Integer, List<Person>> person3 : map11.entrySet()) {
		//	System.out.println(person3.getKey()+" "+person3.getValue());
			if(map10.containsKey(person3.getKey())) {
				map10.get(person3.getKey()).addAll(person3.getValue());
			}
			else {
				map10.put(person3.getKey(), person3.getValue());
			}
		}*/
		//Merging a Map java 8
/*		map11.entrySet().stream().forEach(entry -> {
			map10.merge(entry.getKey(), entry.getValue(),(l1,l2)->{
				l1.addAll(l2);
				return l1;
			});
		});*/
		
/*		Map<Integer, String> map12=new HashMap<>();
		map12.put(1, "Java");
		map12.put(2, "HTML");
		map12.put(3, "CSS");
		
		Map<Integer, String> map13=new HashMap<>();
		map13.put(1, "Python");
		map13.put(2, "JS");
		map13.put(3, "Angular");
		*/
/*		for (Map.Entry<Integer, String> person3 : map13.entrySet()) {
		//	System.out.println(person3.getKey()+" "+person3.getValue());
			if(map12.containsKey(person3.getKey())) {
			//	map12.get(person3.getKey()).join(",", person3.getValue());
				map12.put(person3.getKey(),  String.join(",", map12.get(person3.getKey()),person3.getValue()));
				//map12.get(person3.getKey())+person3.getValue();
			//	String.join(",", map12.get(person3.getKey()),person3.getValue());
			}
			else {
				map12.put(person3.getKey(), person3.getValue());
			}
		}*/
		
/*		map13.entrySet().stream().forEach(entry->{
			map12.merge(entry.getKey(), entry.getValue(), (l1,l2)->{
				return String.join(",", l1,l2);
			});
		});*/
	//	map10.forEach((key,value)->System.out.println("Jey : "+key+" Value :"+value));
		
		//map12.forEach((key,value)->System.out.println("Jey : "+key+" Value :"+value));
	
	}

	private static Map<Integer, List<Person>> mapByAge(List<Person> subList) {
		return subList.stream().collect(Collectors.groupingBy(Person::getAge));
	}


	
	
	
}

class Person{
	
	String name;
	int age;
	String gender;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Person(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
}

