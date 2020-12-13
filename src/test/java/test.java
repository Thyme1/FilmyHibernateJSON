//
//
//import hibernate.model.Actors;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//
//
//class test {
//
//    @Test
//    public void whenStoringAJsonColumn_thenDeserializedVersionMatches() {
//        Actors actor = new Actors();
//        actor.setName("first name");
//        actor.setSurname("last name");
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("address", "123 Main Street");
//        attributes.put("zipcode", 12345);
//
//        actor.setCustomerAttributes(attributes);
//        actor.serializeCustomerAttributes();
//
//        String serialized = actor.getCustomerAttributeJSON();
//
//        actor.setCustomerAttributeJSON(serialized);
//        actor.deserializeCustomerAttributes();
//
//        assertEquals(attributes, actor.getCustomerAttributes());
//    }
//
//    }