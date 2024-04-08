package model;


public class Patient {
	
	private int code;
	private String name;
	private int age;
	private String sickness;
	private String level;
	private String report;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSickness() {
		return sickness;
	}
	public void setSickness(String sickness) {
		this.sickness = sickness;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
	
	public Patient() {
		super();
	}
	public Patient(int code, String name, int age, String sickness, String level, String report) {
		super();
		this.code = code;
		this.name = name;
		this.age = age;
		this.sickness = sickness;
		this.level = level;
		this.report = report;
	}
	@Override
	public String toString() {
		return "Patient [code=" + code + ", name=" + name + ", age=" + age + ", sickness=" + sickness + ", level="
				+ level + ", report=" + report + "]";
	}
	
	
	
	
	
}
