import React, { useEffect, useState } from "react";
import {
  deleteCompanies,
  getCompanies,
  activateCompany,
} from "../../../services/company";
import { Button, Space, Table } from "antd";
import "./index.scss";
import { CheckOutlined, DeleteOutlined } from "@ant-design/icons";

const Companies = () => {
  const [companies, setCompanies] = useState([]);

  const onDelete = (value) => {
    deleteCompanies(value.id).then((response) => {
      if (typeof response === "string") {
        alert(response);
      } else {
        setCompanies((prevState) => prevState.filter((t) => t.id !== value.id));
      }
    });
  };
  const onSubmit = (value) => {
    activateCompany(value.token).then((response) => {
      if (typeof response === "string") {
        alert(response);
      } else {
        getCompanies().then((response) => {
          setCompanies(response);
        });
      }
    });
  };

  const columns = [
    {
      title: "Company Tax Number",
      dataIndex: "companyTaxNumber",
      key: "t1",
    },
    {
      title: "Company Name",
      dataIndex: "companyName",
      key: "t2",
    },
    {
      title: "Action",
      dataIndex: "id",
      key: "t3",
      width: "100px",
      render: (cell, row) => (
        <Space>
          <Button
            type="primary"
            shape="circle"
            icon={<CheckOutlined />}
            size="small"
            onClick={() => onSubmit(row)}
          />
          <Button
            type="primary"
            shape="circle"
            icon={<DeleteOutlined />}
            size="small"
            onClick={() => onDelete(row)}
            danger
          />
        </Space>
      ),
    },
  ];

  useEffect(() => {
    getCompanies().then((response) => {
      setCompanies(response);
    });
  }, []);

  return (
    <Space className="companies" direction="vertical">
      <Table dataSource={companies} columns={columns} />
    </Space>
  );
};

export default Companies;
