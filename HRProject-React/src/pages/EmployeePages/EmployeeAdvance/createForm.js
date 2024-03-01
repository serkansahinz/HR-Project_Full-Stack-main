import React from "react";
import { Card, Button, Form, Row, Col, Input } from "antd";

const AdvanceCreate = ({ onFinish, form, isErrorMessage }) => {
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
              name="amount"
              label="Avans Miktarı"
              rules={[
                {
                  required: false,
                  message: "Lütfen avans miktarınızı giriniz!",
                },
                {
                  pattern: /^[0-9]*$/, // Sadece sayıya izin veren regex deseni
                  message: "Lütfen avans miktarını sadece sayı giriniz!",
                },
              ]}
            >
              <Input
                type="money"
                style={{
                  width: "100%",
                }}
              />
            </Form.Item>
          </Col>
        </Row>
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

export default AdvanceCreate;
