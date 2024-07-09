package sample.db;

public class Order extends DBimport{
    private int orderID;
    private String date;
    private int userID;
    private int movieID;
    private int totalPrice;

    public Order(int orderID,int totalPrice, String date, int userID, int movieID) {
        this.totalPrice = totalPrice;
        this.orderID = orderID;
        this.date = date;
        this.userID = userID;
        this.movieID = movieID;
    }
    public void addNewOrderToDB()
    {
            try {
                insertNewOrder(orderID,totalPrice, date, userID, movieID);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public Order[] exportSpesificOrder(int orderID) {
        return super.exportSpesificOrder(orderID);
    }
    public Order[] exexportOrders(){
        return super.exportOrders();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

//    public int getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(int totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
