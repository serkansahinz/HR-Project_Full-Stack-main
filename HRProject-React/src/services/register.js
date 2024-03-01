import axios from "axios";
import { registerGuestUrl, registerCompanyUrl } from "./apiUrl";

export const createUser = async (values) => {
  try {
    let response = await axios.post(registerGuestUrl, values);
    return response.data;
  } catch (error) {
    return error.response.data.message;
  }
};

export const createCompanyManager = async (values) => {
  try {
    let response = await axios.post(registerCompanyUrl, values);
    return response.data;
  } catch (error) {
    return error.response.data.message;
  }
};
