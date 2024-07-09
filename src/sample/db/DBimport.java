package sample.db;

import java.sql.*;

public class DBimport {
    private Statement statement;
    protected static ResultSet resultSet;
    protected static Connection connection;
    private PreparedStatement p;

    public DBimport() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
            statement = connection.createStatement();
            p = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Those delete funcion, delete data depend the pramery and forigens Keys and deleting them from the dataBase
     * */
    public void deleteUser(int id) {
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM user WHERE ID = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteReview(int reviewID, int movieID, int userID) {
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM review WHERE ReviewID = ? AND MovieID = ? AND UserID = ?;");
            st.setInt(1, reviewID);
            st.setInt(2, movieID);
            st.setInt(3, userID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteOreder(int orderID, int movieID, int userID) {
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM review WHERE OrderID = ? AND MovieID = ? AND UserID = ?;");
            st.setInt(1, orderID);
            st.setInt(2, movieID);
            st.setInt(3, userID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteMovie(int movieID, int categoryID) {
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM review WHERE MovieID = ? AND categoryID = ?;");
            st.setInt(1, movieID);
            st.setInt(2, categoryID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCategory(int categoryID) {
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM user WHERE CategoryID = ?");
            st.setInt(1, categoryID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Those insert funcion, inserts to the dataBase the data
     * */
    public void insertNewReview(int reviewID, int rating, String text, int userID, int movieID) {
        try {
            statement.executeUpdate("INSERT INTO review " + "VALUES ('" + reviewID + "','" + rating + "','" + text + "','" + userID + "','" + movieID + "')");
            System.out.println("add successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertNewUser(int id, String userName, String passward, int isAdmin) {
        try {
            statement.executeUpdate("INSERT INTO user " + "VALUES ('" + id + "','" + userName + "','" + passward + "','" + isAdmin + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add successfully");
    }
    public void insertNewOrder(int orderID, int totalPrice, String orderDate,int ID, int movieID) {
        try {
            statement.executeUpdate("INSERT INTO project.order " + "VALUES ('" + orderID   +  "','" + totalPrice + "','" + orderDate  + "','" + ID + "','" + movieID + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add successfully");
    }
    public void insertNewMovie(int movieID, String movieTitle, String description, String releaseDate,
                               String duration, int categoryID, int isAvailable, int price) {
        try {
            statement.executeUpdate("INSERT INTO movie " + "VALUES ('" + movieID + "','" + movieTitle + "','" + description + "'," +
                    "'" + releaseDate + "','" + duration + "','" + categoryID + "','" + isAvailable + "','" + price + "')");
            System.out.println("add successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertNewCategory(int categoryID, String categoryName) {
        try {
            statement.executeUpdate("INSERT INTO category " + "VALUES ('" + categoryID + "','" + categoryName+ "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add successfully");
    }

    /*
     *The next export funcion export from the dataBase the data
     * */
    public Order[] exportOrders() {
        Order[] orders = null;
        try {
            //insert the data into resultSet object
            resultSet = statement.executeQuery("select * from project.order");
            //print from the resultSet object to the app
            orders = new Order[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                orders[resultSet.getRow()-1] = new Order(resultSet.getInt("orderID"), resultSet.getInt("totalPrice"),
                        resultSet.getString("orderDate"), resultSet.getInt("ID"),
                        resultSet.getInt("movieID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public Order[] exportSpesificOrder(int orderID) {
        Order[] orders = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM project.order WHERE orderID = ?");//
            statement.setInt(1, orderID); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            orders = new Order[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                // add order to the array
                if (resultSet.getInt("orderID") == orderID) {
                    orders[resultSet.getRow()-1] = new Order(resultSet.getInt("orderID"), resultSet.getInt("totalPrice"),
                            resultSet.getString("orderDate"), resultSet.getInt("ID"),
                            resultSet.getInt("movieID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public Review[] exportReview() {
        Review[] reviews = null;
        try {
            //insert the data into resultSet object
            resultSet = statement.executeQuery("select * from review");

            reviews = new Review[resultSet.getFetchSize()+1];
            //print from the resultSet object to the app
            while (resultSet.next()) {
                reviews[resultSet.getRow()-1] = new Review(resultSet.getInt("reviewID"), resultSet.getInt("MovieID"),
                        resultSet.getString("Text"),resultSet.getInt("Rating"),
                        resultSet.getInt("UserID"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
    public Review[] exportSpesificReview(int movieID) {
        Review[] reviews = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM review WHERE MovieID = ?");//
            statement.setInt(1, movieID); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            reviews = new Review[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                // add movie to the array is the movie available
                if (resultSet.getInt("MovieID") == movieID) {
                    reviews[resultSet.getRow()-1] = new Review(resultSet.getInt("ReviewID"), resultSet.getInt("Rating"),
                            resultSet.getString("Text"), resultSet.getInt("UserID"),
                            resultSet.getInt("MovieID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
    public Movie[] OLDexportMovie() {
        Movie[] movies = null;
        try {
            resultSet = statement.executeQuery("select * from movie");


            movies = new Movie[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                if (resultSet.getInt("IsAvailable") == 1) {
                    movies[resultSet.getRow()-1] = resultSetToMovie(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
    public Movie[] exportMovie() {
        Movie[] movies = null;
        try {
            resultSet = statement.executeQuery("select * from movie");


            movies = new Movie[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                movies[resultSet.getRow()-1] =resultSetToMovie(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
    public Movie[] exportMovieByCategory(String categoryName) {
        Movie[] movies = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie WHERE CategoryName = ?");//
            statement.setString(1, categoryName); // Set the value for the first placeholder to the value of the userName variable
            resultSet = statement.executeQuery();
            movies = new Movie[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                // add movie to the array is the movie available
                if (resultSet.getString("CategoryName").equals(categoryName)) {
                    movies[resultSet.getRow()-1] = resultSetToMovie(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
    public User[] exportUser() {
        User[] users = null;
        try {
            resultSet = statement.executeQuery("select * from user");

            users = new User[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                users[resultSet.getRow()-1] =new User(resultSet.getInt("ID"),resultSet.getInt("isAdmin"),
                        resultSet.getString("username"),resultSet.getString("passward"));            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public User exportSpesificUser(String userName, String password) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, userName); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("passward").equals(password)) {
                     user = new User(resultSet.getInt("ID"),resultSet.getInt("isAdmin"),
                            resultSet.getString("username"),resultSet.getString("passward"));
                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return null;
    }
    public boolean checkUserExist(String userName, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, userName); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getString("passward").equals(password))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Category[] exportCategory() {
        Category[] category = null;
        try {
            //Export all category
            resultSet = statement.executeQuery("select * from category");

            category = new Category[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                category[resultSet.getRow()-1] =new Category(resultSet.getInt("CategoryID"),resultSet.getString("CategoryName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
    public Category exportSpesificCategory(int categoryID) {
        Category category = null;
        try {
            //Exporting the data by movieID
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE CategoryID = ?");
            statement.setInt(1, categoryID); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            resultSet.next();
            category = new Category(resultSet.getInt("CategoryID"),resultSet.getString("CategoryName"));
            return category;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Category getCategoryByName(String categoryName) {
        Category category = null;
        try {
            //Exporting the data by movieID
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE CategoryName = ?");
            statement.setString(1, categoryName); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
//            resultSet.next();
            if( resultSet.next())
                category = new Category(resultSet.getInt("CategoryID"),resultSet.getString("CategoryName"));
            return category;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /*
     *when the app closed we have to use this function.
     * it's closing the conection with the DB
     */
    public void closeProcess() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Movie resultSetToMovie(ResultSet resultSet) throws SQLException {
        return new Movie(resultSet.getInt("MovieID"), resultSet.getString("MovieTitle"),
                resultSet.getString("Description"), resultSet.getString("ReleaseDate"),
                resultSet.getString("Duration"), resultSet.getInt("CategoryID"),
                resultSet.getInt("IsAvailable"), resultSet.getInt("Price"));
    }
    public Movie[] exportMovieByTitle(String title) {
        Movie[] movies = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie WHERE MovieTitle = ?");//
            statement.setString(1, title); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            movies = new Movie[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                // add movie to the array is the movie available
                if (resultSet.getString("MovieTitle").equals(title)) {
                    movies[resultSet.getRow()-1] = resultSetToMovie(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
    public Movie[] exportMovieByDuration(String duration) {
        Movie[] movies = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie WHERE Duration = ?");//
            statement.setString(1, duration); // Set the value for the first placeholder to the value of the userName variable

            resultSet = statement.executeQuery();
            movies = new Movie[resultSet.getFetchSize()+1];
            while (resultSet.next()) {
                // add movie to the array is the movie available
                if (resultSet.getString("Duration").equals(duration)) {
                    movies[resultSet.getRow()-1] =resultSetToMovie(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }


}
