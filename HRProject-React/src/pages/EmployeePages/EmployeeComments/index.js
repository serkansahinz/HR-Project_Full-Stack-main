import { SendOutlined } from "@ant-design/icons";
import { Button, Col, Form, Input, Modal, Row, Space, Table } from "antd";
import React, { useContext, useEffect, useState } from "react";
import UserContext from "../../../contexts/UserContext";
import { addComment, getCommentsByCompanyId } from "../../../services/comment";

const EmployeeComment = () => {
  const [comments, setComments] = useState([]);
  const { user } = useContext(UserContext);
  const [checkData] = useState();
  const [isCommentModalVisible, setIsCommentModalVisible] = useState(false);

  const [form] = Form.useForm();

  const onFinish = (values) => {
    const userId = user.id;
    const companyId = user.companyId;
    addComment(
      {
        ...values,
        companyName: user.companyName,
        name: user.name,
        surname: user.surname,
      },
      userId,
      companyId
    );
    setIsCommentModalVisible(true);
  };

  const commentModalSubmit = () => {
    form.resetFields();
    setIsCommentModalVisible(false);
  };
  const commentModalClose = () => {
    setIsCommentModalVisible(false);
  };

  const commentColumns = [
    {
      title: "Kullanıcı İsmi",
      dataIndex: "fullName",
      key: "t1",
    },
    {
      title: "Konu",
      dataIndex: "header",
      key: "t2",
    },
    {
      title: "İçerik",
      dataIndex: "content",
      key: "t3",
    },
  ];

  useEffect(() => {
    if (user && user.companyId) {
      getCommentsByCompanyId(user.companyId).then((response) => {
        setComments(response);
      });
    }
  }, [user, checkData]);

  return (
    <Space className="companies" direction="vertical">
      <Table dataSource={comments} columns={commentColumns} />
      <Form form={form} name="add_comment_form" onFinish={onFinish}>
        <Row>
          <Col span={12}>
            <Form.Item
              name="header"
              label="Başlık"
              rules={[{ required: true }]}
            >
              <Input placeholder="Başlık giriniz.."></Input>
            </Form.Item>
          </Col>
          <Col span={16}>
            <Form.Item
              name="content"
              label="Yorum"
              rules={[{ required: true }]}
            >
              <Input.TextArea width="100%" placeholder="Yorumunuzu giriniz.." />
            </Form.Item>
          </Col>
        </Row>

        <Col span={8}>
          <Button
            type="primary"
            shape="circle"
            icon={<SendOutlined />}
            size="large"
            htmlType="submit"
          />
        </Col>
      </Form>
      <Modal
        title="Yorum Bilgisi"
        open={isCommentModalVisible}
        onOk={commentModalSubmit}
        onCancel={commentModalClose}
        cancelButtonProps={{ style: { display: "none" } }}
      >
        Mesajınız onay için iletilmiştir.
      </Modal>
    </Space>
  );
};

export default EmployeeComment;
