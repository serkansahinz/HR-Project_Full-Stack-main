import React, { useEffect, useState } from "react";
import { Button, Col, Layout, Row, Space, Switch, theme } from "antd";
import { Header } from "antd/es/layout/layout";
import { Link } from "react-router-dom";

import logo from "../../assets/images/redapplelogo.png";
import { MoonSvg, SunSvg } from "../../../assets/images/svg";

import "./index.scss";

const { Footer } = Layout;
const defaultTheme = "light";

const Guest = ({ onChangeTheme, setPrimaryColor }) => {
  const [themeState, setThemeState] = useState(defaultTheme);
  const {
    token: { colorBgContainer, colorText },
  } = theme.useToken();

  const handleChangeTheme = (value) => {
    onChangeTheme(value);
    setThemeState(value ? "dark" : "light");
  };

  useEffect(() => {
    if (defaultTheme === "dark") {
      onChangeTheme(true);
    }
    // eslint-disable-next-line
  }, []);

  const headerStyle = {
    textAlign: "center",
    color: colorText,
    height: 64,
    paddingInline: 50,
    lineHeight: "64px",
    backgroundColor: colorBgContainer,
    theme: themeState,
  };

  const footerStyle = {
    textAlign: "center",
    color: colorText,
    backgroundColor: colorBgContainer,
    theme: themeState,
  };

  return (
    <Space
      direction="vertical"
      style={{
        width: "100%",
      }}
      size={[0, 48]}
    >
      <Layout>
        <Header className="header" style={headerStyle}>
          <Col className="header-col">
            <Row className="logo">
              <img src={logo} alt="Logo" style={{ marginRight: 8 }} />
              <span>HR PROJECT</span>
            </Row>
            <Row className="header-button-row">
              <Link to="/register">
                <Button>Register</Button>
              </Link>
              <Link to="/login">
                <Button>Login</Button>
              </Link>
              <Switch
                checkedChildren={<MoonSvg />}
                unCheckedChildren={<SunSvg />}
                onChange={handleChangeTheme}
                defaultChecked={defaultTheme !== "light"}
              />
            </Row>
          </Col>
        </Header>

        <Footer style={footerStyle}>©2023 Created by Java10- Team 3</Footer>
      </Layout>
    </Space>
  );
};

export default Guest;
