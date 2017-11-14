package sort;

import org.apache.spark.SparkConf;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class SparkSort {
	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("Sort Spark").setMaster(args[0]);
		JavaSparkContext js = new JavaSparkContext(conf);
		JavaRDD<String> tf = js.textFile(args[1]);
		long start = System.currentTimeMillis();
		JavaRDD<String> tfs = tf.sortBy(new sortfn<String, String>() {
			public String call(String n1) throws Exception {
				return n1.substring(0, 10);
			}
		});
		tfs.saveAsTextFile("output");
		long end = System.currentTimeMillis();
		System.out.println("Time " + (end - start));
	}
}
