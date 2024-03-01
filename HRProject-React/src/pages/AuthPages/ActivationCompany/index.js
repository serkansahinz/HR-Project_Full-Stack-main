import React from "react";
import "./index.scss";
import companyLogo from "../../../assets/images/pngwing.com.png";

import { Modal } from "antd";
import { HourglassOutlined } from "@ant-design/icons";

const ActivationCompany = ({ isModalOpen, onCancel }) => {
  return (
    <Modal
      title="Notification About Activation"
      open={isModalOpen}
      onCancel={onCancel}
      cancelButtonProps={{ style: { display: "none" } }}
      okButtonProps={{ style: { display: "none" } }}
    >
      <div className="modal-content">
        <div className="waiting-icon">
          <HourglassOutlined className="waiting-icon" />
        </div>
        <div className="waiting-message">
          <p>
            Hesap onayınız yapıldıktan sonra epostanıza gelecek olan mesaj ile
            bilgi verilecektir. Lütfen onay işlemi gerçekleşene kadar bekleyin.
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

export default ActivationCompany;
