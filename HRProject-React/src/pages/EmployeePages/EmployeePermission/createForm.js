import React from "react";
import { DatePicker, Card, Button, Select, Form, Row, Col } from "antd";

import dayjs from "dayjs";
const PermissionCreate = ({ onFinish, form, gender, isErrorMessage }) => {
  const types =
    gender === "KADIN"
      ? ["ANNELİK", "GEBELİK", "YILLIK"]
      : gender === "ERKEK"
      ? ["BABALIK", "YILLIK"]
      : [];
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
      <Form form={form} name="permission-form" onFinish={onFinish}>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item
              name="type"
              label="İzin Türü"
              rules={[{ required: true }]}
            >
              <Select>
                {types.map((type) => (
                  <Select.Option key={type} value={type}>
                    {type}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Form.Item
              name="startDate"
              label="Başlangıç"
              rules={[{ required: true }]}
            >
              <DatePicker
                defaultValue={dayjs("2023/01/01", dateFormat)}
                format={dateFormat}
              />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="endDate"
              label="Bitiş"
              rules={[{ required: true }]}
            >
              <DatePicker
                defaultValue={dayjs("2023/01/01", dateFormat)}
                format={dateFormat}
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

export default PermissionCreate;
