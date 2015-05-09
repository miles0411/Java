
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.hbase.HBaseConfiguration;  
import org.apache.hadoop.hbase.HColumnDescriptor;  
import org.apache.hadoop.hbase.HTableDescriptor;  
import org.apache.hadoop.hbase.KeyValue;  
import org.apache.hadoop.hbase.MasterNotRunningException;  
import org.apache.hadoop.hbase.ZooKeeperConnectionException;  
import org.apache.hadoop.hbase.client.Delete;  
import org.apache.hadoop.hbase.client.Get;  
import org.apache.hadoop.hbase.client.HBaseAdmin;  
import org.apache.hadoop.hbase.client.HTable;  
import org.apache.hadoop.hbase.client.Result;  
import org.apache.hadoop.hbase.client.ResultScanner;  
import org.apache.hadoop.hbase.client.Scan;  
import org.apache.hadoop.hbase.client.Put;  
import org.apache.hadoop.hbase.util.Bytes; 



public class Hbase {
	
	private static Configuration conf = null;
	private static Scanner s = null;
    
	static {
    	
        conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir", "master");
        
    }
   
     public static void createTable(String tablename, String[] cfs){
         try {
             HBaseAdmin admin = new HBaseAdmin(conf);
             if (admin.tableExists(tablename)) {
                 System.out.println("table already exists!");
             } else {
                 HTableDescriptor tableDesc = new HTableDescriptor(tablename);
                 for (int i = 0; i < cfs.length; i++) {
                     tableDesc.addFamily(new HColumnDescriptor(cfs[i]));
                 }
                 admin.createTable(tableDesc);
                 admin.close();
                 System.out.println("create table " + tablename + " ok.");
             }
         } catch (MasterNotRunningException e) {
             e.printStackTrace();
         } catch (ZooKeeperConnectionException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


     public static void addRecord (String tableName, String rowKey, String family, String qualifier, String value){
         try {
             HTable table = new HTable(conf, tableName);
             Put put = new Put(Bytes.toBytes(rowKey));
             put.add(Bytes.toBytes(family),Bytes.toBytes(qualifier),Bytes.toBytes(value));
             table.put(put);
             System.out.println("insert recored " + rowKey + " to table " + tableName +" ok.");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     /**
     * 刪除一筆資料
     * @param tableName
     * @param rowKey
     */
     public static void delRecord (String tableName, String rowKey){
         try {
             HTable table = new HTable(conf, tableName);
             List list = new ArrayList();
             Delete del = new Delete(rowKey.getBytes());
             list.add(del);
             table.delete(list);
             System.out.println("del recored " + rowKey + " ok.");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public static void getOneRecord (String tableName, String rowKey){         
         try {
             HTable table = new HTable(conf, tableName);
             Get get = new Get(rowKey.getBytes());
             Result rs = table.get(get);
             for(KeyValue kv : rs.raw()){
                 System.out.print(new String(kv.getRow()) + " " );
                 System.out.print(new String(kv.getFamily()) + ":" );
                 System.out.print(new String(kv.getQualifier()) + " " );
                 System.out.print(kv.getTimestamp() + " " );
                 System.out.println(new String(kv.getValue()));
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public static void getAllRecord (String tableName) {         
         try{
             HTable table = new HTable(conf, tableName);
             Scan s = new Scan();
             ResultScanner ss = table.getScanner(s);         
             for(Result r:ss){
                 for(KeyValue kv : r.raw()){
                     System.out.print(new String(kv.getRow()) + " ");
                     System.out.print(new String(kv.getFamily()) + ":");
                     System.out.print(new String(kv.getQualifier()) + " ");
                     System.out.print(kv.getTimestamp() + " ");
                     System.out.println(new String(kv.getValue()));
                 }
             }
         } catch (IOException e){
             e.printStackTrace();
         }
     }


     public static void main(String[] args) {
         try {         
             
        	 try {
         		File f = new File("processed.txt");
         		s = new Scanner(f);
         		s.useDelimiter(",");   //Use the normal expression and exclude data we imagine they are not "WORDS"
         	 }
         	 catch ( FileNotFoundException e) {}
        	 
        	 String tablename = "tweets";
             String[] familys = {"tweet_ID"};    
             Hbase Hbase = new Hbase();
             Hbase.createTable(tablename, familys);

             //add record zkb  
             String cur_user_time = s.next()+" "+s.next();
             Hbase.addRecord(tablename,cur_user_time,"tweet_ID",String.valueOf(1),s.next());
             String last_user_time = cur_user_time;
             cur_user_time = s.next()+" "+s.next();
             int i = 1;
             while(s.hasNextLine()){     	 
            	 if(cur_user_time.equals(last_user_time)){
	            	 i++;
	            	 Hbase.addRecord(tablename,cur_user_time,"tweet_ID",String.valueOf(i),s.next());
	            	 last_user_time = cur_user_time;
	            	 cur_user_time = s.next()+" "+s.next();
            	 }
            	 else{
            		 i=1;
            		 Hbase.addRecord(tablename,cur_user_time,"tweet_ID",String.valueOf(i),s.next());
            		 last_user_time = cur_user_time;
	            	 cur_user_time = s.next()+" "+s.next();		 
            	 }
             }
                 
             /*
             System.out.println("===========get one record========");
             Hbase.getOneRecord(tablename, "");

             System.out.println("===========show all record========");
             Hbase.getAllRecord(tablename);

             System.out.println("===========del one record========");
             Hbase.delRecord(tablename, "baoniu");
             */
             Hbase.getAllRecord(tablename);

         } catch (Exception e) {
             e.printStackTrace();
         }
     }

	

}
