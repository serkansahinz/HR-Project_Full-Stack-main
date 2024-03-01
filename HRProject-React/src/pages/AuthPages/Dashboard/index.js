import React, { useEffect } from "react";
import { Card, Carousel, Layout, Space, theme } from "antd";
import { Content } from "antd/es/layout/layout";
import hrphoto1 from "../../../assets/images/hrphoto1.jpg";
import hrphoto2 from "../../../assets/images/hrphoto2.jpg";
import hrphoto3 from "../../../assets/images/hrphoto3.jpg";
import hrphoto4 from "../../../assets/images/hrphoto4.jpg";
import benefit1 from "../../../assets/images/benefit1.jpg";
import benefit2 from "../../../assets/images/benefit2.jpg";
import benefit3 from "../../../assets/images/benefit3.jpg";
import benefit4 from "../../../assets/images/benefit4.jpg";
import benefit5 from "../../../assets/images/benefit5.jpg";

import "./index.scss";
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

const Dashboard = ({ onChangeTheme, setPrimaryColor }) => {
  const {
    token: { colorBgContainer, colorText },
  } = theme.useToken();

  useEffect(() => {
    if (defaultTheme === "dark") {
      onChangeTheme(true);
    }
    // eslint-disable-next-line
  }, []);

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
          <Carousel className="carousel" autoplay>
            <div>
              <img
                src={hrphoto1}
                alt="Resim 1"
                style={{ width: "100%", height: "100vh" }}
              />
            </div>
            <div>
              <img
                src={hrphoto2}
                alt="Resim 2"
                style={{ width: "100%", height: "100vh" }}
              />
            </div>
            <div>
              <img
                src={hrphoto3}
                alt="Resim 3"
                style={{ width: "100%", height: "100vh" }}
              />
            </div>
            <div>
              <img
                src={hrphoto4}
                alt="Resim 4"
                style={{ width: "100%", height: "100vh" }}
              />
            </div>
          </Carousel>
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
        </Content>
      </Layout>
    </Layout>
  );
};
export default Dashboard;
