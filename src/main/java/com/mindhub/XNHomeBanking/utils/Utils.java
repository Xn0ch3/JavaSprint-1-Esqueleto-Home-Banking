package com.mindhub.XNHomeBanking.utils;

public class Utils {

    public static String getRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i<4; i++){
            int seccion=(int) (Math.random()*9000+1000);
            cardNumber.append(seccion).append("-");
        }
        return cardNumber.substring(0,cardNumber.length()-1);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static  String numberAccountRandom(){
        String number;
        number = "VIN-" + getRandomNumber(00000000, 99999999);
        return number.toString();
    }

    public static Integer generateCvv(){
        int cvv = (int) (Math.random() * 899+100);
        return cvv;
    }

}
