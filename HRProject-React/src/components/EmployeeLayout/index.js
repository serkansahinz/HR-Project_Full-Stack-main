import React, { useContext, useEffect, useState } from "react";
import {
  Button,
  Col,
  Dropdown,
  Layout,
  Menu,
  Row,
  Switch,
  theme,
  Modal,
  Avatar,
} from "antd";

import { useNavigate } from "react-router-dom";
import { MoonSvg, SunSvg } from "../../assets/images/svg";
import "./index.scss";
import logo from "../../assets/images/Logo.png";
import jwt_decode from "jwt-decode";
import {
  GithubOutlined,
  LinkedinOutlined,
  LogoutOutlined,
  TwitterOutlined,
  UserOutlined,
} from "@ant-design/icons";
import Sider from "antd/es/layout/Sider";
import { getEmployee } from "../../services/employee";
import EmployeeForm from "./form";
import UserContext from "../../contexts/UserContext";
import { updateUser } from "../../services/user";
import UserProfileForm from "./form";
import RoleContext from "../../contexts/RoleContext";

const { Header, Content, Footer } = Layout;

const defaultTheme = "light";

const EmployeeLayout = ({ menu, children, onChangeTheme }) => {
  const [employee, setEmployee] = useState({});
  const [checkUser, setCheckUser] = useState(0);
  const [collapsed, setCollapsed] = useState(false);
  const [themeState, setThemeState] = useState(defaultTheme);
  const [isProfileVisible, setProfileVisible] = useState(false);
  const [isLogoutVisible, setLogoutVisible] = useState(false);

  const { user, setUser } = useContext(UserContext);
  const { role } = useContext(RoleContext);

  const {
    token: { colorBgContainer, colorText },
  } = theme.useToken();

  const navigate = useNavigate();

  const handleProfileClick = () => {
    setProfileVisible(true);
  };
  const handleLogoutClick = () => {
    setLogoutVisible(true);
  };

  const handleProfileClose = () => {
    setProfileVisible(false);
  };

  const handleLogoutClose = () => {
    setLogoutVisible(false);
  };

  const handleLogout = () => {
    localStorage.removeItem("userToken");
    navigate("/");
  };

  const updateUserProfile = (values) => {
    const updatedUser = { ...values, id: user.id };
    updateUser(updatedUser).then((response) => console.log(response));
    setCheckUser(checkUser + 1);
    setProfileVisible(false);
  };

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
  useEffect(() => {
    if (defaultTheme === "dark") {
      onChangeTheme(true);
    }
  }, []);
  useEffect(() => {
    const fetchData = async () => {
      const storedToken = localStorage.getItem("userToken");
      if (storedToken) {
        const decodedToken = jwt_decode(storedToken);
        try {
          const employeeData = await getEmployee(decodedToken.myId);
          setEmployee(employeeData);
          setUser(employeeData);
        } catch (error) {
          console.error("Hata oluştu: ", error);
        }
      }
    };

    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [checkUser]);

  const menu2 = (
    <Menu>
      <Menu.Item
        key="profile"
        icon={<UserOutlined />}
        onClick={handleProfileClick}
      >
        User Profile
      </Menu.Item>
      <Menu.Item
        key="logout"
        icon={<LogoutOutlined />}
        onClick={handleLogoutClick}
      >
        Logout
      </Menu.Item>
    </Menu>
  );

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
        <div
          className="demo-logo-vertical"
          style={{ display: "flex", alignItems: "center", padding: "20px" }}
        >
          <img
            style={{ marginRight: "8px", borderRadius: "50%" }}
            width="70px"
            height="70px"
            src={logo}
            alt="Company Logo"
          />
          <span style={{ fontSize: "18px", fontWeight: "bold" }}>
            HRPROJECT
          </span>
        </div>
        <Menu defaultSelectedKeys={["1"]} mode="inline" items={menu} />
      </Sider>
      <Layout>
        <Header
          style={{
            background: colorBgContainer,
            theme: themeState,
            padding: "16px",
          }}
        >
          <Row style={{ display: "flex", justifyContent: "flex-end" }}>
            <Col flex="auto"></Col>
            <Col style={{ marginLeft: "16px" }}>
              {role === "COMPANY_MANAGER" && (
                <Button href="/company">Şirket Penceresi</Button>
              )}
            </Col>
            <Col style={{ marginLeft: "16px" }}>
              <Dropdown overlay={menu2}>
                <Button>
                  {employee.name} {employee.surname}{" "}
                </Button>
              </Dropdown>
            </Col>
            <Col>
              <Avatar
                src={user.avatar}
                style={{
                  marginLeft: "20px",
                  height: "40px",
                  width: "40px",
                  marginBottom: "5px",
                }}
              />
            </Col>
            <Col style={{ marginLeft: "16px" }}>
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
            margin: "0 0px",
          }}
        >
          <div
            style={{
              padding: 24,
              minHeight: 360,
              background: colorBgContainer,
              color: colorText,
              theme: themeState,
            }}
          >
            {children}
          </div>

          <Modal
            title="Profil"
            open={isProfileVisible}
            onOk={handleProfileClose}
            onCancel={handleProfileClose}
            cancelButtonProps={{ style: { display: "none" } }}
            okButtonProps={{ style: { display: "none" } }}
          >
            <UserProfileForm
              onFinish={updateUserProfile}
              initialValues={user}
            ></UserProfileForm>
          </Modal>

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

export default EmployeeLayout;
