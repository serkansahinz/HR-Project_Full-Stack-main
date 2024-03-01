import React, { useEffect, useState } from "react";
import {
  deleteComment,
  getPendingComments,
  setCommentStatusActive,
} from "../../../services/comment";
import { Button, Space, Table } from "antd";
import "./index.scss";
import { CheckCircleOutlined, DeleteOutlined } from "@ant-design/icons";

const Comments = () => {
  const [comments, setComments] = useState([]);

  const approveComment = (id) => {
    setCommentStatusActive(id).then(() => {
      setComments((prevState) => prevState.filter((t) => t.id !== id));
    });
  };

  const rejectComment = (id) => {
    deleteComment(id).then(() => {
      setComments((prevState) => prevState.filter((t) => t.id !== id));
    });
  };

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
    {
      title: "Action",
      dataIndex: "id",
      key: "t4",
      width: "100px",
      render: (cell, row) => (
        <Space>
          <Button
            type="primary"
            shape="circle"
            icon={<CheckCircleOutlined />}
            onClick={() => approveComment(row.id)}
            size="small"
          />
          <Button
            type="primary"
            shape="circle"
            icon={<DeleteOutlined />}
            size="small"
            onClick={() => rejectComment(row.id)}
            danger
          />
        </Space>
      ),
    },
  ];

  useEffect(() => {
    getPendingComments().then((response) => {
      setComments(response);
    });
  }, []);

  return (
    <Space className="comments" direction="vertical">
      <Table dataSource={comments} columns={columns} />
    </Space>
  );
};

export default Comments;
