import React, { useContext, useEffect, useState } from "react";
import { getEmployee } from "../../../services/employee";
import { Card, Divider, Space } from "antd";
import "./index.scss";
import jwt_decode from "jwt-decode";

import { MailOutlined, PhoneOutlined } from "@ant-design/icons";
import UserContext from "../../../contexts/UserContext";

const EmployeeDashboard = () => {
  const [employee, setEmployee] = useState({
    id: 4,
    name: "Bob Williams",
    status: "Aktif",
    department: "Pazarlama",
    employeeId: "EMP004",
    email: "bobwilliams@email.com",
    phone: "111-222-3333",
    photo: "bob.jpg",
  });
  const { user } = useContext(UserContext);
  useEffect(() => {
    const fetchData = async () => {
      const storedToken = localStorage.getItem("userToken");
      if (storedToken) {
        const decodedToken = jwt_decode(storedToken);
        try {
          const employeeData = await getEmployee(decodedToken.myId);
          setEmployee(employeeData);
        } catch (error) {
          console.error("Hata oluştu: ", error);
        }
      }
    };

    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [user]);

  return (
    <Space className="employee" direction="vertical">
      <Card
        key={employee.id}
        style={{
          width: "100%",
          marginBottom: 16,
          borderRadius: 10,
          boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
        }}
      >
        <div
          style={{
            width: "300px", // Avatar genişliği
            height: "300px", // Avatar yüksekliği
            borderRadius: "50%", // Yuvarlak avatar için border yarı çapı
            overflow: "hidden", // Taşma durumunda gizleme
            margin: "0 auto", // Dikey hizalama
          }}
        >
          <img
            style={{
              width: "100%", // Görüntünün tamamen dolmasını sağlar
              height: "100%", // Görüntünün tamamen dolmasını sağlar
              objectFit: "cover", // Taşma durumunda avatarı kesecek şekilde ayarlama
            }}
            src={employee.avatar}
            alt="Avatar"
          />
        </div>
        <Divider />
        <h2
          style={{
            color: "#1890ff",
            textAlign: "center",
            margin: "10px 0",
          }}
        >
          {employee.name} {employee.surname}
        </h2>
        <Divider />

        <div style={{ color: "#1890ff", margin: "10px 0" }}>
          <div
            style={{
              display: "flex",
              justifyContent: "flex-start",
              gap: "10px",
            }}
          >
            <span>Şehir :</span>
            <span>{employee.province}</span>
          </div>
          <div>
            <span>Adres :</span>
            <span> {employee.street}</span>
          </div>
          <div
            style={{
              display: "flex",
              justifyContent: "flex-start",
              gap: "10px",
            }}
          ></div>
        </div>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            marginTop: 16,
          }}
        >
          <div>
            <MailOutlined /> {employee.email}
          </div>
          <div>
            <PhoneOutlined /> {employee.phone}
          </div>
        </div>
      </Card>
    </Space>
  );
};

export default EmployeeDashboard;
