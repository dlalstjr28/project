package web;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import database.RecipeDB;
//import database.ShuttleDB;

public class PageParse{
	public static void parsing() throws IOException {
		Document doc = Jsoup.connect("http://sejong.korea.ac.kr/campuslife/facilities/shuttle_bus").get();	
		Elements tables = doc.select("table tbody");
		Element table = tables.get(0);
		Elements trs = table.select("tr");
		
		ShuttleTable tk = new ShuttleTable();
		for(Element tr : trs) {
			Elements tds = tr.select("td");
			tk.add(new Row(tds.eq(0).text(), tds.eq(1).text(), tds.eq(2).text()));
		} //파싱한 데이터를 arraylist에 옮기는 코드
		
		tk.insertDataBase(); 
	}
	
	public static void parseRecipe() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\데이터계산과학전공\\\\Desktop\\\\chromedriver_win32\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // 대기시간 지정 (최대 시간 설정, 시간의 단위 설정) 한번만 선언하면 모든 명령어에 대해 결과값을 받을 때까지 최대 설정 시간까지만 기다린다.
	    Recipe r = new Recipe();
	    RecipeDB rdb = new RecipeDB();
	    
	    driver.get("http://haemukja.com/recipes?utf8=%E2%9C%93&sort=rlv&name=%EC%B4%88%EA%B0%84%EB%8B%A8%EC%9A%94%EB%A6%AC");
	  
	    List<WebElement> recipelist = driver.findElements(By.cssSelector("#content > section > div.recipes > div > ul > li"));
	    
		for(WebElement recipes : recipelist) {
			recipes.click();
			
			WebElement title = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_info > div > div.top > h1 > strong"));
			try {
				WebElement site = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_info > div > div.top > div.user > a"));
				r.setSite(site.getText());
			}catch (Exception e) {
				// TODO: handle exception
			}
			WebElement recipe = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_detail > section.sec_rcp_step > ol"));
			WebElement bloger = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_info > div > div.top > div.user > strong > a"));
			WebElement scrab = driver.findElement(By.cssSelector("#scrap-cnt"));
			WebElement time = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_info > div > div.top > dl > dd:nth-child(2)"));
			WebElement ingredient = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_info > div > div.btm"));
			WebElement tag = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > section.sec_detail > div.box_tag"));	
		
			r.setRecipe(recipe.getText());
			r.setBloger(bloger.getText());
			r.setIngredient(ingredient.getText());
			r.setScrab(scrab.getText());
			r.setTag(tag.getText());
			r.setTitle(title.getText());
			r.setTime(time.getText());
			
			rdb.insert(r);
			
			WebElement element = driver.findElement(By.cssSelector("#modal-content > div > div.view_recipe > button.btn_ly_close"));
			element.click();
			
			r.setRecipe(null);
			r.setSite(null);
			r.setBloger(null);
			r.setIngredient(null);
			r.setScrab(null);
			r.setTag(null);
			r.setTitle(null);
			r.setTime(null);
		}
		
		System.out.println(rdb.sellectAll().toString());
	}
	
	public static void main(String[] args) throws IOException {
		/*ShuttleDB db = new ShuttleDB();
		ShuttleDB.createNewDataBase("shuttle.sqlite");
		ShuttleDB.createNewTable();
		parsing();
		db.getShuttleTable().show();*/
		
		RecipeDB.createNewDataBase("indust_math.sqlite");
		RecipeDB.createNewTable();
		parseRecipe();
	}
}