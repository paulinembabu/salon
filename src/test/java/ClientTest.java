/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.*;

public class ClientTest {
    
     @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test 
    public void instanceOf(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	assertTrue(newClient instanceof Client);
    }
    @Test
    public void  getName_getstheIdOfInstance_name(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	assertEquals("pauline", newClient.getName());

    }@Test
    public void getContact_getstheIdOfInstance_contact(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	assertEquals("254-756-456-445", newClient.getContact());

    }
    @Test
    public void  all_getstheInstances(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	newClient.save();
    	Client newClient2 = new Client ("paulina","255-756-456-445",1);
    	newClient2.save();
    	assertTrue(Client.all().get(0).equals(newClient));
    	assertTrue(Client.all().get(1).equals(newClient2));

    }
    @Test
    public void clear_removesallTheInstances_0(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	assertEquals(0,Client.all().size());
    }
    @Test
    public void getId_getstheIdOfTheInstance_1(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	newClient.save();
    	assertTrue(newClient.getId() > 1);
    }

    @Test
    public void find_returnswithtaskofsameId_newTask2(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	newClient.save();
    	Client newClient2 = new Client ("paulina","255-756-456-445",1);
    	newClient2.save();
    	assertEquals(newClient2, Client.find(newClient2.getId()));
    }

    @Test
    public void equals_returnstrueifdescriptionsareThesame(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	Client newClient2 = new Client ("pauline","254-756-456-445",1);
    	assertTrue(newClient.equals(newClient2));
    }
    @Test
    public void save_returnsIdifinstanceisSaved(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	newClient.save();
    	Client savedClient =Client.all().get(0);
    	assertEquals(newClient.getId(),savedClient.getId());
    }
     @Test
    public void save_savesStylistIdIntoDB_true(){
    	Stylist newStylist = new Stylist ("jane","manicure");
    	newStylist.save();
    	Client newClient = new Client ("pauline","254-756-456-445",newStylist.getId());
    	newClient.save();
    	Client savedClient =Client.find(newClient.getId());
    	assertEquals(savedClient.getStylistId(),newStylist.getId());
    }
     @Test
    public void update_updatesasavedClient_true(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	newClient.save();
    	newClient.update("nombu","255-756-636-445");
        assertEquals("nombu",Client.find(newClient.getId()).getName());
    	assertEquals("255-756-636-445",Client.find(newClient.getId()).getContact());
     
   }
     @Test
    public void delete_deletsaclient_true(){
    	Client newClient = new Client ("pauline","254-756-456-445",1);
    	newClient.save();
        newClient.delete();
    	int myClientId= newClient.getId();
    	assertEquals(null,Client.find(myClientId));
    }     

    }

