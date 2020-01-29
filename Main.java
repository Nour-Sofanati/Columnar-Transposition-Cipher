import java.text.StringCharacterIterator;
import java.util.Arrays; 

class Main {

  public static void main(String[] args) {
    
    String Phrase = "Columnar Transposition Cipher";
    String Key = "NourSofanati";

    String encodedMsg = encrypt(Phrase, Key);
    String decodedMsg = decrypt(encodedMsg,Key);
    
    System.out.printf("Encoded Message: "+encodedMsg+"\nDecoded Message: "+decodedMsg+"\n");
  }

  private static String encrypt(String src, String key) {
    int[] columnorder = getColumnOrder(key);
    String[] columns = new String[key.length()];
    Arrays.fill(columns, "");

    for (int i = 0; i < src.length(); i++) {
      columns[i % key.length()] += src.toCharArray()[i];
    }

    String encryptedText = "";

    for (int i = 0; i < key.length(); i++) {
      encryptedText += columns[columnorder[i]];
    }

    return encryptedText;
  }

  private static String decrypt(String src,String key) {
        int[] columnorder = getColumnOrder(key);

        int p = 0;
        String[] columns = new String[key.length()];
        Arrays.fill(columns,"");

        for (int i = 0; i < key.length(); i++) {
            int k = columnorder[i];
            int l = (int) Math.floor(src.length() / key.length());
            if (k < src.length() % key.length())
                l++;
            columns[k] = src.substring(p, p + l);
            p += l;
        }

        String decryptedText = "";
        for (var i = 0; i < src.length(); i++) {
            var k = i % key.length();
            decryptedText+=columns[k].toCharArray()[i/key.length()];
        }

        return decryptedText;
    }

  private static int[] getColumnOrder(String key) {
    char[] chararr = key.toCharArray();
    Arrays.sort(chararr);
    String sorted = new String(chararr);

    int[] columnorder = new int[key.length()];

    int p = 0;
    char pch = Character.MIN_VALUE;
    for (int i = 0; i < sorted.length(); i++) {
      if (pch != sorted.toCharArray()[i])
        p = 0;
      pch = sorted.toCharArray()[i];
      p = key.indexOf(pch, p);
      columnorder[i] = p;
      p++;
    }

    return columnorder;
  }
}