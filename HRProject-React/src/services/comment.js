import axios from "axios";
import { commentUrl } from "./apiUrl";

export const getCommentsByCompanyId = async (companyId) => {
  const response = await axios.get(
    `${commentUrl}/find_all_active_comments_by_company_id/${companyId}`
  );
  return response.data;
};
export const getPendingComments = async () => {
  const response = await axios.get(`${commentUrl}/find_all_pending_comments`, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};
export const getCommentCount = async () => {
  const response = await axios.get(commentUrl);
  return response.data.length;
};
export const addComment = async (comments, userId, companyId) => {
  const response = await axios.post(
    `${commentUrl}/create_comment/${userId}/${companyId}`,
    comments,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};
export const addComments = async (comments) => {
  const response = await axios.post(commentUrl, comments);
  return response.data;
};

export const deleteComment = async (id) => {
  const response = await axios.delete(`${commentUrl}/delete_comment/${id}`, {
    headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
  });
  return response.data;
};

export const setCommentStatusActive = async (id) => {
  const response = await axios.put(
    `${commentUrl}/activate_comment/${id}`,
    {},
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};
