import React from "react";
import { Card, Input, Button, Form, Row, Col } from "antd";
import { PhoneOutlined } from "@ant-design/icons";

const CompanyEditForm = ({ initialValues, onFinish }) => {
  const [form] = Form.useForm();

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
            <Form.Item name="companyName" label="Şirket İsmi">
              <Input disabled inputMode="disabled" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="companyEmail" label="Şirket Email">
              <Input />
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
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="companyPhone" label="Şirket Telefonu">
              <Input prefix={<PhoneOutlined />} />
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

export default CompanyEditForm;
