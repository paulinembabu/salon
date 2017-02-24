import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.*;

public class StylistTest {
  
  @Rule
  public DatabaseRule database = new DatabaseRule();


   @Test
 public void getId_stylistsInstantiateWithAnId_1() {
   Stylist testStylist= new Stylist("nombuyiselo","manicure");
   testStylist.save();
   assertTrue(testStylist.getId() > 0);
 }

 @Test
 public void find_returnsStylistsWithSameId_secondStylist() {
   Stylist testStylist= new Stylist("nombuyiselo","manicure");
   testStylist.save();
 Stylist secondStylist= new Stylist("Pauline","pedicure");
   secondStylist.save();
   assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
 }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Stylist testStylist= new Stylist("nombuyiselo","manicure");
    Stylist secondStylist= new Stylist("nombuyiselo","manicure");
    assertTrue(testStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
     Stylist myStylist= new Stylist("nombuyiselo","manicure");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist= new Stylist("nombuyiselo","manicure");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void getClients_retrievesALlClientssFromDatabase_clientsList() {
    Stylist myStylist= new Stylist("nombuyiselo","manicure");
    myStylist.save();
    Client firstClient = new Client("pauline","254-756-456-445", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Nombu","255-766-956-445", myStylist.getId());
    secondClient.save();
    Client [] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  } 
    
}
