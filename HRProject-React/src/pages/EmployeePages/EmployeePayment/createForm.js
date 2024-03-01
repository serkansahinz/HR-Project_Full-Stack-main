import React from "react";
import { Card, Button, Form, Row, Col, Input, Select, DatePicker } from "antd";
import dayjs from "dayjs";
const PaymentCreate = ({ onFinish, form, isErrorMessage }) => {
  const amountType = ["TL", "USD", "EURO"];
  const dateFormat = "YYYY-MM-DD";
  return (
    <Card
      style={{
        width: "100%",
        marginBottom: 16,
        borderRadius: 10,
        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
      }}
    >
      <Form form={form} name="advance-form" onFinish={onFinish}>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item
              name="paymentName"
              label="Ödeme İsmi"
              rules={[{ required: true }]}
            >
              <Input />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="amount" label="Tutar" rules={[{ required: true }]}>
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item
              name="currency"
              label="Para Birimi"
              rules={[{ required: true }]}
            >
              <Select>
                {amountType.map((type) => (
                  <Select.Option key={type} value={type}>
                    {type}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Col>

          <Col span={12}>
            <Form.Item
              name="paymentDate"
              label="Tarih"
              rules={[{ required: true }]}
            >
              <DatePicker
                defaultValue={dayjs("2023/01/01", dateFormat)}
                format={dateFormat}
              />
            </Form.Item>
          </Col>
        </Row>
        <Col>
          <Form.Item
            name="paymentDetail"
            label="Fatura Açıklama"
            rules={[{ required: true }]}
          >
            <Input.TextArea />
          </Form.Item>
        </Col>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Kaydet
          </Button>
        </Form.Item>
      </Form>
      {isErrorMessage && (
        <div style={{ display: "flex", flexWrap: "nowrap", color: "red" }}>
          <div className="ant-form-item-explain-error" role="alert">
            <div className="ant-form-item-explain-error">{isErrorMessage}</div>
          </div>
        </div>
      )}
    </Card>
  );
};

export default PaymentCreate;
