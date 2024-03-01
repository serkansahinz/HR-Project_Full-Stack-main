import React from "react";
import { Card, Input, Button, Select, Form, Row, Col } from "antd";
import { PhoneOutlined } from "@ant-design/icons";

const EmployeeCreateForm = ({ onFinish, form }) => {
  const genders = ["ERKEK", "KADIN"];
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
      <Form form={form} name="employee_form" onFinish={onFinish}>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="name" label="Adı" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="surname"
              label="Soyadı"
              rules={[{ required: true }]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="salary" label="Maaş" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="department"
              label="Departman"
              rules={[{ required: true }]}
            >
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
            <Form.Item
              name="province"
              label="Şehir"
              rules={[{ required: true }]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="street" label="Sokak" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="gender"
              label="Cinsiyet"
              rules={[{ required: true }]}
            >
              <Select>
                {genders.map((gender) => (
                  <Select.Option key={gender} value={gender}>
                    {gender}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item
              name="phone"
              label="Telefon"
              rules={[{ required: true }]}
            >
              <Input prefix={<PhoneOutlined />} />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="personalEmail"
              label="Kişisel E-mail"
              rules={[
                {
                  type: "email",
                  message: "The input is not valid E-mail!",
                },
                {
                  required: true,
                  message: "Please input your E-mail!",
                },
              ]}
            >
              <Input />
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

export default EmployeeCreateForm;
