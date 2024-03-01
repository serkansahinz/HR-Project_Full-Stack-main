import React, { useEffect } from "react";
import { Card, Layout, Space, theme } from "antd";
import { Content } from "antd/es/layout/layout";

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

const DashboardGuest = ({ onChangeTheme, setPrimaryColor }) => {
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
    display: "flex",
    alignItems: "center",
    textAlign: "center",
    minHeight: 120,
    lineHeight: "120px",
    color: colorText,
    backgroundColor: colorBgContainer,
    flexDirection: "column",
  };

  return (
    <Layout>
      <Layout className="main-layout" hasSider>
        <Content className="content" style={contentStyle}>
          <Card style={{ width: "700px", height: "auto" }}>
            <h1>Hoş Geldiniz!</h1>
            <p>
              Modern ve etkili insan kaynakları yönetimi platformumuz,
              şirketlerin ve çalışanların verimliliğini artırmak için özel
              olarak tasarlanmış çözümler sunar. Kişiselleştirilmiş izin
              yönetimi, performans değerlendirmeleri ve detaylı raporlama gibi
              özelliklerle iş süreçlerinizi optimize edin.
            </p>
          </Card>
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
export default DashboardGuest;
