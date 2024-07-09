package sample.db;

public class Category extends DBimport {
    private int categoryID;
    private String categoryName;

    private static int categoryIDCounter = 10;

    public Category(int categoryID, String categoryName) {
        if (categoryID== -1) {
            this.categoryID = categoryIDCounter++;
        } else {
            this.categoryID = categoryID;
        }
        this.categoryName = categoryName;
    }
    public Category(){ }
    public void addNewCatogoryToDB()
    {
        try {
            insertNewCategory(categoryID,categoryName);
            System.out.println("add successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void newCategory()
    {
        try {
            insertNewCategory(this.categoryID, this.categoryName);
            System.out.println("add successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Given category id and returns the object of the id
    protected Category getCategoryByID(int categoryID) {
        Category c = exportSpesificCategory(categoryID);
        return c;
    }
    protected Category[] getCategories(int categoryID) {
        return exportCategory();
    }
    public int catgtoryNameToCategoryID(String categoryName) {
        Category category = getCategoryByName(categoryName);
        if (category == null) {
            return -1;
        }
        return category.getCategoryID();
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
