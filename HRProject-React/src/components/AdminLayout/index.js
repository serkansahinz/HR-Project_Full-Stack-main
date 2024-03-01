import React, { useEffect, useState } from "react";
import {
  Avatar,
  Button,
  Col,
  Dropdown,
  Layout,
  Menu,
  Modal,
  Row,
  Switch,
  theme,
} from "antd";
import { MoonSvg, SunSvg } from "../../assets/images/svg";
import "./index.scss";
import logo from "../../assets/images/Logo.png";

import {
  GithubOutlined,
  LinkedinOutlined,
  LogoutOutlined,
  TwitterOutlined,
} from "@ant-design/icons";
import Sider from "antd/es/layout/Sider";
import { useNavigate } from "react-router-dom";

const { Header, Content, Footer } = Layout;

const defaultTheme = "light";

const AdminLayout = ({ menu, children, onChangeTheme }) => {
  const [collapsed, setCollapsed] = useState(false);
  const [themeState, setThemeState] = useState(defaultTheme);
  const [isLogoutVisible, setLogoutVisible] = useState(false);
  const {
    token: { colorBgContainer, colorText },
  } = theme.useToken();

  const navigate = useNavigate();

  const footerStyle = {
    textAlign: "center",
    color: colorText,
    backgroundColor: colorBgContainer,
    theme: themeState,
  };

  const handleChangeTheme = (value) => {
    onChangeTheme(value);
    setThemeState(value ? "dark" : "light");
  };

  const handleLogoutClick = () => {
    setLogoutVisible(true);
  };
  const handleLogoutClose = () => {
    setLogoutVisible(false);
  };

  const handleLogout = () => {
    localStorage.removeItem("userToken");
    navigate("/");
  };
  const menu2 = (
    <Menu>
      <Menu.Item
        key="logout"
        icon={<LogoutOutlined />}
        onClick={handleLogoutClick}
      >
        Logout
      </Menu.Item>
    </Menu>
  );
  useEffect(() => {
    if (defaultTheme === "dark") {
      onChangeTheme(true);
    }
    // eslint-disable-next-line
  }, []);

  return (
    <Layout
      style={{
        minHeight: "100vh",
      }}
    >
      <Sider
        collapsible
        collapsed={collapsed}
        onCollapse={(value) => setCollapsed(value)}
        style={{ background: colorBgContainer }}
        theme={themeState}
      >
        <div className="demo-logo-vertical">
          <img width="70px" height="70px" src={logo} alt="" />
          <span>HR PROJECT</span>
        </div>
        <Menu defaultSelectedKeys={["1"]} mode="inline" items={menu} />
      </Sider>
      <Layout>
        <Header style={{ background: colorBgContainer }}>
          <Row style={{ display: "flex", justifyContent: "flex-end" }}>
            <Col flex="auto"></Col>
            <Col style={{ marginLeft: "16px" }}>
              <Dropdown overlay={menu2}>
                <Button>Admin User</Button>
              </Dropdown>
            </Col>
            <Col>
              <Avatar
                src="https://xsgames.co/randomusers/avatar.php?g=pixel&key=2"
                style={{
                  marginLeft: "20px",
                  height: "40px",
                  width: "40px",
                  marginBottom: "5px",
                }}
              />
            </Col>
            <Col>
              <Switch
                checkedChildren={<MoonSvg />}
                unCheckedChildren={<SunSvg />}
                onChange={handleChangeTheme}
                defaultChecked={defaultTheme !== "light"}
              />
            </Col>
          </Row>
        </Header>
        <Content
          style={{
            margin: "0 16px",
          }}
        >
          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
              color: colorText,
            }}
          >
            {children}
          </div>
          <Modal
            title="Çıkış Yap"
            open={isLogoutVisible}
            onCancel={handleLogoutClose}
            footer={[
              <Button key="hayir" onClick={handleLogoutClose}>
                Hayır
              </Button>,
              <Button key="evet" type="primary" onClick={handleLogout}>
                Evet
              </Button>,
            ]}
          >
            Çıkış yapmak istediğinize emin misiniz?
          </Modal>
        </Content>
        <Footer className="footer" style={footerStyle}>
          <Col className="footer-detail">
            <Row gutter={[16, 16]}>
              <Col>
                <Button href="/">Anasayfa</Button>
              </Col>
              <Col>
                <Button href="/">Hakkımızda</Button>
              </Col>
              <Col>
                <Button href="/">İletişim</Button>
              </Col>
            </Row>
            <Row style={{ marginTop: "16px" }} gutter={[16, 16]}>
              <Col>
                <a
                  href="https://github.com/Java10-Team3"
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <GithubOutlined style={{ fontSize: "24px", color: "#08c" }} />
                </a>
              </Col>
              <Col>
                <a
                  href="https://twitter.com"
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <TwitterOutlined
                    style={{ fontSize: "24px", color: "#08c" }}
                  />
                </a>
              </Col>
              <Col>
                <a
                  href="https://linkedin.com"
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  <LinkedinOutlined
                    style={{ fontSize: "24px", color: "#08c" }}
                  />
                </a>
              </Col>
            </Row>
          </Col>
          <div style={{ marginTop: "16px" }}>
            ©2023 Created by Java10- Team 3
          </div>
        </Footer>
      </Layout>
    </Layout>
  );
};

export default AdminLayout;
