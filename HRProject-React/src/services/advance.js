import axios from "axios";
import { advanceUrl } from "./apiUrl";

export const getAdvancesByUserId = async (userId) => {
  const response = await axios.get(
    `${advanceUrl}/find_all_advance_by_user_id/${userId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const createAdvance = async (advance) => {
  try {
    const response = await axios.post(
      `${advanceUrl}/create_advance/${advance.companyId}/${advance.userId}`,
      advance,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
      }
    );

    return response.data;
  } catch (error) {
    return error.response.data;
  }
};

export const deleteAdvance = async (id) => {
  const response = await axios.put(`${advanceUrl}/delete_advance_by_id/${id}`, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};

export const setAdvanceStatusActive = async (id) => {
  const response = await axios.put(
    `${advanceUrl}/activate_advance_status/${id}`,
    {},
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const getPendingAdvances = async (companyId) => {
  const response = await axios.get(
    `${advanceUrl}/find_pending_advance/${companyId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};
