import { Button, Form, Modal, Table } from "antd";
import React, { useContext, useEffect, useState } from "react";
import UserContext from "../../../contexts/UserContext";
import PermissionCreate from "./createForm";
import { createAdvance, getAdvancesByUserId } from "../../../services/advance";

const EmployeeAdvance = () => {
  const { user } = useContext(UserContext);

  const [isAdvanceVisible, setIsAdvanceVisible] = useState(false);
  const [advanceForm] = Form.useForm();

  const [advances, setAdvances] = useState([]);

  const [isErrorMessage, setIsErrorMessage] = useState("");

  const openAdvanceModel = () => {
    setIsAdvanceVisible(true);
  };
  const closeAdvanceModel = () => {
    setIsAdvanceVisible(false);
  };

  useEffect(() => {
    getAdvancesByUserId(user.id).then((response) => {
      setAdvances(response);
    });
  }, [user, isAdvanceVisible]);

  const onFinishAdvance = (values) => {
    createAdvance({
      ...values,
      companyId: user.companyId,
      userId: user.id,
      name: user.name,
      surname: user.surname,
    }).then((response) => {
      if (typeof response === "string") {
        setIsErrorMessage();
        setIsAdvanceVisible(false);
        advanceForm.resetFields();
      } else {
        setIsErrorMessage(response.message);
      }
    });
  };

  const advancesColumns = [
    {
      title: "Avans Miktarı",
      dataIndex: "amount",
      key: "ta1",
    },
    {
      title: "Onay Durumu",
      dataIndex: "status",
      key: "ta2",
    },
    {
      title: "Cevaplanma Tarihi",
      dataIndex: "updatedDate",
      key: "ta3",
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
      <h2>Avans Talep Listesi</h2>
      <Table
        style={{ width: "100%" }}
        columns={advancesColumns}
        dataSource={advances}
      />

      <Button style={{ width: "80%" }} onClick={openAdvanceModel} block>
        Avans Talebi Oluştur
      </Button>

      <Modal
        title="Avans Talep Formu"
        open={isAdvanceVisible}
        onOk={closeAdvanceModel}
        onCancel={closeAdvanceModel}
        cancelButtonProps={{ style: { display: "none" } }}
        okButtonProps={{ style: { display: "none" } }}
      >
        <PermissionCreate
          onFinish={onFinishAdvance}
          form={advanceForm}
          isErrorMessage={isErrorMessage}
        ></PermissionCreate>
      </Modal>
    </div>
  );
};

export default EmployeeAdvance;
