package jackson;

import hibernate.model.Actors;

import hibernate.model.Address;
import org.joda.time.DateTime;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper class which provides methods to create instances of hibernate.model classes
 */
public class ModelObjectsCreator {

    Actors act;
    Actors act2;
    Address address;

    List<Actors> actors;

    public Actors getAct() {
        return act;
    }

    public Actors getAct2() {
        return act2;
    }

    public Address getAddress() {
        return address;
    }

    public List<Actors> getActors() {
        return actors;
    }

    public void init(){
        act = new Actors();
        act.setName("Johnny");
        act.setSurname("Depp" + new Random().nextInt());
        act.setAge(35);
        act.setGender("male");


        act2 = new Actors();
        act2.setName("Morgan");
        act2.setSurname("Freeman" + new Random().nextInt());
        act2.setAge(55);
        act2.setGender("male");

        //add address
        Address address = new Address();
        address.setCity("Nowy Jork");
        address.setStreet("Oak");
        address.setNr("23");
        address.setPostcode("91239");

        act.setAddress(address);
        act2.setAddress(address);


        actors = new ArrayList<Actors>();
        actors.add(act);
        actors.add(act2);

    }

    public ModelObjectsCreator(){
        init();
    }


}
