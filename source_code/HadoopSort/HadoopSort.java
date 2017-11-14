import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;

public class HadoopSort {

	public static class SortMapper extends Mapper<Object, Text, Text, Text> {

	private Text Key = new Text();
	private Text Value = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String ln = value.toString();
		String[] ln1 = ln.split("\n");
		String k=key.toString();

		for (String line : ln1) {
			Key.set(k.substring(0, 10));
			Value.set(k.substring(10, 98));
			context.write(Key, Value);
		}
	}
        }

	public static class SortReducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		for (Text value : values) {
			context.write(key, value);
		}

	}
        }
 
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

		long start = System.currentTimeMillis();
		Configuration conf = new Configuration(true);
		Job job = Job.getInstance(conf, "HadoopSort");
		job.setJarByClass(HadoopSort.class);
	        job.setOutputKeyClass(Text.class);
 	        job.setOutputValueClass(Text.class);
 	        job.setMapperClass(SortMapper.class);
 	        job.setReducerClass(SortReducer.class);
                job.setInputFormatClass(KeyValueTextInputFormat.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        FileInputFormat.addInputPath(job, new Path(args[0]));
 	        FileOutputFormat.setOutputPath(job, new Path(args[1]));
 	        long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
 	}
}
