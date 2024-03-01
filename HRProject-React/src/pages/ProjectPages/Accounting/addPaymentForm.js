import React from "react";
import { Card, Input, Button, Select, Form, Row, Col, DatePicker } from "antd";
import dayjs from "dayjs";

const AddPaymentForm = ({ onFinish, form }) => {
  const paymentTypes = ["GELİR", "GİDER"];
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
      <Form form={form} name="payment_form" onFinish={onFinish}>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item
              name="paymentType"
              label="Fatura Tipi"
              rules={[{ required: true }]}
            >
              <Select>
                {paymentTypes.map((type) => (
                  <Select.Option key={type} value={type}>
                    {type}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="paymentName"
              label="Ödeme İsmi"
              rules={[{ required: true }]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="amount" label="Tutar" rules={[{ required: true }]}>
              <Input />
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
        <Row gutter={16}>
          <Col span={12}>
            {" "}
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
              name="paymentDetail"
              label="Fatura Açıklama"
              rules={[{ required: true }]}
            >
              <Input.TextArea />
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

export default AddPaymentForm;
