
package dbpath;

public class db {
    
    private static String path="jdbc:derby:C:\\Users\\Pavan\\AppData\\Roaming\\NetBeans\\Derby\\Hardware",user="Hard",pass="123456Kk";

    
    public static String getPath() {
        return path;
    }

    public static void setPath(String aPath) {
        path = aPath;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the pass
     */
    public static String getPass() {
        return pass;
    }

    /**
     * @param aPass the pass to set
     */
    public static void setPass(String aPass) {
        pass = aPass;
    }
    
}
