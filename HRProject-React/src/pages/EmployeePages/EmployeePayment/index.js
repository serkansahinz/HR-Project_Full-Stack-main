import { Button, Card, Form, Modal, Space, Table, Upload, message } from "antd";
import React, { useContext, useEffect, useState } from "react";
import UserContext from "../../../contexts/UserContext";
import PermissionCreate from "./createForm";
import {
  addPaymentEmployee,
  getPaymentsByUserId,
  uploadFile,
} from "../../../services/payment";
import { UploadOutlined } from "@ant-design/icons";

const EmployeePayment = () => {
  const { user } = useContext(UserContext);
  const [isPaymentVisible, setIsPaymentVisible] = useState(false);
  const [isAddFileVisible, setIsAddFileVisible] = useState(false);
  const [paymentId, setPaymentId] = useState();
  const [paymentForm] = Form.useForm();
  const [payments, setPayments] = useState([]);
  const [isErrorMessage, setIsErrorMessage] = useState("");
  const [fileList, setFileList] = useState([]);

  const props = {
    customRequest: ({ file, onSuccess, onError }) => {
      const formData = new FormData();
      formData.append("file", file);

      // Dosya türüne bakmaksızın yükleme işlemi
      setTimeout(() => {
        uploadFile(formData, paymentId);
        message.success(`${file.name} dosyası başarıyla yüklendi.`);
        onSuccess();
      }, 2000);
    },
    onChange: ({ fileList: newFileList }) => {
      setFileList(newFileList);
    },
  };

  const openPaymentModel = () => {
    setIsPaymentVisible(true);
  };
  const closePaymentModel = () => {
    setIsPaymentVisible(false);
  };

  useEffect(() => {
    getPaymentsByUserId(user.id).then((response) => {
      setPayments(response);
    });
  }, [user, isPaymentVisible]);

  const onFinishPayment = (values) => {
    addPaymentEmployee({
      ...values,
      companyId: user.companyId,
      userId: user.id,
      name: user.name,
      surname: user.surname,
    }).then((response) => {
      if (typeof response === "string") {
        setIsErrorMessage();
        setIsPaymentVisible(false);
        paymentForm.resetFields();
      } else {
        setIsErrorMessage(response.message);
        setIsPaymentVisible(false);
      }
    });
  };
  const openAddFileModal = (paymentId) => {
    setIsAddFileVisible(true);
    setPaymentId(paymentId);
  };

  const closeAddFileModel = () => {
    setIsAddFileVisible(false);
    setFileList([]);
  };

  const advancesColumns = [
    {
      title: "Ödeme İsmi",
      dataIndex: "paymentName",
      key: "ta1",
    },
    {
      title: "Tutar",
      dataIndex: "amount",
      key: "ta2",
    },
    {
      title: "Para Birimi",
      dataIndex: "currency",
      key: "ta3",
    },
    {
      title: "Talep Tarihi",
      dataIndex: "createdDate",
      key: "ta4",
    },

    {
      title: "Onay Durumu",
      dataIndex: "status",
      key: "ta5",
    },
    {
      title: "Cevaplanma Tarihi",
      dataIndex: "updatedDate",
      key: "ta6",
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
            icon={<UploadOutlined />}
            onClick={() => openAddFileModal(row.id)}
            size="small"
          />
        </Space>
      ),
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
      <h2>Harcama Listesi</h2>
      <Table
        style={{ width: "100%" }}
        columns={advancesColumns}
        dataSource={payments}
      />

      <Button style={{ width: "80%" }} onClick={openPaymentModel} block>
        Harcama Talebi Oluştur
      </Button>
      <Modal
        title="Harcama Talep Formu"
        open={isPaymentVisible}
        onOk={closePaymentModel}
        onCancel={closePaymentModel}
        cancelButtonProps={{ style: { display: "none" } }}
        okButtonProps={{ style: { display: "none" } }}
      >
        <PermissionCreate
          onFinish={onFinishPayment}
          form={paymentForm}
          isErrorMessage={isErrorMessage}
        ></PermissionCreate>
      </Modal>
      <Modal
        title="Harcama Dosyası Yükle"
        open={isAddFileVisible}
        onOk={closeAddFileModel}
        onCancel={closeAddFileModel}
        cancelButtonProps={{ style: { display: "none" } }}
        okButtonProps={{ style: { display: "none" } }}
      >
        <Card>
          <Upload {...props} fileList={fileList}>
            <Button icon={<UploadOutlined />}>Upload</Button>
          </Upload>
        </Card>
      </Modal>
    </div>
  );
};

export default EmployeePayment;
