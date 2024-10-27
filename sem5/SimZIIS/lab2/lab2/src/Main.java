

public class Main {
    public static void main(String[] args) {
        String a = Skitale.encrypt("ЭТО ШИФР ДРЕВНЕЙ СПАРТЫ", 4);
        String b = Skitale.decrypt(a,5);
        System.out.println(a +"\n" + b);
        System.out.println(bruteForceAttack("ЭТО ШИФР ДРЕВНЕЙ СПАРТЫ", a) + " - количество итераций, " +
                "нужное ддя подбора пароля с к данным ключом.");
    }

    public static int bruteForceAttack(String initStr, String encrStr){
        String decrStr = "";
        int i = 0;
        while(!initStr.replaceAll(" ","").equals(decrStr)){
            i++;
            decrStr = Skitale.decrypt(encrStr,i).replaceAll(" ","");
        }
        return i;
    }




}
