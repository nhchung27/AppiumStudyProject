package test.gson;

import com.google.gson.Gson;
import utils.data.DataObjectBuilder;

public class TestGson {
    public static void main(String[] args) {
//        String jsonObject ="[\n" +
//                "  {\n" +
//                "  \"username\": \"nhchung\",\n" +
//                "  \"password\": \"12345678\",\n" +
//                "    \"job\": {\n" +
//                "      \"position\": \"SE level 2\"\n" +
//                "    }\n" +
//                "},\n" +
//                "  {\n" +
//                "  \"username\": \"nhchung2\",\n" +
//                "  \"password\": \"12345678\",\n" +
//                "    \"job\": {\n" +
//                "      \"position\": \"QA level 1\"\n" +
//                "    }\n" +
//                "}\n" +
//                "]";
//        //Json format -> Object
////        Gson gson = new Gson();
////        LoginCredential loginCredential= gson.fromJson(jsonObject,LoginCredential.class);
////        System.out.println(loginCredential);
////
////        //object to Json
////        System.out.println(gson.toJson(loginCredential));
//
//        // Multiple row in Json file
//        Gson gson = new Gson();
//        LoginCredential[] loginCredentials= gson.fromJson(jsonObject,LoginCredential[].class);
//        for (LoginCredential loginCredential : loginCredentials) {
////            System.out.println(loginCredential);
//            System.out.println(loginCredential.getJob().getPosition());
//        }
        String jsonFilePath = "/src/main/java/test/gson/InvalidLoginCred.json";
        LoginCredential[] loginCredentials = DataObjectBuilder.builderDataObject(jsonFilePath,LoginCredential[].class);
        for (LoginCredential loginCredential : loginCredentials) {
            System.out.println(loginCredential);
            System.out.println(loginCredential.getJob().getPosition());
        }

        String animalJsonFilePath = "/src/main/java/test/gson/Animal.json";
        Animal animal = DataObjectBuilder.builderDataObject(animalJsonFilePath, Animal.class);
        System.out.println(animal);

    }
}
