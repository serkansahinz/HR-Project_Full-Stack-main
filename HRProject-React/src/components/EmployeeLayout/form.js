import React, { useContext, useState } from "react";
import { Card, Input, Button, Upload, Form, Row, Col, message } from "antd";
import { PhoneOutlined, UploadOutlined } from "@ant-design/icons";
import * as LR from "@uploadcare/blocks";
import "./form.scss";
import UserContext from "../../contexts/UserContext";
import { updateAvatar } from "../../services/user";

LR.registerBlocks(LR);

const UserProfileForm = ({ onFinish, initialValues }) => {
  const [form] = Form.useForm();
  const { user } = useContext(UserContext);
  const [fileList, setFileList] = useState([]);

  const props = {
    customRequest: ({ file, onSuccess, onError }) => {
      const formData = new FormData();
      formData.append("file", file);
      setTimeout(() => {
        updateAvatar(formData, user.id);
        message.success(`${file.name} dosyası başarıyla yüklendi.`);
        onSuccess();
      }, 2000);
    },
    onChange: ({ fileList: newFileList }) => {
      setFileList(newFileList);
    },
  };

  return (
    <Card
      style={{
        width: "100%",
        marginBottom: 16,
        borderRadius: 10,
        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
      }}
    >
      <Form
        initialValues={initialValues}
        form={form}
        name="employee_form"
        onFinish={onFinish}
        autoComplete="off"
      >
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="name" label="Adı">
              <Input disabled inputMode="disabled" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="surname" label="Soyadı">
              <Input disabled inputMode="disabled" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="phone" label="Telefon">
              <Input prefix={<PhoneOutlined />} />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="department" label="Departman">
              <Input disabled inputMode="disabled" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="province" label="Şehir">
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="street" label="Sokak">
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col>
            <Form.Item name="file" label="Avatar">
              <Upload {...props} fileList={fileList}>
                <Button icon={<UploadOutlined />}>Upload</Button>
              </Upload>
            </Form.Item>
          </Col>
        </Row>

        <Form.Item>
          <Button type="primary" htmlType="submit">
            Kaydet
          </Button>
        </Form.Item>
      </Form>
    </Card>
  );
};

export default UserProfileForm;
