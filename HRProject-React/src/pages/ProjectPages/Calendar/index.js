import React, { useContext, useEffect, useState } from "react";
import { Calendar } from "antd";
import { getPublicHoliday } from "../../../services/holiday";
import UserContext from "../../../contexts/UserContext";

const CalendarProject = () => {
  const [publicHoliday, setPublicHoliday] = useState([]);

  const { user } = useContext(UserContext);

  const dateCellRender = (date) => {
    const formattedDate = date.format("DD.MM.YYYY");

    const holidayInfo = publicHoliday.filter(
      (holiday) => holiday.localeDateString === formattedDate
    );

    if (holidayInfo.length > 0) {
      return (
        <div style={{ background: "red", color: "white" }}>
          <strong>{holidayInfo[0].title}</strong>
        </div>
      );
    }

    return <div></div>;
  };

  useEffect(() => {
    getPublicHoliday().then((response) => {
      setPublicHoliday(response);
    });
  }, [user]);

  return (
    <div>
      <Calendar cellRender={dateCellRender} />
    </div>
  );
};

export default CalendarProject;
