import { Space, Table } from "antd";
import React, { useContext, useEffect, useState } from "react";
import UserContext from "../../../contexts/UserContext";
import { getCommentsByCompanyId } from "../../../services/comment";

const CompanyComments = () => {
  const [comments, setComments] = useState([]);
  const { user } = useContext(UserContext);

  const commentColumns = [
    {
      title: "Kullanıcı İsmi",
      dataIndex: "fullName",
      key: "t1",
    },
    {
      title: "Konu",
      dataIndex: "header",
      key: "t2",
    },
    {
      title: "İçerik",
      dataIndex: "content",
      key: "t3",
    },
  ];

  useEffect(() => {
    if (user && user.companyId) {
      getCommentsByCompanyId(user.companyId).then((response) => {
        setComments(response);
      });
    }
  }, [user]);

  return (
    <Space className="companies" direction="vertical">
      <Table dataSource={comments} columns={commentColumns} />
    </Space>
  );
};

export default CompanyComments;
