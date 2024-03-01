import React, { useEffect } from "react";
import "./index.scss";

import {
  Button,
  Checkbox,
  Col,
  Form,
  Input,
  Modal,
  Row,
  Select,
  Space,
  theme,
} from "antd";

const { Option } = Select;

const formItemLayout = {
  labelCol: {
    xs: {
      span: 34,
    },
    sm: {
      span: 20,
    },
  },
  wrapperCol: {
    xs: {
      span: 34,
    },
    sm: {
      span: 20,
    },
  },
};
const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 8,
    },
  },
};

const RegisterCompany = ({
  onClickRegister,
  isModalOpen,
  onCancel,
  onFinish,
  isErrorMessage,
}) => {
  const genders = ["ERKEK", "KADIN"];

  const turkishCities = [
    "Adana",
    "Adıyaman",
    "Afyonkarahisar",
    "Ağrı",
    "Amasya",
    "Ankara",
    "Antalya",
    "Artvin",
    "Aydın",
    "Balıkesir",
    "Bilecik",
    "Bingöl",
    "Bitlis",
    "Bolu",
    "Burdur",
    "Bursa",
    "Çanakkale",
    "Çankırı",
    "Çorum",
    "Denizli",
    "Diyarbakır",
    "Düzce",
    "Edirne",
    "Elazığ",
    "Erzincan",
    "Erzurum",
    "Eskişehir",
    "Gaziantep",
    "Giresun",
    "Gümüşhane",
    "Hakkâri",
    "Hatay",
    "Iğdır",
    "Isparta",
    "İstanbul",
    "İzmir",
    "Kahramanmaraş",
    "Karabük",
    "Karaman",
    "Kars",
    "Kastamonu",
    "Kayseri",
    "Kırıkkale",
    "Kırklareli",
    "Kırşehir",
    "Kilis",
    "Kocaeli",
    "Konya",
    "Kütahya",
    "Malatya",
    "Manisa",
    "Mardin",
    "Mersin",
    "Muğla",
    "Muş",
    "Nevşehir",
    "Niğde",
    "Ordu",
    "Osmaniye",
    "Rize",
    "Sakarya",
    "Samsun",
    "Siirt",
    "Sinop",
    "Sivas",
    "Şanlıurfa",
    "Şırnak",
    "Tekirdağ",
    "Tokat",
    "Trabzon",
    "Tunceli",
    "Uşak",
    "Van",
    "Yalova",
    "Yozgat",
    "Zonguldak",
  ];

  const {
    token: { colorBorderSecondary },
  } = theme.useToken();
  const [form] = Form.useForm();
  const prefixSelector = (
    <Form.Item name="prefix" noStyle>
      <Select
        style={{
          width: 70,
        }}
      >
        <Option value="86">+86</Option>
        <Option value="87">+87</Option>
      </Select>
    </Form.Item>
  );

  useEffect(() => {
    console.log(isErrorMessage);
  }, [isErrorMessage]);

  return (
    <Modal
      width="720px"
      className="company-register-modal"
      title="Company Register "
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <Space className="register-company-page">
        <Form
          className="register-company-form"
          {...formItemLayout}
          form={form}
          name="register-company"
          onFinish={onFinish}
          initialValues={{
            residence: ["zhejiang", "hangzhou", "xihu"],
            prefix: "86",
          }}
          style={{
            borderColor: colorBorderSecondary,
            maxWidth: 600,
          }}
          scrollToFirstError
          labelAlign="top"
        >
          <Col className="register-company-col">
            <Row className="ant-row-left">
              <Form.Item
                className="company-manager-name-item"
                name="name"
                label="Company Manager Name"
                rules={[
                  {
                    required: true,
                    message: "Please input your name!",
                  },
                ]}
                hasFeedback
                labelAlign="right"
              >
                <Input
                // onChange={(values) => setManagerName(values.target.value)}
                />
              </Form.Item>
              <Form.Item
                name="surname"
                label="Company Manager Surname"
                rules={[
                  {
                    required: true,
                    message: "Please input your surname!",
                  },
                ]}
                hasFeedback
              >
                <Input
                // onChange={(values) => setManagerSurname(values.target.value)}
                />
              </Form.Item>

              <Form.Item
                name="email"
                label="Company Manager E-mail"
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
                <Input
                //onChange={(values) => setEmail(values.target.value)}
                />
              </Form.Item>

              <Form.Item
                name="password"
                label="Company Manager Password"
                rules={[
                  {
                    required: true,
                    message: "Please input your password!",
                  },
                ]}
                hasFeedback
              >
                <Input.Password
                // onChange={(values) => setPassword(values.target.value)}
                />
              </Form.Item>

              <Form.Item
                name="confirm"
                label="Confirm Password"
                dependencies={["password"]}
                hasFeedback
                rules={[
                  {
                    required: true,
                    message: "Please confirm your password!",
                  },
                  ({ getFieldValue }) => ({
                    validator(_, value) {
                      if (!value || getFieldValue("password") === value) {
                        return Promise.resolve();
                      }
                      return Promise.reject(
                        new Error(
                          "The new password that you entered do not match!"
                        )
                      );
                    },
                  }),
                ]}
              >
                <Input.Password />
              </Form.Item>

              <Form.Item
                name="phone"
                label="Phone Number"
                rules={[
                  {
                    required: false,
                    message: "Please input your phone number!",
                  },
                  {
                    pattern: /^[0-9]*$/, // Sadece sayıya izin veren regex deseni
                    message:
                      "Please enter a valid phone number with digits only!",
                  },
                ]}
              >
                <Input
                  type="phone"
                  addonBefore={prefixSelector}
                  style={{
                    width: "100%",
                  }}
                />
              </Form.Item>
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
            </Row>

            <Row className="ant-row-right">
              <Form.Item
                name="companyEmail"
                label="Company Info E-mail"
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
                <Input
                // onChange={(values) => setCompanyEmail(values.target.value)}
                />
              </Form.Item>

              <Form.Item
                name="companyName"
                label="Company Name"
                rules={[
                  {
                    required: true,
                    message: "Please input your company name!",
                  },
                ]}
                hasFeedback
              >
                <Input
                // onChange={(values) => setCompanyName(values.target.value)}
                />
              </Form.Item>

              <Form.Item
                name="companyTaxNumber"
                label="Company Tax Number"
                rules={[
                  {
                    required: true,
                    message: "Please input company tax number!",
                  },
                  {
                    pattern: /^[0-9]*$/, // Sadece sayıya izin veren regex deseni
                    message:
                      "Please enter a valid phone number with digits only!",
                  },
                ]}
                hasFeedback
              >
                <Input
                // onChange={(values) =>setCompanyTaxNumber(values.target.value)}
                />
              </Form.Item>

              <Form.Item
                name="companyPhone"
                label="Company Phone Number"
                rules={[
                  {
                    required: false,
                    message: "Please input your company phone number!",
                  },
                  {
                    pattern: /^[0-9]*$/, // Sadece sayıya izin veren regex deseni
                    message:
                      "Please enter a valid phone number with digits only!",
                  },
                ]}
              >
                <Input
                  type="phone"
                  //onChange={(values) => setCompanyPhone(values.target.value)}
                  addonBefore={prefixSelector}
                  style={{
                    width: "100%",
                  }}
                />
              </Form.Item>

              <Form.Item
                label="Province"
                name="province"
                rules={[
                  {
                    required: true,
                    message: "Province is required",
                  },
                ]}
              >
                <Select
                  style={{
                    width: "100%",
                  }}
                  placeholder="Select province"
                >
                  {turkishCities.map((city) => (
                    <Option key={city} value={city}>
                      {city}
                    </Option>
                  ))}
                </Select>
              </Form.Item>
              <Form.Item
                label="Street"
                name="street"
                rules={[
                  {
                    required: true,
                    message: "Street is required",
                  },
                ]}
              >
                <Input
                  style={{
                    width: "100%",
                  }}
                  placeholder="Input street"
                />
              </Form.Item>
            </Row>
          </Col>
          <Form.Item
            className="agreement-register-company"
            name="agreement"
            valuePropName="checked"
            rules={[
              {
                validator: (_, value) =>
                  value
                    ? Promise.resolve()
                    : Promise.reject(new Error("Should accept agreement")),
              },
            ]}
            {...tailFormItemLayout}
          >
            <Checkbox>
              I have read the <a href="">agreement</a>
            </Checkbox>
          </Form.Item>
          <Form.Item className="register-button" {...tailFormItemLayout}>
            <Button type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>
          <Button onClick={onClickRegister} type="primary">
            Register as a Guest!
          </Button>
        </Form>
        {isErrorMessage && (
          <div style={{ display: "flex", flexWrap: "nowrap", color: "red" }}>
            <div className="ant-form-item-explain-error" role="alert">
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

export default RegisterCompany;
