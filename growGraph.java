class growGraph {
    static char[] alphabets={' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', '0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static int[][] vertex = {{0, 0}, {0, 0}, {0, 0}};
    static int x = 0, y = 1, z = 2;
    static int counter = 0;
    public static boolean increaseTriangle;
    
    public static void main(String args[]) {
        setVertex();
        System.out.print("\nEnter the text to be encrypted: ");
        String plainText = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789";
        System.out.print("\nPlain Text:\t" + plainText);
        String encodedText = encryptText(plainText.toCharArray());
        System.out.print("\nEncrypted Text:\t" + encodedText);
        String decodedText = decryptText(encodedText.toCharArray());
        System.out.print("\nDecrypted Text:\t" + decodedText + "\n");
    }

    // returns the passed character's position in char[] alphabets.
    public static int findCharacter(char findable){
        for(int i=0 ; i<alphabets.length ; i++){
            if(alphabets[i] == findable)
                return i;
        }
        return -1;
    }
    
    // encryption magic happens here !!!
    public static String encryptText(char[] message) {
        int key = 0;
        for (int i = 0; i < message.length; i++) {
            key = (calculateCenter()) % 63;
            int numChar = findCharacter(message[i]);
            message[i] = alphabets[((numChar + key) % 63)];
        }
        return String.valueOf(message);
    }
    
    //decryption sorcery happen here !!!
    public static String decryptText(char[] message) {
        int key = 0;
        setVertex();
        for (int i = 0; i < message.length; i++) {
            key = (calculateCenter()) % 63;
            int numChar = findCharacter(message[i]);
            message[i] = alphabets[(((numChar - key) + 63) % 63)];
        }
        return String.valueOf(message);
    }

    /*
    // used to print vertex for debugging purpose.
    public static void printVertex() {
        System.out.print("\nVertex:");
        for (int i = 0; i < 3; i++) {
            System.out.print(vertex[i][0] + "," + vertex[i][1] + " ");
        }
    }
    */

    // initial process on initializing the vertex values.
    public static void setVertex() {
        int[] publicKey = { 101,103 };
        int[] privateKey1 = { 107,109 };
        int[] privateKey2 = { 113,127 };

        increaseTriangle = false;
        counter = 0;

        vertex[0] = publicKey;
        vertex[1] = privateKey1;
        vertex[2] = privateKey2;
    }

    // this function finds new vertex coordinates using the existing vertex coordinates as midpoints of sides of the triangle.
    public static void midpointsToVertex() {
        int[] currentX = {0, 0}, currentY = {0, 0}, currentZ = {0, 0};

        for(int i=0 ; i<2 ; i++)
        {
            currentX[i] = (vertex[0][i] + vertex[1][i]) /2;
            currentY[i] = (vertex[1][i] + vertex[2][i]) /2;
            currentZ[i] = (vertex[2][0] + vertex[0][i]) /2;
        }
        vertex[0] = currentX;
        vertex[1] = currentY;
        vertex[2] = currentZ;
    }
    
    // this function finds new vertex coordinates using the midpoints of sides of the triangle by existing vertex coordinates.
    public static void vertexToMidpoints() {
        int[] currentX = {0, 0}, currentY = {0, 0}, currentZ = {0, 0};
        
        for(int i=0 ; i<2 ; i++)
        {
            currentX[i] = vertex[x][i] + vertex[y][i] - vertex[z][i];
            currentY[i] = vertex[x][i] - vertex[y][i] + vertex[z][i];
            currentZ[i] = vertex[y][i] - vertex[z][i] + vertex[x][i];    
        }
        vertex[0] = currentX;
        vertex[1] = currentY;
        vertex[2] = currentZ;
    }

    // This calculates the centroid of the triangle and also maintains the growth and shrink of the triangle
    public static int calculateCenter() {
        int[] centroid = {0, 0};
        if(counter%5 == 0){
            increaseTriangle = !increaseTriangle;
            counter = 0;
        }
        counter++;

        if(increaseTriangle)
            midpointsToVertex();
        else
            vertexToMidpoints();
        
        centroid[0] = (vertex[0][0] + vertex[1][0] + vertex[2][0]) / 3;
        centroid[1] = (vertex[0][1] + vertex[1][1] + vertex[2][1]) / 3;
        int center = centroid[0] + centroid[1];
        return center;
    }
}
