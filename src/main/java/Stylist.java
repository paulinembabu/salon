 import java.util.*;
import org.sql2o.*;

public class Stylist{

    private  String name;
	private  String specialization;
	private int id;

	public Stylist( String stylistName, String stylistSpecialization){
		this.name= stylistName;
		this.specialization= stylistSpecialization;
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public String getSpecialization(){
		return specialization;
	}

	public int getId(){
		return id;
	}

public static Stylist find(int id) {
     try(Connection con = DB.sql2o.open()) {
       String sql = "SELECT * FROM stylists WHERE id =:id";
       Stylist stylist = con.createQuery(sql)
         .addParameter("id", id)
         .executeAndFetchFirst(Stylist.class);
       return stylist;
     }
   }

 public List<Client> getClients() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM clients where stylistId=:id";
     return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Client.class);
   }
 }

 @Override
 public boolean equals(Object otherStylist) {
   if (!(otherStylist instanceof Stylist)) {
     return false;
   } else {
     Stylist newStylist = (Stylist) otherStylist;
     return this.getName().equals(newStylist.getName()) &&
            this.getId() == newStylist.getId();
   }
 }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name,specialization) VALUES (:name, :specialization) ";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("specialization", this.specialization)
        .executeUpdate()
        .getKey();
    }
  }
  public void update(String name,String specialization){
 	 try(Connection con = DB.sql2o.open()) {
 	 	String sql ="UPDATE  stylists SET name =:name, specialization=:specialization WHERE id=:id";
 	 	con.createQuery(sql)
 	 	.addParameter("name",name)
 	 	.addParameter("specialization",specialization)
    .addParameter("id",this.id)
 	 	.executeUpdate();

 }
}

	public void delete(){
	 try(Connection con = DB.sql2o.open()){
	 String sql =  "DELETE FROM stylists WHERE id = :id";
	 con.createQuery(sql)
	 .addParameter("id",id)
	 .executeUpdate();
	 }	
	}

  public void deleteClients() {
    try(Connection con = DB.sql2o.open()){
     String sql =  "DELETE FROM clients WHERE stylistid = :id";
     con.createQuery(sql)
     .addParameter("id",id)
     .executeUpdate();
     }
  }

	public static List<Stylist> all() {
    String sql = "SELECT id, name, specialization FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
}