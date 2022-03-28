package database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import web.Recipe;

public class RecipeDB {
	private static final String FN_TITLE = "title";
	private static final String FN_SITE = "site";
	private static final String FN_RECIPE = "recipe";
	private static final String FN_BLOGER = "bloger";
	private static final String FN_TIME = "time";
	private static final String FN_SCRAB = "scrab";
	private static final String FN_INGREDIENT = "ingredient";
	private static final String FN_TAG = "tag";
	private static final String TABLE_RECIPE = "recipe";
	static Recipe recipe = new Recipe();
	static Class c = recipe.getClass();
	private static Field[] fields = c.getDeclaredFields();
	private static Method[] methods = c.getDeclaredMethods();
	
	public static void createNewDataBase(String filename) {
		String url = "jdbc:sqlite:" + filename;
		
		try(Connection conn = DriverManager.getConnection(url)){
			if(conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The diver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void createNewTable() {
		String url = "jdbc:sqlite:indust_math.sqlite";
		
		/*String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPE + " (\n"
				+ FN_TITLE + " TEXT UNIQUE, \n"
				+ FN_SITE + " TEXT, \n"
				+ FN_RECIPE + " TEXT, \n"
				+ FN_BLOGER + " TEXT, \n"
				+ FN_TIME + " TEXT, \n"
				+ FN_SCRAB + " TEXT, \n"
				+ FN_TAG + " TEXT, \n"
				+ FN_INGREDIENT + " TEXT \n"
				+ ");";*/
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPE + " (\n";
		
		for(int i = 0; i < fields.length; i++) {
			sql += fields[i].getName() + " TEXT, \n";
		}
		
		sql = sql.substring(0, sql.length() - 3) + " \n)";
		
		try(Connection conn = DriverManager.getConnection(url);
				Statement stat = conn.createStatement()){
			stat.execute(sql);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		//db sqlite viewer를 이용하여 데이터베이스 내의 내용 볼것 뷰어는 다운받아야됨
	}
	
	private Connection connect() {// 데이터베이스와 연결 성공시 해당 객체로 표현하여 반환
	        // SQLite connection string
	        String url = "jdbc:sqlite:indust_math.sqlite";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);// 자바프로그램과 데이터베이스를 네트워크 상에서 연결해주는 메소드
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	
	public void insert(Recipe recipe) { //데이터베이스의 값들을 받는 함수
        //String sql = "INSERT OR IGNORE INTO " + TABLE_RECIPE + "(" + FN_TITLE + ", " + FN_SITE + ", " + FN_RECIPE + ", " + FN_BLOGER + ", " + FN_TIME + ", " + FN_SCRAB + ", " + FN_TAG + ", " + FN_INGREDIENT + ") VALUES(?,?,?,?,?,?,?,?)";

		String sql = "INSERT OR IGNORE INTO " + TABLE_RECIPE + "(";
		
		for(int i = 0; i < fields.length; i++) {
			sql += fields[i].getName() + ", ";
		}
		
		sql = sql.substring(0, sql.length() - 2) + ") VALUES(?,?,?,?,?,?,?,?)";
		
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {// sql구문을 실행시키는 객체(지정된 명령어만 실행가능)
        	pstmt.setString(1, recipe.getTitle());
            pstmt.setString(2, recipe.getSite());
            pstmt.setString(3, recipe.getRecipe());
            pstmt.setString(4, recipe.getBloger());
            pstmt.setString(5, recipe.getTime());
            pstmt.setString(6, recipe.getScrab());
            pstmt.setString(7, recipe.getTag());
            pstmt.setString(8, recipe.getIngredient());
           
        	/*Method m = null;
        	for(int i = 1; i <= fields.length; i++) {
				try {
					m = c.getDeclaredMethod("setString", int.class, String.class);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					m.invoke(recipe, i, recipe.getTitle());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}*/
            
            
            /*pstmt.setString(1, recipe.getTitle());
            pstmt.setString(2, recipe.getRecipe());
            pstmt.setString(3, recipe.getSite());
            pstmt.setString(4, recipe.getBloger());
            pstmt.setString(5, recipe.getScrab());
            pstmt.setString(6, recipe.getTime());
            pstmt.setString(7, recipe.getIngredient());
            pstmt.setString(8, recipe.getTag());*/
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public ArrayList<Recipe> sellectAll() {
		String sql = "SELECT " + FN_TITLE 
				+ ", " + FN_RECIPE + ", " 
				+ FN_SITE + ", " 
				+ FN_BLOGER + ", "
				+ FN_SCRAB + ", "
				+ FN_TIME + ", "
				+ FN_INGREDIENT + ", "
				+ FN_TAG + " FROM " + TABLE_RECIPE;
		
		/*String sql = "SELECT ";
		for(int i = 0; i < recipes.array.length; i++) {
			sql += recipes.array[i] + ", ";
		}
		
		sql = sql.substring(0, sql.length() - 2) + " FROM " + TABLE_RECIPE;*/
		
		ArrayList<Recipe> r = new ArrayList<>(); 
		
		try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
			while (rs.next()) { // 데이터베이스 내의 데이터가 있는 만큼 반복하시오 라는 의미이다.
				r.add(new Recipe(rs.getString(FN_TITLE), rs.getString(FN_RECIPE), rs.getString(FN_SITE), rs.getString(FN_BLOGER), rs.getString(FN_SCRAB), rs.getString(FN_TIME), rs.getString(FN_INGREDIENT), rs.getString(FN_TAG)));
				//r.add(new Recipe(recipes.array));
			}
			
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return r;
	}
}
