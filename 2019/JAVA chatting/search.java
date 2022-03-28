package Project¼öÁ¤;

import java.io.IOException;
import java.util.ArrayList;
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

public class search {

	ArrayList<String> list;
	String url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=";
	void linksearch(String want) throws IOException{

		Document doc = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+want).get();	
		Elements tables = doc.select("._related_keyword_ul > li > a");
		int i=0;
		list = new ArrayList<String>();
		for(Element e : tables) {
			list.add(tables.eq(i).text());
			i++;
		}
		
	}
	ArrayList<String> getList(){
		return list;
	}

	   void open(String want){
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url+want);
	}
}
