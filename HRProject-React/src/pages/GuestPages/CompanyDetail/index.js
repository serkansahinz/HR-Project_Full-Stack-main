import React, { useEffect, useState } from "react";
import { getCompanyByCompanyId } from "../../../services/company";
import { Card, Divider, Space, Table } from "antd";
import "./index.scss";

import { MailOutlined, PhoneOutlined } from "@ant-design/icons";
import { useParams } from "react-router-dom";
import { getCommentsByCompanyId } from "../../../services/comment";

const CompanyDetail = () => {
  const { id } = useParams();
  const [company, setCompany] = useState({});
  const [comments, setComments] = useState([]);

  const columns = [
    {
      title: "Yorum Yapan",
      dataIndex: "fullName",
      key: "t1",
    },
    {
      title: "Şirket",
      dataIndex: "companyName",
      key: "t2",
    },
    {
      title: "Konu",
      dataIndex: "header",
      key: "t3",
    },
    {
      title: "Yorum",
      dataIndex: "content",
      key: "t4",
    },
  ];

  useEffect(() => {
    getCompanyByCompanyId(id).then((response) => {
      setCompany(response);
    });
  }, [id]);

  useEffect(() => {
    getCommentsByCompanyId(id).then((response) => {
      setComments(response);
    });
  }, []);

  return (
    // <div>Company Detail</div>
    <Space className="company" direction="vertical">
      <Card
        key={company.id}
        style={{
          width: "100%",
          marginBottom: 16,
          borderRadius: 10,
          boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
        }}
      >
        <h2
          style={{
            color: "#1890ff",
            textAlign: "center",
            margin: "10px 0",
          }}
        >
          {company.companyName}
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
            <span>{company.province}</span>
          </div>
          <div>
            <span>Adres :</span>
            <span> {company.street}</span>
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
            <MailOutlined /> {company.companyEmail}
          </div>
          <div>
            <PhoneOutlined /> {company.companyPhone}
          </div>
        </div>
      </Card>

      <Table dataSource={comments} columns={columns} />
    </Space>
  );
};

export default CompanyDetail;
