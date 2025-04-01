package Model;

public class OfficeEmployeeModel {
	private int employeeId;
	private String employeeName;
	private String position;
    private String department;
    private String salary;
    
    public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public OfficeEmployeeModel(String employeeName, String position, String department, String salary) {
		
		this.employeeName = employeeName;
		this.position = position;
		this.department = department;
		this.salary = salary;
	}
public OfficeEmployeeModel() {
		
		this.employeeName = "";
		this.position = "";
		this.department = "";
		this.salary = "";
	}
}