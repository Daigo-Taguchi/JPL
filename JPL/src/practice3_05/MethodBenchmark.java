package practice3_05;

class MethodBenchmark extends Benchmark{
	 void benchmark() {
		int num = 0;
		 while(num < ) {
			
		}
	}
	
	public static void main(String[] args) {
		int count = Integer.parseInt(args[0]);
		long time = new MethodBenchmark().repeat(count);
		System.out.println(count + "methods in" + time + "nanoseconds");
	}

}
