import axios from "axios";
import { authUrl, employeeUrl } from "./apiUrl";

export const getEmployee = async (id) => {
  const response = await axios.get(`${employeeUrl}/find_by_auth_id/${id}`);
  return response.data;
};

export const getEmployeesByCompanyId = async (id) => {
  const response = await axios.get(
    `${employeeUrl}/find_employee_by_company_id/${id}`
  );
  return response.data;
};

export const getEmployeeCount = async () => {
  const response = await axios.get(employeeUrl);
  return response.data.length;
};

export const addEmployee = async (employee) => {
  const response = await axios.post(`${authUrl}/create_employee`, employee, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};

export const updateEmployeeByManager = async (employee) => {
  const id = employee.id;
  const response = await axios.put(
    `${employeeUrl}/manager_update_employee/${id}`,
    employee,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const deleteEmployee = async (id) => {
  const response = await axios.delete(`${authUrl}/delete_by_id/${id}`, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};
