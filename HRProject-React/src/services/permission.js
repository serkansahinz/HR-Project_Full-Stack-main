import axios from "axios";
import { permissionUrl } from "./apiUrl";

export const getRestPermissionsByUserId = async (id, gender) => {
  try {
    const response = await axios.get(
      `${permissionUrl}/find_remaining_permissions_by_user_id/${id}?gender=${gender}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
      }
    );
    return response.data;
  } catch (error) {}
};

export const createPermission = async (permission) => {
  try {
    const response = await axios.post(
      `${permissionUrl}/create_permission`,
      permission,
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

export const activatePermission = async (id) => {
  try {
    const response = await axios.post(
      `${permissionUrl}/activate_status/${id}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("userToken")}`,
        },
      }
    );
    return response.data;
  } catch (error) {
    return error.response.data.message;
  }
};

export const getPendingPermissions = async (companyId) => {
  const response = await axios.get(
    `${permissionUrl}/find_pending_permissions_by_company_id/${companyId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};
export const getAllPermissionsByUserId = async (userId) => {
  const response = await axios.get(
    `${permissionUrl}/find_permissions_by_user_id/${userId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};

export const deletePermission = async (permissinonId) => {
  const response = await axios.delete(
    `${permissionUrl}/delete_by_id/${permissinonId}`,
    {
      headers: { Authorization: `Bearer ${localStorage.getItem("userToken")}` },
    }
  );
  return response.data;
};
