import React, { useContext, useEffect, useState } from "react";
import { Button, Space, Table } from "antd";
import { CheckOutlined, DeleteOutlined } from "@ant-design/icons";
import UserContext from "../../../contexts/UserContext";
import {
  activatePermission,
  deletePermission,
  getPendingPermissions,
} from "../../../services/permission";

const LeavePage = () => {
  const [permissions, setPermissions] = useState([]);
  const [permissionsLength, setPermissionsLength] = useState(null);
  const [isErrorMessage, setIsErrorMessage] = useState("");
  const { user } = useContext(UserContext);

  const onDelete = (value) => {
    deletePermission(value.id).then(() => {
      setPermissions((prevState) => prevState.filter((t) => t.id !== value.id));
      setPermissionsLength(permissions.length);
    });
  };

  const onSubmit = (value) => {
    activatePermission(value.id).then((response) => {
      if (typeof response === "string") {
        setIsErrorMessage(response);
        setPermissionsLength(permissions.length - 1);
      } else {
        setIsErrorMessage();
        setPermissionsLength(permissions.length - 1);
      }
    });
  };

  const columns = [
    {
      title: "Personel",
      dataIndex: "fullName",
      key: "t1",
    },
    {
      title: "İzin Türü",
      dataIndex: "type",
      key: "t2",
    },
    {
      title: "İzin Başlangıç",
      dataIndex: "startDate",
      key: "t3",
    },
    {
      title: "İzin Bitiş",
      dataIndex: "endDate",
      key: "t4",
    },
    {
      title: "Action",
      dataIndex: "id",
      key: "t5",
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
    getPendingPermissions(user.companyId).then((response) => {
      setPermissions(response);
    });
  }, [isErrorMessage, permissionsLength]);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Personel İzin Talepleri</h1>
      <Table columns={columns} dataSource={permissions} />
      {isErrorMessage && (
        <div style={{ display: "flex", flexWrap: "nowrap", color: "red" }}>
          <div className="ant-form-item-explain-error" role="alert">
            <div className="ant-form-item-explain-error">{isErrorMessage}</div>
          </div>
        </div>
      )}
    </div>
  );
};

export default LeavePage;
