package pojo;

import java.util.List;

public class ObjectFinal {

	
	private int id;
	private category category;
	private String name;
	private  String[] photoUrls;
	private List<pojo.tags> tags;
	private String status;
	
	public String[] getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public category getCategory() {
		return category;
	}
	public void setCategory(pojo.category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<pojo.tags> getTags() {
		return tags;
	}
	public void setTags(List<pojo.tags> tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
