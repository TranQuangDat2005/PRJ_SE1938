//package utils;
//import dal.productDAO;
//import java.util.Random;
//
//public class RandomNameGenerator {
//
//    // H�m sinh t�n ng?u nhi�n v?i chi?u d�i cho tr�?c
//    public static String generateRandomName(int length) {
//            productDAO d = new productDAO();
//
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        StringBuilder randomName = new StringBuilder();
//
//        Random random = new Random();
//
//        for (int i = 0; i < length; i++) {
//            
//            char randomChar = characters.charAt(random.nextInt(characters.length()));
//            
//            randomName.append(randomChar);
//        }
//        if(d.checkID(randomName.toString()).size()>0){
//            generateRandomName(15);
//        }
//
//        return randomName.toString();
//    }
//    
//    
//    
//    
//
//    public static void main(String[] args) {
//       
//        String randomName = generateRandomName(15);
//
//       
//        System.out.println("Random Name: " + randomName);
//    }
//}
