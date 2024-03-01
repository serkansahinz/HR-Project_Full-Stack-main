import axios from "axios";
import { companyUrl } from "./apiUrl";

export const getCompanies = async () => {
  const response = await axios.get(`${companyUrl}/pending_companies`, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};

export const getActiveCompanies = async () => {
  const response = await axios.get(`${companyUrl}/find_all_active_companies`);
  return response.data;
};

export const activateCompany = async (value) => {
  try {
    const response = await axios.get(`${companyUrl}/activate_status/${value}`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    });
    return response.data;
  } catch (error) {
    return error.response.data.message;
  }
};

export const getCompanyByCompanyId = async (companyId) => {
  const response = await axios.get(
    `${companyUrl}/find_company_by_id/${companyId}`
  );
  return response.data;
};

export const getCompanyCount = async () => {
  const response = await axios.get(companyUrl);
  return response.data.length;
};

export const addCompanies = async (companies) => {
  const response = await axios.post(companyUrl, companies);
  return response.data;
};

export const updateCompany = async (company) => {
  const response = await axios.put(
    `${companyUrl}/update_company/${company.id}`,
    company,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const deleteCompanies = async (id) => {
  try {
    const response = await axios.delete(`${companyUrl}/delete_by_id/${id}`, {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    });
    return response.data;
  } catch (error) {
    return error.response.data.message;
  }
};

export const getRemainingDaysByCompanyId = async (companyId) => {
  const response = await axios.get(
    `${companyUrl}/get_remaining_days/${companyId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};
