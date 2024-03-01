import { Button, Form, Modal, Table } from "antd";
import React, { useContext, useEffect, useState } from "react";
import {
  createPermission,
  getAllPermissionsByUserId,
  getRestPermissionsByUserId,
} from "../../../services/permission";
import UserContext from "../../../contexts/UserContext";
import PermissionCreate from "./createForm";

const EmployeePermission = () => {
  const { user } = useContext(UserContext);
  const [restPermissions, setRestPermissions] = useState([]);
  const [allPermissionsByUser, setAllPermissionsByUser] = useState({});
  const [isReqPermissionVisible, setIsReqPermissionVisible] = useState(false);
  const [permissionForm] = Form.useForm();
  const dateFormat = "YYYY-MM-DD";
  const allPermissionArray = Object.values(allPermissionsByUser);

  const [isErrorMessage, setIsErrorMessage] = useState("");

  useEffect(() => {
    getRestPermissionsByUserId(user.id, user.gender).then((response) => {
      setRestPermissions(response);
    });
  }, [user, isReqPermissionVisible]);

  useEffect(() => {
    getAllPermissionsByUserId(user.id).then((response) =>
      setAllPermissionsByUser(response)
    );
  }, [user, isReqPermissionVisible]);

  const openReqPermissionModel = () => {
    setIsReqPermissionVisible(true);
  };
  const closeReqPermissionModel = () => {
    setIsReqPermissionVisible(false);
  };
  const onFinishReqPermission = (values) => {
    const formattedValues = {
      ...values,
      startDate: values.startDate.format(dateFormat),
      endDate: values.endDate.format(dateFormat),
    };
    console.log("Success:", formattedValues);
    createPermission({
      ...formattedValues,
      gender: user.gender,
      companyId: user.companyId,
      userId: user.id,
      name: user.name,
      surname: user.surname,
    }).then((response) => {
      if (typeof response === "string") {
        setIsErrorMessage();
        setIsReqPermissionVisible(false);
        permissionForm.resetFields();
      } else {
        setIsErrorMessage(response.message);
      }
    });
  };
  const columns = [
    {
      title: "İzin Tipi",
      dataIndex: "type",
    },

    {
      title: "Kalan Gün",
      dataIndex: "remainingDays",
    },
  ];

  const allPermissionsColumns = [
    {
      title: "İzin Tipi",
      dataIndex: "type",
      key: "tp1",
    },
    {
      title: "Başlangıç Tarihi",
      dataIndex: "startDate",
      key: "tp2",
    },

    {
      title: "Bitiş Tarihi",
      dataIndex: "endDate",
      key: "tp3",
    },
    {
      title: "Onay Durumu",
      dataIndex: "status",
      key: "tp4",
    },
    {
      title: "Cevaplanma Tarihi",
      dataIndex: "updatedDate",
      key: "tp5",
    },
  ];

  return (
    <div
      style={{
        width: "100%",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <h2>Kalan İzin Listesi</h2>
      <Table
        style={{ width: "100%" }}
        columns={columns}
        dataSource={restPermissions}
      />
      <h2>Geçmiş İzin Listesi</h2>
      <Table
        style={{ width: "100%" }}
        columns={allPermissionsColumns}
        dataSource={allPermissionArray}
      />

      <Button style={{ width: "80%" }} onClick={openReqPermissionModel} block>
        İzin Talebi Oluştur
      </Button>

      <Modal
        title="İzin Talep Formu"
        open={isReqPermissionVisible}
        onOk={closeReqPermissionModel}
        onCancel={closeReqPermissionModel}
        cancelButtonProps={{ style: { display: "none" } }}
        okButtonProps={{ style: { display: "none" } }}
      >
        <PermissionCreate
          onFinish={onFinishReqPermission}
          form={permissionForm}
          gender={user.gender}
          isErrorMessage={isErrorMessage}
        ></PermissionCreate>
      </Modal>
    </div>
  );
};

export default EmployeePermission;
