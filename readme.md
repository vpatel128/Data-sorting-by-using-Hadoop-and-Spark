## Data-sorting-by-using-Hadoop-and-Spark

## Introduction

Configured Hadoop and Spark cluster (1 master + 16 slave nodes) from scratch and written java code to sort 1TB data over this cluster in d2.xlarge instance


Install JAVA 8

	sudo add-apt-repository ppa:webupd8team/java
	sudo apt-get update
	sudo apt-get install oracle-java8-installer

Download gensort-linux-1.5

	wget http://www.ordinal.com/try.cgi/gensort-linux-1.5.tar.gz
	tar -xvf gensort-linux-1.5.tar.gz

./gensort -a 107374182 dataset10gb.txt
./gensort -a 1073741820 dataset100gb.txt

1) Shared Memory Sort

	javac ESort.java 
	
	java ESort dataset10gb.txt
	
	javac ESort.java 
	
	java ESort dataset100gb.txt

2) Hadoop Sort

	Download hadoop-2.7.2

	wget http://download.nextag.com/apache/hadoop/common/hadoop-2.7.2/hadoop-2.7.2.tar.gz
	tar -xvf hadoop-2.7.2.tar.gz

• bin/hdfs dfs -mkdir /user

• bin/hdfs dfs -mkdir /user/biya

• bin/hdfs dfs -mkdir /user/biya/Input

• bin/hdfs dfs -mkdir /user/biya/Output

• bin/hadoop fs -put /root/64/dataset10gb.txt /user/biya/Input/dataset10gb.txt

• mkdir hadoopsort_classes

• javac -classpath "/root/ephemeral-hdfs/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar:/root/hadoop-core-1.2.1.jar:/root/ephemeral-hdfs/share/hadoop/mapreduce/hadoop-mapreduce-client-core-2.7.2.jar:/root/ephemeral-hdfs/share/hadoop/common/hadoop-common-2.7.2.jar" -d hadoopsort_classes HadoopSort.java 

• jar -cvf /root/HadoopSort.jar -C hadoopsort_classes/ .

• bin/hadoop dfs -cat /user/biya/Output/output.txt/part-r-00000

3) Spark Sort

	Download spark-1.6.1-bin-hadoop2.6

• bin/hdfs dfs -mkdir /user

• bin/hdfs dfs -mkdir /user/biya

• bin/hdfs dfs -mkdir /user/biya/Input

• bin/hdfs dfs -mkdir /user/biya/Output

• bin/hadoop fs -put /root/64/dataset10gb.txt /user/biya/Input/dataset10gb.txt

• spark/bin/spark-submit --master spark://ec2-52-201-212-65.compute-1.amazonaws.com --class sort.SparkSort  file:///root/sort-0.0.1-SNAPSHOT.jar spark://ec2-52-201-212-65.compute-1.amazonaws.com hdfs://ec2-52-201-212-65.compute-1.amazonaws.com/user/biya/Input/dataset10gb.txt

• bin/hadoop dfs -cat /user/biya/Output/output.txt/part-r-00000




