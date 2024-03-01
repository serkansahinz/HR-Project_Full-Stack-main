import axios from "axios";
import { loginUrl } from "./apiUrl";
import jwt_decode from "jwt-decode";
export const getUserRoleFromBackend = async (email, password) => {
  try {
    const response = await axios.post(loginUrl, {
      email: email,
      password: password,
    });
    const decodedToken = jwt_decode(response.data);
    const role = decodedToken.role;
    if (role) {
      return role;
    } else {
      throw new Error("Geçersiz kullanıcı adı veya şifre");
    }
  } catch (error) {
    return error.response.data.message;
  }
};

export const getTokenFromBackend = async (email, password) => {
  try {
    const response = await axios.post(loginUrl, {
      email: email,
      password: password,
    });
    return response.data;
  } catch (error) {
    return error.response.data;
  }
};
