package web;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassTest {
	public static void main(String[] args) {
		Recipe recipe = new Recipe();
		Class c = recipe.getClass();
		
		Field[] fields = c.getDeclaredFields();
		Method[] m = c.getMethods();
		
		for(int i = 0; i < m.length; i++) {
			System.out.print(m[i].getName() + "\n");
		}
	}
}
