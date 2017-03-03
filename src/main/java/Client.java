 
import java.util.*;
import org.sql2o.*;

public class Client{
	private  String name;
	private  String contact;
	private int id;
	private  int stylistId; 

	public Client( String clientName, String clientContact, int stylistId){
		this.contact= clientContact;
		this.name= clientName;
		this.stylistId = stylistId;
	}
	public String getName(){
		return name;
	}
	
	public String getContact(){
		return contact;
	}

	public int getId(){
		return id;
	}

	public int getStylistId(){
		return stylistId;
	}
    
    public static List<Client> all(){
     String sql = "SELECT id, name, contact, stylistId FROM clients";
     try(Connection con = DB.sql2o.open()){
     	 return con.createQuery(sql)
     	 .executeAndFetch(Client.class);
     }
    }
   
   @Override
   public boolean equals(Object otherClient){
   	if(!(otherClient instanceof Client)){
   		return false;
   	}
   	else{
   		Client newClient = (Client) otherClient;
   		return this.getName().equals(newClient.getName())&&
   		this.getContact().equals(newClient.getContact())&&
   		this.getId()==(newClient.getId())&&
   		this.getStylistId() == (newClient.getStylistId());

   	}
   }
   public void save(){
   	try(Connection con =DB.sql2o.open()){
   		String sql = "INSERT into clients(name,contact,stylistId) VALUES(:name,:contact,:stylistId)";
   		this.id=(int) con.createQuery(sql,true)
   		.addParameter("name",name)
   		.addParameter("contact",contact)
   		.addParameter("stylistId",stylistId)
   		.executeUpdate()
   		.getKey();
}
   	}
   

   public static Client find(int id){
   	try(Connection con =DB.sql2o.open()){
   		String sql = "SELECT * FROM clients WHERE id =:id";
   		Client client =con.createQuery(sql)
   		.addParameter("id",id)
   		.executeAndFetchFirst(Client.class);
   		return client;
   	}
   }
 public void update(String name,String contact){
 	 try(Connection con = DB.sql2o.open()) {
 	 	String sql ="UPDATE clients SET name = :name, contact =:contact WHERE id = :id";
 	 	con.createQuery(sql)
 	 	.addParameter("name",name)
 	 	.addParameter("contact",contact)
    .addParameter("id",id)
 	 	.executeUpdate();

 }
}

public void delete(){
 try(Connection con = DB.sql2o.open()){
 String sql =  "DELETE FROM clients WHERE id = :id";
 con.createQuery(sql)
 .addParameter("id",id)
 .executeUpdate();
 }	
}

}
  
  
 

  
















