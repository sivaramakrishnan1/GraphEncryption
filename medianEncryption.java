import java.util.Scanner;
 

public class medianEncryption {
    // static char[]
    static char[] alphabets={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    public static int[][] vertex = {{0, 0}, {0, 0}, {0, 0}};
    static int x = 0, y = 1, z = 2;

    public static void main(String args[]) {
        Scanner s = new Scanner( System.in );
        int[] publicKey = { 8,1 };
        int[] privateKey1 = { 3,5 };
        int[] privateKey2 = { 5,8 };
        setVertex(publicKey, privateKey1, privateKey2);
        midpointsToVertex();
        System.out.println("Enter the text to be encrypted: ");
        String plainText = s.nextLine();
        System.out.println("Plain Text:\t" + plainText);
        String encodedText = encryptText(plainText.toCharArray());
        System.out.println("Encrypted Text:\t" + encodedText);
        String decodedText = decryptText(encodedText.toCharArray());
        System.out.println("Decrypted Text:\t" + decodedText);
        s.close(); 
    }

    public static int findCharacter(char findable){
        for(int i=0 ; i<alphabets.length ; i++){
            if(alphabets[i] == findable)
                return i;
        }
        return -1;
    }

    public static String encryptText(char[] message) {
        int key = calculateCenter();

        for (int i = 0; i < message.length; i++) {
            int numChar = findCharacter(message[i]);
            message[i] = alphabets[((numChar + key) % 61)];
        }
        return String.valueOf(message);
    }

    public static String decryptText(char[] message) {
        int key = calculateCenter();
        for (int i = 0; i < message.length; i++) {
            int numChar = findCharacter(message[i]);
            message[i] = alphabets[(((numChar - key) + 61) % 61)];
        }
        return String.valueOf(message);
    }

    public static void printVertex() {
        for (int i = 0; i < 3; i++) {
            System.out.println("[" + vertex[i][0] + " , " + vertex[i][1] + "]");
        }
    }

    public static void setVertex(int x[], int y[], int z[]) {
        vertex[0] = x;
        vertex[1] = y;
        vertex[2] = z;
    }

    public static void midpointsToVertex() {
        int[] currentX = {0, 0}, currentY = {0, 0}, currentZ = {0, 0};
        currentX[0] = vertex[x][0] - vertex[y][0] + vertex[z][0];
        currentX[1] = vertex[x][1] - vertex[y][1] + vertex[z][1];
        currentY[0] = vertex[x][0] + vertex[y][0] - vertex[z][0];
        currentY[1] = vertex[x][1] + vertex[y][1] - vertex[z][1];
        currentZ[0] = vertex[y][0] + vertex[z][0] - vertex[x][0];
        currentZ[1] = vertex[y][1] + vertex[z][1] - vertex[x][1];
        vertex[0] = currentX;
        vertex[1] = currentY;
        vertex[2] = currentZ;
    }

    public static void vertexToMidpoints() {
        int[] currentX = {0, 0}, currentY = {0, 0}, currentZ = {0, 0};
        currentX[0] = vertex[x][0] + vertex[y][0] - vertex[z][0];
        currentX[1] = vertex[x][1] + vertex[y][1] - vertex[z][1];
        currentY[0] = vertex[x][0] - vertex[y][0] + vertex[z][0];
        currentY[1] = vertex[x][1] - vertex[y][1] + vertex[z][1];
        currentZ[0] = vertex[y][0] - vertex[z][0] + vertex[x][0];
        currentZ[1] = vertex[y][1] - vertex[z][1] + vertex[x][1];
        vertex[0] = currentX;
        vertex[1] = currentY;
        vertex[2] = currentZ;
    }

    public static int calculateCenter() {
        int[] centroid = {0, 0};
        centroid[0] = (vertex[x][0] + vertex[y][0] + vertex[z][0]) / 3;
        centroid[1] = (vertex[x][1] + vertex[y][1] + vertex[z][1]) / 3;
        int center = centroid[0] + centroid[1];
        return center;
    }
}