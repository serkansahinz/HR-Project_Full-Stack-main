import axios from "axios";

export const getPublicHoliday = async () => {
  const response = await axios.get(
    `https://script.googleusercontent.com/macros/echo?user_content_key=vTzHCWW0WLFDb-y1CTe5i2co7Wc5AAwAd91UPjXLy44mRVRe9yn8PjCakF9VK9IpxOlbVhVYuAUIKznioGIZSt5BKdwtrJ1Cm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBJmqZ7aCXgr_T9raHPdKD6FzJhOrtBk09Ompd0XWIbEdnRLihd5L-SribzJCTkISQa9gIUwtEVBJ4l_9wfuv83zfNqxja1jVw&lib=MuYsjupAABHnb8YXhYhaAABxIoJWoTElh`
  );
  return response.data;
};
