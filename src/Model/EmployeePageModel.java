package Model;

public class EmployeePageModel {
	private int employeeId;
	private String employeeName;
	private String Phone;
	private String Address;
	private String Email;

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

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public EmployeePageModel(int employeeId, String employeeName, String phone, String address, String email) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		Phone = phone;
		Address = address;
		Email = email;

	}

	public EmployeePageModel() {

		this.employeeId = 0;
		this.employeeName = "";
		Phone = "";
		Address = "";
		Email = "";

	}

}
