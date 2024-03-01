import React, { useEffect, useState } from "react";
import { getActiveCompanies } from "../../../services/company";
import { Space, Table } from "antd";
import "./index.scss";
import { Link } from "react-router-dom";

const Companies = () => {
  const [companies, setCompanies] = useState([]);

  const [searchTerm, setSearchTerm] = useState("");

  const filterCompanies = (term) => {
    return companies.filter((company) => {
      return company.companyName.toLowerCase().includes(term.toLowerCase());
    });
  };

  const columns = [
    {
      title: "Şirket İsmi",
      dataIndex: "companyName",
      key: "t1",
      render: (text, row) => (
        <Link to={`/guest/company/${row.id}`}>{text}</Link>
      ),
    },
    {
      title: "Şirket Adresi",
      dataIndex: "address",
      key: "t2",
      render: (text, row) => (
        <span>
          {row.province} - {row.street}
        </span>
      ),
    },
  ];

  useEffect(() => {
    getActiveCompanies().then((response) => {
      setCompanies(response);
    });
  }, []);

  const filteredCompanies = filterCompanies(searchTerm);

  return (
    <Space className="companies" direction="vertical">
      <input
        style={{
          height: "30px",
          paddingLeft: "30px",
          marginLeft: "30px",
          borderRadius: "10px",
          boxShadow: "0px 0px 4px 0px",
        }}
        type="text"
        placeholder="Şirket İsmine Göre Ara"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <Table dataSource={filteredCompanies} columns={columns} />
    </Space>
  );
};

export default Companies;
