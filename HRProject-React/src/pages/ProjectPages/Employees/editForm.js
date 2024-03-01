import React from "react";
import { Card, Input, Button, Select, Form, Row, Col } from "antd";
import { PhoneOutlined } from "@ant-design/icons";

const EmployeeEditForm = ({ initialValues, onFinish }) => {
  const [form] = Form.useForm();

  const shifts = ["GÜNDÜZ", "GECE"];

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
            <Form.Item name="salary" label="Maaş">
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="department" label="Departman">
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item
              name="shift"
              label="Vardiya"
              rules={[{ required: true }]}
            >
              <Select>
                {shifts.map((shift) => (
                  <Select.Option key={shift} value={shift}>
                    {shift}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="province" label="Şehir">
              <Input disabled inputMode="disabled" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="street" label="Sokak">
              <Input disabled inputMode="disabled" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="phone" label="Telefon">
              <Input disabled inputMode="disabled" prefix={<PhoneOutlined />} />
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

export default EmployeeEditForm;
