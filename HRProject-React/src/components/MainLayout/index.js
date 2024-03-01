import React, { useContext, useEffect, useState } from "react";
import { Button, Col, Layout, Row, Switch, theme } from "antd";
import { MoonSvg, SunSvg } from "../../assets/images/svg";
import logo from "../../assets/images/Logo.png";
import Login from "../../pages/AuthPages/Login";
import Register from "../../pages/AuthPages/Register";
import RegisterCompany from "../../pages/AuthPages/RegisterCompany";
import { getTokenFromBackend } from "../../services/login";
import { createCompanyManager, createUser } from "../../services/register";
import { useNavigate } from "react-router-dom";

import {
  GithubOutlined,
  LinkedinOutlined,
  TwitterOutlined,
} from "@ant-design/icons";
import Activation from "../../pages/AuthPages/ActivationGuest";
import ActivationCompany from "../../pages/AuthPages/ActivationCompany";
import jwt_decode from "jwt-decode";
import "./index.scss";
import RoleContext from "../../contexts/RoleContext";
import PricingBox from "../../pages/AuthPages/Pricing/PricingBox";

const { Header, Content, Footer } = Layout;

const defaultTheme = "light";

const MainLayout = ({ children, onChangeTheme }) => {
  const [isErrorMessage, setIsErrorMessage] = useState("");
  const [isErrorMessageCompany, setIsErrorMessageCompany] = useState("");
  const [isErrorMessageLogin, setIsErrorMessageLogin] = useState("");
  const [isLoginModalOpen, setIsLoginModelOpen] = useState(false);
  const [isPricingModalOpen, setIsPricingModelOpen] = useState(false);
  const [isRegisterModalOpen, setIsRegisterModelOpen] = useState(false);
  const [isRegisterCompanyModalOpen, setIsRegisterCompanyModelOpen] =
    useState(false);
  const [isActivationModalOpen, setIsActivationModelOpen] = useState(false);
  const [isActivationCompanyModalOpen, setIsActivationCompanyModelOpen] =
    useState(false);
  const [themeState, setThemeState] = useState(defaultTheme);

  const { setRole } = useContext(RoleContext);

  const {
    token: { colorBgMask, colorBgContainer, colorText },
  } = theme.useToken();

  const navigate = useNavigate();

  const headerStyle = {
    textAlign: "center",
    color: colorText,
    height: 64,
    paddingInline: 50,
    lineHeight: "64px",
    backgroundColor: colorBgContainer,
    theme: themeState,
    paddingTop: "15px",
  };

  const contentStyle = {
    textAlign: "center",
    minHeight: 120,
    lineHeight: "120px",
    color: colorText,
    backgroundColor: colorBgContainer,
    theme: themeState,
    paddingTop: "30px",
  };

  const footerStyle = {
    textAlign: "center",
    color: colorText,
    backgroundColor: colorBgMask,
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
    // eslint-disable-next-line
  }, []);

  const onClickLogin = () => {
    setIsRegisterModelOpen(false);
    setIsRegisterCompanyModelOpen(false);
    setIsLoginModelOpen(true);
  };
  const onClickRegister = () => {
    setIsLoginModelOpen(false);
    setIsRegisterCompanyModelOpen(false);
    setIsErrorMessage();
    setIsRegisterModelOpen(true);
  };

  const onClickRegisterCompany = () => {
    setIsPricingModelOpen(true);
    setIsLoginModelOpen(false);
    setIsRegisterModelOpen(false);
    setIsErrorMessageCompany();
  };
  const onRegisterCancel = () => {
    setIsRegisterModelOpen(false);
  };
  const onLoginCancel = () => {
    setIsLoginModelOpen(false);
  };
  const onRegisterCompanyCancel = () => {
    setIsRegisterCompanyModelOpen(false);
  };
  const onActivationModalCancel = () => {
    setIsActivationModelOpen(false);
  };
  const onActivationCompanyModalCancel = () => {
    setIsActivationCompanyModelOpen(false);
  };
  const onPricingCancel = () => {
    setIsPricingModelOpen(false);
  };
  const onPricingFinish = () => {
    setIsPricingModelOpen(false);
    setIsRegisterCompanyModelOpen(true);
  };

  const onRegisterFinish = (values) => {
    createUser({ ...values }).then((response) => {
      if (typeof response === "string") {
        setIsErrorMessage(response);
      } else {
        setIsErrorMessage();
        setIsRegisterModelOpen(false);
        setIsActivationModelOpen(true);
      }
    });
  };

  const onRegisterCompanyFinish = (values) => {
    const remainingDays = sessionStorage.getItem("remainingDays");
    createCompanyManager({ ...values, remainingDays: remainingDays }).then(
      (response) => {
        if (typeof response === "string") {
          setIsErrorMessageCompany(response);
        } else {
          setIsErrorMessageCompany();
          setIsRegisterCompanyModelOpen(false);
          setIsActivationCompanyModelOpen(true);
        }
      }
    );
  };

  const onLoginFinish = async (values) => {
    const userToken = await getTokenFromBackend(values.email, values.password);
    if (typeof userToken === "string") {
      console.log(userToken);
      localStorage.setItem("userToken", userToken);
      const decodedToken = jwt_decode(userToken);
      const userRole = decodedToken.role;
      setRole(userRole);
      if (userRole === "COMPANY_MANAGER") {
        setIsLoginModelOpen(false);
        navigate("/company");
      } else if (userRole === "GUEST") {
        setIsLoginModelOpen(false);
        navigate("/guest");
      } else if (userRole === "ADMIN") {
        setIsLoginModelOpen(false);
        navigate("/admin/company");
      } else if (userRole === "EMPLOYEE") {
        setIsLoginModelOpen(false);
        navigate("/employee");
      } else {
        const errorMessage = userRole;
        setIsErrorMessageLogin(errorMessage);
        setIsLoginModelOpen(true);
      }
    } else {
      setIsErrorMessageLogin(userToken.message);
    }
  };

  return (
    <Layout
      style={{
        minHeight: "100vh",
      }}
    >
      <Layout>
        <Header className="header" style={headerStyle}>
          <Col className="header-col">
            <Row className="logo">
              <img
                src={logo}
                alt="Logo"
                style={{
                  width: "60px",
                  height: "60px",
                  marginRight: 0,
                  borderRadius: "50%",
                }}
              />
              <span style={{ fontSize: "18px", fontWeight: "bold" }}>
                HRPROJECT
              </span>
            </Row>
            <Row className="header-button-row">
              <Button onClick={onClickRegister}>Register</Button>

              <Button onClick={onClickLogin}>Login</Button>

              <Switch
                checkedChildren={<MoonSvg />}
                unCheckedChildren={<SunSvg />}
                onChange={handleChangeTheme}
                defaultChecked={defaultTheme !== "light"}
              />
            </Row>
          </Col>
        </Header>
        <Content style={contentStyle}>
          <div
            style={{
              minHeight: 360,
              background: colorBgMask,
              color: colorText,
            }}
          >
            {children}
          </div>
        </Content>

        {isLoginModalOpen && (
          <Login
            onClickRegister={onClickRegister}
            isModalOpen={isLoginModalOpen}
            onCancel={onLoginCancel}
            onFinish={onLoginFinish}
            isErrorMessage={isErrorMessageLogin}
          />
        )}

        {isRegisterModalOpen && (
          <Register
            onClickRegisterCompany={onClickRegisterCompany}
            isModalOpen={isRegisterModalOpen}
            onCancel={onRegisterCancel}
            onFinish={onRegisterFinish}
            isErrorMessage={isErrorMessage}
          />
        )}

        {isPricingModalOpen && (
          <PricingBox
            isModalOpen={isPricingModalOpen}
            onCancel={onPricingCancel}
            onFinish={onPricingFinish}
            isErrorMessage={isErrorMessage}
            style={{ width: "600px", textAlign: "center" }}
          />
        )}

        {isRegisterCompanyModalOpen && (
          <RegisterCompany
            onClickRegister={onClickRegister}
            isModalOpen={isRegisterCompanyModalOpen}
            onCancel={onRegisterCompanyCancel}
            onFinish={onRegisterCompanyFinish}
            isErrorMessage={isErrorMessageCompany}
          />
        )}

        {isActivationModalOpen && (
          <Activation
            isModalOpen={isActivationModalOpen}
            onCancel={onActivationModalCancel}
          />
        )}

        {isActivationCompanyModalOpen && (
          <ActivationCompany
            isModalOpen={isActivationCompanyModalOpen}
            onCancel={onActivationCompanyModalCancel}
          />
        )}

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

export default MainLayout;
