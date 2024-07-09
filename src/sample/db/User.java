package sample.db;

public class User extends DBimport {
    private int id;
    private String userName;
    private String passward;
    private int isAdmin;

    static int idCounter = 1;
    public User()
    {
    }
    public User(int isAdmin, String userName, String passward)
    {
        this.isAdmin = isAdmin;
        this.id = idCounter;
        this.userName = userName;
        this.passward = passward;
        idCounter++;
    }

    public User(int id, int isAdmin, String userName, String passward)
    {
        this.isAdmin = isAdmin;
        this.id = id;
        this.userName = userName;
        this.passward = passward;

    }
// Add check if user in table and return user
    protected  boolean user_login(String username, String password) {
        return checkUserExist(username, password);
    }
    //return details of user after login
    public User login(String username, String password) {
        if(checkUserExist(username, password)){
            return exportSpesificUser(password,username);
        }
        else{
            System.out.println("user not exist");
            return null;
        }
    }
    //add user working(cheked)
    public void addNewUserInDB()
    {
        try {
            insertNewUser(this.id,  this.userName,  this.passward,  this.isAdmin);
            System.out.println("add successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }


    public int isAdmin() {
        return isAdmin;
    }

    public void setAdmin(int admin) {
        isAdmin = admin;
    }


//    public boolean createNewUser(int id,String userName,String passward,int isAdmin) {
//        try {
//            insertNewUser(id, userName, passward, isAdmin);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}

