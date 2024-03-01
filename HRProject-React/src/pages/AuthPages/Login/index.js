import React from "react";
import { Checkbox, Col, Modal, Row, Space } from "antd";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Form, Input } from "antd";
import "./index.scss";

const Login = ({
  onClickRegister,
  isModalOpen,
  onCancel,
  onFinish,
  isErrorMessage,
}) => {
  return (
    <Modal
      width="330px"
      title="Login"
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <Space className="login-page">
        <Form
          name="normal_login"
          className="login-form"
          initialValues={{
            username: "",
            password: "",
            remember: true,
          }}
          onFinish={onFinish}
        >
          <Form.Item
            name="email"
            rules={[
              {
                type: "email",
                required: true,
                message: "Please input your Email!",
              },
            ]}
          >
            <Input
              prefix={<UserOutlined className="site-form-item-icon" />}
              placeholder="Email"
            />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              {
                required: true,
                message: "Please input your Password!",
              },
            ]}
          >
            <Input
              prefix={<LockOutlined className="site-form-item-icon" />}
              type="password"
              placeholder="Password"
            />
          </Form.Item>
          <Form.Item>
            <Col className="remember-col">
              <Row>
                <Form.Item name="remember" valuePropName="checked" noStyle>
                  <Checkbox>Remember me</Checkbox>
                </Form.Item>
              </Row>
              <Row>
                <a className="login-form-forgot" href="">
                  Forgot password
                </a>
              </Row>
            </Col>
          </Form.Item>

          <Form.Item className="register-item">
            <Button
              type="primary"
              htmlType="submit"
              className="login-form-button"
            >
              Login
            </Button>

            <Button
              style={{
                marginTop: "10px",
                backgroundColor: "#fff",
                color: "#1677ff",
              }}
              onClick={onClickRegister}
              size="small"
              type="primary"
            >
              Or Register Now!
            </Button>
          </Form.Item>
          <Form.Item>{}</Form.Item>
        </Form>
        {isErrorMessage && (
          <div style={{ display: "flex", flexWrap: "nowrap", color: "red" }}>
            <div
              className="ant-form-item-explain ant-form-item-explain-connected css-dev-only-do-not-override-f3kfka"
              role="alert"
            >
              <div className="ant-form-item-explain-error">
                {isErrorMessage}
              </div>
            </div>
          </div>
        )}
      </Space>
    </Modal>
  );
};

export default Login;
