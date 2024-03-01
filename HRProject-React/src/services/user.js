import axios from "axios";
import { userUrl } from "./apiUrl";

export const updateUser = async (user) => {
  const response = await axios.put(
    `${userUrl}/employee_update/${user.id}`,
    user,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("userToken")}`,
      },
    }
  );

  return response.data;
};

export const updateAvatar = async (file, userId) => {
  const response = await axios.put(
    `${userUrl}/employee_update_avatar/${userId}`,
    file
  );
  return response.data;
};
