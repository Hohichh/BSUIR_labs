public class Skitale {

    public static String encrypt(String mess, int key){
        return cipherProcessing(mess, key, true);
    }

    public static String decrypt(String mess, int key){
        return cipherProcessing(mess, key, false);
    }

    private static String cipherProcessing(String mess, int key, boolean flag){
        mess = mess.replaceAll("[^A-Za-zА-Яа-яЁё]", "");
        StringBuilder encrypted = new StringBuilder();
        int colNum = (int)Math.ceil(mess.length() / key);
        int rowNum = key;

        int tableSize = rowNum * colNum;

        for (int i = 0; i < mess.length(); i += tableSize) {
            String block = mess.substring(i, Math.min(i + tableSize, mess.length()));
            while (block.length() < tableSize) {
                block += ' ';
            }
            String result = flag? encryptBlock(block, rowNum, colNum) : decryptBlock(block, rowNum, colNum);
            encrypted.append(result);
        }
        return encrypted.toString();
    }

    private static String encryptBlock(String mess, int rowNum, int colNum) {
        mess = mess.replaceAll("[^A-Za-zА-Яа-яЁё]", "");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < colNum; i++){
            for(int j = 0; j < rowNum; j++){
                int index = j * colNum + i;
                if(index < mess.length()) {
                    sb.append(mess.charAt(index));
                }
            }
        }
        return sb.toString();
    }
    private static String decryptBlock(String mess, int rowNum, int colNum) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < mess.length(); j+=rowNum){
                int index = i + j;
                if(index < mess.length()) {
                    sb.append(mess.charAt(index));
                }
            }
        }
        return sb.toString();
    }
}
