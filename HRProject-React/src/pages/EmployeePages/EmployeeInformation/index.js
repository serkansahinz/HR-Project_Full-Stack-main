import React, { useContext, useEffect, useState } from "react";
import { Card, Avatar, Divider, Calendar } from "antd";
import UserContext from "../../../contexts/UserContext";
import { getCompanyByCompanyId } from "../../../services/company";
import { getPublicHoliday } from "../../../services/holiday";

const { Meta } = Card;

const EmployeeInformation = () => {
  const [company, setCompany] = useState({});

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

    if (user.shift === "GÜNDÜZ") {
      return (
        <div>
          {user.shift} <br></br>
          Mola Bilgisi : <br></br> 12.30 - 13.30
        </div>
      );
    } else if (user.shift === "GECE") {
      return (
        <div>
          {user.shift} <br></br>
          Mola Bilgisi : <br></br> 03.30 - 04.30
        </div>
      );
    } else {
      return <div>{user.shift}</div>;
    }
  };

  useEffect(() => {
    if (user && user.companyId) {
      getCompanyByCompanyId(user.companyId).then((response) => {
        setCompany(response);
      });
    }
  }, [user]);

  useEffect(() => {
    getPublicHoliday().then((response) => {
      setPublicHoliday(response);
    });
  }, [user]);

  return (
    <Card style={{ width: "100%", margin: "auto" }}>
      <Meta
        avatar={<Avatar src="link_to_personel_fotografi" />}
        title={`${user.name} ${user.surname}`}
        description={user.companyName}
      />
      <Divider />
      <Calendar cellRender={dateCellRender} />
      <Divider />
      <p>
        <strong>Maaş:</strong> {user.salary} TL
      </p>

      <Divider />
      <p>
        <strong>İletişim Bilgileri:</strong>
        <br /> {company.companyPhone} <br />
        {company.companyMail} <br />
        {company.province} <br />
        {company.street}
      </p>
    </Card>
  );
};

export default EmployeeInformation;
