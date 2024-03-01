import React from "react";
import "./index.scss";
import companyLogo from "../../../assets/images/pngwing.com.png";

import { Modal } from "antd";
import { CheckCircleFilled } from "@ant-design/icons";

const Activation = ({ isModalOpen, onCancel }) => {
  return (
    <Modal
      title="Activation"
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <div className="modal-content">
        <div className="success-icon">
          <CheckCircleFilled className="success-icon" />
        </div>
        <div className="success-message">
          <p>
            Mail adresinizi kontrol edin! Aktivasyon linki ile hesabınızı aktive
            ettikten sonra giriş yapabilirsiniz.
          </p>
        </div>
        <div className="company-info">
          <img src={companyLogo} alt="Company Logo" className="company-logo" />
          <h2>HR Project</h2>
        </div>
      </div>
    </Modal>
  );
};

export default Activation;
