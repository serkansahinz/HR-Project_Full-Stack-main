import React from "react";
import Box from "./Box";
import { Col, Modal, Row } from "antd";

const PricingBox = ({ isModalOpen, onCancel, onFinish, isErrorMessage }) => {
  const featureBox1 = ["30 Gün Erişim"];
  const featureBox2 = ["60 Gün Erişim"];
  const featureBox3 = ["90 Gün Erişim"];

  const onClickPricingButton = (days) => {
    console.log(days);
    onFinish();
    sessionStorage.setItem("remainingDays", days);
  };

  return (
    <Modal
      width={700}
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <Row>
        <Col span={8}>
          <Box
            price="300TL"
            title="30 Günlük - Giriş Paketi"
            btnClass="btn-outline-primary"
            btnTitle="Aylık Kayıt"
            feature={featureBox1}
            onClickPricingButton={() => onClickPricingButton(30)}
          />
        </Col>
        <Col span={8}>
          <Box
            feature={featureBox2}
            price="250TL"
            title="60 Günlük - Standart Paket"
            btnClass="btn-outline-primary"
            btnTitle="Standart Süreli Kayıt"
            onClickPricingButton={() => onClickPricingButton(60)}
          />
        </Col>
        <Col span={8}>
          <Box
            feature={featureBox3}
            price="220TL"
            title="90 Günlük - Uzun Dönem Paketi"
            btnClass="btn-outline-primary"
            btnTitle="Uzun Dönem Kayıt"
            onClickPricingButton={() => onClickPricingButton(90)}
          />
        </Col>
      </Row>
    </Modal>
  );
};

export default PricingBox;
