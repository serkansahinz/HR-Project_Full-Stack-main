import React, { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Divider, Layout, Modal, Space, theme } from "antd";
import { Content } from "antd/es/layout/layout";
import benefit1 from "../../../assets/images/benefit1.jpg";
import benefit2 from "../../../assets/images/benefit2.jpg";
import benefit3 from "../../../assets/images/benefit3.jpg";
import benefit4 from "../../../assets/images/benefit4.jpg";
import benefit5 from "../../../assets/images/benefit5.jpg";

import "./index.scss";
import { EditOutlined, MailOutlined, PhoneOutlined } from "@ant-design/icons";
import UserContext from "../../../contexts/UserContext";
import {
  getCompanyByCompanyId,
  updateCompany,
} from "../../../services/company";
import CompanyEditForm from "./editForm";
const { Meta } = Card;

const benefits = [
  {
    title: "Verimlilik ve Zaman Tasarrufu",
    description:
      "İnsan kaynakları işlemlerini online platform üzerinden yönetmek, verimliliği artırır ve zaman tasarrufu sağlar. Personel dosyaları, izin yönetimi, performans değerlendirmeleri gibi işlemler otomatik hale getirilir.",
    image: benefit1,
  },
  {
    title: "Merkezi Veri Yönetimi",
    description:
      "Firmanın tüm çalışan bilgileri, dokümanları ve diğer veriler merkezi bir şekilde yönetilir. Bu, verilerin kaybolma veya karışma riskini azaltır.",
    image: benefit2,
  },
  {
    title: "İletişim ve İşbirliği",
    description:
      "Çalışanlar ve yöneticiler arasındaki iletişim platformlarıyla işbirliği artar. İnsan kaynakları projesi, mesajlaşma, bildirimler ve etkileşim imkanları sunarak iletişimi güçlendirir.",
    image: benefit3,
  },
  {
    title: "İzin Yönetimi",
    description:
      "Çalışanların izin talepleri, onayları ve kullanılan izin günleri sistematik bir şekilde takip edilir. Bu, iş gücü planlamasını kolaylaştırır.",
    image: benefit4,
  },
  {
    title: "Performans Yönetimi",
    description:
      "Çalışan performansını değerlendirmek için kullanıcı dostu araçlar sunar. Bu, çalışanların güçlü yönlerini belirlemeye ve gelişim alanlarını analiz etmeye yardımcı olur.",
    image: benefit5,
  },
];

const defaultTheme = "light";

const DashboardProject = ({ onChangeTheme, setPrimaryColor }) => {
  const [company, setCompany] = useState({});
  const [selectedCompany, setSelectedCompany] = useState(null);
  const [isCompanyEditVisible, setCompanyEditVisible] = useState(false);
  const {
    token: { colorBgContainer, colorText },
  } = theme.useToken();

  const { user } = useContext(UserContext);

  const onEditCompany = (company) => {
    setSelectedCompany(company);
    setCompanyEditVisible(true);
  };

  const handleCompanyEditClose = () => {
    setCompanyEditVisible(false);
  };

  const onFinishEdit = (values) => {
    const updatedCompany = { ...values, id: user.companyId };
    updateCompany(updatedCompany);
    setSelectedCompany(" ");
    setCompanyEditVisible(false);
  };

  useEffect(() => {
    if (defaultTheme === "dark") {
      onChangeTheme(true);
    }
    // eslint-disable-next-line
  }, []);

  useEffect(() => {
    if (user.companyId) {
      getCompanyByCompanyId(user.companyId).then((response) => {
        setCompany(response);
      });
    }
  }, [isCompanyEditVisible, user]);

  const contentStyle = {
    textAlign: "center",
    minHeight: 120,
    lineHeight: "120px",
    color: colorText,
    backgroundColor: colorBgContainer,
  };

  return (
    <Layout>
      <Layout className="main-layout" hasSider>
        <Content className="content" style={contentStyle}>
          <Space className="company" direction="vertical">
            <Card
              key={company.id}
              style={{
                width: "100%",
                marginBottom: 16,
                borderRadius: 10,
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
              }}
              extra={
                <Col>
                  <Button
                    type="text"
                    icon={<EditOutlined />}
                    onClick={() => onEditCompany(company)}
                  />
                </Col>
              }
            >
              <h2
                style={{
                  color: "#1890ff",
                  textAlign: "center",
                  margin: "10px 0",
                }}
              >
                {company.companyName}
              </h2>
              <Divider />

              <div style={{ color: "#1890ff", margin: "10px 0" }}>
                <div
                  style={{
                    display: "flex",
                    justifyContent: "flex-start",
                    gap: "10px",
                  }}
                >
                  <span>Şehir :</span>
                  <span>{company.province}</span>
                </div>
                <div>
                  <span>Adres :</span>
                  <span> {company.street}</span>
                </div>
                <div
                  style={{
                    display: "flex",
                    justifyContent: "flex-start",
                    gap: "10px",
                  }}
                ></div>
              </div>
              <div
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                  marginTop: 16,
                }}
              >
                <div>
                  <MailOutlined /> {company.companyEmail}
                </div>
                <div>
                  <PhoneOutlined /> {company.companyPhone}
                </div>
              </div>
            </Card>
          </Space>
          <Space>
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                paddingTop: "20px",
              }}
            >
              {benefits.map((benefit, index) => (
                <Card
                  key={index}
                  hoverable
                  style={{ height: 400, width: 300, margin: 20 }}
                  cover={<img alt={benefit.title} src={benefit.image} />}
                >
                  <Meta
                    title={benefit.title}
                    description={benefit.description}
                  />
                </Card>
              ))}
            </div>
          </Space>
          <Modal
            title="Şirket Düzenle"
            open={isCompanyEditVisible}
            onOk={handleCompanyEditClose}
            onCancel={handleCompanyEditClose}
            cancelButtonProps={{ style: { display: "none" } }}
            okButtonProps={{ style: { display: "none" } }}
            destroyOnClose
          >
            <CompanyEditForm
              onFinish={onFinishEdit}
              initialValues={selectedCompany}
            ></CompanyEditForm>
          </Modal>
        </Content>
      </Layout>
    </Layout>
  );
};
export default DashboardProject;
