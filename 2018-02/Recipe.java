package web;

public class Recipe {
	private String title;
	private String recipe;
	private String site;
	private String bloger;
	private String scrab;
	private String time;
	private String ingredient;
	private String tag;

	public Recipe() {}
	
	public Recipe(String title, String recipe, String site, String bloger, String scrab, String time, String ingredient,
			String tag) {
		super();
		this.title = title;
		this.recipe = recipe;
		this.site = site;
		this.bloger = bloger;
		this.scrab = scrab;
		this.time = time;
		this.ingredient = ingredient;
		this.tag = tag;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getBloger() {
		return bloger;
	}
	public void setBloger(String bloger) {
		this.bloger = bloger;
	}
	public String getScrab() {
		return scrab;
	}
	public void setScrab(String scrab) {
		this.scrab = scrab;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return getTitle() + "\n" + getSite() + "\n" + getBloger() + "\n" + getTime() + "\n" + getScrab() + "\n" + getTag() + "\n" + getIngredient() + "\n" + getRecipe();
	}
}
