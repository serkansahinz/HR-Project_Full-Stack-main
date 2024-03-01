import axios from "axios";
import { paymentUrl } from "./apiUrl";

export const getExpensesByCompanyId = async (companyId) => {
  const response = await axios.get(
    `${paymentUrl}/get_all_expense/${companyId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const getIncomesByCompanyId = async (companyId) => {
  const response = await axios.get(
    `${paymentUrl}/get_all_income/${companyId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const addPayment = async (payment) => {
  const response = await axios.post(
    `${paymentUrl}/create_new_payment/${payment.companyId}`,
    payment,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const addPaymentEmployee = async (payment) => {
  const response = await axios.post(
    `${paymentUrl}/employee_create_new_payment/${payment.companyId}/${payment.userId}`,
    payment,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const deletePaymentEmployee = async (id) => {
  const response = await axios.put(
    `${paymentUrl}/delete_employee_payment/${id}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const activatePaymentEmployee = async (id) => {
  const response = await axios.put(
    `${paymentUrl}/activate_status/${id}`,
    {},
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const getPaymentsByUserId = async (userId) => {
  const response = await axios.get(
    `${paymentUrl}/get_all_payments_by_user_id/${userId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const getPendingPayments = async (companyId) => {
  const response = await axios.get(
    `${paymentUrl}/get_all_pending_payment/${companyId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const deletePayment = async (id) => {
  const response = await axios.delete(`${paymentUrl}/delete_by_id/${id}`, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};

export const getChartDataByCompanyId = async (companyId) => {
  try {
    const response = await axios.get(
      `${paymentUrl}/get_calculation/${companyId}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
      }
    );
    return response.data;
  } catch (error) {
    console.log(error);
    return error.response.data.message;
  }
};

export const uploadFile = async (file, userId) => {
  const response = await axios.put(`${paymentUrl}/upload_file/${userId}`, file);
  return response.data;
};
